package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.receiverAddress.ReceiverAddressBIZ;
import com.lunchtasting.server.helper.MapResult;

@Controller
public class ReceiverAddressController {
	@Autowired
	private ReceiverAddressBIZ addressBIZ;
	
	@RequestMapping(value = "/queryReceiverAddress"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public Object queryReceiverAddress(HttpServletRequest request,HttpServletResponse response){
		Map map = new HashedMap();
		HashMap requestMap =  new  HashMap();
		try {
			requestMap.put("userId",Integer.parseInt(request.getParameter("userId")));
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, "参数存在问题", null, request);
		}
		
		requestMap.put("addrId",request.getParameter("addrId"));
		requestMap.put("areaId",request.getParameter("areaId"));
		try {
			map.put("receiverAddressList",addressBIZ.queryReceiverAddress(requestMap));
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！",map, request);
		} catch (Exception e) {
			// TODO: handle exception
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
