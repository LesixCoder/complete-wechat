package com.lunchtasting.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
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
import com.lunchtasting.server.biz.AdminNoteBIZ;
import com.lunchtasting.server.biz.AdminUserForNoteBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminUserController extends BaseController{
		
		@Autowired
		private AdminUserForNoteBIZ adminUserForNoteBIZ;
		
		@Autowired
		private AdminNoteBIZ adminNoteBIZ;
		
		/**
		 * 查询马甲列表
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/queryUserList")
		@ResponseBody
		public Object queryUserList(HttpServletRequest request){
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
			mapCondition.put("curPage", iDisplayStart);
			mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
			
			HashMap strmap = adminUserForNoteBIZ.queryUserList(mapCondition);
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
		@RequestMapping(value = "/goUserList")
		public String goUserList(Model model){
			return "user/admin_user_list";
		}
		
		/**
		 * 前往保存页面
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/goUserSave")
		public String goUserSave(Model model,HttpServletRequest request){
			String id = request.getParameter("id");
			if(id != null && id != "null" && !"".equals(id)){
				User ac =  adminUserForNoteBIZ.queryUserById(id);
				model.addAttribute("user", ac);
				model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
			}
			return "user/admin_user_save";
		}
		
		/**
		 * 保存马甲
		 * @param model
		 * @param request
		 * @return
		 * @throws SQLException
		 * @throws ParseException
		 * @throws IOException 
		 */
		@RequestMapping(value = "/saveUser")
		@ResponseBody
		public Model saveUser(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
			String id = request.getParameter("id");
			User ac = new User();
			String phone = request.getParameter("phone");
			ac.setName(request.getParameter("name"));
			ac.setPhone(phone);
			ac.setSex(Integer.parseInt(request.getParameter("sex")));
			String imgUrl = request.getParameter("imgUrl");
			if(imgUrl != null && !"".equals(imgUrl) && imgUrl != "null"){
				if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
					ac.setImgUrl(imgUrl.substring(33, 59));
				}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
					ac.setImgUrl(imgUrl.substring(25, 51));
				}
			}
			
			try {
				if(id != "null" && id != null && !"".equals(id)){
					    ac.setId(Long.parseLong(id));
					    adminUserForNoteBIZ.updateUser(ac);
						model.addAttribute("flag", "success");
				}else{
					Long uuid = IdWorker.getId();
					ac.setId(uuid);
					if(phone != "" && phone != null){
					if(adminUserForNoteBIZ.queryUserByPhone(phone) == 0){
						adminUserForNoteBIZ.saveUser(ac);
						adminUserForNoteBIZ.saveDevice(IdWorker.getId(), uuid);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此号码!");
					}
				  }else{
					    ac.setPhone(null);
					    adminUserForNoteBIZ.saveUser(ac);
						adminUserForNoteBIZ.saveDevice(IdWorker.getId(), uuid);
						model.addAttribute("flag", "success");
				  }
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("flag", "fail");
				return null;
			}
			return model;
		}
		
		/**
		 * 删除
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/deleteUser",method=RequestMethod.POST)
		@ResponseBody
		public Model deleteUser(Model model,HttpServletRequest request){
			String id = request.getParameter("id");
			try {
				if(id != null && !"".equals(id) && id != "null"){
					adminUserForNoteBIZ.deleteUser(id);
					adminNoteBIZ.updateNoteByUserId(Long.parseLong(id));
					model.addAttribute("flag", "success");
				}else{
					model.addAttribute("flag", "fail");
					return model;
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("flag", "fail");
			}
			return model;
		}
		
//		public static void main(String[] args) {
//		String a = "http://image.mperfit.com/user/20170220190448975.jpg";
//		System.out.println(a.substring(25, 51));
//	}
}
