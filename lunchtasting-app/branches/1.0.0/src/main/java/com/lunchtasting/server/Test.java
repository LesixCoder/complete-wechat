package com.lunchtasting.server;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;

public class Test {

	public static void main(String[] args) throws ParseException {
		//Test.getTime();
		//StringBuilder sb = new StringBuilder();
		//System.out.println(sb.toString());
//		String strDate = "2016-08-27 1:00:00";
//		String strDate2 = "2016-08-27 16:59:00";
//		System.out.println(CommonHelper.getActivityTime(strDate,strDate2,2));
//		System.out.println(111);
//		 Map map = null;
//		 System.out.println(map);
		String eTime = "2016-09-6 9:18:42";
		Date endTime = DateUtil.convertStringTODate(eTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		Date nowTime = new Date();
		if(nowTime.after(endTime)){
			System.out.println(1);
		}else{
			System.out.println(2);
		}

		
	}
	
	public static void getTime() throws ParseException{
		String strDate = "2016-08-26 16:59:00";
		System.out.println(DateUtil.convertDateToString(new Date(),"M.dd HH:mm"));
		System.out.println(CommonHelper.getActivityDifferDay(strDate));
		
		
	}
}
