/**
 * Location class construct a location entity with locationID, description
 * and bitmap(not used in current version.
 */
package com.unknow.cpt.location;

import android.graphics.Bitmap;

import com.google.android.maps.GeoPoint;

public class Location {
	public Location(){
		
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public GeoPoint getPoint() {
		return point;
	}
	public void setPoint(GeoPoint point) {
		this.point = point;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	private int locationID;
	private GeoPoint point;
	private String description;
	private Bitmap bitmap;
	
}
