package com.uta.crs.bo;

import java.util.Date;

import com.uta.crs.constants.StringConstants;

public class Car {
	private String vehicleId;
	private String model;
	private Integer year;
	private Double dailyRate;
	private Double weeklyRate;
	private String available="N";
	private String carTypes;
	private String compact="N";
	private String medium="N";
	private String large="N";
	private String suv="N";
	private String truck="N";
	private String van="N";
	private String ownerId;
	
	public String getCarTypes() {
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
		setTypesOfCar(carTypes);
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getCompact() {
		return compact;
	}
	public void setCompact(String compact) {
		this.compact = compact;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public String getSuv() {
		return suv;
	}
	public void setSuv(String suv) {
		this.suv = suv;
	}
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getVan() {
		return van;
	}
	public void setVan(String van) {
		this.van = van;
	}

	private void setTypesOfCar(String carTypes){
		String[] carTypesList=carTypes.split(",");
		for(int i=0;i<carTypesList.length;i++){
			if(carTypesList[i].equals(StringConstants.COMPACT_CARTYPE)){
				setCompact(StringConstants.YES);
			}
			if(carTypesList[i].equals(StringConstants.LARGE_CARTYPE)){
				setLarge(StringConstants.YES);
			}
			if(carTypesList[i].equals(StringConstants.MEDIUM_CARTYPE)){
				setMedium(StringConstants.YES);
			}
			if(carTypesList[i].equals(StringConstants.SUV_CARTYPE)){
				setSuv(StringConstants.YES);
			}
			if(carTypesList[i].equals(StringConstants.TRUCK_CARTYPE)){
				setTruck(StringConstants.YES);
			}
			if(carTypesList[i].equals(StringConstants.VAN_CARTYPE)){
				setVan(StringConstants.YES);
			}
			
		}
	}
	public Double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(Double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public Double getWeeklyRate() {
		return weeklyRate;
	}
	public void setWeeklyRate(Double weeklyRate) {
		this.weeklyRate = weeklyRate;
	}
}
