package br.com.cvc.HoteisCVC.model;


public class RoomModel {
 
	private int roomID;
	private String categoryName;
	private PriceModel price;
	private double totalPrice;
	

	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int qtdPax , int qtdChd , int qtdDias) {
		this.totalPrice = 
				(((price.getPricePerDayAdult() * qtdPax) 
				+ (price.getPricePerDayChild() * qtdChd)) * qtdDias) /0.7;
	}

	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public PriceModel getPrice() {
		return price;
	}
	public void setPrice(PriceModel price) {
		this.price = price;
	}
	
}
