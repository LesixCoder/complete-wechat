package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 商户登录注册模块
 * 
 * @author xq
 * 
 */
@Controller
@RequestMapping("/seller")
public class SellerLoginController {

	@Autowired
	private UserBIZ userBIZ;

	/**
	 * 商家登录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		if (ValidatorHelper.isEmpty(account)
				|| ValidatorHelper.isEmpty(password)) {
			return MapResult.build(MapResult.CODE_PARAM_ERROR, "电话或密码错误！",
					request);
		}

		try {

			/**
			 * 判断账号密码是否正确
			 */
			User user = userBIZ.getByAccountAndPwd(account, password);
			if (user == null) {
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "账号或密码错误！",
						request);
			}

			/**
			 * 用户登录，修改设备号
			 */
			Map dataMap = userBIZ.loginSeller(user.getId(), request);
			if (dataMap != null) {
				return MapResult.build(MapResult.CODE_SUCCESS,
						MapResult.MESSAGE_SUCCESS, dataMap, request);
			}

		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR,
					MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE,
				MapResult.MESSAGE_FAILURE, request);
	}

	// /**
	// * 商家注册
	// * @param request
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/register")
	// @ResponseBody
	// public Object register(HttpServletRequest request) throws Exception {
	// return null;
	// }

}
