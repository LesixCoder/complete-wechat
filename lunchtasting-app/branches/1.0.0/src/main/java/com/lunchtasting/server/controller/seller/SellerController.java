package com.lunchtasting.server.controller.seller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 商家登录/管理模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerBIZ sellerBIZ;

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
			Seller seller = sellerBIZ.getByAccountAndPwd(account, password);
			if (seller == null) {
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "账号或密码错误！",
						request);
			}

			/**
			 * 用户登录，修改设备号
			 */
			Map dataMap = sellerBIZ.login(seller.getId(), request);
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


}
