package com.uta.dao.impl;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.constants.QueryConstants;
import com.uta.crs.constants.StringConstants;
import com.uta.crs.dao.CarDao;

public class CarDaoImpl implements CarDao {

	@Override
	public void addCar(Car car,Connection conn) {
		
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn, QueryConstants.INSERT_CAR_DETAILS_QUERY);
		try {
			preparedStatement.setString(1, car.getVehicleId());
			preparedStatement.setString(2, car.getModel());
			preparedStatement.setInt(3, car.getYear());
			preparedStatement.setDouble(4, car.getDailyRate());
			preparedStatement.setDouble(5, car.getWeeklyRate());
			preparedStatement.setString(6, car.getCompact());
			preparedStatement.setString(7,  car.getMedium());
			preparedStatement.setString(8,  car.getLarge());
			preparedStatement.setString(9,  car.getSuv());
			preparedStatement.setString(10,  car.getTruck());
			preparedStatement.setString(11,  car.getVan());
			preparedStatement.setString(12,  car.getAvailable());
			preparedStatement.setTimestamp(13, new Timestamp(Calendar.getInstance().getTime().getTime()));
			
			preparedStatement.executeUpdate();
			
			DaoUtility.closePreparedStatement(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Car> getCarList(boolean isForRental,String query,int carTypesCount) {
		List<Car>carList=null;
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=null;
		try {
			if(isForRental){
				statement=DaoUtility.getPreparedStatement(conn,query);
				statement.setString(1, "Y");
				for(int i=1;i<=carTypesCount;i++){
					statement.setString(i+1, "Y");
				}
			}else{
				statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_CAR_LIST);
			}
			ResultSet resultSet= statement.executeQuery();
			while(resultSet.next()){
				Car car=new Car();
				car.setVehicleId(resultSet.getString("vehicle_id"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setDailyRate(resultSet.getDouble("daily_rate"));
				car.setWeeklyRate(resultSet.getDouble("weekly_rate"));
				car.setCompact(resultSet.getString("compact_flag"));
				car.setMedium(resultSet.getString("medium_flag"));;
				car.setLarge(resultSet.getString("large_flag"));
				car.setSuv(resultSet.getString("suv_flag"));
				car.setTruck(resultSet.getString("truck_flag"));
				car.setVan(resultSet.getString("van_flag"));
				car.setAvailable(resultSet.getString("available"));
				car.setOwnerId(resultSet.getString("owner_id"));
				if(carList==null){
					carList=new ArrayList<Car>();
				}
				carList.add(car);
			}
			DaoUtility.closePreparedStatement(statement);
			DaoUtility.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carList;
	}

	@Override
	public Car getCarInfo(String vehicleId) {
		Car car=null;
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_CAR_INFO_QUERY);
		try {
				statement.setString(1, vehicleId);
				ResultSet resultSet= statement.executeQuery();
				while(resultSet.next()){
					car=new Car();
					car.setVehicleId(resultSet.getString("vehicle_id"));
					car.setModel(resultSet.getString("model"));
					car.setYear(resultSet.getInt("year"));
					car.setDailyRate(resultSet.getDouble("daily_rate"));
					car.setWeeklyRate(resultSet.getDouble("weekly_rate"));
					car.setCompact(resultSet.getString("compact_flag"));
					car.setMedium(resultSet.getString("medium_flag"));;
					car.setLarge(resultSet.getString("large_flag"));
					car.setSuv(resultSet.getString("suv_flag"));
					car.setTruck(resultSet.getString("truck_flag"));
					car.setVan(resultSet.getString("van_flag"));
					car.setOwnerId(resultSet.getString("owner_id"));
			}
				DaoUtility.closePreparedStatement(statement);
				DaoUtility.closeConnection(conn);
		}catch(SQLException e){
				e.printStackTrace();
		}
		return car;
	}

	@Override
	public void updateCarRates(String vehicleId, Double dailyRate,
			Double weeklyRate,Connection conn) {
		
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn,QueryConstants.UPDATE_CAR_RATES);
		try {
			preparedStatement.setDouble(1, dailyRate);
			preparedStatement.setDouble(2,weeklyRate);
			preparedStatement.setString(3, vehicleId);
			preparedStatement.executeUpdate();
			DaoUtility.closePreparedStatement(preparedStatement);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkVehicleIdExists(String vehicleId) {
		Connection conn=DaoUtility.getConnection();
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn,QueryConstants.CHECK_VEHICLE_ID_EXISTS);
		int count=0;
		try {
			preparedStatement.setString(1, vehicleId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				count=resultSet.getInt(1);
			}
			if(count > 0){
				return true;
			}
			DaoUtility.closePreparedStatement(preparedStatement);
			DaoUtility.closeConnection(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return false;
	}


}
