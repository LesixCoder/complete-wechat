package com.lunchtasting.server.biz.seller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.seller.SellerDAO;
import com.lunchtasting.server.dao.seller.SellerDeviceDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.po.lt.SellerDevice;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class SellerBIZImpl implements SellerBIZ {

	@Autowired
	private SellerDAO sellerDAO;
	@Autowired
	private SellerDeviceDAO sellerDeviceDAO;

	@Override
	public Seller getByAccountAndPwd(String account, String pwd) {
		return sellerDAO.getByAccountAndPwd(account, pwd);
	}

	@Override
	public Map login(Long sellerId, HttpServletRequest request)
			throws Exception {
		/**
		 * 判断商家是否登录过
		 */
		String authId = "";
		String authCode = "";
		Long deviceId = sellerDeviceDAO.getDeviceId(sellerId);
		if(deviceId == null){
			
			/**
			 * 创建设备信息
			 */
			String accessToken = RequestAuthHelper.getAccessToken(sellerId);
			authId = EncryptAuth.encode(sellerId);
			authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
			SellerDevice device = getDevice(sellerId,authId,accessToken,request);
			sellerDeviceDAO.create(device);
			if(ValidatorHelper.isEmpty(device.getId())){
				return null;
			}
		}else{
			
			/**
			 * 修改设备信息
			 */
			String accessToken = RequestAuthHelper.getAccessToken(sellerId);
			authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
			authId = EncryptAuth.encode(sellerId);
			SellerDevice device = getDevice(sellerId,authId,accessToken, request);
			if(sellerDeviceDAO.updateDevice(device) == 0){
				return null;
			}
		}
		
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		map.put("seller_id", sellerId);
		return map;
	}
	
	
	/**
	 * 获得设备实体
	 * @param sellerId
	 * @param authId
	 * @param accessToken
	 * @param request
	 * @return
	 */
	private SellerDevice getDevice(Long sellerId,String authId,String accessToken,HttpServletRequest request){
		SellerDevice device = new SellerDevice();
		device.setSellerId(sellerId);
		device.setAuthId(authId);
		device.setAccessToken(accessToken);
		device.setSystemType(request.getHeader("system_type"));
		device.setSystemVersion(request.getHeader("system_version"));
		device.setScreenWidth(Integer.parseInt(request.getHeader("screen_width").toString()));
		device.setScreenHeight(Integer.parseInt(request.getHeader("screen_height").toString()));
		device.setPlatform(request.getHeader("platform").toString());
		device.setDeviceToken(request.getHeader("device_token"));
		device.setChannel(request.getHeader("channel"));
		device.setImei(request.getHeader("imei"));
		device.setAppVersion(request.getHeader("app_version"));
		return device;
	}

	@Override
	public Integer getSellerCount(Long areaId) {
		return sellerDAO.getSellerCount(areaId);
	}

	@Override
	public List querySellerList(Long areaId, Double longitude, Double latitude,
			Integer page, Integer pageSize) {
		List list = sellerDAO.querySellerList(areaId, longitude, latitude, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/340/h/288");
				
				/**
				 * 随机数，做颜色标签用
				 */
				map.put("random", CommonHelper.getRandomColor(map.get("seller_id").toString()));
				
				
				/**
				 * 地理位置
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("distance"))){
					map.put("distance", CommonHelper.getDistance(Double.parseDouble(map.get("distance").toString())));
				}else{
					map.put("distance", "");
				}
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Map getSellerDetail(Long sellerId,Long userId) {
		Map map = sellerDAO.getSellerDetail(sellerId,userId);
		if(map != null){
			
			/**
			 * 图片
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
				List imgList = new ArrayList();
				String imgArray = map.get("img_array").toString();
				for(String img : imgArray.split(",")){
					imgList.add(SysConfig.IMG_VISIT_URL+img
							+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
				}
				map.put("img_list", imgList);
				map.remove("img_array");
			}
			
			/**
			 * 判断是否收藏
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("is_collect"))){
				map.put("is_collect", 1);
			}else{
				map.put("is_collect", 0);
			}
		}
		
		return map;
	}

	@Override
	public List queryRecommendSellerList(Integer size) {
		List list = sellerDAO.queryRecommendSellerList(size);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/234/h/234");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	
}
