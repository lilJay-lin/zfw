package com.mimi.zfw.util;

import org.apache.commons.lang.math.RandomUtils;

public class MathUtil {
	public static void main(String[] args) {
////		double[] x = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
////		double[] y = { 23, 44, 32, 56, 33, 34, 55, 65, 45, 55 };
////		double[] x = { 0, 8, 9, 1, 4, 5, 6, 7, 2, 3 };
////		double[] y = { 23, 45, 55, 44, 33, 34, 55, 65, 32, 56 };
//		double[] x = { 1.00000000001,1,1 };
//		double[] y = { 23, 45,51};
////		Double[] x = { 0, 8, 9, 1, 4, 5, 6, 7, 2, 3 };
////		Double[] y = { 23, 45, 55, 44, 33, 34, 55, 65, 32, 56 };
////		double[] x = {3,0,1,2,4,5};
////		double[] y = {40,10,20,30,50,60};
////		double[] x = {  3, 4, 5, 6, 7, 8, 9 };
////		double[] y = {  60, 100, 80, 90, 120, 70, 55 };
////		double[] x = {  3, 8, 9 , 4, 5, 6, 7};
////		double[] y = {  60, 70, 55, 100, 80, 90, 120 };
////		System.out.println(estimate(x, y, x.length));
//		double a = getXc(x, y);
//		double b = getC(x, y, a);
//		System.out.println("y="+a+"x+"+b);
//		for(int i=0;i<x.length;i++){
//			System.out.println(y[i]+"_"+(a*x[i]+b));
//		}
//		float total = 100;
//		float rate = 0.05f;
//		int n = 30;
//		for(int i=0;i<n;i++){
//			total= total * (1+rate);
//		}
//		System.out.println(total);
		testRandom();
	}
	
	public static void testRandom(){
		int n= 1000000;
		float notChanged = 0;
		float changed = 0;
		for(int i=0;i<n;i++){
			int result = RandomUtils.nextInt(3);//0,1,2
			int choose1 = RandomUtils.nextInt(3);
			if(choose1==result){
				notChanged++;
			}else{
				changed++;
			}
		}
		System.out.println(notChanged/n+"_"+changed/n);
	}

	/**
	 * 预测
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @return
	 */
	public static double estimate(double[] x, double[] y, int i) {
		double a = getXc(x, y);
		double b = getC(x, y, a);
		return a * i + b;
	}

	/**
	 * 计算 x 的系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getXc(double[] x, double[] y) {
		int n = x.length;
		return (n * pSum(x, y) - sum(x) * sum(y))
				/ (n * sqSum(x) - Math.pow(sum(x), 2));
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @param a
	 * @return
	 */
	public static double getC(double[] x, double[] y, double a) {
		int n = x.length;
		return sum(y) / n - a * sum(x) / n;
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getC(double[] x, double[] y) {
		int n = x.length;
		double a = getXc(x, y);
		return sum(y) / n - a * sum(x) / n;
	}

	private static double sum(double[] ds) {
		double s = 0;
		for (double d : ds)
			s = s + d;
		return s;
	}

	private static double sqSum(double[] ds) {
		double s = 0;
		for (double d : ds)
			s = s + Math.pow(d, 2);
		return s;
	}

	private static double pSum(double[] x, double[] y) {
		double s = 0;
		for (int i = 0; i < x.length; i++)
			s = s + x[i] * y[i];
		return s;
	}
}
