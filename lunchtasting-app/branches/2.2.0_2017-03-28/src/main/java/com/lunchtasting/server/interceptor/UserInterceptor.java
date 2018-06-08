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
import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 普通用户请求鉴权
 * @author xq
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger("outputControllerLogger");

	@Autowired
	private UserDeviceBIZ userDeviceBIZ;
	
	//不需要拦截请求
	private List<String> noInterceptorList = new ArrayList<String>();
	//不需要鉴权请求
	private List<String> noAuthList = new ArrayList<String>();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setAttribute("startTime", System.currentTimeMillis());
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String className = handler.getClass().getSimpleName().toString();
		String headers = RequestAuthHelper.getHeadersJson(request);
		String parameter = RequestAuthHelper.getParameterJson(request);
		String requestUrl = request.getRequestURI();
		requestUrl = requestUrl.replace("//", "/");
		
		/**
		 * 无需拦截请求
		 */
		noInterceptorList = getNoInterceptorList();
		if (noInterceptorList.contains(requestUrl)) {  
			return true;
		}
		
		logger.info("#" + className + "# URL=" + requestUrl + " headers = "
				+ headers);
		logger.info("#" + className + "# URL=" + requestUrl + " parameter = "
				+ parameter);
		
		noAuthList = getNoAuthList();
		if (noAuthList.contains(requestUrl)) { // 无需登录鉴权的请求
			boolean bol = RequestAuthHelper.checkHeaders(request);
			if (bol == false) {
				
				String result = MapResult.build(MapResult.CODE_HEADERS_ERROR, "headers参数错误!");
				logger.info("#" + className + "# URL="+ request.getRequestURI() + " return = " + result);
				responsePrint(response, result);
				return false;
			}
		} else { //需要登录鉴权的请求
			
			if (ValidatorHelper.isEmpty(request.getHeader("authId"))) {
				String result = MapResult.build(MapResult.CODE_AGAIN_LOGIN, "重新登录!");
				logger.info("#" + className + "# URL="+ request.getRequestURI() + " return = " + result);
				responsePrint(response,result);
				return false;
			}

			/**
			 * 认证
			 */
			String accessToken = userDeviceBIZ.getAccessTokenByAuthId(request
					.getHeader("authId").toString());
			if (StringUtils.isEmpty(accessToken)) {
				
				String result = MapResult.build(MapResult.CODE_AUTH_ERROR, "登录鉴权失败!");
				logger.info("#" + className + "# URL="+ request.getRequestURI() + " return = " + result);
				responsePrint(response,result);
				return false;
			}

			String authCode = RequestAuthHelper.getAuthCode(request,
					VariableConfig.AUTH_TOKEN, accessToken);
			if (StringUtils.isEmpty(request.getHeader("authCode"))
					|| !authCode.equals(request.getHeader("authCode"))) {
				
				String result = MapResult.build(MapResult.CODE_AUTH_ERROR, "登录鉴权失败!");
				logger.info("#" + className + "# URL="+ request.getRequestURI() + " return = " + result);
				responsePrint(response,result);
				return false;
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//super.afterCompletion(request, response, handler, ex);
		String className = handler.getClass().getSimpleName().toString();
		String requestUrl = request.getRequestURI();
		requestUrl = requestUrl.replace("//", "/");
		
		/**
		 * 无需拦截请求
		 */
		if (noInterceptorList.contains(requestUrl)) {  
			return;
		}
		
		/**
		 * 输出参数
		 */
		if (ValidatorHelper.isNotEmpty(request
				.getAttribute(VariableConfig.RETURN_LOGGER_MSG))) {
			String logMsg = request.getAttribute(
					VariableConfig.RETURN_LOGGER_MSG).toString();
			logger.info("#" + className + "# URL="
					+ requestUrl + " return = " + logMsg);
		}
		
		/**
		 * 请求耗时
		 */
		long startTime = Long.parseLong(request.getAttribute("startTime")
				.toString());
		logger.info("#" + className + "# URL=" + requestUrl
				+ " executeTime = " + (System.currentTimeMillis() - startTime)
				+ "ms");
	}
	
	/**
	 * 无需拦截请求
	 * @return
	 */
	private List getNoInterceptorList(){
		if(noInterceptorList.size() == 0){
			//支付
			noInterceptorList.add("/alipay/course/notify");
			noInterceptorList.add("/wxpay/course/notify");
			
			//定时器
			noInterceptorList.add("/timer/course/refund/query");
			//有米
			noInterceptorList.add("/youmi/flush");
		}
		return noInterceptorList;
	}
	

	/**
	 * 无需登录鉴权的请求
	 * @return
	 */
	private List getNoAuthList() {
		if(noAuthList.size() == 0){
			//登录注册
			noAuthList.add("/user/login");
			noAuthList.add("/user/register");
			noAuthList.add("/user/smsCode");
			noAuthList.add("/user/changePwd");
			noAuthList.add("/weChat/login");
			
			//首页
			noAuthList.add("/index");
			noAuthList.add("/index_v2_0");
			noAuthList.add("/index_v2_1_0");
			noAuthList.add("/index_v2_2_0");
			//文章
			noAuthList.add("/article/list");
			noAuthList.add("/article/list_v2_0");
			noAuthList.add("/article/detail");
			noAuthList.add("/develop/list");
			
			//活动
			noAuthList.add("/activity/list");
			noAuthList.add("/activity/detail");
			
			//帖子
			noAuthList.add("/note/list");
			noAuthList.add("/note/detail");
			
			
			//赛事
			noAuthList.add("/match/category/list");
			noAuthList.add("/match/list");
			noAuthList.add("/match/detail");
			
			//用户中心
			noAuthList.add("/user/another");

			
//			//场馆
//			noAuthList.add("/venue/list");
//			noAuthList.add("/venue/detail");
//			noAuthList.add("/venue/area/list");
//			noAuthList.add("/venue/comment/list");
//			
//			
//			//课程
//			noAuthList.add("/course/sort/list");
//			noAuthList.add("/course/list");
//			noAuthList.add("/course/detail");
			
		}
		return noAuthList;
	}

	/**
	 * 返回结果
	 * 
	 * @param response
	 * @param map
	 * @throws IOException
	 */
	private void responsePrint(HttpServletResponse response, String result)
			throws IOException {
		response.getWriter().print(result);
	}
}
