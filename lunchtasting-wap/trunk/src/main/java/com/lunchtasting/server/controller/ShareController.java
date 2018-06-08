package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.biz.note.NoteCommentBIZ;
import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.biz.seller.SellerCommentBIZ;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 分享模块
 * Created on 2016-9-6
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/share")
public class ShareController {
	
	@Autowired
	private ActivityBIZ activityBIZ;
	@Autowired
	private ArticleBIZ articleBIZ;
	@Autowired
	private SellerBIZ sellerBIZ;
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private SellerCommentBIZ sellerCommentBIZ;
	@Autowired
	private NoteBIZ noteBIZ;
	@Autowired
	private NoteCommentBIZ noteCommentBIZ;
	@Autowired
	private MatchBIZ matchBIZ;
	
	
	
	/**
	 * 活动
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/activity/{id}")
	public String activity(@PathVariable("id") String id,Model model,HttpServletRequest request) throws Exception{
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		
		long activityId = Long.parseLong(id);
		Map activity = activityBIZ.getShareDetail(activityId);
		if(activity == null){
			return "";
		}
		model.addAttribute("activity", activity);
		return "/share/activity";
	}
	
	/**
	 * 文章
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/article/{id}")
	public String article(@PathVariable("id") String id,Model model,HttpServletRequest request){
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		
		long articleId = Long.parseLong(id);
		Map article = articleBIZ.getShareDetail(articleId);
		if(article == null){
			return "";
		}
		model.addAttribute("article", article);
		return "/share/article";	
	}
	
	/**
	 * 场馆
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/venue/{id}")
	public String venue(@PathVariable("id") String id,Model model,HttpServletRequest request) throws Exception{
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		
		long sellerId = Long.parseLong(id);
		Map seller = sellerBIZ.getShareDetail(sellerId);
		if(seller == null){
			return "";
		}
		model.addAttribute("seller", seller);
		
		/**
		 * 商家下的活动列表
		 */
		List activityList = activityBIZ.queryVenueActivityList(sellerId);
		model.addAttribute("activityList", activityList);
		
		return "/share/venue";	
	}
	/**
	 * 课程
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/course/{id}")
	public String course(@PathVariable("id") String id,Model model,HttpServletRequest request) throws Exception{
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		long courseId = Long.parseLong(id);
		Map course = courseBIZ.getCourseDetail(courseId);
		if(course == null){
			return "";
		}
		model.addAttribute("bean", course);
		HashMap map = new HashMap();
		map.put("crouseId", id);
		List sellerCommenList = sellerCommentBIZ.getShareCommentByCourseTop2(map);
		model.addAttribute("bean2",sellerCommenList);
		return "/share/course";	
	}
	
	/**
	 * 帖子
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/note/{id}")
	public String note(@PathVariable("id") String id,Model model,HttpServletRequest request) throws Exception{
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		long noteId = Long.parseLong(id);
		Map note = noteBIZ.getNoteById(noteId);
		if(note == null){
			return "";
		}
		model.addAttribute("bean", note);
		List noteCommenList = noteCommentBIZ.queryNoteCommentList(2, 0, noteId);
		model.addAttribute("bean2",noteCommenList);
		return "/share/note/note";	
	}
	
	/**
	 * 赛事
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/match/{id}")
	public String match(@PathVariable("id") String id,Model model,HttpServletRequest request) throws Exception{
		if(!ValidatorHelper.isNumber(id)){
			return "";
		}
		long matchId = Long.parseLong(id);
		Map match = matchBIZ.getMatchDetail(matchId);
		if(match == null){
			return "";
		}
		model.addAttribute("bean", match);
		return "/share/match";	
	}
	
}
