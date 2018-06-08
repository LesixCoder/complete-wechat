package com.lunchtasting.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/explain")
public class ExplainController {
	
	@RequestMapping(value = "/saishi")
	public String saishi(){
		return "explain/match";
	}
	
	@RequestMapping(value = "/jifeng")
	public String jifeng(){
		return "explain/integral";
	}
	
	@RequestMapping(value = "/huoyuedu")
	public String indexGw(){
		return "explain/netlist";
	}
	
}
