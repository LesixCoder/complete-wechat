package com.lunchtasting.server.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lunchtasting.server.biz.AdminAreaBIZ;
import com.lunchtasting.server.biz.AdminCourseCategoryBIZ;
import com.lunchtasting.server.biz.AdminCourseTemporaryBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.biz.AdminSellerTemporaryBIZ;
import com.lunchtasting.server.biz.AdminWeekDayBIZ;
import com.lunchtasting.server.biz.MatchScoreBIZ;
import com.lunchtasting.server.helper.GetXY;
import com.lunchtasting.server.helper.ImageHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.CourseTemporary;
import com.lunchtasting.server.po.lt.MatchScore;
import com.lunchtasting.server.po.lt.SellerTemporary;
import com.lunchtasting.server.po.lt.WeekDay;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;
@Controller
public class ImportController extends BaseController{
	@Autowired
	private AdminSellerTemporaryBIZ adminSellerTemporaryBIZ;
	@Autowired
	private AdminAreaBIZ adminAreaBIZ;
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	@Autowired
	private AdminCourseTemporaryBIZ adminCourseTemporaryBIZ;
	@Autowired
	private AdminCourseCategoryBIZ adminCourseCategoryBIZ;
	@Autowired
	private AdminWeekDayBIZ adminWeekDayBIZ;
	@Autowired
	private MatchScoreBIZ matchScoreBIZ;
	
	/**
	 * 商家导入
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@RequestMapping(value = "/excel/importSeller", method = RequestMethod.POST)
	@ResponseBody
	public void importSeller(HttpServletRequest request, HttpServletResponse response)
			throws IOException, FileUploadException {

		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "xls,xlsx");

		// 最大文件大小
		long maxSize = 6000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}

		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		Map map = mrequest.getFileMap();
		Collection<MultipartFile> c = map.values();
		Iterator item = c.iterator();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<WeekDay> weekDayList = adminWeekDayBIZ.getWNextDay(sdf.format(new Date()));
		
		try {
			while (item.hasNext()) {
				CommonsMultipartFile file=(CommonsMultipartFile) item.next();
				FileItem fileItem=file.getFileItem();
				long fileSize = file.getSize();
				if (!fileItem.isFormField()) {
					// 检查文件大小
					if (fileSize > maxSize) {
						out.println(getError("上传文件大小超过限制。"));
						return;
					}
					// 检查扩展名
					String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();  
					if (!Arrays.<String> asList(extMap.get(request.getParameter("dir")).split(","))
							.contains(fileExt)) {
						out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
								+ extMap.get(request.getParameter("dir")) + "格式。"));
						return;
					}
					
					Workbook workbook = null;
					if("xls".equals(fileExt)){
						workbook = new HSSFWorkbook(fileItem.getInputStream());
					}else if("xlsx".equals(fileExt)){
						workbook = new XSSFWorkbook(fileItem.getInputStream());
					}
					
					if(!file.getOriginalFilename().startsWith("商家")){
						out.println(getError("导入模板不正确!请重新导入!"));
						return;
					}
					
					String str = "\r\n";
					//标记错误信息
					String wrongSeller = "";
					
						for (int j = 0,s = workbook.getNumberOfSheets(); j < s; j++) {
							SellerTemporary st = null;
							st = new SellerTemporary();
							//标记错误信息
//							int wrongSeller = 0;
//							boolean flag = true;
							Sheet sheet = workbook.getSheetAt(j);
							int totalRowNum = sheet.getLastRowNum();
							if (workbook.getSheetName(j) != null) {
								for (int i = 3; i <= totalRowNum; i++) {
									Row row = sheet.getRow(i);
									if(row != null){
									if("".equals(getCellValue(row.getCell(0)))){
										continue;
									}else{
									  if(adminSellerBIZ.querySellerByName(getCellValue(row.getCell(0)).trim()) == null){
										Cell cell0 = row.getCell(0);
										Cell cell1 = row.getCell(1);
										Cell cell2 = row.getCell(2);
										Cell cell3 = row.getCell(3);
										Cell cell4 = row.getCell(4);
										Cell cell5 = row.getCell(5);
										Cell cell6 = row.getCell(6);
										Cell cell7 = row.getCell(7);
//										Cell cell8 = row.getCell(8);
										
										st.setId(IdWorker.getId());
										st.setName(getCellValue(cell0));
										st.setIntroduction(getCellValue(cell1));
										st.setAddress(getCellValue(cell3));
										st.setSpecificAddress(getCellValue(cell4));
										st.setPhone(getCellValue(cell5).trim());
										st.setBusinessHours(getCellValue(cell6));
										st.setTag(getCellValue(cell7));
										
										//区域id
										if(!"".equals(cell2.toString()) && null != cell2){
											if(adminAreaBIZ.queryAreaByName(cell2.toString()) != null){
												st.setAreaId(adminAreaBIZ.queryAreaByName(cell2.toString()).getId());
											}else{
												st.setAreaId(null);
											}
//											if(adminAreaBIZ.queryAreaByName(cell2.toString()).getId() != null && !adminAreaBIZ.queryAreaByName(cell2.toString()).getId().toString().equals("")){
//												st.setAreaId(adminAreaBIZ.queryAreaByName(cell2.toString()).getId());
//											}else{
//												st.setAreaId(null);
//											}
										}else{
											st.setAreaId(null);
										}
										
										//经纬度
										if(!"".equals(cell4.toString()) && null != cell4){
											String address = cell4.toString().replaceAll("\\s*", "").trim();
											st.setLongitude(Double.parseDouble(GetXY.getGaoDeXY(address).split(",")[0]));
											st.setLatitude(Double.parseDouble(GetXY.getGaoDeXY(address).split(",")[1]));
										}else{
											st.setLongitude(0.0);
											st.setLatitude(0.0);
										}
										
										//图片上传
										/*if(!"".equals(cell8.toString()) && null != cell8){
											StringBuffer sb = null;
											String[]  imgs = cell8.toString().split("，");
											for(int k = 0,L = imgs.length;k<L;k++){
												String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.SELLER_IMG_PREFIX);
												if("/".equals(File.separator)){
													if(k == 0){
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[0], 1, "jpg")) == true){
															st.setImgUrl(imgName);
														}
														sb = new StringBuffer();
														sb.append(imgName).append(",");
													}else{
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[k], 1, "jpg")) == true){
															sb.append(imgName).append(",");
														}
													}
												}else if("\\".equals(File.separator)){
													if(k == 0){
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[0].replaceAll("/", "\\\\"), 1, "jpg")) == true){
															st.setImgUrl(imgName);
														}
														sb = new StringBuffer();
														sb.append(imgName).append(",");
													}else{
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[k].replaceAll("/", "\\\\"), 1, "jpg")) == true){
															sb.append(imgName).append(",");
														}
													}
												}
											}
											st.setImgArray(sb.toString().substring(0, sb.toString().length()-1));
										}else{
											st.setImgUrl("");
											st.setImgArray("");
										}*/
										
