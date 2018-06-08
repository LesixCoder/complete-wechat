package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 运营模块
 * Created on 2016-10-9
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/business")
public class BusinessController {
	@Autowired
	private NoteBIZ noteBIZ;

	@RequestMapping(value = "/invite")
	public String activity(Model model) throws Exception{
		return "/business/invite";
	}
	
	@RequestMapping(value = "/channel/register")
	public String channelRegister(HttpServletRequest request) throws Exception{
		String channel = request.getParameter("channel");
		if(!ValidatorHelper.isNumber(channel)){
			return "/business/channel_register";
		}
		try{
			noteBIZ.addRunInfo(Long.parseLong(channel));
		}catch (Exception e) {
			e.printStackTrace();
			return "/business/channel_register";
		}
		return "/business/channel_register";
	}
}
