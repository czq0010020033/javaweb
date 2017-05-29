package com.asiainfo.coder;


import org.apache.commons.lang3.StringUtils;

import com.asiainfo.coder.enumer.CoderJavaEnum;
import com.asiainfo.coder.utils.DBUtils;


/**
 * 
 * <b>类名称</b> :CoderFactory<br>
 * <b>类描述</b> :<br>
 * <b>创建人</b> : zhangjp<br>
 * <b>创建时间</b> : 2015-7-13 上午09:28:17<br>
 * <b>版本号</b> :1.0.0<br>
 ************************************<br>
 * <b>修改人	</b> :<br>
 * <b>修改时间</b> :<br>
 * <b>修改备注</b> :<br>
 * <b>版本号</b> :<br>
 * 
 */
public class CoderFactory {
	
	public static String FACT_TYPE_DAO = "Dao";
	public static String FACT_TYPE_SERVICE = "Service";
	
	public static String METHOD_ADD = "ins";
	public static String METHOD_MOD = "upd";
	public static String METHOD_DEL = "del";
	public static String METHOD_COUNT = "getCount4";
	public static String METHOD_LIST = "getList4";
	public static String METHOD_SINGLE = "getObj4";
	
	public static String METHOD_SERVICE_ADD = "save";
	public static String METHOD_SERVICE_MOD = "update";
	public static String METHOD_SERVICE_DEL = "delete";
	public static String METHOD_SERVICE_COUNT = "findCount4";
	public static String METHOD_SERVICE_LIST = "findList4";
	public static String METHOD_SERVICE_GRID = "findGrid4";
	public static String METHOD_SERVICE_SINGLE = "findObj4";
	
	public static String METHOD_CONTROLLER_SAVE = "saveTo";
	public static String METHOD_CONTROLLER_EDIT = "edit";
	public static String METHOD_CONTROLLER_REMOVE = "rmv";
	public static String METHOD_CONTROLLER_LIST = "list";
	public static String METHOD_CONTROLLER_INIT = "init";
	
	private String fact_type;
	private String fact_name;
	private String pkg_path;
	private java.util.List<String> list_table;

    private CoderJavaEnum fact_pkg_enum = CoderJavaEnum.Impl;

    public CoderJavaEnum getFact_pkg_enum() {
        return fact_pkg_enum;
    }

    public void setFact_pkg_enum(CoderJavaEnum fact_pkg_enum) {
        this.fact_pkg_enum = fact_pkg_enum;
    }

	public CoderFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFact_type() {
		return fact_type;
	}

	public void setFact_type(String fact_type) {
		this.fact_type = fact_type;
	}

	public String getFact_name() {
		return fact_name;
	}

	public void setFact_name(String fact_name) {
		this.fact_name = fact_name;
	}

	public String getPkg_path() {
		return pkg_path;
	}

	public void setPkg_path(String pkg_path) {
		this.pkg_path = pkg_path;
	}

	public java.util.List<String> getList_table() {
		return list_table;
	}

	public void setList_table(java.util.List<String> list_table) {
		this.list_table = list_table;
	}

	public void outputCoder(){
		StringBuffer _buffer = new StringBuffer();

		_buffer.append(jointPackageCoder(this.fact_pkg_enum));
		
		_buffer.append(jointImportCoder());
		
		_buffer.append(jointPackageBodyCoder(this.fact_pkg_enum));
		
		System.out.println(_buffer.toString());
	}
	
