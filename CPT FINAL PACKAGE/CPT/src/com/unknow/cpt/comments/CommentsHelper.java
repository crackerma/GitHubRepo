/**
 * CommentsHelper: Transmit the comments information from server.
 * 		postComments: post the comments to server.
 * 		viewComments: download the comments from server.
 */
package com.unknow.cpt.comments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.unknow.cpt.product.Product;
import com.unknow.cpt.product.ProductNotFoundException;

import android.util.Log;

public class CommentsHelper {
	public String postComments(String p, String f,String review, String rate) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		//Set Request timeout
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		//Set connection timeout
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
		String uri = "http://1.crackerma.sinaapp.com/storeProductReview.php?p="+p+"&f="+f+"&review="+review.replaceAll(" ", "%20")+"&rate="+rate;
		Log.i("URI", uri);
		HttpGet get = new HttpGet(uri);
		Log.i("debug", "After HttpGet");
		StringBuilder builder = new StringBuilder();
		HttpResponse response = httpclient.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
        response.getEntity().getContent()));
        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
        	builder.append(s);
        	}
        Log.i("test", builder.toString());
		return builder.toString();
	}
	
	public List<Comments> viewComments(String pID) throws CommentsNotFoundException, ClientProtocolException, IOException, JSONException{
		List<Comments> ret = new ArrayList<Comments>();
		HttpClient client = new DefaultHttpClient();
		//Set Request timeout
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
		//Set connection timeout
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);
		StringBuilder builder = new StringBuilder();
		JSONObject jsonObject = null;
		HttpGet get = new HttpGet("http://1.crackerma.sinaapp.com/productReview.php?p="+pID);
		Log.i("debug", "http://1.crackerma.sinaapp.com/productReview.php?p="+pID);
		HttpResponse response = client.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
        response.getEntity().getContent()));
        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
        	builder.append(s);
        	}
        Log.i("json_str", builder.toString());
        jsonObject = new JSONObject(builder.toString());
        if(jsonObject.getString("info").equals("0")){
        	throw new CommentsNotFoundException();
        }else{
        	JSONArray jArray = jsonObject.getJSONArray("data");
        	for(int i=0;i<jArray.length(); i++){
        		JSONObject jo = jArray.getJSONObject(i);
            	Comments c = new Comments();
            	c.setProductName(jo.getString("productName"));
            	c.setFacebookID(jo.getString("facebookID"));
            	c.setReviews(jo.getString("reviews"));
            	c.setRating(jo.getString("ratings"));
            	ret.add(c);
        	}
        }
		return ret;
	}
	
}
