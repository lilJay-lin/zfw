package com.mimi.zfw.util;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {
	public static final String FORMAT_ERROR_NOT_NULL = "不能为空";
	public static final String FORMAT_ERROR_COMMON = "格式有误";
	public static final String FORMAT_ERROR_LENGTH = "长度超出[{minLen},{maxLen}]的长度限制";

	public static final String REGEX_USER_NAME = "^[a-z]([a-z0-9_]){4,32}$";
	public static final String REGEX_USER_PHONE_NUM = "^1[0-9]{10}$";
	public static final String REGEX_USER_EMAIL = "([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";

	public static final String REGEX_NO_NEED = "";
	public static final String REGEX_COMMON_NAME = "^[0-9a-zA-Z_\u4E00-\u9FA5]+$";

	public static final int MIN_LENGTH_COMMON = 0;
	public static final int MIN_LENGTH_USER_NAME = 4;

	public static final int MAX_LENGTH_COMMON = Integer.MAX_VALUE;
	public static final int MAX_LENGTH_COMMON_SHORT_L1 = 20;
	public static final int MAX_LENGTH_COMMON_SHORT_L2 = 32;
	public static final int MAX_LENGTH_COMMON_NORMAL_L1 = 100;
	public static final int MAX_LENGTH_COMMON_NORMAL_L2 = 200;
	public static final int MAX_LENGTH_COMMON_LONG_L1 = 2000;
	public static final int MAX_LENGTH_COMMON_LONG_L2 = 6000;

	public static String checkLengthOnly(String value, int minLen, int maxLen,
			String errPrefix) {
		return checkFormat(value, REGEX_NO_NEED, false, minLen, maxLen,
				errPrefix);
	}

	public static String checkFormat(String value, String regex,
			boolean notNull, int minLen, int maxLen, String errPrefix) {
		if (StringUtils.isBlank(errPrefix)) {
			errPrefix = "";
		}
		if (StringUtils.isBlank(value)) {
			if (notNull) {
				return errPrefix + FormatUtil.FORMAT_ERROR_NOT_NULL;
			} else {
				return null;
			}
		}
		int length = value.length();
		if (length < minLen || length > maxLen) {
			String errStr = errPrefix + FORMAT_ERROR_LENGTH;
			errStr = errStr.replace("{minLen}", String.valueOf(minLen));
			errStr = errStr.replace("{maxLen}", String.valueOf(maxLen));
			return errStr;
		}
		if (checkValueFormat(value, regex)) {
			return null;
		}
		return errPrefix + FormatUtil.FORMAT_ERROR_COMMON;
	}

	public static boolean checkValueFormat(String value, String regex) {
		return checkValueFormat(value, regex, false);
	}

	public static boolean checkValueFormat(String value,
			String regex, boolean notNull, int maxLen) {
		return checkValueFormat(value, regex, notNull, MIN_LENGTH_COMMON,
				maxLen);
	}

	public static boolean checkValueFormat(String value, String regex,
			boolean notNull) {
		return checkValueFormat(value, regex, notNull, MIN_LENGTH_COMMON,
				MAX_LENGTH_COMMON);
	}

	public static boolean checkValueFormat(String value, String regex,
			boolean notNull, int minLen, int maxLen) {
		if (StringUtils.isBlank(value)) {
			if (notNull) {
				return false;
			} else {
				return true;
			}
		}
		int length = value.length();
		if (length < minLen || length > maxLen) {
			return false;
		}
		if (REGEX_NO_NEED.equals(regex)) {
			return true;
		}
		return value.matches(regex);
	}

}
