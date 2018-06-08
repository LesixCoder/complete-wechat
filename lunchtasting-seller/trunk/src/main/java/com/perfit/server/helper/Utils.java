package com.perfit.server.helper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {
	
	public static final String USER_REGISTER_AUTHCODE="authCode";
	
	public static final String USER_LOGINE_SESSION="userSession";
	
	public static final String USER_LOGINE_SESSION_DATA="userSessionData";
	
	public static final String USER_LOGINE_WEIXIN_DATA="vipUserSessionWeiXinData";
	
	public static final String USER_TEMP_SESSION_DATA="vipUserTempSessionData";
	
	public static final String ACTION_RESULT_SUCCESS="success";
	
	public static final String CHARSET_ENCODE="UTF-8";
	
		
	/***********************************
	 * 从输入流中读取信息
	 * @param in	输入流
	 * @return
	 ***********************************/
	public static final String inputStream2String(InputStream in){
		String resultStr="";
		try {
			if(in == null)
				return resultStr;
			
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n, CHARSET_ENCODE));
			}
			resultStr=out.toString();
		} catch (Exception e) {
            e.printStackTrace();
            resultStr="";
        }
		return resultStr;
	}
	
	/***********************************
	 * 从输入流中读取信息
	 * @param in	输入流
	 * @param size 	内容大小
	 * @return
	 ***********************************/
	public static final String inputStream2String(InputStream in,int size){
		String resultStr="";
		try {
			if(in == null)
				return resultStr;
			
			// 用于缓存每次读取的数据
            byte[] buffer = new byte[size];
            // 用于存放结果的数组
            byte[] xmldataByte = new byte[size];
          
            int count = 0;
            int rbyte = 0;
            // 循环读取
            while (count < size) { 
                // 每次实际读取长度存于rbyte中
                rbyte = in.read(buffer); 
                for(int i=0;i<rbyte;i++) {
                    xmldataByte[count + i] = buffer[i];
                }
                count += rbyte;
            }
            in.close();
            resultStr = new String(xmldataByte, CHARSET_ENCODE);
			
		} catch (Exception e) {
            e.printStackTrace();
            resultStr="";
        }
		return resultStr;
	}
	
	public static Map<String,String> param=new HashMap<String,String>();
	
	public static final String getProperty(String key)
	{
		String value="";
        try {
			
			if(param.get(key)!=null)
			{
				value=param.get(key).toString();
				return value;
			}
			
			//获取配置文件中的url信息
	        Properties prop = new Properties();
	        InputStream in =Utils.class.getClassLoader().getResourceAsStream("lunchtasting.properties");
	        prop.load(in);
	        
			if(prop.getProperty(key)!=null && !prop.getProperty(key).equals("")){
				
				value = prop.getProperty(key).trim();
				
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				param.put(key, value);
				
			}
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static final String getStringValue(String value){
		if (value==null || value.equals("null")) value="";
		return value;
	}
	
	public static final String getAppBaseUrl(){
	    return getPropertyValue("AppBaseUrl");
	}
	
	public static final String getAgentLogoImageUrl(String logoUrl){
		if (logoUrl==null || logoUrl.equals("null")) logoUrl="";
	    return getPropertyValue("AgentLogoBaseUrl")+getStringValue(logoUrl)+"_logo.jpg?checkCodePic=dfsAgent";
	}
	
	public static final String getAgentImageUrl(String imageUrl){
		if (imageUrl==null || imageUrl.equals("null")) imageUrl="";
	    return getPropertyValue("AgentImageUrl")+getStringValue(imageUrl);
	}
	
	public static final String getAgentItemImageUrl(String itemImageUrl){
		if (itemImageUrl==null || itemImageUrl.equals("null")) itemImageUrl="";
	    return getPropertyValue("AgentItemBaseUrl")+getStringValue(itemImageUrl);
	}
	
	public static final String getPropertyValue(String key){
		StringBuffer tempUrl = new StringBuffer(getProperty(key));
		 
	    if ((tempUrl.toString() == null) || (tempUrl.toString().trim().equals("")))
	    {
	        System.out.println(key+" property is no define!");
	    }
	 
	    return tempUrl.toString();
	}
	
	/*****************************************************************/
	/**
     * MD5 32位加密算法
     * @param str
     * @return
     */
    public static final String changeToMD5System(String str)
    {
   
    	str=changeToMD5(str)+"lUncH-TasTINg_6102-System";
    	
    	return changeToMD5(str);
    }
    
    /**
     * MD5 32位加密算法 
     * @return
     */
    public static final String changeToMD5(String str)
    {
        try
        {
            if(str==null) str="";
        	MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if(i < 0)
                    i += 256;
                if(i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return str;
    }
	
    public static final String filterCharacter(String value){
    	if(value!=null && !value.trim().equals("")){
    		try {
				value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
				value = java.net.URLDecoder.decode(value , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value=null;
			}
		}
    	return value;
    }
    
	public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(filePath);
        pps.load(in);
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        pps.store(out, "Update " + pKey + " name");
    }
	
	/**
     * 获取img标签中的src值
     * @param content
     * @return
     */
    public static List<String> getImgSrc(String content){
         
        List<String> list = new ArrayList<String>();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);
                 
                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                //结束匹配<img />标签中的src
                 
                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }
	
}
