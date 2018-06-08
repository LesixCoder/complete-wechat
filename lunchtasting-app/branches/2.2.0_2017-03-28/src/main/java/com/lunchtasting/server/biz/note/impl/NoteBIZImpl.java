package com.lunchtasting.server.biz.note.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.friend.FriendBIZ;
import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.friend.FriendDAO;
import com.lunchtasting.server.dao.note.NoteDAO;
import com.lunchtasting.server.dao.note.NoteLikeDAO;
import com.lunchtasting.server.dao.topic.TopicDAO;
import com.lunchtasting.server.dao.user.UserHotDAO;
import com.lunchtasting.server.enumeration.SysEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Note;
import com.lunchtasting.server.po.lt.NoteLike;
import com.lunchtasting.server.po.lt.UserHot;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class NoteBIZImpl implements NoteBIZ{
	
	@Autowired
	private NoteDAO noteDAO;
	@Autowired
	private NoteLikeDAO noteLikeDAO;
	@Autowired
	private TopicDAO topicDAO;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private UserHotDAO userHotDAO;
	
	@Override
	public Note getById(Long id) {
		return noteDAO.getById(id);
	}
	
	@Override
	public List queryNoteList(Long userId,Long topicId,Integer page, Integer pageSize,Integer friend) throws ParseException {
		List list =null;
		if(topicId!=null){
			list = noteDAO.queryNoteListByTopic(userId,topicId, pageSize, page);
		}else if(friend!=null && friend==1){ //friend == 1 查询关注好友
			list = noteDAO.queryUserFriendNoteList(userId,pageSize,page);
		}else{
			list = noteDAO.queryNoteList(userId,null,pageSize, page);
		}
		list = ListHlep(list, userId);
		return list;
	}
	@Override
	public Integer getNoteCount(Long topicId) {
		if(topicId!=null){
			return noteDAO.getNoteByTopicCount(topicId);
		}
		return noteDAO.getNoteCount(null);
	}

	@Override
	public List queryGoodNoteList(Long userId, Integer page, Integer pageSize) throws ParseException {
		// TODO Auto-generated method stub
		List list = noteDAO.queryNoteList(userId,1,pageSize, page);
		list = ListHlep(list, userId);
		return list;
	}
	
	@Override
	public Integer getGoodNoteCoint() {
		// TODO Auto-generated method stub
		return noteDAO.getNoteCount(1);
	}
	
	@Override
	public Boolean createLike(Long userId, Long noteId) {
		if(noteDAO.getNoteById(noteId,userId)==null){
			return false;
		}
		NoteLike noteLike1 = noteLikeDAO.getNoteLike(userId, noteId);
		if(noteLike1!=null && noteLike1.getId()!=0){
			return true;
		}
		NoteLike noteLike = new NoteLike();
		noteLike.setId(IdWorker.getId());
		noteLike.setUserId(userId);
		noteLike.setNoteId(noteId);
		noteLikeDAO.create(noteLike);
		if(userHotDAO.getTypeCount(userId,SysEnum.UserHotType.LIKE_NOTE.getType())<20){
			userHotDAO.createUserHot(userId, SysEnum.UserHotType.LIKE_NOTE.getNumber(), SysEnum.UserHotType.LIKE_NOTE.getType());
		}
		return true;
	}
	@Override
	public Boolean publish(Long userId, Integer type, String content,
			String imgUrl, String videoUrl,Long topicId,Integer imgHeight, Integer imgWidth) {
		Note note = new Note();
		note.setId(IdWorker.getId());
		note.setContent(content);
		note.setImgUrl(imgUrl);
		note.setType(type);
		note.setUserId(userId);
		note.setVideoUrl(videoUrl);
		note.setImgHeight(imgHeight);
		note.setImgWidth(imgWidth);
		note.setFlag(0);
		noteDAO.create(note);
		if(topicId!=null){
			topicDAO.createConnect(note.getId(), topicId);
		}
		if(userHotDAO.getTypeCount(userId,SysEnum.UserHotType.ADD_NOTE.getType())<20){
			userHotDAO.createUserHot(userId, SysEnum.UserHotType.ADD_NOTE.getNumber(), SysEnum.UserHotType.ADD_NOTE.getType());
		}
		return true;
	}
	@Override
	public Integer getUserNoteCount(Long userId) {
		return noteDAO.getUserNoteCount(userId);
	}
	@Override
	public List queryUserNoteList(Long userId, Integer page, Integer pageSize) {
		List list = noteDAO.queryUserNoteList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/233/h/233");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	@Override
	public Integer getUserLikeNoteCount(Long userId) {
		return noteDAO.getUserLikeNoteCount(userId);
	}
	@Override
	public List queryUserLikeNoteList(Long userId, Integer page,
			Integer pageSize) {
		List list = noteDAO.queryUserLikeNoteList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/233/h/233");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	@Override
	public Boolean deleteLike(Long userId,Long noteId) {
		NoteLike noteLike1 = noteLikeDAO.getNoteLike(userId, noteId);
		if(noteLike1==null){
			return true;
		}
		noteLikeDAO.deleteLike(noteLike1.getId());
		return true;
	}
	@Override
	public Map getNoteById(Long noteId, Long userId) throws ParseException {
		Map map = noteDAO.getNoteById(noteId,userId);
		if(map != null){
			
			if(ValidatorHelper.isNotNull(map.get("icon"))){
				map.put("icon", SysConfig.IMG_VISIT_URL+map.get("icon")
						+ QiNiuStorageHelper.MODEL1+"w/60/h/60");
			}
			
			if(ValidatorHelper.isNotNull(map.get("img_url"))){
				int height = Integer.parseInt(map.get("img_height").toString());
				int width = Integer.parseInt(map.get("img_width").toString());
				
				/**
				 * 压缩后的高宽
				 */
				Map imageMap = CommonHelper.getImageSize(width, height, 1080, 936);
				int imageWidth = Integer.parseInt(imageMap.get("width").toString());
				int imageHeight = Integer.parseInt(imageMap.get("height").toString());
				
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL2+"w/"+imageWidth+"/h/"+imageHeight);
				
				map.put("img_width", imageWidth);
				map.put("img_height", imageHeight);
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
			/**
			 * 判断是否喜欢
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("is_like"))){
				map.put("like", 1);
			}else{
				map.put("like", 0);
			}
			map.remove("is_like");
		}
		return map;
	}
	@Override
	public Integer getUserFriendNoteCount(Long userId) {
		return noteDAO.getUserFriendNoteCount(userId);
	}

	@Override
	public boolean removeNote(Long noteId) {
		// TODO Auto-generated method stub
		Integer pf = noteDAO.updateNote(2, noteId);
		if((int)pf==1){
			return true;
		}
		return false;
	}
	/**
	 * list的数据处理
	 * @param list
	 * @param userId
	 * @return
	 * @throws ParseException
	 */
	public List ListHlep(List list,Long userId) throws ParseException{
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片 1080*936
				 */
				if(ValidatorHelper.isNotNull(map.get("img_url"))){
					int height = Integer.parseInt(map.get("img_height").toString());
					int width = Integer.parseInt(map.get("img_width").toString());
					
					/**
					 * 获得压缩后的图片高宽
					 */
					Map imageMap = CommonHelper.getImageSize(width, height, 1080, 936);
					int imageWidth = Integer.parseInt(imageMap.get("width").toString());
					int imageHeight = Integer.parseInt(imageMap.get("height").toString());
					
					map.put("img_url_ios", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL2+"w/"+imageWidth+"/h/"+imageHeight+"%7Cimageslim");
					
					map.put("img_url_android", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL2+"w/"+imageWidth+"/h/"+imageHeight+"|imageslim");
					
					map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL2+"w/"+imageWidth+"/h/"+imageHeight+"");
					
					map.put("img_width", imageWidth);
					map.put("img_height", imageHeight);
				}
				/**
				 * 头像 
				 */
				if(ValidatorHelper.isNotNull(map.get("icon"))){
					map.put("icon", SysConfig.IMG_VISIT_URL+map.get("icon")
							+ QiNiuStorageHelper.MODEL0+"w/60/h/60");
				}
				/**
				 * 是否喜爱,关注
				 */
				if(userId==null){
					map.put("like", 0);
					map.put("friend", 0);
			/*      NoteLike noteLike = noteLikeDAO.getNoteLike(userId, Long.parseLong(map.get("id").toString()));
					if(noteLike!=null && noteLike.getId()!=0){
						map.put("like", "1");
					}else{
						map.put("like", "0");
					}
					if(friend==null){
						Long desUserId=Long.parseLong(map.get("user_id").toString());
						String id = friendDAO.getFollow(userId, desUserId);
						if(ValidatorHelper.isNumber(id)){
							map.put("friend", "1");
						}else{
							map.put("friend", "0");
						}
					}else if(friend==1){
						map.put("friend", "1");
					}*/
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
				/**
				 * 评论前3条,
				 */
				if(ValidatorHelper.isNotNull(map.get("note_comment_list"))){
					try {
						String [] comlist = map.get("note_comment_list").toString().split("<%@;>");
						List noteCommentList = new ArrayList(); 
						for (int j = 0; j < comlist.length; j++) {
							String com = comlist[j];
							Map newMap = new HashMap();
							newMap.put("user_id", com.split("<%@,>")[0]);
							newMap.put("user_name", com.split("<%@,>")[1]);
							newMap.put("content", com.split("<%@,>")[2]);
							noteCommentList.add(newMap);
						}
						map.put("note_comment_list",noteCommentList);
					} catch (Exception e) {
						e.printStackTrace();
						map.put("note_comment_list",null);
					}
					
				}
				//List topicList = null;//topicDAO.queryTopicListByNote(Long.parseLong(map.get("id").toString()));
				//map.put("topicList", topicList);
				newList.add(map);
			}
			return newList;
		}
		return null;
	}

}
