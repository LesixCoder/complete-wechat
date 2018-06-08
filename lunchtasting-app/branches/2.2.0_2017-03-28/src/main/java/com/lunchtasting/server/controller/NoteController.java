package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.biz.note.NoteCommentBIZ;
import com.lunchtasting.server.biz.topic.TopicBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Note;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteBIZ noteBIZ;
	@Autowired
	private NoteCommentBIZ noteCommentBIZ;
	@Autowired
	private TopicBIZ topicBIZ;
	/**
	 * 帖子列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String type = request.getParameter("type");
		String topicIdString = request.getParameter("topic_id");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		Long topicId = null;
		if(ValidatorHelper.isNumber(topicIdString)&&!topicIdString.equals("0")){
			topicId=Long.parseLong(topicIdString);
		}
		try {
			Map map = new HashMap();
			Long userId = EncryptAuth.getUserId(request);
			map.put("list",noteBIZ.queryNoteList(userId,topicId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE,null));
			map.put("total_page", Utils.getTotalPage(noteBIZ.getNoteCount(topicId),VariableConfig.PAGE_SIZE));
			map.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 精品帖子列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/good/list")
	@ResponseBody
	public Object goodlist(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		try {
			Map map = new HashMap();
			Long userId = EncryptAuth.getUserId(request);
			map.put("list",noteBIZ.queryGoodNoteList(userId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			map.put("total_page", Utils.getTotalPage(noteBIZ.getGoodNoteCoint(),VariableConfig.PAGE_SIZE));
			map.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 好友关注帖子列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/friendList")
	@ResponseBody
	public Object friendList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		try {
			Map map = new HashMap();
			Long userId =EncryptAuth.getUserId(request);
			Integer friend = 1;
			map.put("list",noteBIZ.queryNoteList(userId,null,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE,friend));
			map.put("total_page", Utils.getTotalPage(noteBIZ.getUserFriendNoteCount(userId),VariableConfig.PAGE_SIZE));
			map.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 创建喜爱（收藏）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/createLike")
	@ResponseBody
	public Object createLike(HttpServletRequest request)  {
		String noteIdString = request.getParameter("note_id");
		if(!ValidatorHelper.isNumber(noteIdString)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			Long noteId = Long.parseLong(noteIdString);
			Long userId = EncryptAuth.getUserId(request);
			Boolean result=noteBIZ.createLike(userId, noteId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 取消喜爱（收藏）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/removeLike")
	@ResponseBody
	public Object removeLike(HttpServletRequest request)  {
		String noteIdString = request.getParameter("note_id");
		if(!ValidatorHelper.isNumber(noteIdString)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			Long noteId = Long.parseLong(noteIdString);
			Long userId = EncryptAuth.getUserId(request);
			Boolean result=noteBIZ.deleteLike(userId, noteId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 发布一条帖子
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/publish")
	@ResponseBody
	public Object publish(HttpServletRequest request)  {
		//Long userId, Integer type, String content, String imgUrl, String videoUrl
		String content = request.getParameter("content");
		String imgUrl = request.getParameter("img_url");
		String videoUrl = request.getParameter("video_url");
		String topicIdString = request.getParameter("topic_id");
		String height = request.getParameter("img_height");
		String width = request.getParameter("img_width");
		
		Integer type = 2;
		if(ValidatorHelper.isEmpty(imgUrl)&&ValidatorHelper.isEmpty(videoUrl)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		if(!ValidatorHelper.isNumber(height)||!ValidatorHelper.isNumber(width)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			Long topicId = null;
			Long userId = EncryptAuth.getUserId(request);
			Integer imgHeight = Integer.parseInt(height);
			Integer imgWidth = Integer.parseInt(width);
			if(ValidatorHelper.isEmpty(content)){
				type=1;
			}
			if(!ValidatorHelper.isEmpty(videoUrl)){
				type=3;
			}
			if(ValidatorHelper.isNumber(topicIdString)){
				topicId = Long.parseLong(topicIdString);
			}
			Boolean result = noteBIZ.publish(userId, type, content, imgUrl, videoUrl,topicId,imgHeight,imgWidth);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 查看帖子详细
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request){
		String nId = request.getParameter("note_id");
		if(!ValidatorHelper.isNumber(nId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			Long noteId = Long.parseLong(nId);
			
			Map noteMap = noteBIZ.getNoteById(noteId, userId);
			if(noteMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"帖子不存在", request);
			}
			
			Map map = new HashMap();
			map.put("note",noteMap);
			map.put("comment", noteCommentBIZ.queryNoteCommentList(2,0, noteId));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	/**
	 * 删除帖子
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Object remove(HttpServletRequest request){
		String nId = request.getParameter("note_id");
		if(!ValidatorHelper.isNumber(nId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			//Long userId =  778614808516755456L;
			Long noteId = Long.parseLong(nId);
			
			Note note =  noteBIZ.getById(noteId);
			if(note == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"帖子不存在", request);
			}
			if(!note.getUserId().equals(userId)){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"您没有权限", request);
			}
			if(note.getFlag()==2){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"已经删除", request);
			}
			if(noteBIZ.removeNote(noteId)){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS,request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
}
