package com.lunchtasting.server.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@Deprecated
public class Config4ThreadPool {

	private static final Log log = LogFactory.getLog(Config4ThreadPool.class);
	
	public static int proMin;
	public static int proMax;
	public static int proTimeout;
	public static int proWorkQueue;
	
	public static int commMin;
	public static int commMax;
	public static int commTimeout;
	public static int commWorkQueue;
	
	public static int regMin;
	public static int regMax;
	public static int regTimeout;
	public static int regWorkQueue;
	
	public static boolean isLoaded;
	
	public static void getConfig(){
		System.out.println("isLoaded = " +isLoaded);
		if(!isLoaded){
			try {
				String _proMin = System.getProperty(Config4ThreadPool.TPConfigService.proMin);
				String _proMax = System.getProperty(Config4ThreadPool.TPConfigService.proMax);
				String _proTimeout = System.getProperty(Config4ThreadPool.TPConfigService.proTimeout);
				String _proWorkQueue = System.getProperty(Config4ThreadPool.TPConfigService.proWorkQueue);
			
				String _commMin = System.getProperty(Config4ThreadPool.TPConfigService.commentMin);
				String _commMax = System.getProperty(Config4ThreadPool.TPConfigService.commentMax);
				String _commTimeout = System.getProperty(Config4ThreadPool.TPConfigService.commentTimeout);
				String _commWorkQueue = System.getProperty(Config4ThreadPool.TPConfigService.commentWorkQueue);
				
				String _regMin = System.getProperty(Config4ThreadPool.TPConfigService.regMin);
				String _regMax = System.getProperty(Config4ThreadPool.TPConfigService.regMax);
				String _regTimeout = System.getProperty(Config4ThreadPool.TPConfigService.regTimeout);
				String _regWorkQueue = System.getProperty(Config4ThreadPool.TPConfigService.regWorkQueue);
				
				InputStream inputStream = Config4ThreadPool.class.getClassLoader().getResourceAsStream("threadpool.properties");
				
				if(inputStream != null){
					Properties p = new Properties();        
			        p.load(inputStream);
					proMin = (_proMin == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.proMin)): Integer.parseInt(_proMin);
					proMax = (_proMax == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.proMax)): Integer.parseInt(_proMax);
					proTimeout = (_proTimeout == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.proTimeout)):Integer.parseInt(_proTimeout);
					proWorkQueue = (_proWorkQueue == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.proWorkQueue)):Integer.parseInt(_proWorkQueue);
					
					commMin = (_commMin == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.commentMin)) : Integer.parseInt(_commMin);
					commMax = (_commMax == null) ? Integer.valueOf(p.getProperty(Config4ThreadPool.TPConfigService.commentMax)).intValue() : Integer.parseInt(_commMax);
					commTimeout = (_commTimeout == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.commentTimeout)) : Integer.parseInt(_commTimeout);
					commWorkQueue = (_commWorkQueue == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.commentWorkQueue)):Integer.parseInt(_commWorkQueue);
					
					regMin = (_regMin == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.regMin)) : Integer.parseInt(_regMin);
					regMax = (_regMax == null) ? Integer.valueOf(p.getProperty(Config4ThreadPool.TPConfigService.regMax)).intValue() : Integer.parseInt(_regMax);
					regTimeout = (_regTimeout == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.regTimeout)) : Integer.parseInt(_regTimeout);
					regWorkQueue = (_regWorkQueue == null) ? Integer.parseInt(p.getProperty(Config4ThreadPool.TPConfigService.regWorkQueue)):Integer.parseInt(_regWorkQueue);
					
				}
				
			}catch(Exception e){
				log.error("线程池参数加载失败:"+e.getMessage(),e);
			}
			isLoaded = true;
			
		}
	}
	
	
	
	public static class TPConfigService{
	    public final static String proMin = "product.corePoolSize";
	    public final static String proMax = "product.maximumPoolSize";
	    public final static String proTimeout = "product.keepAliveTime";
	    public final static String proWorkQueue = "product.workQueueSize";
	    
	    public final static String commentMin = "comment.corePoolSize";
	    public final static String commentMax = "comment.maximumPoolSize";
	    public final static String commentTimeout = "comment.keepAliveTime";
	    public final static String commentWorkQueue = "comment.workQueueSize";
	    
	    public final static String regMin = "reg.corePoolSize";
	    public final static String regMax = "reg.maximumPoolSize";
	    public final static String regTimeout = "reg.keepAliveTime";
	    public final static String regWorkQueue = "reg.workQueueSize";
	 }  
}
