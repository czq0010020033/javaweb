package com.asiainfo.coder.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <b>类名称</b> :DBUtil<br>
 * <b>类描述</b> :数据库操作工具类<br>
 * <b>创建人</b> : zhangjp<br>
 * <b>创建时间</b> : 2015-7-14 上午09:46:14<br>
 * <b>版本号</b> :1.0.0<br>
 ************************************<br>
 * <b>修改人	</b> :<br>
 * <b>修改时间</b> :<br>
 * <b>修改备注</b> :<br>
 * <b>版本号</b> :<br>
 * 
 */
public class DBUtils {

	private	String driver="oracle.jdbc.driver.OracleDriver";
    private String url="jdbc:oracle:thin:@localhost:1521:orcl";

    private String user="scott";
    private String pwd="123456";
    
    private Connection conn;
    
    public DBUtils() throws Exception {
		super();
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url,user, pwd);
	}

	public DBUtils(String driver, String url, String user, String pwd) throws Exception {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url,user, pwd);
	}

	public java.util.List<Map<String, String>> queryList4Col(String tab_name) throws Exception{
		java.util.List<Map<String, String>> list_cols = new java.util.ArrayList<Map<String, String>>();
		tab_name = tab_name.toUpperCase();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = '%s'", tab_name));
		while (rs.next()) {
		    Map<String, String> colMap = new HashMap<String, String>();
		    colMap.put("COLUMN_NAME", rs.getString("COLUMN_NAME"));
		    colMap.put("REMARKS", StringUtils.isNotEmpty(rs.getString("COMMENTS")) ? rs.getString("COMMENTS").trim() : null);
		    list_cols.add(colMap);
		}
		return list_cols;
    }

	public java.util.List<String> queryList4ColName(String tab_name) throws Exception{
		java.util.List<String> list_cols = new java.util.ArrayList<String>();
		tab_name = tab_name.toUpperCase();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = '%s'", tab_name));
		while (rs.next()) {
		    list_cols.add(rs.getString("COLUMN_NAME"));
		}
		return list_cols;
    }
    
	
	public void closeConn() throws Exception{
		this.conn.close();
	}
	
	public static void main(String[] args) {
		try {
			DBUtils db = new DBUtils();
			Connection conn = db.conn;
			Statement stmt = conn.createStatement();
			String tab_name = "FAP_API";
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = '%s'", tab_name));
			while (rs.next()) {
			    System.out.println(rs.getString("COLUMN_NAME")+"----"+rs.getString("COMMENTS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
