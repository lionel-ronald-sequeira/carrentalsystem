package com.uta.crs.dao;

import java.sql.Connection;
import java.util.List;

import com.uta.crs.bo.Car;

public interface CarDao {
	void addCar(Car car,Connection conn);
	
	List<Car>getCarList(boolean isForRental,String query,int carTypesCount);
	
	Car getCarInfo(String vehicleId);
	
	void updateCarRates(String vehicleId,Double dailyRate,Double weeklyRate,Connection conn);
	
	boolean checkVehicleIdExists(String vehicleId);
}
