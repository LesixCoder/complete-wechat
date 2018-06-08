package com.lunchtasting.server.controller;

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

import com.lunchtasting.server.biz.user.UserSmsBIZ;
import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.biz.user.UsersInfoBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.po.lt.UsersInfo;
import com.lunchtasting.server.util.PropertiesLoader;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 用户模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UsersInfoBIZ usersInfoBIZ;
	@Autowired
	private UserDeviceBIZ userDeviceBIZ;
	@Autowired
	private UserSmsBIZ userSmsBIZ;
	
	/**
	 * 用户登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");		
		
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(password)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, "电话或密码错误！", null, request);
		}
		
		try {
			
			/**
			 * 判断账号密码是否正确
			 */
			UsersInfo user = usersInfoBIZ.getByPhoneAndPwd(phone, password);
			if(user == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "用户名或密码错误！", null, request);
			}
			
			/**
			 * 用户登录，修改设备号
			 */
			Map dataMap = usersInfoBIZ.login(user.getId(), request);
			if(dataMap != null){
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", dataMap, request);
			}
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, null, request);
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public Object register(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(password)
				|| ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, null, request);
		}
		
		try {
			
			/**
			 * 判断手机是否已经注册
			 */
			Boolean checkPhone = usersInfoBIZ.checkRegisterPhone(phone);
			if(checkPhone){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "号码已经注册！", null, request);
			}
			
			/**
			 * 判断验证码是否存在或正确
			 */
			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, StateEnum.SMS_REGISTER.getValue());
			if(ValidatorHelper.isEmpty(userSms) || !code.equals(userSms.getCode())
					|| userSms.getStatus().intValue() != 0){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "验证码不存在或已经使用！", null, request);
			}
			
			/**
			 * 注册用户
			 */
			Map dataMap = usersInfoBIZ.registerUser(phone, password, userSms.getId(), request);
			if(dataMap != null){
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", dataMap, request);
			}
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, null, request);
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
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, null, request);
		}
		
		try {
			int intType = Integer.parseInt(type);
			
			/**
			 * 判断是否是手机号
			 */
			Pattern pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8]{1}\\d{9}$"); 
		    Matcher matcher = pattern.matcher(phone);
		    if(!(matcher.matches())){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "您输入的不是手机号！！", null, request);
		    }
			
			/**
			 * 注册短信，判断号码是否已经注册过
			 */
			if(intType == StateEnum.SMS_REGISTER.getValue().intValue()){
				Boolean checkPhone = usersInfoBIZ.checkRegisterPhone(phone);
				if(checkPhone){
					return MapResult.build(MapResult.CODE_PARAM_ERROR, "号码已经注册！", null, request);
				}
			}
			
			/**
			 * 创建并发送短信验证码
			 */
			Boolean result = userSmsBIZ.createUserSms(phone, intType);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", null, request);
			}
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, null, request);
		
	}
	
	
}
