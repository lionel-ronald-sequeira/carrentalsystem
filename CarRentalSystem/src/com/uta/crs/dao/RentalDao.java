package com.uta.crs.dao;

import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Rental;

public interface RentalDao {
	List<Car>getCarListforRental(Rental rental);
	
	List<Rental>getRentalInformation();
	
	void addRental(Rental rental);
	
	boolean checkRentalInfoExists();
	
	String modifyQueryForCarRental(String carTypes,String query);
	
	void updateRentalInformation(Integer rentalId,Double amountDue);
	
	Car getRatesForCarForRental(String vehicleId);
	
	Rental getRentalInfoForRentalId(Integer rentalId);
	
	void updateRentalAmountDue(Integer rentalId,Double amountDue);
}
