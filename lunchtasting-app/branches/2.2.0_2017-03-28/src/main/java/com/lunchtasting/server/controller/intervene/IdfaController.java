package com.lunchtasting.server.controller.intervene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.biz.youmi.YoumiIosBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/youmi")
public class IdfaController {
/*	@Autowired
	private UserDeviceBIZ userDeviceBIZ; */
	@Autowired
	private YoumiIosBIZ youmiIosBIZ; 
/*	@RequestMapping(value = "/flus22h")
	@ResponseBody
	public Object add222(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idfas = request.getParameter("idfa");
		String appid = request.getParameter("appid");
		if(ValidatorHelper.isEmpty(appid) || ValidatorHelper.isEmpty(idfas)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			StringBuffer resut = new StringBuffer();
			String[] shuzu = idfas.split(",");
			for (int i = 0; i < shuzu.length; i++) {
				
				if(userDeviceBIZ.verifyByIdfa(shuzu[i], appid)){
					resut.append(shuzu[i].toString()+":1,");
				}else {
					resut.append("\""+shuzu[i].toString()+"\""+":0,");
				}
			}
			String s = resut.substring(0, resut.length()-1);
			response.getWriter().write("{"+s+"}");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	*/
	@RequestMapping(value = "/flush")
	@ResponseBody
	public Object flush(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String idfa = request.getParameter("idfa");
		String appid = request.getParameter("appid");
		String url = request.getParameter("callback_url");
		if(ValidatorHelper.isEmpty(appid) || ValidatorHelper.isEmpty(idfa)||ValidatorHelper.isEmpty(url)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		if(!SysConfig.YOUMI_APP_ID.equals(appid)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,"appid和服务器不符合", request);
		}
		try {
			youmiIosBIZ.create(idfa, url);
			return  MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}

}
