package com.lunchtasting.server.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

/**
 * 发送http请求
 * 
 * @author: 周爽
 */
public class HttpClient {
	/**
	 * 发送http请求
	 * 
	 * @author 周爽
	 * @param url
	 * @param method
	 * @param param
	 * @return
	 * @return: String
	 */
	public static String sendRequest(String url, String method, String param) throws Exception {
		byte[] inData = sendRequestGetBytes(url, method, param);
		if (inData != null) {
			return new String(inData);
		}
		return null;
	}

	public static byte[] sendRequestGetBytes(String url, String method, String param) throws Exception {

		if (StringUtils.isBlank(url)) {
			throw new Exception("URL不能为空!");
		}

		if (StringUtils.isBlank(method)) {
			throw new Exception("URL不能为空!");
		} else {
			if (!(method.equalsIgnoreCase("get") || method.equalsIgnoreCase("post"))) {
				throw new Exception("method只能位post或get");
			}
		}

		HttpURLConnection conn = null;
		OutputStream out = null;
		InputStream in = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();

			method = method.toUpperCase();
			conn.setRequestMethod(method);
			conn.setReadTimeout(30 * 1000);
			conn.setDoInput(true);

			if ("POST".equals(method)) {

				if (StringUtils.isBlank(param)) {
					throw new Exception("请求参数param不能为空!");
				}

				conn.setDoOutput(true);
				out = conn.getOutputStream();
				byte[] outData = param.getBytes();
				out.write(outData);
			}

			in = conn.getInputStream();

			byte[] tmp = new byte[1024];

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int len = 0;
			while (-1 != (len = in.read(tmp))) {
				buffer.write(tmp, 0, len);
			}

			buffer.flush();
			byte[] inData = buffer.toByteArray();

			return inData;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
		return null;
	}
}

