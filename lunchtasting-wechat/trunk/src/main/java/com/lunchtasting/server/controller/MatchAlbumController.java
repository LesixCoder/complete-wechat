package com.lunchtasting.server.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.match.MatchAlbumBIZ;
import com.lunchtasting.server.biz.match.MatchAlbumTagBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.MatchAlbum;
import com.lunchtasting.server.po.lt.MatchAlbumTag;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 赛事照片
 * Created on 2017-6-13
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/match/album")
public class MatchAlbumController {

	@Autowired
	private MatchAlbumBIZ albumBIZ;
	@Autowired
	private MatchAlbumTagBIZ albumTagBIZ;
	
//	/**
//	 * 赛后图片批量上传（按参赛用户号码牌）
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/image/batch/upload")
//	@ResponseBody
//	public Object imageBatchUpload(Model model, HttpServletRequest request) throws IOException{
//		
//		String path = "D:\\matchImage\\upload\\20170627\\upload";
//		long matchId = 840043772388573183L;
//		long albumId = 1L;
//		
//		File file = new File(path);
//		if(file.exists()){
//			File[] files = file.listFiles();
//			for (File fileDirectory : files) {
//				
//				/**
//				 * 创建选手的照片集合
//				 */
//				System.out.println(fileDirectory.getName());
//				String code = fileDirectory.getName();
//				if(fileDirectory.isDirectory()){
//					File[] filesImage = fileDirectory.listFiles();		
//					if(filesImage != null){
//						for (File fileImage : filesImage) {
//							
//							if(fileImage.getName().indexOf("._") == -1 && fileImage.getName().indexOf(".DS") == -1){
//				
//								System.out.println(fileImage.getName() +"++" + fileImage.getAbsolutePath());
//								/**
//								 * 单个图片集上传
//								 */
//								String key = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.MATCH_ALBUM_IMAGE_PREFIX);
//								boolean result = QiNiuStorageHelper.upload(key, fileImage.getAbsolutePath());
//								if(result){
//									
//									/**
//									 * 获取图片高宽
//									 */
//									BufferedImage bufferedImage = ImageIO.read(fileImage);   
//									int width = bufferedImage.getWidth();   
//									int height = bufferedImage.getHeight();  
//									albumBIZ.createAlbumImage(albumId, matchId, code, key,width,height);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		return 100;
//	}
	
	/**
	 * 赛事相册列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String albumList(Model model, HttpServletRequest request) throws IOException{
		String name = request.getParameter("name");
		
		List list = albumBIZ.queryAlbumList(name,0,20);
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		
		return "/match/match_album_list";
	}
	
	/**
	 * 赛事相册照片列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/image/list")
	public String imageList(Model model, HttpServletRequest request) throws IOException{
		String aId = request.getParameter("album_id");
		String tId = request.getParameter("tag_id");
		String code = request.getParameter("code");
		String page = request.getParameter("page");
		if(!ValidatorHelper.isNumber(aId)){
			return VariableHelper.ERROR_JSP;
		}
		
		long albumId = Long.parseLong(aId);
		long userId = (long)request.getSession()
				.getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		
		MatchAlbum album = albumBIZ.getById(albumId);
		if(album == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("imgUrl",  SysConfig.IMG_VISIT_URL+album.getImgUrl()
					+ QiNiuStorageHelper.MODEL0+"w/750/h/540");
		model.addAttribute("album", album);
		

		StringBuilder parameter = new StringBuilder();
		parameter.append("album_id="+albumId);

		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
		}
		
		if(ValidatorHelper.isNotEmpty(code)){
			parameter.append("&code="+code);
		}
		
		Long tagId = null;
		if(ValidatorHelper.isNumber(tId)){
			tagId = Long.parseLong(tId);
			model.addAttribute("tagId", tagId);
			parameter.append("&tag_id="+tagId);
		}
		
		/**
		 * 总数页
		 */
		int totalPage = Utils.getTotalPage(albumBIZ.getAlbumImageCount(albumId,tagId, code),20);
		int previousPage = pPage - 1;
		if(previousPage <= 0){
			previousPage = 1;
		}
		
