package com.asiainfo.coder.utils;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * 类名称   ：FileUtil
 * 类描述   ：
 * 创建人   : zhangjp
 * 创建时间 : 2014年4月21日 下午7:13:22
 * 版本号   : 1.0.0
 ************************************
 * 修改人	:
 * 修改时间	:
 * 修改备注	:
 * 版本号	:
 * 
 */
public class FileUtils {
    
    public static void String2File(StringBuffer buffer,String filePath,String charsetName) throws IOException{
        java.io.File file=new java.io.File(filePath);
        if(!file.exists())
            file.createNewFile();
        
        OutputStreamWriter osw = null;
    	try {
			osw = new OutputStreamWriter(new FileOutputStream(filePath),charsetName);
			osw.write(buffer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
		{
            try {
				osw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

	public static StringBuffer File2String(String filePath,String charsetName) {
		StringBuffer _buffer = new StringBuffer();
		InputStream in = null;
        java.io.InputStreamReader isw = null;
		try {
			// 一次读多个字节
			char[] tempchars = new char[50]; 
			int charread = 0;
			in = new FileInputStream(filePath);
			isw = new InputStreamReader(in,charsetName);
			
			BufferedReader bufferedReader = new BufferedReader(isw);
			String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	_buffer.append(lineTxt);
            	_buffer.append("\n");
            }
//			while ((charread = isw.read(tempchars)) != -1) {
//				if ((charread == tempchars.length)
//						&& (tempchars[tempchars.length - 1] != 'r')) {
//					_buffer.append(tempchars);
//				} else {
//					for (int i = 0; i < charread; i++) {
//						if (tempchars[i] == 'r') {
//							continue;
//						} else {
//							_buffer.append(tempchars[i]);
//						}
//					}
//				}
//			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return _buffer;
	}

	public static void main(String[] args) 
	{
		try {
			FileUtils.String2File(FileUtils.File2String("D:\\MMS0000181153058.txt","UTF-8"),"D:\\AAA.txt","GBK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用于页面显示图片。
	 * 由于图片存放位置不在项目内部，因此不能在img标签的src
	 * 属性中直接设置图片路径，需要由程序读取文件导入输出流
	 * By Chen Lu， Zhouce Chen
	 *
	 * @param fileName	服务器上的图片文件名
	 * @param path		图片所在目录
	 * @param response	输出流
	 * @throws IOException
	 */
	public static void showImage(String fileName, String path, HttpServletResponse response) throws IOException {
		InputStream is = new FileInputStream(path + fileName);
		response.setContentType("image/jpg; charset=UTF8");
		response.addHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		FileCopyUtils.copy(is, response.getOutputStream());
	}

	/**
	 * 文件下载
	 * By Zhouce Chen
	 *
	 * @param displayName	下载给用户展示的文件名
	 * @param srcFile	将要下载的文件
	 * @param response	文件输出流
	 * @throws IOException
	 */
	public static void download(String displayName, File srcFile, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(displayName, "UTF-8"));
		FileCopyUtils.copy(new FileInputStream(srcFile), response.getOutputStream());
	}

	/**
	 * 文件下载
	 * By Zhouce Chen
	 *
	 * @param displayName	下载给用户展示的文件名
	 * @param srcFilePath	将要下载的文件的路径
	 * @param response	文件输出流
	 * @throws IOException
	 */
	public static void download(String displayName, String srcFilePath, HttpServletResponse response) throws IOException {
		download(displayName, new File(srcFilePath), response);
	}
}
