package com.lunchtasting.server.biz.match.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchAlbumBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchAlbumDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.MatchAlbum;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class MatchAlbumBIZImpl implements MatchAlbumBIZ {

	@Autowired
	private MatchAlbumDAO matchAlbumDAO;

	@Override
	public MatchAlbum getById(Long id) {
		return matchAlbumDAO.getById(id);
	}

	@Override
	public List queryAlbumList(String name,Integer page,Integer pageSize) {
		return matchAlbumDAO.queryAlbumList(name,page,pageSize);
	}
	
	@Override
	public Integer getAlbumCount(String name) {
		return matchAlbumDAO.getAlbumCount(name);
	}

	@Override
	public List queryAlbumImageList(Long albumId,Long tagId,String code,Integer isLogo,Integer page,
			Integer pageSize) {
		
		List list = null;
		if(tagId == null){
			list = matchAlbumDAO.queryAlbumImageList(albumId, code, page, pageSize);
		}else{
			list = matchAlbumDAO.queryAlbumTagImageList(albumId, tagId, page, pageSize);
		}
		
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				String imgUrl = map.get("img_url").toString();
//				String watermark = "watermark/1/image/aHR0cDovL29janA5eDZ4OS5ia3QuY2xvdWRkbi5jb20vcGFveGlhb2dvdS9sb2dvX3N5LnBuZw" +
//						"==/dissolve/100/gravity/SouthEast";
				
				/**
				 * 判断图片是否需要打logo水印
				 * 0需要   1已打不需要
				 */
				if(isLogo == 0){
					
					map.put("img_url", SysConfig.IMG_VISIT_URL+imgUrl
							+ "-sy_1");
					map.put("img_url_big", SysConfig.IMG_VISIT_URL+imgUrl+"-sy_2");
					
				}else{
					
					map.put("img_url", SysConfig.IMG_VISIT_URL+imgUrl
							+ QiNiuStorageHelper.MODEL1);
					
					map.put("img_url_big", SysConfig.IMG_VISIT_URL+imgUrl);
					
				}
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	} 

	@Override
	public Integer getAlbumImageCount(Long albumId,Long tagId,String code) {
		if(tagId == null){
			return matchAlbumDAO.getAlbumImageCount(albumId, code);
		}else{
			return matchAlbumDAO.getAlbumTagImageCount(albumId, tagId);
		}
	}

	@Override
	public void createAlbumImage(Long albumId, Long matchId, String code,
			String imgUrl,Integer width,Integer height) {
		matchAlbumDAO.createAlbumImage(IdWorker.getId(),albumId, matchId, code, imgUrl,width,height);
	}

	@Override
	public Map getAlbumImageMap(Long imageId) {
		Map map = matchAlbumDAO.getAlbumImageMap(imageId);
		if(map != null){
			map.put("img_url_big", SysConfig.IMG_VISIT_URL+map.get("img_url")+"-sy_2");
		}
		return map;
	}


}
