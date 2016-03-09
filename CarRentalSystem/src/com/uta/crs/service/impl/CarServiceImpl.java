package com.uta.crs.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import com.uta.crs.bo.Car;
import com.uta.crs.dao.CarDao;
import com.uta.crs.dao.UserDao;
import com.uta.crs.service.CarService;
import com.uta.dao.impl.CarDaoImpl;
import com.uta.dao.impl.DaoUtility;
import com.uta.dao.impl.UserDaoImpl;

public class CarServiceImpl  implements CarService{

	@Override
	public boolean addCarService(Car car) {
		Connection conn=DaoUtility.getConnection();
		DaoUtility.settingAutoCommitTranscation(conn);
		boolean result=false;
		try{
			car.setAvailable("Y");
			CarDao carDao=new CarDaoImpl();
			result=checkVehicleIdExists(car.getVehicleId(), carDao);
			if(result){
				result= false;
			}else{
				carDao.addCar(car,conn);
				DaoUtility.commitTranscation(conn);
				DaoUtility.closeConnection(conn);
				result= true;
			}
		}catch(Exception e){
			DaoUtility.rollbackTransaction(conn);
			DaoUtility.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<Car> getCarList() {
		List<Car>carList=null;
		CarDao carDao=new CarDaoImpl();
		carList=carDao.getCarList(false,null,0);
		return carList;
	}

	@Override
	public String getCarInfo(String vehicleId) {
		Car car=null;
		String result=null;
		StringBuffer sb=new StringBuffer();
		CarDao carDao=new CarDaoImpl();
		car=carDao.getCarInfo(vehicleId);
		sb.append(car.getVehicleId()+","+car.getModel()+","+car.getYear()+","+car.getDailyRate()+","+car.getWeeklyRate()+","+car.getOwnerId()+",");
		if(car.getCompact().equals("Y")){
			sb.append("Compact,");
		}
		if(car.getMedium().equals("Y")){
			sb.append("Medium,");
		}
		if(car.getLarge().equals("Y")){
			sb.append("Large,");
		}
		if(car.getSuv().equals("Y")){
			sb.append("SUV,");
		}
		if(car.getTruck().equals("Y")){
			sb.append("Truck,");
		}
		if(car.getVan().equals("Y")){
			sb.append("Van,");
		}
		result=sb.substring(0, sb.length()-1);
		
		return result;
	}

	@Override
	public void updateCarRates(String vehicleId, Double dailyRate,
			Double weeklyRate) {
		Connection conn=DaoUtility.getConnection();
		DaoUtility.settingAutoCommitTranscation(conn);
		try{
			CarDao carDao=new CarDaoImpl();
			carDao.updateCarRates(vehicleId, dailyRate, weeklyRate,conn);
			DaoUtility.commitTranscation(conn);
			DaoUtility.closeConnection(conn);
		}catch(Exception e){
			DaoUtility.rollbackTransaction(conn);
			DaoUtility.closeConnection(conn);
		}
		
	}
	
	private boolean checkVehicleIdExists(String vehicleId,CarDao carDao){
		boolean result=false;
		result =carDao.checkVehicleIdExists(vehicleId);
		return result;
	}

	@Override
	public boolean checkUserExists(String userName, String password) {
		boolean result=false;
		UserDao userDao=new UserDaoImpl();
		result=userDao.checkUser(userName, password);
		return result;
	}
}
