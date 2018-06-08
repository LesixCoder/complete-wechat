package com.lunchtasting.server.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminArticleBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminArticleController extends BaseController{
	@Autowired
	private AdminArticleBIZ adminArticleBIZ;
	/**
	 * 查询文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryArticleList")
	@ResponseBody
	public Object queryArticleList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		HashMap mapCondition = new HashMap<String, Object>();//条件
		mapCondition.put("name", !JSON.parseObject(jo.get(23).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(23).toString()).get("value").toString():"");
		mapCondition.put("type", !JSON.parseObject(jo.get(24).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(24).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminArticleBIZ.queryArticle(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		mv.setViewName("/admin_article_list");
		return map;
}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goArticleList")
	public String goArticleList(){
		return "/admin_article_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goArticleSave")
	public String goArticleSave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Article ac =  adminArticleBIZ.queryArticleById(id);
			model.addAttribute("article", ac);
			model.addAttribute("imgUrl",SysConfig.IMG_VISIT_URL+ac.getImgUrl());
		}
		return "/admin_article_save";
	}
	
	/**
	 * 保存文章
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveArticle")
	@ResponseBody
	public Model saveArticle(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		Article ac = new Article();
		ac.setName(request.getParameter("name"));
		ac.setContent(request.getParameter("content"));
		ac.setTag(request.getParameter("tag"));
		ac.setCategoryId(Long.parseLong(request.getParameter("type")));
		ac.setId(IdWorker.getId());
		String imgText = request.getParameter("imgText");
		String url = request.getParameter("url");
		if(url != null && !"".equals(url) && url != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(33, 63));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(25, 55));
			}
		}
		/*if (url != null && url != "" && url != "null") {
			String imgName = QiNiuStorageHelper
					.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
//			if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url)){
//				ac.setImgUrl(imgName);
//			}
			
			if("/".equals(File.separator)){
				if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url)){
					ac.setImgUrl(imgName);
				}
			}else if("\\".equals(File.separator)){
				if (QiNiuStorageHelper.upload(imgName, request.getRealPath("")+url.replaceAll("/", "\\\\"))){
					ac.setImgUrl(imgName);
				}
			}
		}*/
		ac.setImgText(imgText);
/*		if (imgText != null && imgText != "" && imgText != "null") {
			List<String> list = Utils.getImgSrc(imgText);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String s = "";
					String imgName = QiNiuStorageHelper
							.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
//					if (i == 0) {
						if (QiNiuStorageHelper.urlUpload(imgName, list.get(i)) == true) {
//							ac.setImgUrl(imgName);
							s = imgText.replaceAll(list.get(i), SysConfig.IMG_VISIT_URL+imgName);
							imgText = s;
						}
//					} else if (QiNiuStorageHelper.urlUpload(imgName,
//							list.get(i)) == true) {
//						    s = imgText.replaceAll(list.get(i), SysConfig.IMG_VISIT_URL+imgName);
//						    imgText=s;
//					}
				}
				ac.setImgText(imgText);
			}else{
				ac.setImgText(imgText);
			}
		}*/
		
		if(request.getParameter("sort") != null && request.getParameter("sort") !="null" && request.getParameter("sort") != ""){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				adminArticleBIZ.updateArticle(ac);
			}else{
				adminArticleBIZ.addArticle(ac);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/**
	 * 删除或发布或撤销
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteArticle",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteArticle(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		if(type.equals("del")){
			map.put("flag", 2);
		}else if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}
		map.put("id", id);
		try {
			adminArticleBIZ.deleteArticle(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 图文
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findArticleText")
	@ResponseBody
	public Model findText(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			model.addAttribute("imgText", adminArticleBIZ.queryArticleById(id).getImgText());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 置顶
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topArticle",method=RequestMethod.POST)
	@ResponseBody
	public Model topArticle(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			adminArticleBIZ.topArticle(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
