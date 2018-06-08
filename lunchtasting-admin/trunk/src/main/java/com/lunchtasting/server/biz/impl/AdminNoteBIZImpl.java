package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminNoteBIZ;
import com.lunchtasting.server.dao.AdminNoteDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Note;
@Service
public class AdminNoteBIZImpl implements AdminNoteBIZ{

	@Autowired
	private AdminNoteDao adminNoteDao;
	
	@Override
	public HashMap queryNoteList(HashMap map) {
		HashMap result = new HashMap();
	    List<Note> noteList;
	    int totalCount;
    	 try{
    		 noteList = adminNoteDao.queryNoteList(map);
    		 for(int i = 0;i<noteList.size();i++){
    			 noteList.get(i).setNewId(noteList.get(i).getId().toString());
    		 }
    		 totalCount = adminNoteDao.getNoteCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(noteList != null && noteList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(noteList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(noteList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}

	@Override
	public int updateNote(HashMap map) {
		// TODO Auto-generated method stub
		return adminNoteDao.updateNote(map);
	}

	@Override
	public Long addNote(Note note) {
		// TODO Auto-generated method stub
		return adminNoteDao.addNote(note);
	}

	@Override
	public Long updateNote(Note note) {
		// TODO Auto-generated method stub
		return adminNoteDao.updateNote(note);
	}

	@Override
	public Note queryNoteById(String id) {
		// TODO Auto-generated method stub
		return adminNoteDao.queryNoteById(id);
	}

	@Override
	public int updateNoteGood(String id) {
		// TODO Auto-generated method stub
		return adminNoteDao.updateNoteGood(id);
	}

	@Override
	public Long saveComment(Long id, Long userId, Long noteId, Long noteUserId,
			String content) {
		// TODO Auto-generated method stub
		return adminNoteDao.saveComment(id, userId, noteId, noteUserId, content);
	}

	@Override
	public int updateFabulous(Long id, int fabulous) {
		// TODO Auto-generated method stub
		return adminNoteDao.updateFabulous(id, fabulous);
	}

	@Override
	public int updateNoteByUserId(Long userId) {
		// TODO Auto-generated method stub
		return adminNoteDao.updateNoteByUserId(userId);
	}

}
