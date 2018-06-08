package com.lunchtasting.server.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.AdminNoteBIZ;
import com.lunchtasting.server.biz.AdminTopicBIZ;
import com.lunchtasting.server.biz.AdminUserForNoteBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Note;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminNoteController {
	
	@Autowired
	private AdminNoteBIZ adminNoteBIZ;
	
	@Autowired
	private AdminUserForNoteBIZ adminUserForNoteBIZ;
	
	@Autowired
	private AdminTopicBIZ adminTopicBIZ;
	
	/**
	 * 查询帖子列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryNoteList")
	@ResponseBody
	public Object queryNoteList(HttpServletRequest request){
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
//        System.out.println(aoData);//[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,{"name":"mark","value":""}]

		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		Object mark = JSON.parseObject(jo.get(27).toString()).get("value");
		HashMap mapCondition = new HashMap<String, Object>();//条件
		if(mark != null){
			mapCondition.put("mark", !JSON.parseObject(jo.get(27).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(27).toString()).get("value").toString():"");
		}
//		mapCondition.put("mark", !JSON.parseObject(jo.get(23).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(23).toString()).get("value").toString():"");
//		mapCondition.put("mark", !JSON.parseObject(jo.get(27).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(27).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminNoteBIZ.queryNoteList(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		return map;
    }
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goNoteList")
	public String goCourseList(Model model){
		model.addAttribute("userList1", adminUserForNoteBIZ.queryUser1());
		model.addAttribute("baseUrl", SysConfig.IMG_VISIT_URL);
		return "note/admin_note_list";
	}
	
	/**
	 * 启用或禁用
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateNote",method=RequestMethod.POST)
	@ResponseBody
	public Model updateNote(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}else if("del".equals(type)){
			map.put("flag", 2);
		}
		map.put("id", id);
		try {
			adminNoteBIZ.updateNote(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goNoteSave")
	public String goNoteSave(Model model,HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Note ac =  adminNoteBIZ.queryNoteById(id);
			model.addAttribute("bean", ac);
			if(!"".equals(ac.getImgUrl()) && null != ac.getImgUrl()){
				model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
			}
		}
		model.addAttribute("baseUrl", SysConfig.IMG_VISIT_URL);
		model.addAttribute("userList1", adminUserForNoteBIZ.queryUser1());
		model.addAttribute("userList2", adminUserForNoteBIZ.queryUser2());
		model.addAttribute("topicList", adminTopicBIZ.queryTopicNotLimit());
		return "note/admin_note_save";
	}
	
	/**
	 * 保存帖子
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveNote")
	@ResponseBody
	public Model saveNote(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		Note ac = new Note();
		if(request.getParameter("userId") != null && !"".equals(request.getParameter("userId")) && !"null".equals(request.getParameter("userId"))){
			ac.setUserId(Long.parseLong(request.getParameter("userId")));
		}
		ac.setType(Integer.parseInt(request.getParameter("type")));
		String content = request.getParameter("content");
		if(content != null && !"".equals(content) && !"null".equals(content)){
//			String result = null;
//			try {
//				result = Utils.queryStringForPost(VariableHelper.SENSITIVE_WORD_FILTERING_URL, content.trim());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(result != null){
//				JSONObject jsonObject = JSON.parseObject(result);
//				if(jsonObject.getString("code").equals("0")){
//					ac.setContent(content);
//				}else if(jsonObject.getString("code").equals("1")){
//					JSONObject newStr = JSON.parseObject(jsonObject.getString("data"));
//					ac.setContent(newStr.getString("new_str"));
//				}
//			}else{
				ac.setContent(content);
//			}
		}
		String url = request.getParameter("imgUrl");
		String videoUrl = request.getParameter("videoUrl");
		if(url != null && !"".equals(url) && url != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(33, 59));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(25, 51));
			}
		}
		ac.setVideoUrl(videoUrl);
		String height = request.getParameter("height");
		String width = request.getParameter("width");
		if(height != null && !"".equals(height) && !"null".equals(height)){
			ac.setImgHeight(Integer.parseInt(height));
		}
		if(width != null && !"".equals(width) && !"null".equals(width)){
			ac.setImgWidth(Integer.parseInt(width));
		}
		
		if("".equals(request.getParameter("userId")) || 0 == Long.parseLong(request.getParameter("userId"))){
			model.addAttribute("flag", "kong");
			return model;
		}
		String topicId = request.getParameter("topicId");
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				adminNoteBIZ.updateNote(ac);
				adminTopicBIZ.updateTNMiddle(Long.parseLong(topicId), Long.parseLong(id));
			}else{
				Long noteId = IdWorker.getId();
				ac.setId(noteId);
				adminNoteBIZ.addNote(ac);
				adminTopicBIZ.saveTNMiddle(Long.parseLong(topicId), noteId);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return model;
		}
		return model;
	}

	/**
	 * 精品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateNoteGood",method=RequestMethod.POST)
	@ResponseBody
	public Model updateNoteGood(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			adminNoteBIZ.updateNoteGood(id);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 评论
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveComment",method=RequestMethod.POST)
	@ResponseBody
	public Model saveComment(Model model,HttpServletRequest request){
		String userId = request.getParameter("userId");
		String noteId = request.getParameter("noteId");
		String noteUserId = request.getParameter("noteUserId");
		String content = request.getParameter("content");
		if(content.trim() == null || "".equals(content.trim())){
			return model.addAttribute("flag", "kong");
		}
		try {
			adminNoteBIZ.saveComment(IdWorker.getId(), Long.parseLong(userId), Long.parseLong(noteId), Long.parseLong(noteUserId), content);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 赞
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateFabulous",method=RequestMethod.POST)
	@ResponseBody
	public Model updateFabulous(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String fabulous = request.getParameter("fabulous");
		if(fabulous == null || "".equals(fabulous)){
			return model.addAttribute("flag", "kong");
		}
		try {
			adminNoteBIZ.updateFabulous(Long.parseLong(id), Integer.parseInt(fabulous));
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
