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
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Note;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/note/comment")
public class NoteCommentController {
	
	@Autowired
	private NoteCommentBIZ commentBIZ;
	@Autowired
	private NoteBIZ noteBIZ;
	
	
	/**
	 * 查看评论
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String noteIdString = request.getParameter("note_id");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		if(!ValidatorHelper.isNumber(noteIdString)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			Long noteId = Long.parseLong(noteIdString);
			Map map = new HashMap();
			map.put("list",commentBIZ.queryNoteCommentList(VariableConfig.PAGE_SIZE,(pPage-1)*VariableConfig.PAGE_SIZE, noteId));
			Integer con = commentBIZ.getNoteCommenCount(noteId);
			map.put("count",con);
			map.put("total_page",Utils.getTotalPage(con,VariableConfig.PAGE_SIZE));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	@RequestMapping(value = "/publish")
	@ResponseBody
	public Object publish(HttpServletRequest request){
		String content = request.getParameter("content");
		String noteIdString = request.getParameter("note_id");
		if(!ValidatorHelper.isNumber(noteIdString) || ValidatorHelper.isEmpty(content)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		try {
			long noteId = Long.parseLong(noteIdString);
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 判断帖子是否存在
			 */
			Note note = noteBIZ.getById(noteId);
			if(note == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "帖子不存在", request);
			}
			
			boolean result = commentBIZ.publish(userId, noteId,note.getUserId(),content);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
}
