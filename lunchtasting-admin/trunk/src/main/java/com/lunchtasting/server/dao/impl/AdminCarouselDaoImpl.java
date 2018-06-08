package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminCarouselDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.BannerIndex;
@Repository
public class AdminCarouselDaoImpl extends GenericDAOSupport<Long,BannerIndex> implements AdminCarouselDao{

	@Override
	public Long addCarousel(BannerIndex photoCarousel) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",photoCarousel);
	}

	@Override
	public List queryCarouselList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryPhotoCarouselList",map);
	}

	@Override
	public BannerIndex queryCarouselById(String id) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("id", id);
		return (BannerIndex) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById",map);
	}

	@Override
	public int getCarouselCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryPhotoCarouselListCount",map);
	}

	@Override
	public int deleteCarousel(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deletePhotoCarousel",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return BannerIndex.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "banner_index";
	}

	@Override
	public Long updateCarousel(BannerIndex photoCarousel) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",photoCarousel);
	}

}
