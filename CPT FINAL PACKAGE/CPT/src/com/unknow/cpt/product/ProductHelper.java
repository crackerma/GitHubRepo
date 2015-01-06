/**
 * Provide getProductInfo method that get the product information from server.
 */
package com.unknow.cpt.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ProductHelper {
	
	public ProductHelper(){
		
	}
	public Product getProductInfo(String productID) throws ProductNotFoundException, ClientProtocolException, IOException, JSONException{
		Product p = null;
		HttpClient client = new DefaultHttpClient();
		//Set Request timeout
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
		//Set connection timeout
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
		StringBuilder builder = new StringBuilder();
		JSONObject jsonObject = null;
		try{
			HttpGet get = new HttpGet("http://1.crackerma.sinaapp.com/productInfo.php?p="+productID);
			Log.i("debug", "After HttpGet");
			HttpResponse response = client.execute(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
	        response.getEntity().getContent()));
	        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
	        	builder.append(s);
	        	}
	        
		}catch(Exception e){
			throw new ConnectionException();
		}
        Log.i("json_str", builder.toString());
        jsonObject = new JSONObject(builder.toString());
        if(jsonObject.getString("info").equals("0")){
        	throw new ProductNotFoundException();
        }else{
        	jsonObject = jsonObject.getJSONObject("data");
        	p = new Product();
        	p.setProductID(productID);
        	p.setProductName(jsonObject.getString("productName"));
        	p.setProductDescription(jsonObject.getString("description"));
        	p.setProductImg(jsonObject.getString("image"));
        	p.setSustainability(jsonObject.getString("sustainabilityInformation"));
        	p.setEnergy(jsonObject.getString("energy"));
        	p.setWater(jsonObject.getString("water"));
        	p.setCO2(jsonObject.getString("CO2"));
        	p.setManufacturerName(jsonObject.getString("manufacturerName"));
        	Log.i("debug", "before img");
        	//Try get product image;
        	Bitmap bitmap = null;
            URL  url = new URL(p.getProductImg());
            HttpURLConnection conn  = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setConnectTimeout(8000);
            conn.setDoInput(true);
            conn.connect(); 
            InputStream inputStream=conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream); 
            p.setBitmap(bitmap);
        }
		return p;
	}
}
