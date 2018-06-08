package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.area.AreaBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.vo.DataModel;

/**
 * 区域模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/area")
public class AreaController {   
	
	@Autowired
	private AreaBIZ areaBIZ;
	
	/**
	 * 查找可选区域
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAreaList",method=RequestMethod.POST)
	@ResponseBody
	public Object getAreaList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashedMap();
		try{
			List<Area> areaList = areaBIZ.queryAreaListNew();
			map.put("areaList", areaList);
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！", map, request);
		}catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 根父级查找子集地址目录
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEdificeList",method=RequestMethod.POST)
	@ResponseBody
	public Object getEdificeList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashedMap();
		
		HashMap requestMap = new HashMap();
		String pid = request.getParameter("pid");
		requestMap.put("id",pid);
		try{
			List<DataModel> dataModeList = areaBIZ.queryEdificeList(requestMap);
			map.put("edificeList", dataModeList);
			return  MapResult.build(MapResult.CODE_SUCCESS, "成功！", map, request);
		}catch (Exception e) {
			// TODO: handle exception
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
