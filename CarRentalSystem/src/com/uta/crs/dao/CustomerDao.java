package com.uta.crs.dao;

import java.sql.Connection;
import java.util.List;

import com.uta.crs.bo.Customer;

public interface CustomerDao {

	List<Customer >getCustomerList();
	
	void addCustomer(Customer customer,Connection conn);
	
	List<Customer>getCustomerListWithNameandId();
}
