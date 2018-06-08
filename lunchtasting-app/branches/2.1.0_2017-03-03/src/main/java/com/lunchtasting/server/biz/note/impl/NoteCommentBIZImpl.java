package com.lunchtasting.server.biz.note.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.note.NoteCommentBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.note.NoteCommentDAO;
import com.lunchtasting.server.dao.note.NoteDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.NoteComment;
import com.lunchtasting.server.po.lt.NoteLike;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class NoteCommentBIZImpl implements NoteCommentBIZ{
	@Autowired
	private NoteCommentDAO noteCommentDAO;
	@Autowired
	private NoteDAO noteDAO;

	@Override
	public List queryNoteCommentList(Integer pageSize, Integer page, Long noteId) throws ParseException {
		// TODO Auto-generated method stub
		List list  =  noteCommentDAO.queryNoteCommentList(pageSize, page, noteId);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				/**
				 * 头像
				 */
				if(ValidatorHelper.isNotNull(map.get("icon"))){
					map.put("icon", SysConfig.IMG_VISIT_URL+map.get("icon")
							+ QiNiuStorageHelper.MODEL1+"w/340/h/288");
				}
				/**
				 * 时间差
				 */
				if(ValidatorHelper.isNotNull(map.get("create_time"))){
					Date time = DateUtil.convertStringTODate(map.get("create_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
					String timeString = CommonHelper.getPerfitTime(time);
					map.put("time",timeString);
					map.remove("create_time");
				}
				newList.add(map);
			}
			return newList;
		}
		return null;
	}

	@Override
	public Integer getNoteCommenCount(Long noteId) {
		return noteCommentDAO.getNoteCommenCount(noteId);
	}

	@Override
	public Boolean publish(Long userId,Long noteId,Long noteUserId,String content) {
		NoteComment noteComment = new NoteComment();
		noteComment.setId(IdWorker.getId());
		noteComment.setContent(content);
		noteComment.setUserId(userId);
		noteComment.setNoteId(noteId);
		noteComment.setNoteUserId(noteUserId);
		noteCommentDAO.create(noteComment);
		return true;
	}

}
