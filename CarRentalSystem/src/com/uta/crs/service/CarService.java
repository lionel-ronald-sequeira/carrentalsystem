package com.uta.crs.service;
import java.util.List;

import com.uta.crs.bo.Car;

public interface CarService {

	boolean addCarService(Car car);
	
	List<Car>getCarList();
	
	String getCarInfo(String vehicleId);
	
	void updateCarRates(String vehicleId,Double dailyRate,Double weeklyRate);
	
	boolean checkUserExists(String userName,String password);
}
