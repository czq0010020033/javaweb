package com.asiainfo.coder.utils;

/**
 * @author zhengfeng
 */
public class CharsetUtils {    
    
    private static final String CHARSET_ENCODING_GB2312="gb2312";
    private static final String CHARSET_ENCODING_ISO88591="ISO-8859-1";
    private static final String CHARSET_ENCODING_UTF8="UTF-8";
    private static final String CHARSET_ENCODING_UNICODE="Unicode";
    
    public static String convert(String srcSTR){
      return convertIsoToGb(srcSTR);  
    }
    
    public static String convertIsoToGb(String srcSTR){       
      return convert(srcSTR,CHARSET_ENCODING_ISO88591,CHARSET_ENCODING_GB2312);
    }
    
    public static String convertGbToISO(String srcSTR){       
      return convert(srcSTR,CHARSET_ENCODING_GB2312,CHARSET_ENCODING_ISO88591);
    }
    
    public static String convert(String srcSTR,String fromCharset,String toCharset){
      
      if(srcSTR==null) return null;
      if(srcSTR.equals("")) return srcSTR;
      String toSTR=srcSTR;
      try{
        toSTR=new String(srcSTR.getBytes(fromCharset),toCharset);
      }catch(Exception e){
        System.out.println("Error:Converting Charset Error("+fromCharset+"-->"+toCharset+"),cause:"+e.toString());        
      }
      return toSTR;
    }
    
    public static byte[] gbkToUTF8(String chenese){
    	  char c[] = chenese.toCharArray();
    	        byte [] fullByte =new byte[3*c.length];
    	        for(int i=0; i<c.length; i++){
    	         int m = (int)c[i];
    	         String word = Integer.toBinaryString(m);
//    	         System.out.println(word);
    	        
    	         StringBuffer sb = new StringBuffer();
    	         int len = 16 - word.length();
    	         //����
    	         for(int j=0; j<len; j++){
    	          sb.append("0");
    	         }
    	         sb.append(word);
    	         sb.insert(0, "1110");
    	         sb.insert(8, "10");
    	         sb.insert(16, "10");
    	         
//    	         System.out.println(sb.toString());
    	         
    	         String s1 = sb.substring(0, 8);          
    	         String s2 = sb.substring(8, 16);          
    	         String s3 = sb.substring(16);
    	         
    	         byte b0 = Integer.valueOf(s1, 2).byteValue();
    	         byte b1 = Integer.valueOf(s2, 2).byteValue();
    	         byte b2 = Integer.valueOf(s3, 2).byteValue();
    	         byte[] bf = new byte[3];
    	         bf[0] = b0;
    	         fullByte[i*3] = bf[0];
    	         bf[1] = b1;
    	         fullByte[i*3+1] = bf[1];
    	         bf[2] = b2;
    	         fullByte[i*3+2] = bf[2];
    	         
    	        }
    	        return fullByte;
    	 }

    public static String getInputCharset(String sIn){
    	
    	//java�е��ַ�����unicode����4��ʾ��,
    	String charset="GB2312";   
        try{   
        	if(sIn.equals(new String(sIn.getBytes(charset), charset))) return charset;   
        }catch(Exception e)   {   
        }   
        
        /*
        
        
        encode   =   "ISO-8859-1";   
        try   {   
        	String a=new String("a".getBytes(),"b");    
        	if   (str.equals(new   String(str.getBytes(encode),   encode)))   {   
                        String   s1   =   encode;   
                        return   s1;   
                }   
        }   catch   (Exception   exception1)   {   
        }   
        encode   =   "UTF-8";   
        try   {   
                if   (str.equals(new   String(str.getBytes(encode),   encode)))   {   
                        String   s2   =   encode;   
                        return   s2;   
                }   
        }   catch   (Exception   exception2)   {   
        }   
        encode   =   "GBK";   
        try   {   
                if   (str.equals(new   String(str.getBytes(encode),   encode)))   {   
                        String   s3   =   encode;   
                        return   s3;   
                }   
        }   catch   (Exception   exception3)   {   
        }   
        encode   =   "BIG5";   
        try   {   
                if   (str.equals(new   String(str.getBytes(encode),   encode)))   {   
                        String   s4   =   encode;   
                        return   s4;   
                }   
        }   catch   (Exception   exception3)   {   
        }   
        
        */
        
        return   ""; 
    	
    	
    	
    	
    	
    	
    	
    	
    }
    
    
}
