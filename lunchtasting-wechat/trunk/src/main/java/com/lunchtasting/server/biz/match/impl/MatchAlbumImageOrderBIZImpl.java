package com.lunchtasting.server.biz.match.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchAlbumImageOrderBIZ;
import com.lunchtasting.server.dao.match.MatchAlbumImageOrderDAO;
import com.lunchtasting.server.po.lt.MatchAlbumImageOrder;
import com.lunchtasting.server.util.IdWorker;

@Service
public class MatchAlbumImageOrderBIZImpl implements MatchAlbumImageOrderBIZ {

	@Autowired
	private MatchAlbumImageOrderDAO imageOrderDAO;

	@Override
	public MatchAlbumImageOrder getById(Long id) {
		return imageOrderDAO.getById(id);
	}

	@Override
	public Long createImageOrder(Long userId, Long imageId, Double price,
			String code) {
		
		IdWorker idWorker = new IdWorker(0, 0);
		MatchAlbumImageOrder imageOrder = new MatchAlbumImageOrder();
		long orderId = idWorker.nextId();
		imageOrder.setId(orderId);
		imageOrder.setImageId(imageId);
		imageOrder.setUserId(userId);
		imageOrder.setPrice(price);
		imageOrder.setCode(code);
		imageOrder.setStatus(1);
		imageOrderDAO.create(imageOrder);
		return orderId;
	}
	
	@Override
	public Boolean checkOrderPay(Long orderId) {
		MatchAlbumImageOrder order = imageOrderDAO.getById(orderId);
		if(order != null && order.getStatus().intValue() == 2){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderPay(Long orderId) {
		Integer result = imageOrderDAO.updateOrderPay(orderId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

}
