/**
 * Product class, construct a product entity with productID and productName.
 */
package com.unknow.cpt.history;

public class Product {
	private String pID;
	private String pName;

	public Product(){
	}
	public Product(String pID, String pName){
		this.pID = pID;
		this.pName = pName;
	}
	public String getpID() {
		return pID;
	}
	public void setpID(String pID) {
		this.pID = pID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
}
