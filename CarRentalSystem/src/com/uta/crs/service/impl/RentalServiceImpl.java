package com.uta.crs.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Customer;
import com.uta.crs.bo.Rental;
import com.uta.crs.constants.QueryConstants;
import com.uta.crs.dao.CarDao;
import com.uta.crs.dao.RentalDao;
import com.uta.crs.service.CarService;
import com.uta.crs.service.CustomerService;
import com.uta.crs.service.RentalService;
import com.uta.dao.impl.CarDaoImpl;
import com.uta.dao.impl.RentalDaoImpl;

public class RentalServiceImpl implements RentalService {

	@Override
	public List<Car> getCarListforRental(Rental rental) {
		List<Car>carList=null;
		boolean check=false;
		String query=null;
		RentalDao rentalDao=new RentalDaoImpl();
		check=rentalDao.checkRentalInfoExists();
		if(check){
			carList=rentalDao.getCarListforRental(rental);
		}else{
			query=rentalDao.modifyQueryForCarRental(rental.getCarTypes(), QueryConstants.GET_CAR_LIST_FOR_RENTAL);
			CarDao carDao=new CarDaoImpl();
			carList=carDao.getCarList(true,query,rental.getCarTypes()==null ?0:rental.getCarTypes().split(",").length);
		}
		return carList;
	}

	@Override
	public List<Customer> getCustomerListWithNameId() {
		List<Customer>customerList=null;
		CustomerService customerService=new CustomerServiceImpl();
		customerList=customerService.getCustomerListWithNameAndId();
		return customerList;
	}

	@Override
	public List<Rental> getRentalInformation() {
		List<Rental>rentalList=null;
		RentalDao rentalDao=new RentalDaoImpl();
		rentalList=rentalDao.getRentalInformation();
		return rentalList;
	}

	@Override
	public void addRental(Rental rental) {
		RentalDao rentalDao=new RentalDaoImpl();
		long noOfDays=0;
		long startDateinMillis=rental.getStartDate().getTime();
		long endDateinMillis=rental.getEndDate().getTime();
		long difference=endDateinMillis-startDateinMillis;
		noOfDays= difference / (24 * 60 * 60 * 1000);
		if(rental.getRentalType().equals("daily")){
			rental.setNoOfDays(((int)noOfDays)+1);
		}else{
			rental.setNoOfWeeks((int)((noOfDays+1)/7));
		}
		Date currentDate=getFormattedDate(new Date());
		Date rentalStartDate=getFormattedDate(rental.getStartDate());
		
		if(currentDate.before(rentalStartDate)){
			rental.setRentalTiming("scheduled");
		}else if(currentDate.after(rentalStartDate)){
			rental.setRentalTiming("complete");
		}else if(currentDate.equals(rentalStartDate)){
			rental.setRentalTiming("active");
		}
		rentalDao.addRental(rental);
	}
	
	
	public Date getFormattedDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString=sdf.format(date);
		Date formattedDate=null;
		try{
			formattedDate=sdf.parse(dateString);
			
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;
	}
	
	public static void main(String args[]){
		Date currentDate=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(currentDate);
		try {
			Date date1=sdf.parse(date);
			System.out.println(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String editRentalInformation(Integer rentalId) {
		
			Rental rental=null;
			Car car=null;
			String response=null;
			RentalDao rentalDao=new RentalDaoImpl();
			rental=rentalDao.getRentalInfoForRentalId(rentalId);
			rental.setRentalId(rentalId);
			car=rentalDao.getRatesForCarForRental(rental.getVehicleId());
			
			if(rental.getRentalType().equals("daily")){
				rental.setAmountDue((car.getDailyRate()* rental.getNoOfDays()));
			}else if(rental.getRentalType().equals("weekly")){
				rental.setAmountDue((car.getWeeklyRate()* rental.getNoOfWeeks()));
			}
			response=rentalObjectInString(rental);
			return response;
	}
	
	private String rentalObjectInString(Rental rental){
		StringBuffer response=new StringBuffer();
		response.append(rental.getStartDate().toString()+",");
		response.append(rental.getEndDate().toString()+",");
		response.append(rental.getCustomerId()+",");
		response.append(rental.getRentalType()+",");
		response.append(rental.getAmountDue().toString()+",");
		response.append(rental.getRentalId()+",");
		//response.append(rental.getCarTypes()+",");
		return response.substring(0,response.length()-1).toString();
	}

	@Override
	public void updateRentalAmountDue(Integer rentalId,Double amountDue) {
		RentalDao rentalDao=new RentalDaoImpl();
		rentalDao.updateRentalAmountDue(rentalId, amountDue);
	}

}