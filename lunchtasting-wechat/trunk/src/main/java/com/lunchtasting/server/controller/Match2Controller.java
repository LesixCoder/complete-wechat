package com.lunchtasting.server.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.biz.match.MatchGoodsBIZ;
import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.biz.match.MatchTicketBIZ;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.po.lt.MatchTicket;
import com.lunchtasting.server.util.ValidatorHelper;


@Controller
@RequestMapping("/match")
public class Match2Controller {
	
	@Autowired
	private MatchBIZ matchBIZ;
	@Autowired
	private MatchTicketBIZ matchTicketBIZ;
	@Autowired
	private MatchGoodsBIZ matchGoodsBIZ;
	@Autowired
	private MatchOrderBIZ orderBIZ;
	
	/**
	 * 赛事报名列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order/list")
	public String orderList(Model model, HttpServletRequest request, HttpServletResponse response){
		return "/match2/match_list";
	}
	
	/**
	 * 赛事入口
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enter")
	public String enter(Model model,HttpServletRequest request){
		return "/match2/match_enter";
	}
	
	/**
	 * 大众入口报名页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enter/dz")
	public String enterDz(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		if(!ValidatorHelper.isNumber(mId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);

		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("match", match);
		
		List ticketList = matchTicketBIZ.queryMatchTicketList(matchId, 1);
		model.addAttribute("ticketList", ticketList);
		
		return "/match2/match_enter_dz";
	}
	
	/**
	 * 专业入口报名页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enter/zy")
	public String enterZy(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		if(!ValidatorHelper.isNumber(mId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		

		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("match", match);
		
		List ticketList = matchTicketBIZ.queryMatchTicketList(matchId, 2);
		model.addAttribute("ticketList", ticketList);
		return "/match2/match_enter_zy";
	}
	

	
	/**
	 * 票务选择
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ticket/meal")
	public String ticketMeal(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		String tId = request.getParameter("ticket_id");
		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(tId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		long ticketId = Long.parseLong(tId);
		
		MatchTicket ticket = matchTicketBIZ.getById(ticketId);
		if(ticket == null){
			return VariableHelper.ERROR_JSP;
		}
		double price = ticket.getPrice();
		if(ValidatorHelper.isNotEmpty(ticket.getEarlyBirdPrice())){
			ticket.getEarlyBirdTime().after(new Date());
			price = ticket.getEarlyBirdPrice();
		}
		model.addAttribute("ticket", ticket);
		model.addAttribute("price", price);
		model.addAttribute("matchId", matchId);
		
		/**
		 * 票商品列表
		 */
		List goodsList = matchGoodsBIZ.queryMatchTicketGoodsList(ticketId);
		model.addAttribute("goodsList", goodsList);
		
