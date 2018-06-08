package com.perfit.server.helper;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/**
 * 公共帮助类
 * 
 * @author 
 * 
 */
public class CommonHelper {
	
	
	public static String gotoWebRoot(HttpServletRequest request) {
		return "http://" + request.getServerName() + request.getContextPath() + "/";
	}
}
