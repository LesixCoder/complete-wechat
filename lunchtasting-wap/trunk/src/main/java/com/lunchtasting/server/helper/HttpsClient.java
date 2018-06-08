package com.lunchtasting.server.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class HttpsClient {
	
	//X509TrustManager
	private dfsX509TrustManager xtm;
	
	//HostnameVerifier
    private dfsHostnameVerifier hnv;
    
    public HttpsClient() {
        SSLContext sslContext = null;
        try {
        	
        	xtm = new dfsX509TrustManager();
        	hnv = new dfsHostnameVerifier();
        	
            sslContext = SSLContext.getInstance("TLS"); //或SSL
            X509TrustManager[] xtmArray = new X509TrustManager[] {xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }
    
    public String call(String url) {
    	return call(url,null,null);
    }
    
    
    public String call(String url,String requestMethod,byte[] content) {
    	String result="";
    	sun.net.www.protocol.https.HttpsURLConnectionImpl urlCon = null;
        try {
        	URLConnection urlConn=(new URL(url)).openConnection();
            urlCon = (sun.net.www.protocol.https.HttpsURLConnectionImpl)urlConn;
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod((requestMethod==null?"GET":"POST"));
            
            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            if(content==null || content.length==0)
            	urlCon.setRequestProperty("Content-Length", "1024");
            else{
            	
            	urlCon.setRequestProperty("Content-Length", String.valueOf(content.length));
            }
            urlCon.setUseCaches(false);
            urlCon.setDoInput(true);
            
            OutputStreamWriter osw = new OutputStreamWriter(urlCon.getOutputStream(), "UTF-8");
            if(content==null || content.length==0)
            	osw.write("");
            else{
            	osw.write(new String(content,"UTF-8"));
            }
            osw.flush();
            osw.close();
            
            //
            byte[] resBuffer=getReceiveContent(urlCon.getInputStream());
            
            //System.out.println("接收回应报文完毕");
            if(resBuffer!=null)
            {	
            	//System.out.println(new String(resBuffer));
            	result=new String(resBuffer,"UTF-8");
            	
            }
            else
            {
            	System.out.println("核心回应报文为空值");
            }
           
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(InterruptedIOException e){
        	e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    public String callPostBytes(String url,byte[] content) {
    	String result="";
    	sun.net.www.protocol.https.HttpsURLConnectionImpl urlCon = null;
        try {
        	URLConnection urlConn=(new URL(url)).openConnection();
            urlCon = (sun.net.www.protocol.https.HttpsURLConnectionImpl)urlConn;
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("POST");
            
            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            if(content==null || content.length==0)
            	urlCon.setRequestProperty("Content-Length", "1024");
            else{
            	
            	urlCon.setRequestProperty("Content-Length", String.valueOf(content.length));
            }
            urlCon.setUseCaches(false);
            urlCon.setDoInput(true);

            OutputStream out=urlCon.getOutputStream();
          
            if(content==null || content.length==0)
            	out.write("".getBytes("UTF-8"));
            else{
            	out.write(content);
            }
            out.flush();
            out.close();
            //
            byte[] resBuffer=getReceiveContent(urlCon.getInputStream());
            
            //System.out.println("接收回应报文完毕");
            if(resBuffer!=null)
            {	
            	//System.out.println(new String(resBuffer));
            	result=new String(resBuffer,"UTF-8");
            	
            }
            else
            {
            	System.out.println("核心回应报文为空值");
            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(InterruptedIOException e){
        	e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    
    public String callPostString(String url,String content) {
    	String result="";
    	sun.net.www.protocol.https.HttpsURLConnectionImpl urlCon = null;
        try {
        	URLConnection urlConn=(new URL(url)).openConnection();
            urlCon = (sun.net.www.protocol.https.HttpsURLConnectionImpl)urlConn;
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("POST");
            
            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            if(content==null || content.length()==0)
            	urlCon.setRequestProperty("Content-Length", "1024");
            else{
            	
            	urlCon.setRequestProperty("Content-Length", String.valueOf(content.length()));
            }
            urlCon.setUseCaches(false);
            urlCon.setDoInput(true);
            
            OutputStreamWriter osw = new OutputStreamWriter(urlCon.getOutputStream(), "UTF-8");
            if(content==null || content.length()==0)
            	osw.write("");
            else{
            	osw.write(content);
            }
            osw.flush();
            osw.close();
            
            //
            byte[] resBuffer=getReceiveContent(urlCon.getInputStream());

            //System.out.println("接收回应报文完毕");
            if(resBuffer!=null)
            {	
            	//System.out.println(new String(resBuffer));
            	result=new String(resBuffer,"UTF-8");
            	
            }
            else
            {
            	System.out.println("核心回应报文为空值");
            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(InterruptedIOException e){
        	e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private static final int capacity=1024;
    
    private byte[] getReceiveContent(InputStream in){
    	byte[] resBuffer=null;
    	
    	int dataLen=0,r=0;
        byte[] buffer = new byte[capacity];
        ByteArrayInputStream bais =null;
        
        //String temp=null;
        try{
	        while((r = in.read(buffer))!=-1 ) {
	        	//System.out.println("接收回应报文...");
	        	bais=new ByteArrayInputStream(buffer);
	        	resBuffer=grow(resBuffer,r);
	        	System.arraycopy(buffer, 0, resBuffer, dataLen, r);
	        	dataLen+=r;
	        }
	        //System.out.println("接收回应报文完毕");
	        if(resBuffer==null)
	        {
	        	System.out.println("核心回应报文为空值");
	        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(InterruptedIOException e){
        	e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resBuffer;
    }
    
    private byte[] grow(byte[] src, int size) {
		  if(src==null)return new byte[size];
		  byte[] tmp = new byte[src.length + size];
		  System.arraycopy(src, 0, tmp, 0, src.length);
		  return tmp;
	}
    
}

/** *//**
 * X509证书
 * @author luoxy
 *
 */
class dfsX509TrustManager implements X509TrustManager {
    
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        //System.out.println("cert: " + chain[0].toString() + ", authType: " + authType);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

/** *//**
 * Hostname验证
 * @author luoxy
 *
 */
class dfsHostnameVerifier implements HostnameVerifier {

    public boolean verify(String hostname, SSLSession session) {
        //System.out.println("Warning: URL Host: " + hostname + " vs. " + session.getPeerHost());
        return true;
    }
}