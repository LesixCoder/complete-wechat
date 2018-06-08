package com.lunchtasting.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.MatchAlbumTag;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.SmsUtil;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

import javax.imageio.ImageIO;

public class Test {
	
	public static void main(String[] args){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = df.format(new Date()) + "" + Utils.getRandomNumber(2);
		
		System.out.println(s.length());
		
	}
}
	
	
