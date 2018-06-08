package com.lunchtasting.server.controller.intervene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/youmi")
public class IdfaController {
	@Autowired
	private UserDeviceBIZ userDeviceBIZ; 
	@RequestMapping(value = "/flush")
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		String idfas = request.getParameter("idfa");
		String appid = request.getParameter("appid");
		if(ValidatorHelper.isEmpty(appid) || ValidatorHelper.isEmpty(idfas)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			List list = new ArrayList();
			String[] shuzu = idfas.split(",");
			for (int i = 0; i < shuzu.length; i++) {
				Map map = new HashMap();
				if(userDeviceBIZ.verifyByIdfa(shuzu[i], appid)){
					map.put(shuzu[i], 1);
				}else {
					map.put(shuzu[i], 0);
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
