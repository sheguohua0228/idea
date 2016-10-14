package com.leyes.app.util;

import java.util.List;

public class StringUtil {
	/**
	 * 
	* @Title: 字符串格式化
	* @Description: 使用模板格式化字符串 
	* @param template:模板中的占位符为^故，模板中的有用字符不可使用^(暂未设置转义字符)
	* @param params:必须与模板中占位符出现的顺序一致
	* @return String:格式化完的字符串
	 */
	public static String[] Format(String template,List<String[]> list){
		int size = list.size();
		String[] content = new String[size];
		for (int j=0;j<size;j++) {
			String[] params = list.get(j);
			String[] src = template.split("\\^");
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < src.length; i++) {
				builder.append(src[i]);
				if (i < params.length)
					builder.append(params[i]);
			}
			content[j] = builder.toString();
		}
		return content;
	}
}
