package com.li.util;

public class StringUtil {
	public static boolean isBlank(String str){
		boolean flag = true;
		if((str!=null)&&(str.trim().length()>0))
			flag=false;
		return flag;
	}
}
