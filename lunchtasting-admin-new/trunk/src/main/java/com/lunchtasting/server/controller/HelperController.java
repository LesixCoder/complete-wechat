package com.lunchtasting.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelperController {

	
	@RequestMapping(value = "/")
	public String index(){
		return "/index";
	}
	
	@RequestMapping(value = "/login")
	public String login(){
		return "/login";
	}
	
	@RequestMapping(value = "/top")
	public String top(){
		return "/common/top";
	}
	
	@RequestMapping(value = "/bar")
	public String bar(){
		return "/common/bar";
	}
	
	@RequestMapping(value = "/main")
	public String main(){
		return "/common/main";
	}
	
	@RequestMapping(value = "/menu")
	public String menu(){
		return "/common/menu";
	}
	
	@RequestMapping(value = "/testImage")
	public String testImage(){
		return "/image";
	}
}
