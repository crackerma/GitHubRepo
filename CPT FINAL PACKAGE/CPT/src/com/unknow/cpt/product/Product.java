/**
 * Product class:
 * Include all details in the product table from server.
 *	private String productID;
 *	private String productName;
 *  private String productImg;
 *  private String productDescription;
 *  private String sustainability;
 *  private String energy;
 *  private String water;
 *  private String CO2;
 *  private Bitmap bitmap;
 */
package com.unknow.cpt.product;


import java.util.ArrayList;

import android.graphics.Bitmap;

public class Product {
	private String productID;
	private String productName;
	private String productImg;
	private String productDescription;
	private String sustainability;
	private String energy;
	private String water;
	private String CO2;
	private Bitmap bitmap;
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	private ArrayList<String> allergens;
	private String manufacturerName;
	public Product(){
		allergens = new ArrayList<String>();
	}
	public Product(String productID,String productName){
		allergens = new ArrayList<String>();
		this.productID = productID;
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSustainability() {
		return sustainability;
	}
	public void setSustainability(String sustainability) {
		this.sustainability = sustainability;
	}
	public String getEnergy() {
		return energy;
	}
	public void setEnergy(String energy) {
		this.energy = energy;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String getCO2() {
		return CO2;
	}
	public void setCO2(String cO2) {
		CO2 = cO2;
	}
	public ArrayList<String> getAllergens() {
		return allergens;
	}
	public void setAllergens(ArrayList<String> allergens) {
		this.allergens = new ArrayList<String>(allergens);
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

}
