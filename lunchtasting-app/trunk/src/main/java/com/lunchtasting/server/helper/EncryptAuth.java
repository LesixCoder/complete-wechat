package com.lunchtasting.server.helper;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.lunchtasting.server.util.IdWorker;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密authId
 * @author xq
 *
 */
public class EncryptAuth {

	/**
	 * 中间解密数字
	 */
	private final static int NUMBER = 3;
	private final static String DES = "DES";
    private final static String KEY = "!@#app$%|";


	public static String encode(long userId) throws Exception {
		String timestamp = System.currentTimeMillis()+"";
		String result = timestamp.substring(0, NUMBER) + userId + ""
				+ timestamp.substring(NUMBER, 13);
		return encrypt(result, KEY);
	}

	public static Map getAuthMap(String authId) throws IOException, Exception {
		if (StringUtils.isEmpty(authId)) {
			return null;
		}

		String result = "";
		try {
			result = decrypt(authId, KEY);
		} catch (Exception e) {
			return null;
		}
		int length = result.length();
		int minus = length - 13;

		String before = result.substring(0, NUMBER);
		String middle = result.substring(NUMBER, NUMBER + minus);
		String after = result.substring(NUMBER + minus, length);

		Map map = new HashMap();
		map.put("user_id", middle);
		map.put("timestamp", before + after);
		return map;
	}
	
	public static Long getUserId(String authId) throws IOException, Exception {
		if (StringUtils.isEmpty(authId)) {
			return null;
		}

		String result = "";
		try {
			result = decrypt(authId, KEY);
		} catch (Exception e) {
			return null;
		}
		int length = result.length();
		int minus = length - 13;

		String middle = result.substring(NUMBER, NUMBER + minus);
		return Long.parseLong(middle);
	}
	
	public static Long getUserId(HttpServletRequest request) throws IOException, Exception {
		String authId = request.getHeader("authId");
		if (StringUtils.isEmpty(authId)) {
			return null;
		}
		
		System.out.println("authId = " + authId);
		
		String result = "";
		try {
			result = decrypt(authId, KEY);
		} catch (Exception e) {
			return null;
		}
		int length = result.length();
		int minus = length - 13;

		String middle = result.substring(NUMBER, NUMBER + minus);
		return Long.parseLong(middle);
	}

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException,
			Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf, key.getBytes());
		return new String(bt);
	}

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
	

	public static void main(String[] args) throws Exception {
		String authId = encode(IdWorker.getId());
		System.out.println(authId);
		
		
		Map map = getAuthMap(authId);
		System.out.println(map);
		System.out.println(authId + "//" );
		System.out.println(getUserId(authId));
	}
}
