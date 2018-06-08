package com.lunchtasting.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ResourceUtils;

import com.google.common.io.Files;

/**
 * <b><code>KeywordsFilterUtil</code></b>
 * <p>
 * 关键字 工具类
 * </p>
 * <b>Creation Time:</b> Dec 22, 2011 4:19:22 PM
 * 
 * @author Kenny Qi
 * @version $Revision$ $Date$
 * @since Guang 0.0.1
 */
public class KeywordsFilterUtil {
	
	public static String path = "classpath:files/keywords.txt";
	
	public static List<String> keywordList =importKeywords(path);

	public static List<String> importKeywords(String path) {
		
		try {
			keywordList = Files.readLines(ResourceUtils.getFile(path),Charset.forName("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return keywordList;
	}

	/**
	 * 检查是否包含关键字
	 * 
	 * @return
	 * @since Guang 0.0.1
	 */
	public static boolean containKeyword(String word) {
		importKeywords("classpath:files/keywords.txt");
		if(StringUtils.isBlank(word)) return false;
		return keywordList.contains(word.trim());
	}
	
	/**
	 * 含有关键字的字符 转化成*
	 * @param args
	 */
	public static String replaceWord(String word){
		importKeywords("classpath:files/keywords.txt");
		if(StringUtils.isBlank(word)) return null;
		StringBuffer sb = new StringBuffer();
		String str = null;
		for(int i = 0; i<word.length();i++){
			str = word.substring(i,i+1);
			if(keywordList.contains(str)){
				sb.append("*");
			}else{
				sb.append(str);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(containKeyword("温家宝"));
		System.out.println(replaceWord("温家宝"));
	}
}
