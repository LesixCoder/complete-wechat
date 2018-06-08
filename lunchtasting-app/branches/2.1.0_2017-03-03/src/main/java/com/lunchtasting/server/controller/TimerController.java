//package com.lunchtasting.server.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.lunchtasting.server.biz.common.TimerBIZ;
//import com.lunchtasting.server.biz.user.UserBIZ;
//import com.lunchtasting.server.config.SysConfig;
//
//@Controller
//@RequestMapping("/timer")
//public class TimerController {
//	
//	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TOOL);
//
//	@Autowired
//	private TimerBIZ timerBIZ;
//	
//	/**
//	 *  课程退款订单定时查询
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/course/refund/query")
//	public void courseRefundQuery(HttpServletRequest request) throws Exception {
//		logger.info("#TimerController# courseRefundQuery begin!");
//		timerBIZ.doCourseRefundQuery();
//		logger.info("#TimerController# courseRefundQuery end!");
//
//	}
//}