	private StringBuffer jointPackageBodyCoder(CoderJavaEnum _enum) {
		StringBuffer _buffer_pkgBody = new StringBuffer();
		String _tmpPkg = getGeneralName(this.fact_name);
		if(_enum.equals(CoderJavaEnum.Impl))
		{
			if(StringUtils.isEmpty(fact_type))
			{
				_buffer_pkgBody.append("@Repository\n");
				_buffer_pkgBody.append("public class "+_tmpPkg+"DaoImpl extends BaseDao implements I"+_tmpPkg+"Dao {");
				_buffer_pkgBody.append("\n");
				_buffer_pkgBody.append("\tprivate Logger logger = LoggerFactory.getLogger("+_tmpPkg+"DaoImpl.class);\n");
			}
			else if(FACT_TYPE_DAO.equals(fact_type))
			{
				_buffer_pkgBody.append("@Repository\n");
				_buffer_pkgBody.append("public class "+_tmpPkg+"DaoImpl extends BaseDao implements I"+_tmpPkg+"Dao {");
				_buffer_pkgBody.append("\n");
				_buffer_pkgBody.append("\tprivate Logger logger = LoggerFactory.getLogger("+_tmpPkg+"DaoImpl.class);\n");
			}
			else if(FACT_TYPE_SERVICE.equals(fact_type))
			{
				_buffer_pkgBody.append("@Service\n");
				_buffer_pkgBody.append("@Transactional\n");
				_buffer_pkgBody.append("public class "+_tmpPkg+"ServiceImpl implements I"+_tmpPkg+"Service {");
				_buffer_pkgBody.append("\n");
				_buffer_pkgBody.append("\tprivate Logger logger = LoggerFactory.getLogger("+_tmpPkg+"ServiceImpl.class);\n");
				_buffer_pkgBody.append("\n");
				_buffer_pkgBody.append("\t@Resource\n");
				_buffer_pkgBody.append("\tprivate I"+_tmpPkg+"Dao "+_tmpPkg.toLowerCase()+"Dao;\n");
			}
			_buffer_pkgBody.append("\n");

		}
		else if(_enum.equals(CoderJavaEnum.Interface))
		{
			if(StringUtils.isEmpty(fact_type))
			{
				_buffer_pkgBody.append("public interface I"+_tmpPkg+"Dao {");
			}
			else if(FACT_TYPE_DAO.equals(fact_type))
			{
				_buffer_pkgBody.append("public interface I"+_tmpPkg+"Dao {");
			}
			else if(FACT_TYPE_SERVICE.equals(fact_type))
			{
				_buffer_pkgBody.append("public interface I"+_tmpPkg+"Service {");
			}

		}
		else if(_enum.equals(CoderJavaEnum.Controller))
		{
			_buffer_pkgBody.append("@Controller\n");
			_buffer_pkgBody.append("@RequestMapping(\"/"+this.fact_name.toLowerCase()+"\")\n");
			_buffer_pkgBody.append("public class "+_tmpPkg+"Controller extends BaseController {");
			_buffer_pkgBody.append("\n");
			_buffer_pkgBody.append("\tprivate Logger logger = LoggerFactory.getLogger("+_tmpPkg+"Controller.class);\n");
			_buffer_pkgBody.append("\n");
			_buffer_pkgBody.append("\t@Resource\n");
			_buffer_pkgBody.append("\tprivate I"+_tmpPkg+"Service "+_tmpPkg.toLowerCase()+"Service;\n");
			_buffer_pkgBody.append("\n");
		}
		
        for(String _tab:this.list_table)
        {
            if(StringUtils.isNotEmpty(_tab))
            {
                String _tmpTab = getGeneralName(_tab);
                if(_enum.equals(CoderJavaEnum.Controller))
                {
                    _buffer_pkgBody.append(jointTabBodyCoder(_enum,_tmpTab,_tab,getGeneralName(this.fact_name)));
                }
                else
                {
                    _buffer_pkgBody.append(jointTabBodyCoder(_enum,_tmpTab,_tab,getGeneralName(this.fact_name).toLowerCase()+"Dao"));
                }
            }
        }
		
		_buffer_pkgBody.append("\n");
		return _buffer_pkgBody.append("}");
	}

	private StringBuffer jointTabCommentCoder(String _tab) {
		
		String _cols = "";
		try {
			DBUtils dbU = new DBUtils();
			java.util.List<String> list = dbU.queryList4ColName(_tab);
			for(String _col:list){
				_cols += StringUtils.isEmpty(_cols)?_col:","+_col;
			}
			dbU.closeConn();
		} catch (Exception e) {
		}
		
		
		StringBuffer _buffer_comment = new StringBuffer();
		_buffer_comment.append("\n");
		_buffer_comment.append("\t/****\n");
		_buffer_comment.append("\t*\n");
		_buffer_comment.append("\t* tab:"+_tab.toUpperCase()+"\n");
		_buffer_comment.append("\t* cols:"+_cols.toUpperCase()+"\n");
		_buffer_comment.append("\t*\n");
		_buffer_comment.append("\t****/");
		return _buffer_comment;
	}

