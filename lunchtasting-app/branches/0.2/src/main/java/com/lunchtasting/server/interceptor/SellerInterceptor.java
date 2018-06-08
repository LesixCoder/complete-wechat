package com.lunchtasting.server.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 商家请求鉴权
 * @author xq
 *
 */
public class SellerInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserBIZ userBIZ;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		/**
		 * 判断当前用户是否是商家用户
		 */
		String authId = request.getHeader("auth_id").toString();
		Long userId = EncryptAuth.getUserId(authId);
		if(ValidatorHelper.isEmpty(userId)){
			responsePrint(response, MapResult.build(
					MapResult.CODE_PARAM_ERROR, "用户授权id错误!"));
			return false;
		}
		
		User user = userBIZ.getById(userId);
		if(null == user || user.getIdentity().intValue() != StateEnum.USER_IDENTITY_SALLER.getValue().intValue()){
			responsePrint(response, MapResult.build(
					MapResult.CODE_PARAM_ERROR, "用户无权限访问当前请求!"));
			return false;
		}
		
		return true;
	}



	/**
	 * 返回结果
	 * 
	 * @param response
	 * @param map
	 * @throws IOException
	 */
	private void responsePrint(HttpServletResponse response, Map map)
			throws IOException {
		response.getWriter().print(JSONObject.toJSONString(map));
	}
}
