package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminAlbumBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.AdminAlbumDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Album;
@Service
public class AdminAlbumBIZImpl implements AdminAlbumBIZ{
	
	@Autowired
	private AdminAlbumDao adminAlbumDao;
	
	@Override
	public HashMap queryAlbumList(HashMap map) {
		HashMap result = new HashMap();
	    List<Album> albumList;
	    int totalCount;
    	 try{
    		 albumList = adminAlbumDao.queryAlbumList(map);
    		 for(int i = 0;i<albumList.size();i++){
    			 albumList.get(i).setNewId(albumList.get(i).getId().toString());
    		 }
    		 totalCount = adminAlbumDao.queryAlbumCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(albumList != null && albumList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(albumList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(albumList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}

	@Override
	public int updateAlbum(HashMap map) {
		// TODO Auto-generated method stub
		return adminAlbumDao.updateAlbum(map);
	}

	@Override
	public Long saveAlbum(Album album) {
		// TODO Auto-generated method stub
		return adminAlbumDao.saveAlbum(album);
	}

	@Override
	public Long updateAlbum(Album album) {
		// TODO Auto-generated method stub
		return adminAlbumDao.updateAlbum(album);
	}

	@Override
	public Album queryAlbumById(String id) {
		// TODO Auto-generated method stub
		return adminAlbumDao.queryAlbumById(id);
	}

	@Override
	public List queryAlbumNotLimit(Long id) {
		List<Album> list = adminAlbumDao.queryAlbumNotLimit(id);
		for(int i = 0;i<list.size();i++){
			list.get(i).setNewId(list.get(i).getId().toString());
			list.get(i).setBaseUrl(SysConfig.IMG_VISIT_URL.toString());
		}
		return list;
	}

	@Override
	public List querySelect() {
		List<Album> list = adminAlbumDao.querySelect();
		for(int i = 0;i<list.size();i++){
			list.get(i).setNewId(list.get(i).getId().toString());
			list.get(i).setBizIdNew(list.get(i).getBizId().toString());
		}
		return list;
	}

}
