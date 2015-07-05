package com.mimi.zfw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
	public final static String MD5_OLD(String s) {
		if(s==null){
			return "";
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String MD5(String input){
		try {  
			  
		      // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）  
		      MessageDigest messageDigest =MessageDigest.getInstance("MD5");  
		  
		      // 输入的字符串转换成字节数组  
		      byte[] inputByteArray = input.getBytes();  
		  
		      // inputByteArray是输入字符串转换得到的字节数组  
		      messageDigest.update(inputByteArray);  
		  
		      // 转换并返回结果，也是字节数组，包含16个元素  
		      byte[] resultByteArray = messageDigest.digest();  
		  
		      // 字符数组转换成字符串返回  
		      return byteArrayToHex(resultByteArray);  
		   } catch (NoSuchAlgorithmException e) {  
		      return null;  
		   }  
	}
	public static String byteArrayToHex(byte[] byteArray) {  
		  
		   // 首先初始化一个字符数组，用来存放每个16进制字符  
//		   char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };  
		   char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f' }; 
		  
		   // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））  
		   char[] resultCharArray =new char[byteArray.length * 2];  
		  
		   // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去  
		   int index = 0;  
		   for (byte b : byteArray) {  
		      resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
		      resultCharArray[index++] = hexDigits[b& 0xf];  
		   }  
		  
		   // 字符数组组合成字符串返回  
		   return new String(resultCharArray);  
	}

	public static void main(String[] args) {
		String username = "test";
		String password = "admin";
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		int count = 2;
		SimpleHash sh = new SimpleHash("md5", password, username + salt, count);
		System.out.println(MD5Util.MD5(""));
		String str = password+username+salt;
		for(int i=0;i<count;i++){
			str = MD5Util.MD5(str);
		}
		System.out.println(salt+"_"+sh.toString()+"_"+str);
	}
}