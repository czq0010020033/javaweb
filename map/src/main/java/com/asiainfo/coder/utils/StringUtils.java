package com.asiainfo.coder.utils;

import java.util.List;

public class StringUtils {

	public static String[] tokenizeToStringArray(String mainMenuStr, String delimiters)
	{
		return tokenizeToStringArray(mainMenuStr,delimiters,true,true);
	}
	public static String[] tokenizeToStringArray(String mainMenuStr, String delimiters,boolean trimTokens,boolean ignoreEmptyTokens) {
		// TODO Auto-generated method stub
		//boolean trimTokens = true;
		//boolean ignoreEmptyTokens = true;
		mainMenuStr = mainMenuStr==null?"":mainMenuStr;
		java.util.StringTokenizer st = new java.util.StringTokenizer(mainMenuStr, delimiters);
        List tokens = new java.util.ArrayList();
        do
        {
            if(!st.hasMoreTokens())
                break;
            String token = st.nextToken();
            if(trimTokens)
                token = token.trim();
            if(!ignoreEmptyTokens || token.length() > 0)
                tokens.add(token);
        } while(true);
        return (String[])tokens.toArray(new String[tokens.size()]);
	}
}
