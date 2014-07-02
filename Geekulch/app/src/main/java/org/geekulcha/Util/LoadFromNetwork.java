package org.geekulcha.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.geekulcha.Beans.JsonResponse;



import com.google.gson.Gson;

public class LoadFromNetwork {

	Gson gson;
	public LoadFromNetwork()
	{
		gson=new Gson();
	}
	
	public JsonResponse convertToObject(String url) throws IOException
	{
		String jsonString=downloadUrl(url);
		JsonResponse jSonResponse=gson.fromJson(jsonString, JsonResponse.class);
		
		return jSonResponse;
	}
	
	public String downloadUrl(String myurl) throws IOException {
	    InputStream is = null;
	    // Only display the first 500 characters of the retrieved
	    // web page content.
	    int len = 5000;
	        
	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        System.out.println("response: "+response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        String contentAsString ;
	        BufferedReader bf= new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb=new StringBuilder();
	        
	        while((contentAsString=bf.readLine())!=null)
	        {
	        	sb.append(contentAsString);
	        }
	        bf.close();
	        return sb.toString();
	        
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
	}
	
}
