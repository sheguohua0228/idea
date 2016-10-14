package com.aplus.lk.clothes.utils;

public class StringUtils {

	public static boolean isNotEmpty(String target) {
		return target != null && target.length() > 0;
	}

	public static boolean isEmpty(String target) {
		return target == null || target.length() == 0;
	}

	public static boolean isBlank(String target) {
		int length;
		if ((target == null) || ((length = target.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(target.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = false
	 * StringUtil.isBlank("")        = false
	 * StringUtil.isBlank(" ")       = false
	 * StringUtil.isBlank("bob")     = true
	 * StringUtil.isBlank("  bob  ") = true
	 * </pre>
	 * @param str 要检查的字符串
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

}
