package com.lunchtasting.server.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件读取工具类
 * 
 * @author xuqian
 * 
 */
public class GetProperties {

	private static final Logger log = Logger.getLogger(GetProperties.class);

	private GetProperties() {
	}

	/**
	 * 获取邮件发送的SMTP主机
	 * 
	 * @return
	 */
	public static String getSMTP() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("邮件smtp初始化失败！");
			ex.printStackTrace();
		}
		return p.getProperty("javaMail_SMTP");

	}

	/**
	 * 获取邮件发送的端口
	 * 
	 * @return
	 */
	public static String getSmtpPort() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("jmail端口初始化失败！");
			ex.printStackTrace();
		}
		return p.getProperty("javaMail_PORT");
	}

	/**
	 * 获取发件人邮件帐号
	 * 
	 * @return
	 */
	public static String getFrom() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("jmail发件人帐号初始化失败！");
			ex.printStackTrace();
		}
		return p.getProperty("javaMail_FROM");
	}

	/**
	 * 获取发件人用户名
	 * 
	 * @return
	 */
	public static String getJMUserName() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("jmail用户名初始化失败！");
			ex.printStackTrace();
		}
		return p.getProperty("javaMail_UserName");
	}

	/**
	 * 获取发件人密码
	 * 
	 * @return
	 */
	public static String getJMUserPwd() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("jmail用户密码初始化失败！");
			ex.printStackTrace();
		}
		return p.getProperty("javaMail_UserPwd");
	}
	
	/**
	 * 获取激活主题
	 * 
	 * @return
	 */
	public static String getActiveSubject() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("获取主题！");
			ex.printStackTrace();
		}
		return p.getProperty("activeMail_Subject");
	}
	
	/**
	 * 获取活动主题
	 * 
	 * @return
	 */
	public static String getValidationSubject() {
		InputStream inputStream = GetProperties.class.getClassLoader()
				.getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (Exception ex) {
			log.error("获取主题！");
			ex.printStackTrace();
		}
		return p.getProperty("validMail_Subject");
	}


}
