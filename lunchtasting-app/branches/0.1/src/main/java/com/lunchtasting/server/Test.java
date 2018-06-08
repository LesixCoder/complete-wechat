package com.lunchtasting.server;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.util.StringEncrypt;

public class Test {

	public static void main(String[] args) {
		Object c = new RequestAuthHelper();
		System.out.println(c.getClass().toString());
		System.out.println(c.getClass().getName());
		

	}
}
