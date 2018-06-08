package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.BannerIndex;

public interface AdminCarouselDao extends GenericDAO<BannerIndex>{
	Long addCarousel(BannerIndex photoCarousel);
	Long updateCarousel(BannerIndex photoCarousel);
	List queryCarouselList(HashMap map);
	BannerIndex queryCarouselById(String id);
	int getCarouselCount(HashMap map);
	int deleteCarousel(HashMap map);
	
}
