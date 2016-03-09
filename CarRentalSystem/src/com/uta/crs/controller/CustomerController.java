package com.uta.crs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uta.crs.bo.Customer;
import com.uta.crs.service.CustomerService;
import com.uta.crs.service.impl.CustomerServiceImpl;

@Controller
public class CustomerController {
	
	@RequestMapping(value="/addcustomerinfo",method=RequestMethod.POST)
	public String addCar(@ModelAttribute("customerinfo")Customer customer,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		List<Customer>customerList=null;
		CustomerService customerService=new CustomerServiceImpl();
		customerService.addCustomer(customer);
			model.addAttribute("msg","Customer information added successfully");
			customerList=customerService.getCustomerList();
				model.addAttribute("customerList",customerList);
		return "customer";
	}
	
	@RequestMapping(value="/addcustomerinfo",method=RequestMethod.GET)
	public String viewCustomerInfo(Model model,HttpServletRequest request){
		List<Customer>customerList=null;
		Customer customer=new Customer();
		CustomerService customerService=new CustomerServiceImpl();
		customerList=customerService.getCustomerList();
		model.addAttribute("customerList",customerList);
		model.addAttribute("customerinfo", customer);
		return "customer";
	}

}
