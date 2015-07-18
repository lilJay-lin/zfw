package com.mimi.zfw.util;

import java.util.Date;

public class DateUtil {
	public static String getUpdateTimeStr(Date date) {
		String dateDesc = "秒前更新";
		if (date != null) {
			long tempTime = (System.currentTimeMillis() - date.getTime()) / (1000);
			if (tempTime >= 60) {
				tempTime = tempTime / 60;
				dateDesc = "分钟前更新";
				if (tempTime >= 60) {
					tempTime = tempTime / 60;
					dateDesc = "小时前更新";
					if (tempTime >= 24) {
						tempTime = tempTime / 24;
						dateDesc = "天前更新";
						if (tempTime >= 30) {
							tempTime = tempTime / 30;
							dateDesc = "个月前更新";
							if (tempTime >= 12) {
								tempTime = tempTime / 12;
								dateDesc = "年前更新";
							}
						}
					}
				}
			}
			if (tempTime > 0) {
				dateDesc = "(" + tempTime + dateDesc + ")";
			} else {
				dateDesc = "";
			}
		}
		return dateDesc;
	}
}
