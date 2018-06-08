package com.lunchtasting.server.dao.temporaryEnroll;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryCinema;

public interface TemporaryCinemaDao extends GenericDAO<TemporaryCinema>{
	Long addCinema(TemporaryCinema temporaryCinema);
	int checkRandom(String random);
	TemporaryCinema getCinema(String id);
}
