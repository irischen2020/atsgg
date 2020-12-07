package com.atsgg.util;


import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 通用工具方法类
 */
public class CrowdUtil {
	
	
	/**
	 * 判断请求的类型是AJAX请求还是普通请求
	 * @param request
	 * @return
	 */
	public static boolean judgeRequestType(HttpServletRequest request){
		//1、获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		//判断
		if((acceptHeader != null && acceptHeader.contains("application/json"))
			||(xRequestHeader!=null && xRequestHeader.contains("XMLHttpRequest")))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * 对明文字符串进行MD5加密
	 * @param source 传入的字符串
	 * @return  返回加密后的字符串
	 */
	public static String md5(String source){
		// 1、判断source是否有效
		if (source == null || source.length() ==0){
			//2、如果不是有效的字符串抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		try {
			// 3、获取MessageDigest对象
			String algorithm = "md5";
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			
			//4、将传入的字符串转成字节数组
			byte[] input = source.getBytes();
			
			// 5、执行加密
			byte[] output = messageDigest.digest(input);
			
			// 6、创建一个BigInterger对象
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum,output);
			
			// 7、按照16进制将bigInteger的值转换为字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			
			return encoded;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
