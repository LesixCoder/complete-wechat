package com.lunchtasting.server.biz.note.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.note.NoteDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class NoteBIZImpl implements NoteBIZ {
	@Autowired 
	private NoteDAO notedao;
	@Override
	public Map getNoteById(Long noteId) throws ParseException {
		// TODO Auto-generated method stub
		Map map = notedao.getNoteById(noteId);
		if(map != null){
			
			if(ValidatorHelper.isNotNull(map.get("icon"))){
				map.put("icon", SysConfig.IMG_VISIT_URL+map.get("icon")
						+ QiNiuStorageHelper.MODEL1+"w/60/h/60");
			}
			/**
			 * 时间差
			 */
			if(ValidatorHelper.isNotNull(map.get("create_time"))){
				Date time = DateUtil.convertStringTODate(map.get("create_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
				String timeString = CommonHelper.getPerfitTime(time);
				map.put("time",timeString);
				map.remove("create_time");
			}
			if(ValidatorHelper.isNotNull(map.get("img_url"))){
				int height = Integer.parseInt(map.get("img_height").toString());
				if(height > 936){
					map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL1+"w/750/h/936");
				}else{
					map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL1+"w/750/h/"+height);
				}
			}
		}
		return map;
	}
	
	
	@Override
	public boolean addRunInfo(Long id) {
		// TODO Auto-generated method stub
		if(notedao.addRunInfo(id)==1){
			return true;
		}
		return false;
	}

}
