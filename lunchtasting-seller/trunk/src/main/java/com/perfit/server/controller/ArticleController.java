package com.perfit.server.controller;

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
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.perfit.server.biz.article.ArticleBIZ;
import com.perfit.server.helper.PageBean;
import com.perfit.server.helper.Utils;
import com.perfit.server.helper.VariableHelper;

@Controller
public class ArticleController extends BaseController{
	@Autowired
	private ArticleBIZ adminArticleBIZ;
	/**
	 * 查询文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryArticleList")
	@ResponseBody
	public Object queryArticleList(HttpServletRequest request){
		Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
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
		//mapCondition.put("name", !JSON.parseObject(jo.get(23).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(23).toString()).get("value").toString():"");
		//mapCondition.put("type", !JSON.parseObject(jo.get(24).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(24).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		mapCondition.put("sellerId", seller.getId());
		
		HashMap strmap = adminArticleBIZ.queryArticle(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		return null;
		//return map;
}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goArticleList")
	public String goArticleList(){
		return null;
		//return "/article_list";
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
		}
		return null;
		//return "/article_save";
	}
	
	/**
	 * 保存文章
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/saveArticle")
	@ResponseBody
	public Model saveArticle(Model model,HttpServletRequest request) throws SQLException, ParseException{
		String id = request.getParameter("id");
		Article ac = new Article();
		ac.setName(request.getParameter("name"));
		ac.setContent(request.getParameter("content"));
		ac.setTag(request.getParameter("tag"));
		ac.setType(Integer.parseInt(request.getParameter("type")));
		String imgText = request.getParameter("imgText");
		
		if (imgText != null && imgText != "" && imgText != "null") {
			List<String> list = Utils.getImgSrc(imgText);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String s = "";
					String imgName = QiNiuStorageHelper
							.getQiNiuImgName(QiNiuStorageHelper.ACTIVITY_IMG_PREFIX);
					if (i == 0) {
						if (QiNiuStorageHelper.urlUpload(imgName, list.get(0)) == true) {
							ac.setImgUrl(imgName);
							s = imgText.replaceAll(list.get(0), SysConfig.IMG_VISIT_URL+imgName);
							imgText = s;
						}
					} else if (QiNiuStorageHelper.urlUpload(imgName,
							list.get(i)) == true) {
						    s = imgText.replaceAll(list.get(i), SysConfig.IMG_VISIT_URL+imgName);
						    imgText=s;
					}
				}
				ac.setImgText(imgText);
			}
		}
		
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
		//return model;
		return null;
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
		return null;
		//return model;
	}
}
