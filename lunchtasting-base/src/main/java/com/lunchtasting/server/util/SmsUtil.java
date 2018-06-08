package com.lunchtasting.server.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.MessageFormat;

import com.lunchtasting.server.enumeration.StateEnum;



/**
 * 短信发送第三方
 * @author xq
 *
 */
public class SmsUtil {

	private final static String HttpURL = "http://www1.jc-chn.cn/";
	private final static String USRENAME = "clyjyj";
	private final static String PASSWORD = "kc1xyyc8";
	
	/**
	 * 加密密码
	 * @return
	 */
	private static String getMd5Password(){
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(USRENAME + md5.getMD5ofStr(PASSWORD));
	}
	
	/**
	 * 方法名称：getBalance 
	 * 功 能：获取余额 
	 * 参 数：无 
	 * 返 回 值：余额（String）
	 */
	public static String getBalance() {
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(USRENAME)
			  .append("&password=").append(getMd5Password());
		try {
			URL realUrl = new URL(HttpURL + "balanceQuery.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 方法名称：mt 
	 * 功 能：发送短信
	 * @param content 发送内容
	 * @param mobile  发送的手机号码，多个手机号为用半角 , 分开
	 * @param dstime  定时时间 ，为空时表示立即发送，格式：yyyy-MM-dd HH:mm:ss
	 * @param msgid   客户自定义消息ID
	 * @param ext	     用户自定义扩展
	 * @param msgfmt  提交消息编码格式（UTF-8/GBK）置空时默认是UTF-8
	 * 返 回 值：若用户自定义消息ID，则返回用户的ID，否则系统随机生成一个任务ID
	 * @throws UnsupportedEncodingException
	 */
	public static String mt(String content, String mobile, String dstime,
			String msgid, String ext, String msgfmt)
			throws UnsupportedEncodingException {
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(USRENAME)
				.append("&password=").append(getBalance())
				.append("&mobile=").append(mobile)
				.append("&content=").append(content)
				.append("&dstime=").append(dstime)
				.append("&ext=").append(ext)
				.append("&msgid=").append(msgid)				
				.append("&msgfmt=").append(msgfmt);
		try {
			URL realUrl = new URL(HttpURL + "smsSend.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 方法名称：mtData
	 * 功 能：发送个性短信(一个号码对应一条内容)
	 * 参数：
	 * @param content 发送内容，同号码个数一致，内容单条编码之后用英文逗号（,）隔开变成串，之后再对整个串进行二次编码，编码方式为UTF-8
	 * @param mobile  发送的号码，多个号码用英文,分隔
	 * @param dstime  定时时间
	 * @param ext	     用户自定义扩展
	 * @param msgid   用户自定义消息ID
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Boolean sendMsg(String content, String mobile, String dstime, String ext, String msgid) throws UnsupportedEncodingException{
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(USRENAME)
				.append("&password=").append(getMd5Password())
				.append("&mobile=").append(mobile)
				.append("&content=").append(content)
				.append("&dstime=").append(dstime)
				.append("&ext=").append(ext)
				.append("&msgid=").append(msgid)				
				.append("&msgfmt=").append("UTF-8");
		try {
			URL realUrl = new URL(HttpURL + "sendData.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			if(ValidatorHelper.isNotEmpty(result)){
				String strCode = result.split("\n")[0];
				long code = Long.valueOf(strCode);
				if(code > 0){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 方法名称：mtData
	 * 功 能：发送个性短信(一个号码对应一条内容)
	 * 参数：
	 * @param content 发送内容，同号码个数一致，内容单条编码之后用英文逗号（,）隔开变成串，之后再对整个串进行二次编码，编码方式为UTF-8
	 * @param mobile  发送的号码，多个号码用英文,分隔
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Boolean sendMsg(String content, String mobile){
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(USRENAME)
				.append("&password=").append(getMd5Password())
				.append("&mobile=").append(mobile)
				.append("&content=").append(content)
				.append("&msgfmt=").append("UTF-8");
		try {
			URL realUrl = new URL(HttpURL + "sendData.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			if(ValidatorHelper.isNotEmpty(result)){
				String strCode = result.split("\n")[0];
				long code = Long.valueOf(strCode);
				if(code > 0){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 方法名称：UpdatePassword 
	 * 功 能：修改密码 
	 * 参 数：newPassword(新密码) 
	 * 返 回 值：状态报告（string）
	 */
	public String UpdatePassword(String newPassword) {
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("username=").append(USRENAME)
			  .append("&password=").append(getMd5Password())
			  .append("&newpassword=").append(newPassword);
		try {
			URL realUrl = new URL(HttpURL + "passwordUpdate.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 方法名称: getDigest
	 * 功 能：数字签名（明文MD5加密）
	 * 参数：
	 * @param plaintext 明文
	 * 返回参数：MD5密文
	 */
	public String getDigest(String plaintext){
		String result = "";
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder params = new StringBuilder();
		params.append("plaintext=").append(plaintext);
		try {
			URL realUrl = new URL(HttpURL + "md5Digest.do");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(params.toString());
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	
	/**
	 * 个性短信(一个号码对应一条内容)
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String charset = "UTF-8";
		String mobile = "18518481875";
	
		String content = "【稼Perfit】本次注册验证码：123456。请勿告知他人!";
		SmsUtil.sendMsg(content, mobile);
		
//		String strCode = result.split("\n")[0];
//        long code = 0;
//        code = Long.valueOf(strCode);
//        String Info = null;
//        if (code > 0) {//成功提交
//            Info = "发送成功";
//        } else if (code == 0) {
//            Info = "发送失败";
//        } else if (code == -1) { // 用户名密码不正确
//            Info = "用户名密码不正确";
//        } else if (code == -2) { // 必填选项为空
//            Info = "必填选项为空";
//        } else if (code == -3) { // 短信内容0个字节
//            Info = "短信内容0个字节";
//        } else if (code == -4) { // 0个有效号码
//            Info = "0个有效号码";
//        } else if (code == -5) { // 余额不够
//            Info = "余额不够";
//        } else if (code == -10) { // 用户被禁用
//            Info = "用户被禁用";
//        } else if (code == -11) { // 短信内容过长
//            Info = "短信内容过长";
//        } else if(code == -12){	 //用户无扩展权限
//        	Info = "无扩展权限";
//        } else if(code == -13){  //IP地址校验错
//        	Info = "IP校验错误";
//        } else if(code == -14){  //内容解析异常
//        	Info = "内容解析异常";
//        } else {
//            Info = "未知错误";
//        }
	}
	
}
