package com.asiainfo.coder;

import com.asiainfo.coder.enumer.CoderJavaEnum;

/**
 * 
 * 
 */
public class JavaCoderTest {

	public static void main(String[] args) {
		CoderFactory coder = new CoderFactory();
		coder.setPkg_path("com.asiainfo.ilv");
		coder.setFact_name("user");
        coder.setFact_pkg_enum(CoderJavaEnum.Impl);		//实现类  or  接口类//CoderEnum.Impl/CoderEnum.Interface/CoderEnum.Controller
        coder.setFact_type(CoderFactory.FACT_TYPE_SERVICE);		//Dao层 or Service层 or Controller层
		
		java.util.List<String> list_tab = new java.util.ArrayList<String>();
	//	list_tab.add("bi_ilv_user");
	//	list_tab.add("bi_ilv_role");
		list_tab.add("bi_ilv_user_role");
		coder.setList_table(list_tab);//表名
		
		coder.outputCoder();
	}

}
