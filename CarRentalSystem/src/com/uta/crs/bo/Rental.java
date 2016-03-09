package com.uta.crs.bo;

import java.util.Date;

public class Rental {
	
	private Integer rentalId;
	private Date startDate;
	private Date endDate;
	private Date returnDate;
	private Double amountDue=0.0;
	private String rentalType;
	private String rentalTiming;
	private Integer noOfDays=0;
	private Integer noOfWeeks=0;
	private String vehicleId;
	private Integer customerId=0;
	private String carTypes;
	private String customerName;
	private String vehicleModel;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRentalId() {
		return rentalId;
	}
	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(Double amountDue) {
		this.amountDue = amountDue;
	}
	public String getRentalType() {
		return rentalType;
	}
	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}
	public String getRentalTiming() {
		return rentalTiming;
	}
	public void setRentalTiming(String rentalTiming) {
		this.rentalTiming = rentalTiming;
	}
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public Integer getNoOfWeeks() {
		return noOfWeeks;
	}
	public void setNoOfWeeks(Integer noOfWeeks) {
		this.noOfWeeks = noOfWeeks;
	}
	public String getCarTypes() {
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
