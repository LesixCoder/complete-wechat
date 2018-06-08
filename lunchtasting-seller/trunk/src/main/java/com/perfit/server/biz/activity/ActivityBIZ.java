package com.perfit.server.biz.activity;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.po.lt.Activity;

public interface ActivityBIZ {
       Long addActivity(Activity activity);
       Activity queryActivityById(String id);
       Long updateActivity(Activity activity);
       HashMap queryActivity(HashMap map);
       Boolean deleteActivity(HashMap map);
       /**
        * 验证活动是否属于商家
        * 
        */
       boolean authenticationUser(int activityId,int sellerId);
       
}
