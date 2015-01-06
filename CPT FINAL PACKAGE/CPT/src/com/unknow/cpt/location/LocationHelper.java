/**
 * Provide getProductLocation method that connect to server to get information.
 */
package com.unknow.cpt.location;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;

import com.unknow.cpt.location.LocationOverLays;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.unknow.cpt.product.Product;
import com.unknow.cpt.product.ProductNotFoundException;

public class LocationHelper {
	
	public LocationOverLays getProductLocation(String pID) throws ClientProtocolException, IOException, JSONException, LocationNotFoundException{
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
		LocationOverLays lo = null;
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		JSONObject jsonObject = null;
		HttpGet get = new HttpGet("http://1.crackerma.sinaapp.com/productLocation.php?p="+pID);
		HttpResponse response = client.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
		        response.getEntity().getContent()));
        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
        	builder.append(s);
        	}
        jsonObject = new JSONObject(builder.toString());
        if(jsonObject.getString("info").equals("0")){
        	throw new LocationNotFoundException();
        }else{
        	lo = new LocationOverLays();
        	JSONObject data = jsonObject.getJSONObject("data");
        	JSONArray locations = data.getJSONArray("Locations");
        	for(int i=0;i<locations.length();i++){
        		JSONObject o = locations.getJSONObject(i);
        		Location location = new Location();
        		location.setDescription(o.getString("Description"));
        		GeoPoint gp = new GeoPoint((int)(o.getInt("Latitude")*1e6),(int)(o.getInt("Longitdue")*1e6));
        		Log.i("", gp.getLatitudeE6()+" "+gp.getLongitudeE6());
        		location.setPoint(gp);
        		lo.addLocation(location);
        	}
        }
		return lo;
	}
}