		int nextPage = pPage + 1;
		if(nextPage >= totalPage){
			nextPage = totalPage;
		}
		model.addAttribute("totalPage", totalPage);
		
		/**
		 * 总照片数
		 */
		int totalCount = albumBIZ.getAlbumImageCount(albumId,null,null);
		model.addAttribute("totalCount", totalCount);
		
//		/**
//		 * 标签列表
//		 */
//		List tagList = albumTagBIZ.queryAlbumTagList(albumId, userId);
//		model.addAttribute("tagList", tagList);
		
		/**
		 * 相册图片列表
		 */
		List imageList = albumBIZ.queryAlbumImageList(albumId, tagId,code,album.getIsLogo(), (pPage-1)*20, 20);
		model.addAttribute("imageList", imageList);
		model.addAttribute("currentPage", pPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("previousPage", previousPage);
		model.addAttribute("albumId", albumId);
		model.addAttribute("code", code);
		model.addAttribute("parameter", parameter.toString());
		
		
		/**
		 * 微信分享信息
		 */
		String ticket = WeChatHelper.getTicket(request);
		String timestamp = WeChatHelper.getTimeStamp();
		String nonceStr = WeChatHelper.getNonceStr();
		String url = "http://wchat.mperfit.com/match/album/image/list?album_id="+albumId;
		model.addAttribute("url", url);
		model.addAttribute("appId", WeChatConfig.APP_ID);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", nonceStr);
		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
		String sign = WeChatHelper.sign(params);
		model.addAttribute("signature", sign);
		
		return "/match/match_photos";
	}
	
//	/**
//	 * 图片列表（分页）
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/image/listMore")
//	@ResponseBody
//	public Object imageListMore(Model model, HttpServletRequest request) throws IOException{
//		String aId = request.getParameter("album_id");
//		String tId = request.getParameter("tag_id");
//		String code = request.getParameter("code");
//		String page = request.getParameter("page");
//		if(!ValidatorHelper.isNumber(aId)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		long albumId = Long.parseLong(aId);
//		
//		Long tagId = null;
//		if(ValidatorHelper.isNumber(tId)){
//			tagId = Long.parseLong(tId);
//		}
//		
//		int pPage = 1;
//		if(ValidatorHelper.isNumber(page)){
//			pPage = Integer.parseInt(page);
//		}
//		
//		int totalPage = Utils.getTotalPage(albumBIZ.getAlbumImageCount(albumId,tagId, code),3);
//		
//		/**
//		 * 相册图片列表
//		 */
//		List list = albumBIZ.queryAlbumImageList(albumId,tagId,code, (pPage-1)*3, 3);
//		Map map = new HashMap();
//		map.put("list", list);
//		map.put("total_page", totalPage);
//		map.put("current_page", pPage);	 
//		return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
//
//	}
	
	/**
	 * 照片详情
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/image/detail")
	public String imageDetail(Model model, HttpServletRequest request) throws IOException{
		String iId = request.getParameter("image_id");
		if(!ValidatorHelper.isNumber(iId)){
			return "";
		}
		long imageId = Long.parseLong(iId);
		
		
		return "";
	}
	
	/**
	 * 单张图片下载
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/image/upload")
	public String imageUpload(Model model, HttpServletRequest request) throws IOException{
		String iId = request.getParameter("image_id");
		if(!ValidatorHelper.isNumber(iId)){
			return "";
		}
		long imageId = Long.parseLong(iId);
		
		Map imageMap = albumBIZ.getAlbumImageMap(imageId);
		if(imageMap == null){
			return null;
		}
		model.addAttribute("image", imageMap);
		
		return "/match/match_photo_upload";
	}
	
}
