package com.lunchtasting.server.ocr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * baidu识别图片技术
 * Created on 2017-7-7
 * @author xuqian
 *
 */
public class OcrImageHelper {

	 //设置APPID/AK/SK
    public static final String APP_ID = "9855187";
    public static final String API_KEY = "GQikGREVj5zGbcEuBQbC5fma";
    public static final String SECRET_KEY = "NbkG0MFDNqFmXfTabTewK1ZBIrBUfmVU";
    
    
    public static final String APP_ID2 = "9855187";
    public static final String API_KEY2 = "GQikGREVj5zGbcEuBQbC5fma";
    public static final String SECRET_KEY2 = "NbkG0MFDNqFmXfTabTewK1ZBIrBUfmVU";
    
    public static final String APP_ID3 = "9855187";
    public static final String API_KEY3 = "GQikGREVj5zGbcEuBQbC5fma";
    public static final String SECRET_KEY3 = "NbkG0MFDNqFmXfTabTewK1ZBIrBUfmVU";
    
    static AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    static AipOcr client2 = new AipOcr(APP_ID2, API_KEY2, SECRET_KEY2);
    static AipOcr client3 = new AipOcr(APP_ID3, API_KEY3, SECRET_KEY3);

    /**
     * 多账号平均调用
     * @param number
     * @return
     */
    private AipOcr getAipOcr(int number){
    	AipOcr client = null;
    	if(number > 0 & number <= 500){
    		client = this.client;
    	}
    	if(number > 500 & number <= 1000){
    		client = this.client2;
    	}
    	if(number > 1000 & number <= 1500){
    		client = this.client3;
    	}
    	return client;
    }
    
    /**
     * 获得图片获取的关键字列表
     * @param imagePath
     * @param number
     * @return
     * @throws JSONException 
     */
    public List getWordsList(String imagePath,int number) throws JSONException{
    	if(ValidatorHelper.isEmpty(imagePath)){
    		return null;
    	}
    	AipOcr client = getAipOcr(number);
    	return basicGeneral(client,imagePath);
    }

    /**
     * 通用文字识别可以接受任意图片，并识别出图片中的文字以及全部文字串。
	 * 图片接受参数类型：支持本地图片路径字符串，图片文件二进制数组。
	 * 举例，要对一张图片进行文字识别，具体的文字的内容和信息在返回的words_result字段中：
     * @return
     * @throws JSONException 
     */
    private List basicGeneral(AipOcr client,String imagePath) throws JSONException{
    	List list = null;
        JSONObject response = client.basicGeneral(imagePath,new HashMap<String, String>());
        System.out.println(response.toString());
        if(response != null && !response.isNull("words_result_num")){
        	int resultNum = response.getInt("words_result_num");
        	if(resultNum > 0){
        		list = new ArrayList();
            	JSONArray array = response.getJSONArray("words_result");
            	for(int i=0; i<array.length(); i++){
            		/**
            		 * 组装数据
            		 */
            		JSONObject wordJson = array.getJSONObject(i); 
            		String work = wordJson.getString("words");
            		if(checkWord(work)){
                		list.add(work);
            		}            		
            	}
            	return list;
        	}
        }
    	return null;
    }
    
    
    /**
     * 网络图片文字识别用于识别一些网络上背景复杂，特殊字体的文字。
     * 图片接受参数类型：支持本地图片路径字符串，图片文件二进制数组。
     * 举例，要对一张网络图片进行文字识别，具体的文字的内容和信息在返回的words_result字段中：
     * @return
     */
    private List webImage(AipOcr client,String imagePath) throws JSONException{
    	List list = null;
    	JSONObject response = client.webImage(imagePath,new HashMap<String, String>());
    	 System.out.println(response.toString());
         if(response != null && !response.isNull("words_result_num")){
         	int resultNum = response.getInt("words_result_num");
         	if(resultNum > 0){
         		list = new ArrayList();
             	JSONArray array = response.getJSONArray("words_result");
             	for(int i=0; i<array.length(); i++){
             		/**
             		 * 组装数据
             		 */
             		JSONObject wordJson = array.getJSONObject(i); 
             		String work = wordJson.getString("words");
             		if(checkWord(work)){
                		list.add(work);
            		}   
             	}
             	return list;
         	}
         }
     	return null;
    }
    
    /**
     * 二次优化计算
     * @param str
     * @return
     */
    private boolean checkWord(String str){
    	if(ValidatorHelper.isEmpty(str)){
    		return false;
    	}
    	
    	if(!ValidatorHelper.isNumber(str)){
    		return false;
    	}
    	
    	int length = str.length();
    	if(length > 4 || length < 4){
    		return false;
    	}
    	return true;
    }
}
