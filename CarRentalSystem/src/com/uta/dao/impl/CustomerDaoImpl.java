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

import com.uta.crs.bo.Customer;
import com.uta.crs.constants.QueryConstants;
import com.uta.crs.dao.CustomerDao;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> getCustomerList() {
		List<Customer>customerList=null;
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_CUSTOMER_LIST_QUERY);
		try {
			ResultSet resultSet= statement.executeQuery();
			while(resultSet.next()){
				Customer customer=new Customer();
				customer.setCustomerId((resultSet.getInt("customer_id")));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setPhoneNo(resultSet.getString("phone_no"));
				customer.setStreet(resultSet.getString("street"));
				customer.setState(resultSet.getString("state"));
				customer.setZipcode(resultSet.getString("zipcode"));
				if(customerList==null){
					customerList=new ArrayList<Customer>();
				}
				customerList.add(customer);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public void addCustomer(Customer customer,Connection conn){
		PreparedStatement preparedStatement=DaoUtility.getPreparedStatement(conn, QueryConstants.INSERT_CUSTOMER_DETAILS_QUERY);
		try {
			preparedStatement.setString(1,customer.getFirstName());
			preparedStatement.setString(2,customer.getLastName());
			preparedStatement.setString(3,customer.getPhoneNo());
			preparedStatement.setString(4, customer.getStreet());
			preparedStatement.setString(5,customer.getState());
			preparedStatement.setString(6, customer.getZipcode());
			preparedStatement.setTimestamp(7, new Timestamp(Calendar.getInstance().getTime().getTime()));
			preparedStatement.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}

	@Override
	public List<Customer> getCustomerListWithNameandId() {
		List<Customer>customerList=null;
		Connection conn=DaoUtility.getConnection();
		PreparedStatement statement=DaoUtility.getPreparedStatement(conn,QueryConstants.GET_CUSTOMER_LIST_WITH_NAME_ID_QUERY);
		try {
			ResultSet resultSet= statement.executeQuery();
			while(resultSet.next()){
				Customer customer=new Customer();
				customer.setCustomerId((resultSet.getInt("customer_id")));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				if(customerList==null){
					customerList=new ArrayList<Customer>();
				}
				customerList.add(customer);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
}
