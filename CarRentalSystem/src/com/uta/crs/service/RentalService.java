package com.uta.crs.service;

import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Customer;
import com.uta.crs.bo.Rental;

public interface RentalService {
	List<Car> getCarListforRental(Rental rental);
	
	List<Customer>getCustomerListWithNameId();
	
	List<Rental>getRentalInformation();
	
	void addRental(Rental rental);
	
	String editRentalInformation(Integer rentalId);
	
	void updateRentalAmountDue(Integer rentalId,Double amountDue);
	
}
