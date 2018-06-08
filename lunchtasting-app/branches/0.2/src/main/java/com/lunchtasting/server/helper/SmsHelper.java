//package com.lunchtasting.server.helper;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
///**
// * 
// * @author 周爽
// * 
// */
//public class SmsHelper {
//	
//	public static final String SMS_KEY = "7d6be0c6bff4b1e0eefff7e2ee563d01";
//	public static final String SMS_WEBRESOURCE_STATUS_JSON= "http://sms-api.luosimao.com/v1/status.json";
//	public static final String SMS_WEBRESOURCE_SEND_JSON= "http://sms-api.luosimao.com/v1/send.json";
//	
//	public static void main(String[] args) {
//		SmsHelper sms = new SmsHelper();
//        String httpResponse =  sms.testSend();
//         try {
//            JSONObject jsonObj = new JSONObject( httpResponse );
//            int error_code = jsonObj.getInt("error");
//            String error_msg = jsonObj.getString("msg");
//            if(error_code==0){
//                System.out.println("Send message success.");
//            }else{
//                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
//            }
//        } catch (JSONException ex) {
//            Logger.getLogger(SmsHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        httpResponse =  sms.testStatus();
//        try {
//            JSONObject jsonObj = new JSONObject( httpResponse );
//            int error_code = jsonObj.getInt("error");
//            if( error_code == 0 ){
//                int deposit = jsonObj.getInt("deposit");
//                System.out.println("Fetch deposit success :"+deposit);
//            }else{
//                String error_msg = jsonObj.getString("msg");
//                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
//            }
//        } catch (JSONException ex) {
//            Logger.getLogger(SmsHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//    }
//
//    private String testSend(){
//        // just replace key here
//        Client client = Client.create();
//        client.addFilter(new HTTPBasicAuthFilter("api" , SmsHelper.SMS_KEY));
//        WebResource webResource = client.resource(SMS_WEBRESOURCE_SEND_JSON);
//        MultivaluedMapImpl formData = new MultivaluedMapImpl();
//        formData.add("mobile", "18825241187");
//        formData.add("message", "验证码：月棱镜威力，变身！【辉太狼】");
//        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
//        post(ClientResponse.class, formData);
//        String textEntity = response.getEntity(String.class);
//        int status = response.getStatus();
////        System.out.print(textEntity);
////        System.out.print(status);
//        return textEntity;
//    }
//
//    private String testStatus(){
//        Client client = Client.create();
//        client.addFilter(new HTTPBasicAuthFilter("api" , SmsHelper.SMS_KEY));
//        WebResource webResource = client.resource(SmsHelper.SMS_WEBRESOURCE_STATUS_JSON);
//        MultivaluedMapImpl formData = new MultivaluedMapImpl();
//        ClientResponse response =  webResource.get( ClientResponse.class );
//        String textEntity = response.getEntity(String.class);
//        int status = response.getStatus();
////        System.out.print(status);
////        System.out.print(textEntity);
//        return textEntity;
//    }
//}
