package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/venue")
public class VenueController {
	
	@Autowired
	private SellerBIZ sellerBIZ;
	
	/**
	 * 查看场馆地图
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/map")
	public String map(Model model,HttpServletRequest request) throws Exception{
		String sId = request.getParameter("venue_id");
		if(!ValidatorHelper.isNumber(sId)){
			return "";
		}
		
		long sellerId = Long.parseLong(sId);
		Map seller = sellerBIZ.getShareDetail(sellerId);
		if(seller == null){
			return "";
		}
		model.addAttribute("seller", seller);
		return "/share/venue_map";
	}
}
