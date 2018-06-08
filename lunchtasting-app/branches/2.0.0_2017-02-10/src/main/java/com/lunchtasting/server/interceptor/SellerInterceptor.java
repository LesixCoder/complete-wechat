//package com.lunchtasting.server.interceptor;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.lunchtasting.server.biz.seller.SellerDeviceBIZ;
//import com.lunchtasting.server.config.SysConfig;
//import com.lunchtasting.server.enumeration.StateEnum;
//import com.lunchtasting.server.helper.EncryptAuth;
//import com.lunchtasting.server.helper.MapResult;
//import com.lunchtasting.server.helper.RequestAuthHelper;
//import com.lunchtasting.server.helper.VariableConfig;
//import com.lunchtasting.server.po.lt.User;
//import com.lunchtasting.server.util.ValidatorHelper;
//
///**
// * 商家请求鉴权
// * @author xq
// *
// */
//public class SellerInterceptor extends HandlerInterceptorAdapter {
//	
//	private Logger logger = Logger.getLogger("sellerControllerLogger");
//
//	@Autowireda
//	private SellerDeviceBIZ sellerDeviceBIZ;
//
//	/**
//	 * 不需要鉴权请求
//	 */
//	private List<String> noAuthList = null;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//
//		request.setAttribute("startTime", System.currentTimeMillis());
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		String className = handler.getClass().getSimpleName().toString();
//		String headers = RequestAuthHelper.getHeadersJson(request);
//		String parameter = RequestAuthHelper.getParameterJson(request);
//		String requestUrl = request.getRequestURI();
//		logger.info("#" + className + "# URL=" + requestUrl + " headers = "
//				+ headers);
//		logger.info("#" + className + "# URL=" + requestUrl + " parameter = "
//				+ parameter);
//
//		noAuthList = getNoAuthList();
//		if (noAuthList.contains(requestUrl)) {
//
//			/**
//			 * 无需登录鉴权的请求
//			 */
//			boolean bol = RequestAuthHelper.checkHeaders(request);
//			if (bol == false) {
//				responsePrint(response, MapResult.build(
//						MapResult.CODE_HEADERS_ERROR, "headers参数错误!"));
//				return false;
//			}
//		} else {
//			/**
//			 * 需要登录鉴权的请求
//			 */
//			if (!ValidatorHelper.isMapNotEmpty(request.getHeader("auth_id"))) {
//				responsePrint(response, MapResult.build(
//						MapResult.CODE_PARAM_ERROR, "用户标识参数错误!"));
//				return false;
//			}
//
//			/**
//			 * 认证
//			 */
//			String accessToken = sellerDeviceBIZ.getAccessTokenByAuthId(request
//					.getHeader("auth_id").toString());
//			if (StringUtils.isEmpty(accessToken)) {
//				responsePrint(response, MapResult.build(
//						MapResult.CODE_AUTH_ERROR, "登录鉴权失败!"));
//				return false;
//			}
//
//			String authCode = RequestAuthHelper.getAuthCode(request,
//					VariableConfig.AUTH_TOKEN, accessToken);
//			if (StringUtils.isEmpty(request.getHeader("auth_code"))
//					|| !authCode.equals(request.getHeader("auth_code"))) {
//				responsePrint(response, MapResult.build(
//						MapResult.CODE_AUTH_ERROR, "登录鉴权失败!"));
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// super.afterCompletion(request, response, handler, ex);
//		String className = handler.getClass().getSimpleName().toString();
//		if (ex == null) {
//			if (ValidatorHelper.isNotEmpty(request
//					.getAttribute(VariableConfig.RETURN_LOGGER_MSG))) {
//				String logMsg = request.getAttribute(
//						VariableConfig.RETURN_LOGGER_MSG).toString();
//				logger.info("#" + className + "# URL="
//						+ request.getRequestURI() + " return = " + logMsg);
//			}
//
//		} else {
//			logger.error("#" + className + "# URL=" + request.getRequestURI()
//					+ " error = " + ex);
//		}
//		long startTime = Long.parseLong(request.getAttribute("startTime")
//				.toString());
//		logger.info("#" + className + "# URL=" + request.getRequestURI()
//				+ " executeTime = " + (System.currentTimeMillis() - startTime)
//				+ "ms");
//	}
//
//	/**
//	 * 无需登录鉴权的请求
//	 * 
//	 * @return
//	 */
//	private List getNoAuthList() {
//		noAuthList = new ArrayList<String>();
//		noAuthList.add("/seller/login");
//		return noAuthList;
//	}
//
//	/**
//	 * 返回结果
//	 * 
//	 * @param response
//	 * @param map
//	 * @throws IOException
//	 */
//	private void responsePrint(HttpServletResponse response, Map map)
//			throws IOException {
//		response.getWriter().print(JSONObject.toJSONString(map));
//	}
//}