										//默认结算日期
										if(weekDayList.get(0).getwFlag() == 0){
											st.setAttribute06(new Date());
										}else{
											st.setAttribute06(weekDayList.get(0).getwLastDay());
										}
										
											//插入数据
											adminSellerTemporaryBIZ.addSellerTemporary(st);
											
										}else{
											wrongSeller += ""+str+"【"+getCellValue(row.getCell(0))+"】";
										}
								      }
									}
								}
							}
						}
					
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					if("".equals(wrongSeller)){
						obj.put("message", "导入成功!");
					}else{
						obj.put("message", "导入成功!"+str+"已有名称为"+wrongSeller+""+str+"的商家!请核对!");
					}
					out.println(obj.toJSONString());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(getError("导入excel失败。"));
			return;
		}
		
}
	
	/**
	 * 课程导入
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@RequestMapping(value = "/excel/importCourse", method = RequestMethod.POST)
	@ResponseBody
	public void importCourse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, FileUploadException {

		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "xls,xlsx");

		// 最大文件大小
		long maxSize = 6000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}

		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		Map map = mrequest.getFileMap();
		Collection<MultipartFile> c = map.values();
		Iterator item = c.iterator();
		
		try {
			while (item.hasNext()) {
				CommonsMultipartFile file=(CommonsMultipartFile) item.next();
				FileItem fileItem=file.getFileItem();
				long fileSize = file.getSize();
				if (!fileItem.isFormField()) {
					// 检查文件大小
					if (fileSize > maxSize) {
						out.println(getError("上传文件大小超过限制。"));
						return;
					}
					
					// 检查扩展名
					String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();  
					if (!Arrays.<String> asList(extMap.get(request.getParameter("dir")).split(","))
							.contains(fileExt)) {
						out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
								+ extMap.get(request.getParameter("dir")) + "格式。"));
						return;
					}
					
					Workbook workbook = null;
					if("xls".equals(fileExt)){
						workbook = new HSSFWorkbook(fileItem.getInputStream());
					}else if("xlsx".equals(fileExt)){
						workbook = new XSSFWorkbook(fileItem.getInputStream());
					}
					
					/*if(!file.getOriginalFilename().equals("课程模板.xls") && !file.getOriginalFilename().equals("课程模板.xlsx")){
						out.println(getError("导入模板不正确!请重新导入!"));
						return;
					}*/
					
					if(!file.getOriginalFilename().startsWith("课程")){
					   out.println(getError("导入模板不正确!请重新导入!"));
					   return;
				    }
					
					String str = "\r\n";
					//标记错误信息
					String wrongCourse = "";
					
						for (int j = 0,s = workbook.getNumberOfSheets(); j < s; j++) {
							CourseTemporary ct = null;
							ct = new CourseTemporary();
							Sheet sheet = workbook.getSheetAt(j);
							int totalRowNum = sheet.getLastRowNum();
							if (workbook.getSheetName(j) != null) {
								for (int i = 3; i <= totalRowNum; i++) {
									Row row = sheet.getRow(i);
									if(row != null){
									if ("".equals(getCellValue(row.getCell(0)))) {
									    continue;
									}else{
									  if(adminSellerBIZ.querySellerByName(getCellValue(row.getCell(0)).trim()) != null){
										ct.setSellerId(adminSellerBIZ.querySellerByName(getCellValue(row.getCell(0)).trim()).getId());
										Cell cell1 = row.getCell(1);
										Cell cell2 = row.getCell(2);
										Cell cell3 = row.getCell(3);
										Cell cell4 = row.getCell(4);
										Cell cell5 = row.getCell(5);
										Cell cell6 = row.getCell(6);
										Cell cell7 = row.getCell(7);
										Cell cell8 = row.getCell(8);
//										Cell cell9 = row.getCell(9);
//										Cell cell10 = row.getCell(10);
										
										ct.setId(IdWorker.getId());
										ct.setTitle(getCellValue(cell1));
										ct.setName(getCellValue(cell2));
										ct.setContent(getCellValue(cell3));
										
										SimpleDateFormat sdf = null;
										
										if(!"".equals(cell4.toString().trim()) && null != cell4){
											if(adminCourseCategoryBIZ.getCategoryByName(cell4.toString().trim()) != null){
												ct.setCategoryId(adminCourseCategoryBIZ.getCategoryByName(cell4.toString().trim()).getId());
											}else{
												ct.setCategoryId(null);
											}
										}else{
											ct.setCategoryId(null);
										}
										
										//课程时间起
										if(!"".equals(cell7.toString()) && null != cell7){
											if (cell7.getCellStyle().getDataFormat() == HSSFDataFormat  
							                        .getBuiltinFormat("h:mm")) {  
							                    sdf = new SimpleDateFormat("HH:mm");  
							                } else {// 日期  
							                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
							                }  
											Date date = cell7.getDateCellValue();
											ct.setBeginTime(sdf.format(date));
										}else{
											ct.setBeginTime("");
										}
										
										//课程时间止
										if(!"".equals(cell8.toString()) && null != cell8){
											if (cell8.getCellStyle().getDataFormat() == HSSFDataFormat  
							                        .getBuiltinFormat("h:mm")) {  
							                    sdf = new SimpleDateFormat("HH:mm");  
							                } else {// 日期  
							                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
							                }  
											Date date = cell8.getDateCellValue();
											ct.setEndTime(sdf.format(date));
										}else{
											ct.setEndTime("");
										}
											
										//商家id
										/*if(!"".equals(cell0.toString()) && null != cell0){
											if(adminSellerBIZ.querySellerByName(cell0.toString().trim()) != null){
												ct.setSellerId(adminSellerBIZ.querySellerByName(cell0.toString().trim()).getId());
											}else{
												ct.setSellerId(null);
											}
										}else{
											out.println(getError("名称为【"+cell0.toString()+"】的商家不存在!"));
											return;
										}*/
										
										//平台价格
										if(!"".equals(cell5.toString()) && null != cell5){
											ct.setPrice(Double.parseDouble(getCellValue(cell5)));
										}else{
											ct.setPrice(null);
										}
										
										//市场价格
										if(!"".equals(cell6.toString()) && null != cell6){
											ct.setMarketPrice(Double.parseDouble(getCellValue(cell6)));
										}else{
											ct.setMarketPrice(null);
										}
										
										//图片上传(单图)
										/*if(!"".equals(cell9.toString()) && null != cell9){
											String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.COURSE_IMG_PREFIX);
											if("/".equals(File.separator)){
												if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(cell9.toString(), 1, "jpg")) == true){
													ct.setImgUrl(imgName);
												}
											}else{
												if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(cell9.toString().replaceAll("/", "\\\\"), 1, "jpg")) == true){
													ct.setImgUrl(imgName);
												}
											}
										}else{
											ct.setImgUrl("");
										}*/
										
										//图片上传(多图)
										/*if(!"".equals(cell10.toString()) && null != cell10){
											StringBuffer sb = new StringBuffer();
											String[]  imgs = cell10.toString().split("，");
											for(int k = 0,L = imgs.length;k<L;k++){
												String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.COURSE_IMG_PREFIX);
												if("/".equals(File.separator)){
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[k], 1, "jpg")) == true){
															sb.append(imgName).append(",");
														}
													}else if("\\".equals(File.separator)){
														if (QiNiuStorageHelper.byteUpload(imgName, ImageHelper.scaleImageUrl(imgs[k].replaceAll("/", "\\\\"), 1, "jpg")) == true){
															sb.append(imgName).append(",");
														}
												}
											}
											ct.setImgArray(sb.toString().substring(0, sb.toString().length()-1));
										}else{
											ct.setImgArray("");
										}*/
										
										    //插入数据
											adminCourseTemporaryBIZ.addCourseTemporary(ct);
											
										}else{
											wrongCourse += ""+str+"【"+getCellValue(row.getCell(0))+"】";
										}
								    }
								  }
								}
							}
						}
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					if("".equals(wrongCourse)){
						obj.put("message", "导入成功!");
					}else{
						obj.put("message", "导入成功!"+str+"名称为"+wrongCourse+""+str+"的商家不存在!请核对!");
					}
					out.println(obj.toJSONString());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(getError("导入excel失败。"));
			return;
		}
		
}
	
	/**
	 * 成绩导入
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@RequestMapping(value = "/excel/importScore", method = RequestMethod.POST)
	@ResponseBody
	public void importScore(HttpServletRequest request, HttpServletResponse response)
			throws IOException, FileUploadException {

		PrintWriter out = response.getWriter();
		String matchIdString = request.getParameter("matchId");
		Long matchId = null;
		
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "xls,xlsx");
		// 最大文件大小
		long maxSize = 6000000;
		response.setContentType("text/html; charset=UTF-8");
		if(!ValidatorHelper.isNumber(matchIdString)){
			out.println(getError("参数有误"));
			return;
		}
		matchId=Long.parseLong(matchIdString);
		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Map map = mrequest.getFileMap();
			Collection<MultipartFile> c = map.values();
			Iterator item = c.iterator();
			
			if(!item.hasNext()){
				out.println(getError("文件解析失败"));
				return;
			}
			
			CommonsMultipartFile file=(CommonsMultipartFile) item.next();
			FileItem fileItem=file.getFileItem();
			long fileSize = file.getSize();
			//判断是否是文件域
			if (fileItem.isFormField()){
				out.println(getError("文件解析失败"));
				return;
			}
			
			// 检查文件大小
			if (fileSize > maxSize) {
				out.println(getError("上传文件大小超过限制。"));
				return;
			}
			
			// 检查扩展名
			String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();  
			if (!Arrays.<String> asList(extMap.get("file").split(","))
					.contains(fileExt)) {
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
						+ extMap.get("file") + "格式。"));
				return;
			}
			
			Workbook workbook = null;
			if("xls".equals(fileExt)){
				workbook = new HSSFWorkbook(fileItem.getInputStream());
			}else if("xlsx".equals(fileExt)){
				workbook = new XSSFWorkbook(fileItem.getInputStream());
			}
			Sheet sheet = workbook.getSheetAt(0);
			
			String wrongCourse="";
			for (int i = 2;sheet.getRow(i)!=null ; i++){
				Row row = sheet.getRow(i);
				String tel = getCellValue(row.getCell(2));
				Integer rank=Integer.parseInt(getCellValue(row.getCell(0)));
				Integer score = Integer.parseInt(getCellValue(row.getCell(6)));
				matchScoreBIZ.activityScore(tel, matchId, score);
			}
			
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("message", "导入成功!");
			out.println(obj.toJSONString());
		
		} catch (Exception e) {
			e.printStackTrace();
			out.println(getError("文件解析失败"));
			return;
		}
}
	/**
	 * 返回错误信息
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		
		DecimalFormat df = new DecimalFormat("0");
		
		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue().trim();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(df.format(cell.getNumericCellValue()));

		}

		return "";
	}
}