	private StringBuffer jointTabBodyCoder(CoderJavaEnum _enum,String _tmpTab,String _tab,String _tmpPkg) {
		StringBuffer _buffer_tabBody = new StringBuffer();
		if(_enum.equals(CoderJavaEnum.Impl))
		{
			String _cols = "";
			try {
				DBUtils dbU = new DBUtils();
				java.util.List<String> list = dbU.queryList4ColName(_tab);
				for(String _col:list){
					_cols += StringUtils.isEmpty(_cols)?_col:","+_col;
				}
				dbU.closeConn();
			} catch (Exception e) {
			}
			
			_buffer_tabBody.append(jointTabCommentCoder(_tab));
			
			if(StringUtils.isEmpty(fact_type) || FACT_TYPE_DAO.equals(this.fact_type))
			{
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_COUNT,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_LIST,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_SINGLE,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
			}
			else if(FACT_TYPE_SERVICE.equals(this.fact_type))
			{
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_SERVICE_COUNT,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_SERVICE_LIST,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
				_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_SERVICE_SINGLE,_tmpTab,_tab.toUpperCase(),_cols.toUpperCase(),_tmpPkg));
			}
		}
		else if(_enum.equals(CoderJavaEnum.Interface))
		{
			_buffer_tabBody.append(jointTabCommentCoder(_tab));
			if(StringUtils.isEmpty(fact_type) || FACT_TYPE_DAO.equals(this.fact_type))
			{
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_COUNT,_tmpTab));
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_LIST,_tmpTab));
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_SINGLE,_tmpTab));
			}
			else if(FACT_TYPE_SERVICE.equals(this.fact_type))
			{
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_SERVICE_COUNT,_tmpTab));
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_SERVICE_LIST,_tmpTab));
				_buffer_tabBody.append(jointIMethodBodyCoder(CoderFactory.METHOD_SERVICE_SINGLE,_tmpTab));
			}
		}
		else if(_enum.equals(CoderJavaEnum.Controller)){
			_buffer_tabBody.append(jointTabCommentCoder(_tab));
			_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_CONTROLLER_INIT,_tmpTab,_tab.toUpperCase(),"",_tmpPkg));
			_buffer_tabBody.append(jointMethodBodyCoder(CoderFactory.METHOD_CONTROLLER_LIST,_tmpTab,_tab.toUpperCase(),"",_tmpPkg));
		}
		return _buffer_tabBody;
	}

	private StringBuffer jointIMethodBodyCoder(String method_type, String _tab) {
		String _tmpTab = getGeneralName(_tab);
		StringBuffer _buffer_IMethod = new StringBuffer();
		_buffer_IMethod.append("\n");
		if(CoderFactory.METHOD_COUNT.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic Integer "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
		}
		else if(CoderFactory.METHOD_LIST.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic List<"+_tmpTab+"> "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
		}
		else if(CoderFactory.METHOD_SINGLE.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic "+_tmpTab+" "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
		}
		else if(CoderFactory.METHOD_SERVICE_COUNT.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic Integer "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
		}
		else if(CoderFactory.METHOD_SERVICE_LIST.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic List<"+_tmpTab+"Vo> "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
				_buffer_IMethod.append("\n");
				_buffer_IMethod.append("\tpublic GridModel "+CoderFactory.METHOD_SERVICE_GRID+_tmpTab+"(Map<String, Object> map, PagerBaseVo pagerBaseVo) throws Exception;\n");
		}
		else if(CoderFactory.METHOD_SERVICE_SINGLE.equals(method_type))
		{
				_buffer_IMethod.append("\tpublic "+_tmpTab+"Vo "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception;\n");
		}
		return _buffer_IMethod;
	}

	/**
	 * Method 具体实现代码
	 */
	private StringBuffer jointMethodBodyCoder(String method_type, String _tmpTab,String _tab,String _cols,String _tmpPkg) {
		StringBuffer _buffer_Method = new StringBuffer();
		_buffer_Method.append("\n");
		String[] cols = com.asiainfo.coder.utils.StringUtils.tokenizeToStringArray(_cols, ",");
		if(CoderFactory.METHOD_COUNT.equals(method_type))
		{
			_buffer_Method.append("\tpublic Integer "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			_buffer_Method.append("\t\tStringBuffer sSQL = new StringBuffer();\n");
			_buffer_Method.append("\t\tsSQL.append(\"SELECT COUNT(*) FROM "+_tab+" WHERE 1=1 \");\n");
			for(String col:cols){
				_buffer_Method.append("\t\tif(StringUtils.isNotEmpty((String)map.get(\""+col.toLowerCase()+"\"))){\n");
				_buffer_Method.append("\t\t\tsSQL.append(\" AND "+col+" = :"+col.toLowerCase()+" \");\n");
				_buffer_Method.append("\t\t}\n");
			}
			_buffer_Method.append("\n");
			_buffer_Method.append("\t\treturn namedTemplate.queryForObject(sSQL.toString(), map, Integer.class);\n");
			_buffer_Method.append("\t}\n");
		}else if(CoderFactory.METHOD_LIST.equals(method_type))
		{
			_buffer_Method.append("\tpublic List<"+_tmpTab+"> "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			_buffer_Method.append("\t\tStringBuffer sSQL = new StringBuffer();\n");
			_buffer_Method.append("\t\tsSQL.append(\"SELECT "+_cols+" FROM "+_tab+" WHERE 1=1 \");\n");
			for(String col:cols){
				_buffer_Method.append("\t\tif(StringUtils.isNotEmpty((String)map.get(\""+col.toLowerCase()+"\"))){\n");
				_buffer_Method.append("\t\t\tsSQL.append(\" AND "+col+" = :"+col.toLowerCase()+" \");\n");
				_buffer_Method.append("\t\t}\n");
			}
			_buffer_Method.append("\n");
			_buffer_Method.append("\t\tObject page = map.get(\"page\");\n");
			_buffer_Method.append("\t\tObject rows = map.get(\"rows\");\n");
			_buffer_Method.append("\t\tString sql = null;\n");
			_buffer_Method.append("\t\tif(null!=page && null!=rows){//需要分页\n");
			_buffer_Method.append("\t\t\tsql = PageUtils.assembleOraclePageSQLByName(sSQL.toString());\n");
			_buffer_Method.append("\t\t}else{//不需要分页\n");
			_buffer_Method.append("\t\t\tsql = sSQL.toString();\n");
			_buffer_Method.append("\t\t}\n");
			_buffer_Method.append("\t\treturn namedTemplate.query(sql, map, BeanPropertyRowMapper.newInstance("+_tmpTab+".class));\n");
			_buffer_Method.append("\t}\n");
		}
		else if(CoderFactory.METHOD_SERVICE_COUNT.equals(method_type))
		{
			_buffer_Method.append("\tpublic Integer "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			_buffer_Method.append("\t\treturn  "+_tmpPkg+"."+CoderFactory.METHOD_COUNT+_tmpTab+"(map);\n");
			_buffer_Method.append("\t}\n");
			_buffer_Method.append("\n");
		}
		else if(CoderFactory.METHOD_SERVICE_LIST.equals(method_type))
		{
			_buffer_Method.append("\tpublic List<"+_tmpTab+"Vo> "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			_buffer_Method.append("\t\tList<"+_tmpTab+"> list = "+_tmpPkg+"."+CoderFactory.METHOD_LIST+_tmpTab+"(map);\n");
			_buffer_Method.append("\t\treturn BeanCopierUtils.convertList(list, "+_tmpTab+"Vo.class);\n");
			_buffer_Method.append("\t}\n");
			_buffer_Method.append("\n");
			_buffer_Method.append("\tpublic GridModel "+CoderFactory.METHOD_SERVICE_GRID+_tmpTab+"(Map<String, Object> map, PagerBaseVo pageBaseVo) throws Exception {\n");
			_buffer_Method.append("\t\tInteger total = "+_tmpPkg+"."+CoderFactory.METHOD_COUNT+_tmpTab+"(map);\n");
			_buffer_Method.append("\t\tif(total > 0){\n");
			_buffer_Method.append("\t\t\tmap.put(\"page\", pageBaseVo.getPage());\n");
			_buffer_Method.append("\t\t\tmap.put(\"rows\", pageBaseVo.getRows());\n");
			_buffer_Method.append("\t\t\tList<"+_tmpTab+"> list =  "+_tmpPkg+"."+CoderFactory.METHOD_LIST+_tmpTab+"(map);\n");
			_buffer_Method.append("\t\t\treturn new GridModel(total, BeanCopierUtils.convertList(list, "+_tmpTab+"Vo.class));\n");
			_buffer_Method.append("\t\t}else{\n");
			_buffer_Method.append("\t\t\treturn new GridModel(0, new ArrayList<"+_tmpTab+"Vo>());\n");
			_buffer_Method.append("\t\t}\n");
			_buffer_Method.append("\t}\n");
		}
		else if(CoderFactory.METHOD_SINGLE.equals(method_type))
		{
			_buffer_Method.append("\tpublic "+_tmpTab+" "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			_buffer_Method.append("\t\tStringBuffer sSQL = new StringBuffer();\n");
            _buffer_Method.append("\t\tsSQL.append(\"SELECT "+_cols+" FROM "+_tab+" WHERE "+cols[0]+" = :"+cols[0].toLowerCase()+"\");\n");
            _buffer_Method.append("\n");
            _buffer_Method.append("\t\tjava.util.List<"+_tmpTab+"> list = namedTemplate.query(sSQL.toString(),map,BeanPropertyRowMapper.newInstance("+_tmpTab+".class));\n");
            _buffer_Method.append("\t\tif(list != null && list.size() > 0){\n");
            _buffer_Method.append("\t\t\treturn list.get(0);\n");
            _buffer_Method.append("\t\t} else {\n");
            _buffer_Method.append("\t\t\treturn null;\n");
            _buffer_Method.append("\t\t}\n");
    		_buffer_Method.append("\t}\n");
		}
		else if(CoderFactory.METHOD_SERVICE_SINGLE.equals(method_type))
		{
			_buffer_Method.append("\tpublic "+_tmpTab+"Vo "+method_type+_tmpTab+"(Map<String, Object> map) throws Exception {\n");
			String _po = _tmpTab.replaceFirst(_tmpTab.substring(0, 1), _tmpTab.substring(0, 1).toLowerCase()); 
			_buffer_Method.append("\t\t"+_tmpTab+" "+_po+" = "+_tmpPkg+"."+CoderFactory.METHOD_SINGLE+_tmpTab+"(map);\n");
			_buffer_Method.append("\t\treturn BeanCopierUtils.convert("+_po+", "+_tmpTab+"Vo.class);\n");
			_buffer_Method.append("\t}\n");
		}
		return _buffer_Method;
	}

	private StringBuffer jointPackageCoder(CoderJavaEnum _enum) {
		StringBuffer _buffer_package = new StringBuffer();
		
		if(_enum.equals(CoderJavaEnum.Impl))
		{
			if(StringUtils.isEmpty(fact_type))
			{
				_buffer_package.append("package "+this.pkg_path+".dao.impl;\n");
			}
			else
			{
				_buffer_package.append("package "+this.pkg_path+"."+this.fact_type.toLowerCase()+".impl;\n");
			}
		}
		else if(_enum.equals(CoderJavaEnum.Interface))
		{
			if(StringUtils.isEmpty(fact_type))
			{
				_buffer_package.append("package "+this.pkg_path+".dao;\n");
			}
			else
			{
				_buffer_package.append("package "+this.pkg_path+"."+this.fact_type.toLowerCase()+";\n");
			}
		}
		else if(_enum.equals(CoderJavaEnum.Controller))
		{
			_buffer_package.append("package "+this.pkg_path+".controller;\n");
		}
		_buffer_package.append("\n");
		
		return _buffer_package;
	}

	/**
	 * 引起java类包
	 */
	private StringBuffer jointImportCoder() {
		StringBuffer _buffer_import = new StringBuffer();
		if(this.fact_pkg_enum.equals(CoderJavaEnum.Impl)){
			_buffer_import.append("import java.util.ArrayList;\n");
			_buffer_import.append("import java.util.HashMap;\n");
		}
		_buffer_import.append("import java.util.Map;\n");
		_buffer_import.append("import java.util.List;\n");

		String _tmpPkg = getGeneralName(this.fact_name);
		if(this.fact_pkg_enum.equals(CoderJavaEnum.Impl))
		{
			_buffer_import.append("import org.slf4j.Logger;\n");
			_buffer_import.append("import org.slf4j.LoggerFactory;\n");
			if(this.fact_type.equals(FACT_TYPE_SERVICE)){
				_buffer_import.append("import javax.annotation.Resource;\n");
				_buffer_import.append("import org.springframework.stereotype.Service;\n");
				_buffer_import.append("import org.springframework.transaction.annotation.Transactional;\n");
				_buffer_import.append("import "+this.pkg_path+".dao.I"+_tmpPkg+"Dao;\n");
				_buffer_import.append("import "+this.pkg_path+".service.I"+_tmpPkg+"Service;\n");
				_buffer_import.append("import "+this.pkg_path+".utils.BeanCopierUtils;\n");
			}else if(this.fact_type.equals(FACT_TYPE_DAO)){
				_buffer_import.append("import org.apache.commons.lang3.StringUtils;\n");
				_buffer_import.append("import org.springframework.jdbc.core.BeanPropertyRowMapper;\n");
				_buffer_import.append("import "+this.pkg_path+".utils.PageUtils;\n");
				_buffer_import.append("import org.springframework.stereotype.Repository;\n");
				_buffer_import.append("import "+this.pkg_path+".dao.I"+_tmpPkg+"Dao;\n");
			}
		}
		if(this.fact_pkg_enum.equals(CoderJavaEnum.Controller))
		{
			_buffer_import.append("import org.slf4j.Logger;\n");
			_buffer_import.append("import org.slf4j.LoggerFactory;\n");
			_buffer_import.append("import javax.annotation.Resource;\n");
			_buffer_import.append("import javax.servlet.http.HttpServletRequest;\n");
			_buffer_import.append("import org.springframework.stereotype.Controller;\n");
			_buffer_import.append("import org.springframework.ui.ModelMap;\n");
			_buffer_import.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
			_buffer_import.append("import org.springframework.web.bind.annotation.RequestParam;\n");
			_buffer_import.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
			_buffer_import.append("import "+this.pkg_path+".web.BaseController;\n");
			_buffer_import.append("import "+this.pkg_path+".constant.SessionConstants;\n");
			_buffer_import.append("import "+this.pkg_path+".model.UserSessionModel;\n");
			_buffer_import.append("import "+this.pkg_path+".service.I"+_tmpPkg+"Service;\n");
		}
		
		if(this.fact_type.equals(FACT_TYPE_SERVICE)){
			_buffer_import.append("import "+this.pkg_path+".model.GridModel;\n");
			_buffer_import.append("import "+this.pkg_path+".vo.PagerBaseVo;\n");
		}
		for(String _tab:this.list_table)
		{
			if(StringUtils.isNotEmpty(_tab))
			{
				String _tmpTab = getGeneralName(_tab);
				if(this.fact_type.equals(FACT_TYPE_SERVICE)){
					_buffer_import.append("import "+this.pkg_path+".vo."+_tmpTab+"Vo;\n");
					if(this.fact_pkg_enum.equals(CoderJavaEnum.Impl)){
						_buffer_import.append("import "+this.pkg_path+".po."+_tmpTab+";\n");
					}
				}else if(this.fact_type.equals(FACT_TYPE_DAO)){
					_buffer_import.append("import "+this.pkg_path+".po."+_tmpTab+";\n");
				}
			}
		}
		_buffer_import.append("\n");
		
		return _buffer_import;
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

	public static void main(String[] args) {
	}

}