		return "/match2/match_ticket_meal";
	}
	
	/**
	 * 大众票务信息确认
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup/dz")
	public String signupDz(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		String tId = request.getParameter("ticket_id");
		String goodsStr = request.getParameter("goods_str");		
		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(tId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		long ticketId = Long.parseLong(tId);
		
		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		
		MatchTicket ticket = matchTicketBIZ.getById(ticketId);
		if(ticket == null){
			return VariableHelper.ERROR_JSP;
		}
		
		if(match.getId().longValue() != ticket.getMatchId().longValue()){
			return VariableHelper.ERROR_JSP;
		}
		double ticketPrice = ticket.getPrice();

		/**
		 * 判断是否早鸟票价格
		 */
		if(ValidatorHelper.isNotEmpty(ticket.getEarlyBirdPrice())){
			ticket.getEarlyBirdTime().after(new Date());
			ticketPrice = ticket.getEarlyBirdPrice();
		}
		
		/**
		 * 套票商品列表
		 */
		double goodsPrice = 0;
		if(ValidatorHelper.isNotEmpty(goodsStr)){
			Map goodsMap = matchGoodsBIZ.getUserSelectGoods(ticketId, goodsStr);
			if(goodsMap != null){
				model.addAttribute("goodsList", goodsMap.get("goodsList"));
				goodsPrice = Double.parseDouble(goodsMap.get("price").toString());
			}
		}
		
		model.addAttribute("price", CommonHelper.getDobule(ticketPrice+goodsPrice));
		model.addAttribute("ticketPrice", CommonHelper.getDobule(ticketPrice));
		model.addAttribute("goodsPrice", CommonHelper.getDobule(goodsPrice));
		model.addAttribute("matchId", matchId);
		model.addAttribute("ticket", ticket);
		model.addAttribute("goodsStr", goodsStr);
		return "/match2/match_signup_dz";
	}
	

	/**
	 * 亲子票务信息确认
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup/qz")
	public String signupQz(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		String tId = request.getParameter("ticket_id");
		String goodsStr = request.getParameter("goods_str");		
		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(tId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		long ticketId = Long.parseLong(tId);
		
		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		
		MatchTicket ticket = matchTicketBIZ.getById(ticketId);
		if(ticket == null){
			return VariableHelper.ERROR_JSP;
		}
		
		if(match.getId().longValue() != ticket.getMatchId().longValue()){
			return VariableHelper.ERROR_JSP;
		}
		double ticketPrice = ticket.getPrice();

		/**
		 * 判断是否早鸟票价格
		 */
		if(ValidatorHelper.isNotEmpty(ticket.getEarlyBirdPrice())){
			ticket.getEarlyBirdTime().after(new Date());
			ticketPrice = ticket.getEarlyBirdPrice();
		}
		
		/**
		 * 套票商品列表
		 */
		double goodsPrice = 0;
		if(ValidatorHelper.isNotEmpty(goodsStr)){
			Map goodsMap = matchGoodsBIZ.getUserSelectGoods(ticketId, goodsStr);
			if(goodsMap != null){
				model.addAttribute("goodsList", goodsMap.get("goodsList"));
				goodsPrice = Double.parseDouble(goodsMap.get("price").toString());
			}
		}
		
		model.addAttribute("price", CommonHelper.getDobule(ticketPrice+goodsPrice));
		model.addAttribute("ticketPrice", CommonHelper.getDobule(ticketPrice));
		model.addAttribute("goodsPrice", CommonHelper.getDobule(goodsPrice));
		model.addAttribute("matchId", matchId);
		model.addAttribute("ticket", ticket);
		model.addAttribute("goodsStr", goodsStr);
		return "/match2/match_signup_qz";
	}
	
	/**
	 * 团体票务信息确认
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup/tt")
	public String signupTt(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		String tId = request.getParameter("ticket_id");
		String number = request.getParameter("number");
		String goodsStr = request.getParameter("goods_str");		
		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(tId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		long ticketId = Long.parseLong(tId);
		
		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		
		MatchTicket ticket = matchTicketBIZ.getById(ticketId);
		if(ticket == null){
			return VariableHelper.ERROR_JSP;
		}
		
		if(match.getId().longValue() != ticket.getMatchId().longValue()){
			return VariableHelper.ERROR_JSP;
		}
		
		/**
		 * 数量
		 */
		int ticketNumber = 1;
		if(ValidatorHelper.isNumber(number)){
			ticketNumber = Integer.parseInt(number);
		}
		
		/**
		 * 判断是否团体打折
		 * 大于5人小于9人
		 * 大于9人
		 */
		double ticketPrice = ticket.getPrice();
		ticketPrice = ticketPrice * ticketNumber;
		if(ticketNumber > 5 && ticketNumber < 9){
			ticketPrice = ticketPrice * 0.8;
		}
		if(ticketNumber > 9){
			ticketPrice = ticketPrice * 0.7;
		}
		
		
		/**
		 * 套票商品列表
		 */
		double goodsPrice = 0;
		if(ValidatorHelper.isNotEmpty(goodsStr)){
			Map goodsMap = matchGoodsBIZ.getUserSelectGoods(ticketId, goodsStr);
			if(goodsMap != null){
				model.addAttribute("goodsList", goodsMap.get("goodsList"));
				goodsPrice = Double.parseDouble(goodsMap.get("price").toString());
			}
		}
		
		model.addAttribute("price", CommonHelper.getDobule(ticketPrice+goodsPrice));
		model.addAttribute("ticketPrice", CommonHelper.getDobule(ticketPrice));
		model.addAttribute("goodsPrice", CommonHelper.getDobule(goodsPrice));
		model.addAttribute("matchId", matchId);
		model.addAttribute("ticket", ticket);
		model.addAttribute("ticketNumber", ticketNumber);
		model.addAttribute("goodsStr", goodsStr);
		return "/match2/match_signup_tt";
	}
	
	/**
	 * 用户报名订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup/order")
	public String signupOrder(Model model,HttpServletRequest request){
		String mId = request.getParameter("match_id");
		if(!ValidatorHelper.isNumber(mId)){
			return VariableHelper.ERROR_JSP;
		}
		long matchId = Long.parseLong(mId);
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);

		Match match = matchBIZ.getById(matchId);
		if(match == null){
			return VariableHelper.ERROR_JSP;
		}
		
		/**
		 * 订单列表
		 */
		List list = orderBIZ.queryUserOrderList(matchId, userId, 2);
		model.addAttribute("list", list);
		
		return "/match2/match_signup_order";
	}
}
