package com.lunchtasting.server;

import java.text.ParseException;

import com.lunchtasting.server.util.DateUtil;

public class Test {

	public static void main(String[] args) throws ParseException {
		String time = "2017-1-1";
		System.out.println(DateUtil.convertStringTODate(time, "yyyy-MM-dd"));
	}
}
