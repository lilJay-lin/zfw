package com.mimi.zfw.util;

import java.util.Calendar;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class NumberUtil {
	public static int getIntOrZero(Object obj){
		if(obj==null){
			return 0;
		}
		try{
			String className = obj.getClass().getName();
			if(className.equals("java.lang.String")){
				return Integer.parseInt((String) obj);
			}
			if(className.equals("java.lang.Integer")){
				return (int) obj;
			}
		}catch(Exception e){
		}
		return 0;
	}
	
	public static void main(String args[]){

//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_YEAR,90);
//		System.out.println(cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH));
//		Float f = 13.12314f;
//		int k =  (int) f.floatValue();
//		System.out.println(k);
		Integer k = null;
		if(k>3){
			System.out.println(23);
		}
	}
}
