package com.uta.crs.service;

import java.util.List;

import com.uta.crs.bo.Customer;

public interface CustomerService {
	
	void addCustomer(Customer customer);
	
	List<Customer>getCustomerList();
	
	List<Customer>getCustomerListWithNameAndId();
}
