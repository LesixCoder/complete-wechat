package com.lunchtasting.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共controller
 * Created on 2016-9-8
 * @author xuqian
 *
 */
@Controller
public class CommonController {

/*	@RequestMapping(value = "/pcIndex")
	public String pcIndex(){
		return "index_gw";
	}
	*/
	@RequestMapping(value = "/")
	public String indexGw(){
		return "index";
	}
	
	@RequestMapping(value = "/joinus")
	public String joinus(){
		return "joinus";
	}
	
	
	@RequestMapping(value = "/test")
	public String test(){
		return "index_gw";
	}
	
	@RequestMapping(value = "/about/us")
	public String us(){
		return "wechat/about";
	}
}
