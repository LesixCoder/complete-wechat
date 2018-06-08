package com.lunchtasting.server.biz.temporaryEnroll.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.temporaryEnroll.TemporaryCinemaBIZ;
import com.lunchtasting.server.dao.temporaryEnroll.TemporaryCinemaDao;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryCinema;
@Service
public class TemporaryCinemaBIZImpl implements TemporaryCinemaBIZ{
	
	@Autowired
	private TemporaryCinemaDao temporaryCinemaDao;
	
	@Override
	public Long addCinema(TemporaryCinema temporaryCinema) {
		// TODO Auto-generated method stub
		return temporaryCinemaDao.addCinema(temporaryCinema);
	}

	@Override
	public int checkRandom(String random) {
		// TODO Auto-generated method stub
		return temporaryCinemaDao.checkRandom(random);
	}

	@Override
	public TemporaryCinema getCinema(String id) {
		// TODO Auto-generated method stub
		return temporaryCinemaDao.getCinema(id);
	}

}
