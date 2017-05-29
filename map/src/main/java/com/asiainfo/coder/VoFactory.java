package com.asiainfo.coder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.asiainfo.coder.utils.DBUtils;
import com.asiainfo.coder.utils.FileUtils;

/**
 * 
 * <b>类名称</b> :VoFactory<br>
 * <b>类描述</b> :<br>
 * <b>创建人</b> : xiabin<br>
 * <b>创建时间</b> : 2016-5-26 上午09:46:33<br>
 * <b>版本号</b> :1.0.0<br>
 ************************************<br>
 * <b>修改人	</b> :<br>
 * <b>修改时间</b> :<br>
 * <b>修改备注</b> :<br>
 * <b>版本号</b> :<br>
 * 
 */
public class VoFactory {
	private java.util.List<String> list_table;
	private String pkg_path;
	
	private static String classPath = VoFactory.class.getClassLoader().getResource("").getPath();
	static
	{
		try {
			classPath= java.net.URLDecoder.decode(classPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	private String file_path = classPath;
	public VoFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public java.util.List<String> getList_table() {
		return list_table;
	}
	public void setList_table(java.util.List<String> list_table) {
		this.list_table = list_table;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getPkg_path() {
		return pkg_path;
	}
	public void setPkg_path(String pkg_path) {
		this.pkg_path = pkg_path;
	}
	public void outputCoder(){
		
		for(String _tab:this.list_table)
		{
			StringBuffer _buffer = new StringBuffer();
			_buffer.append("package "+this.pkg_path+".vo;\n");
			_buffer.append("\n");
			String _tmpTab = getGeneralName(_tab);
			_buffer.append("public class "+_tmpTab+"Vo {\n");
			_buffer.append("\n");
			try {
				DBUtils dbU = new DBUtils();
				java.util.List<Map<String, String>> list = dbU.queryList4Col(_tab);
				dbU.closeConn();
				
				java.util.List<String> _colList = new java.util.ArrayList<String>();
				for(Map<String, String> _col : list){
					String colName = _col.get("COLUMN_NAME");
					String colRemarks = _col.get("REMARKS");
					if(StringUtils.isNotEmpty(colRemarks)){
						_buffer.append("\t/**"+colRemarks+"*/\n");
					}
					_buffer.append("\tprivate String "+colName.toLowerCase()+";\n");
					_colList.add(colName.toLowerCase());
				}
				_buffer.append("\n");
				_buffer.append("\tpublic "+_tmpTab+"Vo() {}\n");
				_buffer.append("\n");
				
				for(String _col : _colList){
					String _tmpCol = getGeneralColName(_col);
					_buffer.append("\tpublic String get"+_tmpCol+"() {\n");
					_buffer.append("\t\treturn "+_col+";\n");
					_buffer.append("\t}\n");
					_buffer.append("\tpublic void set"+_tmpCol+"(String "+_col+") {\n");
					_buffer.append("\t\tthis."+_col+" = "+_col+";\n");
					_buffer.append("\t}\n");
					_buffer.append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			
			_buffer.append("}\n");
			
			try {
				System.out.println(this.file_path);
				java.io.File _ffff = new java.io.File(this.file_path+_tmpTab+"Vo.java");
				if(!_ffff.exists()){
					_ffff.delete();
				}
				FileUtils.String2File(_buffer, this.file_path+_tmpTab+"Vo.java", "UTF-8");
			} catch (IOException e) {
				System.err.println(e);
				e.printStackTrace();
			}
			System.out.println(_buffer.toString());
		}
	}
	private String getGeneralName(String obj) {
		String generalName = obj;
		
		while(generalName.indexOf("_")>=0){
			String _tmpA = generalName.substring(generalName.indexOf("_"), generalName.indexOf("_")+2);
			String _tmpB = _tmpA.replace("_","").toUpperCase();
			generalName = generalName.replace(_tmpA, _tmpB);
		}
		
		generalName = generalName.replaceFirst(generalName.substring(0, 1), generalName.substring(0, 1).toUpperCase()); 
		
		return generalName;
	}
	private String getGeneralColName(String obj) {
		String generalName = obj;
		
//		while(generalName.indexOf("_")>=0){
//			String _tmpA = generalName.substring(generalName.indexOf("_"), generalName.indexOf("_")+2);
//			String _tmpB = _tmpA.replace("_","").toUpperCase();
//			generalName = generalName.replace(_tmpA, _tmpB);
//		}
		
		generalName = generalName.replaceFirst(generalName.substring(0, 1), generalName.substring(0, 1).toUpperCase()); 
		
		return generalName;
	}
}
