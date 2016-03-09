package com.uta.crs.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.uta.crs.bo.Customer;
import com.uta.crs.dao.CustomerDao;
import com.uta.crs.service.CustomerService;
import com.uta.dao.impl.CustomerDaoImpl;
import com.uta.dao.impl.DaoUtility;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public void addCustomer(Customer customer) {
		Connection conn=DaoUtility.getConnection();
		DaoUtility.settingAutoCommitTranscation(conn);
		try{
			CustomerDao customerDao=new CustomerDaoImpl();
			customerDao.addCustomer(customer,conn);
			DaoUtility.commitTranscation(conn);
			DaoUtility.closeConnection(conn);
		}catch(Exception e){
			DaoUtility.rollbackTransaction(conn);
			DaoUtility.closeConnection(conn);
		}
	}

	@Override
	public List<Customer> getCustomerList() {
		List<Customer>customerList=null;
		CustomerDao customerDao=new CustomerDaoImpl();
		customerList=customerDao.getCustomerList();
		return customerList;
	}

	@Override
	public List<Customer> getCustomerListWithNameAndId() {
		List<Customer>customerList=null;
		CustomerDao customerDao=new CustomerDaoImpl();
		customerList=customerDao.getCustomerListWithNameandId();
		return customerList;
	}

}
