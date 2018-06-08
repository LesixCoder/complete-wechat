package com.lunchtasting.server.controller.pay;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xmlpull.v1.XmlPullParserException;

import com.alibaba.fastjson.JSON;
import com.lunchtasting.server.biz.temporaryEnroll.TempooraryInvitationCodeBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryEnrollBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryOrdersReturnBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.helper.KeyStaiticCommonHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.helper.WebchatHelper;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/wxpay")
public class WechatPayController {
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TENPAY);
	@Autowired
	private TemporaryOrdersReturnBIZ temporaryOrdersReturnBiz;
	@Autowired
	private TempooraryInvitationCodeBIZ codeBIZ;
	@Autowired
	private TemporaryEnrollBIZ temporaryEnrollBIZ;
	@RequestMapping(value = "/session")
	public void sesees(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
	}
	@RequestMapping(value = "/temporaryEnroll/notify")
	public void temporaryEnrollNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			//获取xml
			String strxml = WeChatHelper.getResponseXmlString(request, response);
			//xml解析到map
			Map map = WeChatHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			/**
			 * 判断是否成功
			 */
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				//失败
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			/**
			 * 判断签名是否正确
			 */
			if(!WeChatHelper.createSign(parameters,KeyStaiticCommonHelper.APP_KEY).equals(map.get("sign").toString())){
				//失败
				response.getWriter().write(WeChatHelper.printXML("FAIL", "")); 
				return;
			}
			System.out.println("++++++++++++++++++++支付回调接口+++++++++++++++++++");
			/**
			 * 成功 处理微信 多次发送的逻辑
			 */
			String out_trade_no = map.get("out_trade_no").toString();
			if(temporaryOrdersReturnBiz.verifyOrder(out_trade_no)){
				/**
				 * 得到信息
				 */
				Map attachMap = (Map)JSON.parseObject(map.get("attach").toString());
				/**
				 * 回调操作
				 */
				System.out.println("++++++++++++++++++++成功++++++++++++++++++++"+map.get("attach").toString());
				if(temporaryOrdersReturnBiz.insertOrder(map) &&temporaryEnrollBIZ.payUpdate(Long.parseLong(attachMap.get("userId").toString()), attachMap)){
					if(ValidatorHelper.isNumber((String)attachMap.get("code"))){
						if(!codeBIZ.employCode(attachMap.get("code").toString(), Long.parseLong(attachMap.get("userId").toString())) ){
							System.out.println("邀请码失败=========="+attachMap.get("userId").toString());
						}
					}
					response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));  
					return;
				}
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
			}else{
				System.out.println("+++++++++++++++++++微信多发了一次成功++++++++++++++++++++");
				response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		}
	}
	/*=======支付=======*/
	
	/**
	 * 获取OPENID 不提醒授权
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/temporaryEnroll/sign")
	public void orderSign(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String code;
		String openid;
		/**
		 * 得到code
		 */
		if(!"".equals(request.getParameter("code")) && request.getParameter("code") != null){
			code = request.getParameter("code");
		}else{
			return ;
		}

		try {
			/**
			 * 得到openid
			 */
			openid=WeChatHelper.getOpenID(code);
			if(openid==null){
				return ;
			}
			//需要修改。
			request.getSession().setAttribute("openid",openid);
			response.sendRedirect("/wxpay/temporaryEnroll/pay");
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}
	
	/**
	 * 支付
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/temporaryEnroll/pay")
	public String zhifu(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String openid;
		try {
			/**
			 * 判断登录状态
			 */
			TemporaryUserWeChat user = (TemporaryUserWeChat)request.getSession().getAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER);
			if(user==null){
/*				String oldurl = "/wxpay/temporaryEnroll/pay";
				if(request.getAttribute("orderId")!=null && !request.getAttribute("orderId").equals("")){
					oldurl+="?orderId="+request.getAttribute("orderId");
				}
				request.getSession().setAttribute("oldurl", oldurl);*/
				request.getSession().removeAttribute("oldurl");
				response.sendRedirect("/wechat/wechatgetCode");
				return null;
			}
			
			/**
			 * 得到openid
			 */
			openid=user.getOpenId();
		
			//订单号
			String orderNo = WeChatHelper.getOrderNo();
			Long userId = user.getId();
			if(!temporaryOrdersReturnBiz.updateUserOrder(orderNo, userId)){
				System.out.println("修改订单失败。");
				return "temporaryEnroll/index";
			}
			//活动名
			String body = KeyStaiticCommonHelper.TEMPORARY_BODY;
			//key
			String key = KeyStaiticCommonHelper.APP_KEY;
			String nonceStr = WeChatHelper.getNonceStr();
			
			String othId =  request.getParameter("otherId");
			String sex =  request.getParameter("sex");
			String name =  request.getParameter("name");
			String tel =  request.getParameter("tel");
			String age =  request.getParameter("age");
			String type =  request.getParameter("type");
			String code =  request.getParameter("code");
			System.out.println(code);
			//价格
			int price = (int)(KeyStaiticCommonHelper.PRICE*100);
			if(ValidatorHelper.isNumber(type) && Integer.parseInt(type)==521){
				if(temporaryOrdersReturnBiz.checkButton(20, 6000)){
					price = 6000;
				}
			}
			Map map = new HashMap();
			if(ValidatorHelper.isNumber(code) && codeBIZ.verdictCode(code)){
				map.put("code", code);
				price = 100;
			}
			if(ValidatorHelper.isNumber(othId)){
				/***
				 * 判断性别
				 */
				if(!temporaryEnrollBIZ.verifySex(Integer.parseInt(sex), Long.parseLong(othId))){
					map.put("otherId", othId);
				}
			}
			map.put("userId", user.getId());
			map.put("sex", sex);
			map.put("name", name);
			map.put("tel", tel);
			String attach =JSONObject.toJSONString(map);
			/**
			 * 支付签名,预支付订单
			 */
			SortedMap params = WeChatHelper.getSignMap(openid,orderNo,price,body,nonceStr,attach,request);
			String sign = WeChatHelper.createSign(params,key);
			params.put("sign", sign);
			/**
			 * 生成预支付订单
			 */
			String prepayId = WeChatHelper.createPackage(params);
			System.out.println("prepayId============"+prepayId);
			/**
			 * 预支付订单获取成功
			 */
			if(ValidatorHelper.isNotEmpty(prepayId)){
				SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
				String paySign = WeChatHelper.createSign(paySignMap,key);
				
				model.addAttribute("appId", paySignMap.get("appId"));
				model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
				model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
				model.addAttribute("package", paySignMap.get("package"));
				model.addAttribute("paySign", paySign);
				model.addAttribute("orderId", user.getId());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "temporaryEnroll/index";
		}
		return "pay";
	}
	
	/**
	 * 获得微信code (不授权支付)
	 * scope=snsapi_base
	 * @throws IOException 
	 */
	@RequestMapping(value = "/activity/getCode")
	public String getCode(HttpServletResponse response) throws IOException {
		
		String appId = KeyStaiticCommonHelper.APP_ID;
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?";
		url = url + "appid="+appId+"";  http://test-wap.mperfit.com/wxpay/temporaryEnroll/sign         
		url = url + "&redirect_uri=" + URLEncoder.encode(KeyStaiticCommonHelper.WEBCHAT_LOGIN_URL);
		url = url + "&response_type=code";
		url = url + "&scope=snsapi_base";
		url = url + "&state=STATE#wechat_redirect";
		response.sendRedirect(url);
		return null;
	}
	

	
	/**
	 * 获得统一下单签名
	 * @param prepayId
	 * @param nonceStr
	 * @return
	 */
	private SortedMap getPaySignMap(String prepayId,String nonceStr){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appId", KeyStaiticCommonHelper.APP_ID);
		parameters.put("timeStamp", System.currentTimeMillis() / 1000);
		parameters.put("nonceStr", nonceStr);
		parameters.put("package", "prepay_id="+prepayId);
		parameters.put("signType", "MD5");
		return parameters;
	}
}
