package com.asiainfo.coder;

public class EntityTest {

	public static void main(String[] args) {
		EntityFactory coder = new EntityFactory();
		coder.setPkg_path("com.asiainfo.ilv");
		java.util.List<String> list_tab = new java.util.ArrayList<String>();
		list_tab.add("ilv_user");
		list_tab.add("ilv_role");
		list_tab.add("ilv_user_role");
		coder.setList_table(list_tab);
		
		coder.outputCoder();
	}

}
