/**
 * Comments class, generate a comments entity include facebookID,
 * productName, Reviews and rating.
 */
package com.unknow.cpt.comments;

public class Comments {
	private String facebookID;
	private String productName;
	private String reviews;
	private String rating;
	public Comments(){
		
	}
	public Comments(String facebookID,String productName, String reviews, String rating){
		this.facebookID = facebookID;
		this.productName = productName;
		this.reviews = reviews;
		this.rating = rating;
	}
	public String getFacebookID() {
		return facebookID;
	}
	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
}
