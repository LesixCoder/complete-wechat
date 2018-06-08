package com.lunchtasting.server.util;

public class GetEmailUrl {
		
		public static String getEmailUrl(String email){
			String extra = getExtra(email);
			if(extra != null){
				if(extra.indexOf("yahoo") != -1){
					return "http://mail.cn.yahoo.com";
				}
				if(extra.indexOf("gmail") != -1){
					return "http://mail.google.com";
				}
				
				if(isSpecEmail(extra)){
					return "http://mail."+extra;
				}
			}			
			return "http://www."+extra;
		}
		
		private static String getExtra(String email){
			if(email != null){
				int pos = email.lastIndexOf("@");
				return email.substring(pos + 1);
			}
			return null;
		}
		
		
		private static boolean isSpecEmail(String extra){
			String [] specEmail = {"sina","163","126","qq","139","189"};
			for(String str:specEmail){
				if(extra.indexOf(str) != -1){
					return true;
				}
			}
			return false;
			
		}
}
