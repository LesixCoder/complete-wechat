package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.po.lt.Activity;

public interface AdminActivityBIZ {
       Long addActivity(Activity activity);
       Activity queryActivityById(String id);
       Long updateActivity(Activity activity);
       HashMap queryActivity(HashMap map);
       Long deleteActivity(HashMap map);
       int topActivity(HashMap map);
       List queryActivityNotLimit();
}
