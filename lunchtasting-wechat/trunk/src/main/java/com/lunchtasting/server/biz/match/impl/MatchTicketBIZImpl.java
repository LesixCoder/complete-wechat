package com.lunchtasting.server.biz.match.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchTicketBIZ;
import com.lunchtasting.server.dao.match.MatchTicketDAO;
import com.lunchtasting.server.po.lt.MatchTicket;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class MatchTicketBIZImpl implements MatchTicketBIZ {

	@Autowired
	private MatchTicketDAO ticketDAO;

	@Override
	public MatchTicket getById(Long id) {
		return ticketDAO.getById(id);
	}

	@Override
	public List queryMatchTicketList(Long matchId, Integer ticketType) {
		List list = ticketDAO.queryMatchTicketList(matchId, ticketType);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				double price = Double.parseDouble(map.get("price").toString());
				
				/**
				 * 判断是否早鸟票价格
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("early_bird_price"))){
					try {
						Date earlyBirdTime = DateUtil.convertStringTODate(
								map.get("early_bird_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
						
						if(earlyBirdTime.after(new Date())){
							price = Double.parseDouble(map.get("early_bird_price").toString());
							map.put("is_bird", 1);
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				/**
				 * 需要支付的价格
				 */
				map.put("pay_price", price);
				newList.add(map);
			}
			return newList;
		}
		return null;	
	}
	
}
