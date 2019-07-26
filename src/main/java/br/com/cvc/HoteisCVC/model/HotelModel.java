package br.com.cvc.HoteisCVC.model;

public class HotelModel {

	private int id;
	private String name;
	public RoomModel[] rooms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoomModel[] getRooms() {
		return rooms;
	}

	public void setRooms(RoomModel[] rooms) {
		this.rooms = rooms;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	private int cityCode;
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
