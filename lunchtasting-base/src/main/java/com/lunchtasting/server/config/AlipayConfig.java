package com.lunchtasting.server.config;

/**
 * 支付宝支付参数
 * Created on 2016-11-3
 * @author xuqian
 *
 */
public class AlipayConfig {
	
	// 应用id
	public static String APP_ID = "2016101202114739";
	
	//商户id
	public static String SELLER_ID = "2088521047600221";
	
	// 私匙 pkcs8
	public static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO+4/BJ0gqi/aN/kXF+sFroDYM5iS71j1+xLSJdsuB2t47qcH/IS8KB6s5APEcjO8ieTwkY/0VPAoVbPlUs2xItM3FQ4ULU8Up5aYbaogZ7FL35v6VIFrN6TiIciWTsGJBI0d3U4oxNU2UuKHc9h+0Ow33f0AAUydKoCaUP46ydhAgMBAAECgYBQWuGkDgn+Jqsw4YtVRooXsCozBB//TAiE3Hdoyrisui0r0EmlC1Q9duPZ1jNP7I6fVZg5lArG2Ds8rjiNDb6zjN1KuHKrzsUnJby2A7EQ2Ph1V3VwP8VJWxvymvFCLSp13O1RAFPScixn34KdYiSBc3yBgVFTPCMnY8/gBHwXQQJBAP5IugO/OXDr9l+DWmOdasW6N235+LNt6fudWLt6WLc9SKX8dXmvOQVimed1JueZOT/MZ4zFBttI9lv/wqDy2ocCQQDxVxprCwBYiOUCd9xbjmG/lS411GjsMQ0zW9NVHNHrMDbrHE4VaRlyt3ZkEyizydpuHXKAvdSax1lTWxqOpWDXAkEAnvu7iYIymL5/EKKgje2rByrxYwpKC20qQM3U34qUEyT22gipLkZ2oT5PuV4Oz/iFybSz/VhmfA33MfgLqPON2wJACRAJSO2/1JNHbkFMABAnsYcLli/kxMHOyhfvpqmPZHKKDWghsoIg14L2KiniBGZXEn1+mEEIo8tKmmu4YpTpPwJBAPpYX4E6nNFObup2TlCKV07Vos0aAIF/C7DD+ZooP4DaRk26Vx5pyv4vufLod+Vj5lX6xTk8RrlOc8AagcIQgV8=";
	
	//公匙
	public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQABMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	
	//正式环境请求地址
	public static String REQUEST_URL = "https://openapi.alipay.com/gateway.do";
	//课程支付回调地址
	public static String COURSE_NOFIFY_URL = "http://app.mperfit.com/alipay/course/notify";
	//public static String COURSE_NOFIFY_URL = "http://test-app.mperfit.com/alipay/course/notify";
	
	
	
	//字符集
	public static String CHARSET = "utf-8";
}
