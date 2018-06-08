package com.lunchtasting.server.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.util.HttpUtil;

@Controller
public class JuheController extends BaseController{
	
	public static final String TOUTIAO_URL = "http://v.juhe.cn/toutiao/index?";
	public static final String KEY = "05689d14a641cf2326f54302ef613996";
	public static final String TOKEN = "b970163772862f29b00e1ca923719015";
	public static void main(String[] args) throws Exception {
//		String url = TOUTIAO_URL+"type=top"+"&key="+KEY+"";
//		String result = HttpUtil.queryStringForGet(url);
//		JSONObject jsonObject = JSON.parseObject(result);
//		JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("result"));
//		JSONArray jsonArray = JSON.parseArray(jsonObject1.getString("data"));
		String str = "赵艺圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣毛泽东人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣人神圣你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷你大爷";
		String url = "http://www.hoapi.com/index.php/Home/Api/check";
		String result = Utils.queryStringForPost(url, str);
		JSONObject jsonObject = JSON.parseObject(result);
		System.out.println(jsonObject);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getTouTiao")
	@ResponseBody
	public Model getTouTiao(Model model)
			throws SQLException {
		String url = TOUTIAO_URL+"type=top"+"&key="+KEY+"";
		String result = HttpUtil.queryStringForGet(url);
		if(result.length()==0||result==null||result.equals("")){
		   return null;
	    }
		JSONObject jsonObject = JSON.parseObject(result);
		JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("result"));
		JSONArray jsonArray = JSON.parseArray(jsonObject1.getString("data"));
		model.addAttribute("jsonArray", jsonArray);
		return model;
	}
}
