package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserScoreBIZ;
import com.lunchtasting.server.biz.user.UserSmsBIZ;
import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.youmi.YoumiIosBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.util.PropertiesLoader;
import com.lunchtasting.server.util.SmsUtil;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 用户登录注册模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private UserDeviceBIZ userDeviceBIZ;
	@Autowired
	private UserSmsBIZ userSmsBIZ;
	@Autowired
	private YoumiIosBIZ youmiIosBIZ;
	@Autowired 
	private UserScoreBIZ userScoreBIZ; 
	
	/**
	 * 用户登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login" , method=RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");		
		String deviceToken = request.getParameter("device_token");
		
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(password)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, "电话或密码错误！", request);
		}
		
		try {
			
			/**
			 * 判断账号密码是否正确
			 */
			User user = userBIZ.getByPhoneAndPwd(phone, password);
			if(user == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "用户名或密码错误！", request);
			}
			
			/**
			 * 用户登录，修改设备号
			 */
			Map dataMap = userBIZ.login(user.getId(),deviceToken, request);
			if(dataMap != null){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register" , method=RequestMethod.POST)
	@ResponseBody
	public Object register(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String deviceToken = request.getParameter("device_token");
		
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(password)
				|| ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			
			/**
			 * 判断手机是否已经注册
			 */
			Boolean checkPhone = userBIZ.checkRegisterPhone(phone);
			if(checkPhone){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "号码已经注册！", request);
			}
			
			/**
			 * 判断验证码是否存在或正确
			 */
			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, StateEnum.SMS_REGISTER.getValue());
			if(ValidatorHelper.isEmpty(userSms) || !code.equals(userSms.getCode())
					|| userSms.getStatus().intValue() != 0){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "验证码不存在或已经使用！", request);
			}
			
			/**
			 * 注册用户
			 */
			Map dataMap = userBIZ.registerUser(phone, password, deviceToken,userSms.getId(), request);
			if(dataMap != null){
				/**
				 * 有米ios
				 */
				if(ValidatorHelper.isNotEmpty(request.getHeader("idfa"))){
					youmiIosBIZ.succeed(request.getHeader("idfa"));
				}
				
				/**
				 * 补全线下积分
				 */
				userScoreBIZ.completion(phone, Long.parseLong(dataMap.get("user_id").toString()));
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", dataMap, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	
	/**
	 * 用户发送验证码短信
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/smsCode" ,method=RequestMethod.POST)
	@ResponseBody
	public Object smsCode(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		if(ValidatorHelper.isEmpty(phone) || !ValidatorHelper.isNumber(type)){

		}
		
		try {
			int intType = Integer.parseInt(type);
			
			/**
			 * 判断是否是手机号
			 */
			Pattern pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8]{1}\\d{9}$"); 
		    Matcher matcher = pattern.matcher(phone);
		    if(!(matcher.matches())){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "您输入的不是手机号！！", request);
		    }
			
			/**
			 * 注册短信，判断号码是否已经注册过
			 */
			if(intType == StateEnum.SMS_REGISTER.getValue().intValue()){
				Boolean checkPhone = userBIZ.checkRegisterPhone(phone);
				if(checkPhone){
					return MapResult.build(MapResult.CODE_PARAM_ERROR, "号码已经注册！", request);
				}
			}
			
			/**
			 * 创建并发送短信验证码
			 */
			Boolean result = userSmsBIZ.createUserSms(phone, intType);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 用户修改密码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePwd" ,method=RequestMethod.POST)
	@ResponseBody
	public Object changePwd(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(password) 
				||ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			
			/**
			 * 判断是否是手机号
			 */
			Pattern pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8]{1}\\d{9}$"); 
		    Matcher matcher = pattern.matcher(phone);
		    if(!(matcher.matches())){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "您输入的不是手机号！！", request);
		    }
		    
		    /**
		     * 判断手机号是否注册
		     */
		    Long userId = userBIZ.getUserIdByPhone(phone);
		    if(ValidatorHelper.isEmpty(userId)){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "手机号尚未注册！", request);
		    }
		    
		    
		    /**
			 * 判断验证码是否存在或正确
			 */
			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, StateEnum.SMS_GET_PASSWORD.getValue());
			if(ValidatorHelper.isEmpty(userSms) || !code.equals(userSms.getCode())
					|| userSms.getStatus().intValue() != 0){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "验证码不存在或已经使用！", request);
			}
			
			
			/**
			 * 修改密码
			 */
			Boolean result = userBIZ.updatePwd(userId, password, userSms.getId());
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 用户初始化信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	@ResponseBody
	public Object init(HttpServletRequest request) throws Exception {
		String deviceToken = request.getParameter("device_token");
		
		try {
			long userId = EncryptAuth.getUserId(request);
			Map userMap = userBIZ.getUserInit(userId);
			if(userMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 修改友盟推送
			 */
			if(!ValidatorHelper.isMapNotEmpty(userMap.get("device_token")) 
					&& ValidatorHelper.isNotEmpty(deviceToken)){
				userDeviceBIZ.updateDeviceToken(userId, deviceToken);
			}
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("user", userMap);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
