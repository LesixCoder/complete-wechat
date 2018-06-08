package com.lunchtasting.server.biz;

import java.util.HashMap;

import com.lunchtasting.server.po.lt.BannerIndex;

public interface AdminCarouselBIZ {
	Long addCarousel(BannerIndex photoCarousel);
	Long updateCarousel(BannerIndex photoCarousel);
	HashMap queryCarouselList(HashMap map);
	BannerIndex queryCarouselById(String id);
	int getCarouselCount(HashMap map);
	int deleteCarousel(HashMap map);
}
