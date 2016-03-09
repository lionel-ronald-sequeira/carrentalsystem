package com.uta.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Rental;
import com.uta.crs.constants.QueryConstants;
import com.uta.crs.dao.RentalDao;
import com.uta.crs.enums.CarType;

public class RentalDaoImpl implements RentalDao {

	@Override
	public List<Car> getCarListforRental(Rental rental) {
		List<Car>carList=null;
		String newQuery=null;
		Connection conn=DaoUtility.getConnection();
		
		String carTypes[]=rental.getCarTypes()==null?null:rental.getCarTypes().split(",");
		newQuery=carTypes !=null ? modifyQueryForCarRental(rental.getCarTypes(), QueryConstants.CAR_AVAILABLE_FOR_RENT):QueryConstants.CAR_AVAILABLE_FOR_RENT;
		
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,newQuery);
		try {
			Date startDate=new Date(rental.getStartDate().getTime());
			Date endDate=new Date(rental.getEndDate().getTime());

			statement.setDate(1, startDate);
			statement.setDate(2, endDate);
			statement.setDate(3, startDate);
			statement.setDate(4, endDate);
			if(carTypes !=null){
				for(int i=1;i<=carTypes.length;i++){
					statement.setString(i+4, "Y");
				}
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
				
				if(carList==null){
					carList=new ArrayList<Car>();
				}
				carList.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carList;
	}
	
	public String modifyQueryForCarRental(String carTypes,String query){
		
		String additionalConditions[]=carTypes==null?new String[0]:carTypes.split(",");
		StringBuffer newQuery=new StringBuffer(query);
		for(int i=0;i<additionalConditions.length;i++){
			CarType carType=CarType.valueOf(additionalConditions[i]);
			switch(carType){
				case COMPACT:
					newQuery.append("AND compact_flag=?");
				break;
				case MEDIUM:
					newQuery.append("AND medium_flag=?");
				break;
				case LARGE:
					newQuery.append("AND large_flag=?");
				break;
				case SUV:
					newQuery.append("AND suv_flag=?");
				break;
				case TRUCK:
					newQuery.append("AND truck_flag=?");
				break;
				case VAN:
					newQuery.append("AND van_flag=?");
				break;
				default:
				break;
			}
		}
		return newQuery.toString();
	}

	@Override
	public List<Rental> getRentalInformation() {
		List<Rental>rentalList=null;
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_RENTAL_INFO);
		try {
			ResultSet resultSet= statement.executeQuery();
			while(resultSet.next()){
				Rental rental=new Rental();
				rental.setCustomerId(resultSet.getInt("customer_id"));
				rental.setVehicleId(resultSet.getString("vehicle_id"));
				rental.setStartDate(resultSet.getDate("start_date"));
				rental.setEndDate(resultSet.getDate("end_date"));
				rental.setReturnDate(resultSet.getDate("return_date"));
				rental.setAmountDue(resultSet.getDouble("amount_due"));
				rental.setRentalType(resultSet.getString("rental_type"));
				rental.setRentalTiming(resultSet.getString("rental_timing"));
				rental.setNoOfDays(resultSet.getInt("no_of_days"));
				rental.setNoOfWeeks(resultSet.getInt("no_of_weeks"));
				rental.setCustomerName(resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
				rental.setVehicleModel(resultSet.getString("model"));
				rental.setRentalId(resultSet.getInt("rental_id"));
				if(rentalList==null){
					rentalList=new ArrayList<Rental>();
				}
				rentalList.add(rental);
			}
			DaoUtility.closePreparedStatement(statement);
			DaoUtility.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rentalList;
	}

	@Override
	public void addRental(Rental rental) {
		Connection conn=DaoUtility.getConnection();
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn, QueryConstants.INSERT_RENTAL_DETAILS);
		try {
			preparedStatement.setInt(1, rental.getCustomerId());
			preparedStatement.setString(2, rental.getVehicleId());
			preparedStatement.setDate(3, new Date(rental.getStartDate().getTime()));
			//preparedStatement.setDate(4,rental.getReturnDate()==null?null:new Date(rental.getReturnDate().getTime()));
			preparedStatement.setDate(4,new Date(rental.getEndDate().getTime()));
			preparedStatement.setDouble(5, rental.getAmountDue());
			preparedStatement.setString(6,rental.getRentalType());
			preparedStatement.setString(7, rental.getRentalTiming());
			preparedStatement.setInt(8, rental.getNoOfDays());
			preparedStatement.setInt(9,  rental.getNoOfWeeks());
			preparedStatement.setTimestamp(10, new Timestamp(Calendar.getInstance().getTime().getTime()));
			preparedStatement.setDate(11,new Date(rental.getEndDate().getTime()));
			preparedStatement.executeUpdate();
			DaoUtility.closePreparedStatement(preparedStatement);
			DaoUtility.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkRentalInfoExists() {
		Connection conn=DaoUtility.getConnection();
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn,QueryConstants.CHECK_RENTAL_INFO_EXISTS);
		int count=0;
		try {
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

	@Override
	public void updateRentalInformation(Integer rentalId, Double amountDue) {
		
	}

	@Override
	public Car getRatesForCarForRental(String vehicleId) {
		Connection conn=DaoUtility.getConnection();
		ResultSet rs=null;
		Car car=null;
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn, QueryConstants.GET_RATES_FOR_CAR);
		try {
			preparedStatement.setString(1, vehicleId);
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				car=new Car();
				car.setDailyRate(rs.getDouble("daily_rate"));
				car.setWeeklyRate(rs.getDouble("weekly_rate"));
			}
			DaoUtility.closePreparedStatement(preparedStatement);
			DaoUtility.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	@Override
	public Rental getRentalInfoForRentalId(Integer rentalId) {
		Connection conn=DaoUtility.getConnection();
		ResultSet rs=null;
		Rental rental=null;
		
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn, QueryConstants.GET_RENTAL_INFO_FOR_RENTAL_ID);
		try {
			preparedStatement.setInt(1, rentalId);
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				StringBuffer carTypes=new StringBuffer();
				rental=new Rental();
				rental.setNoOfDays(rs.getInt("no_of_days"));
				rental.setNoOfWeeks(rs.getInt("no_of_weeks"));
				rental.setRentalType(rs.getString("rental_type"));
				rental.setVehicleId(rs.getString("vehicle_id"));
				rental.setStartDate(rs.getDate("start_date"));
				rental.setEndDate(rs.getDate("return_date"));
				rental.setCustomerId(rs.getInt("customer_id"));
				/*carTypes=rs.getString("compact_flag").equals("Y")?carTypes.append("Compact:"):carTypes;
				carTypes=rs.getString("medium_flag").equals("Y")?carTypes.append("Medium:"):carTypes;
				carTypes=rs.getString("large_flag").equals("Y")?carTypes.append("Large:"):carTypes;
				carTypes=rs.getString("suv_flag").equals("Y")?carTypes.append("SUV:"):carTypes;
				carTypes=rs.getString("truck_flag").equals("Y")?carTypes.append("Truck:"):carTypes;
				carTypes=rs.getString("van_flag").equals("Y")?carTypes.append("Van:"):carTypes;*/
				//rental.setCarTypes(carTypes.toString());
			}
			DaoUtility.closePreparedStatement(preparedStatement);
			DaoUtility.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rental;
	}

	@Override
	public void updateRentalAmountDue(Integer rentalId,Double amountDue){
		Connection conn=DaoUtility.getConnection();
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn,QueryConstants.UPDATE_AMOUNT_DUE_IN_RENTAL);
		try {
			preparedStatement.setDouble(1, amountDue);
			preparedStatement.setString(2, "complete");
			preparedStatement.setInt(3,rentalId);
			preparedStatement.executeUpdate();
			DaoUtility.closePreparedStatement(preparedStatement);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
