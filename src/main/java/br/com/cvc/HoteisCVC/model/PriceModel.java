package br.com.cvc.HoteisCVC.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PriceModel {

	@JsonAlias({"adult"})
	private Double pricePerDayAdult;
	@JsonAlias({"child"})
	private Double pricePerDayChild;
	
	
	public Double getPricePerDayChild() {
		return pricePerDayChild;
	}


	public void setPricePerDayChild(Double pricePerDayChild) {
		this.pricePerDayChild = pricePerDayChild;
	}


	public PriceModel() {}
	
	
	public Double getPricePerDayAdult() {
		return pricePerDayAdult;
	}
	public void setPricePerDayAdult(Double pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}


}
