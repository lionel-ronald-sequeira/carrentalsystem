package com.uta.crs.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.Customer;
import com.uta.crs.bo.LoginCredential;
import com.uta.crs.bo.Rental;
import com.uta.crs.service.CarService;
import com.uta.crs.service.RentalService;
import com.uta.crs.service.impl.CarServiceImpl;
import com.uta.crs.service.impl.RentalServiceImpl;

@Controller
public class RentalInformationController {
	@RequestMapping(value="/viewrentalinfo",method=RequestMethod.GET)
	public String viewRentalInformation(Model model,HttpServletRequest request){
		List<Rental>rentalList=null;
		List<Customer>customerList=null;
		RentalService rentalService=new RentalServiceImpl();
		customerList=rentalService.getCustomerListWithNameId();
		rentalList=rentalService.getRentalInformation();
		model.addAttribute("customerList", customerList);
		Rental rental=new Rental();
		model.addAttribute("rentalinfo", rental);
		model.addAttribute("rentalmsg", "rentalinfo");
		model.addAttribute("rentalList", rentalList);
		return "rental";
	}
	
	@RequestMapping(value="/searchrentalinfo",method=RequestMethod.POST)
	public String searchCarAvailability(@ModelAttribute("rentalinfo")Rental rentalinfo,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		List<Car>carList=null;
		List<Customer>customerList=null;
		RentalService rentalService=new RentalServiceImpl();
		carList=rentalService.getCarListforRental(rentalinfo);
		customerList=rentalService.getCustomerListWithNameId();
		model.addAttribute("carList", carList);
		model.addAttribute("customerList", customerList);
		return "rental";
	}
	
	@RequestMapping(value="/addrentalinfo",method=RequestMethod.POST)
	public String addRentalInformation(@ModelAttribute("rentalinfo")Rental rentalinfo,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		List<Car>carList=null;
		List<Customer>customerList=null;
		List<Rental>rentalList=null;
		RentalService rentalService=new RentalServiceImpl();
		carList=rentalService.getCarListforRental(rentalinfo);
		customerList=rentalService.getCustomerListWithNameId();
		rentalService.addRental(rentalinfo);
		rentalList=rentalService.getRentalInformation();
		model.addAttribute("carList", carList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("rentalList", rentalList);
		model.addAttribute("rentalmsg", "rentalinfo");
		return "rental";
	}
	
	@RequestMapping(value="/editrentalinfo",method=RequestMethod.GET)
	public @ResponseBody String editRentalInformation(@RequestParam("rentalId")Integer rentalId,
	HttpServletRequest request)
	{
		String response=null;
		RentalService rentalService=new RentalServiceImpl();
		response= rentalService.editRentalInformation(rentalId);
		return response;
	}
	
	@RequestMapping(value="/updaterentalinfo",method=RequestMethod.POST)
	public String updateRentalInformation(@ModelAttribute("rentalinfo")Rental rentalinfo,
			BindingResult bindingResult,Model model,HttpServletRequest request)
	{	List<Rental>rentalList=null;
		List<Customer>customerList=null;
		RentalService rentalService=new RentalServiceImpl();
		rentalService.updateRentalAmountDue(rentalinfo.getRentalId(), rentalinfo.getAmountDue());
		customerList=rentalService.getCustomerListWithNameId();
		rentalList=rentalService.getRentalInformation();
		model.addAttribute("rentalList", rentalList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("rentalmsg", "rentalinfo");
		model.addAttribute("msg", "Amount Due updated for rental id "+ rentalinfo.getRentalId());
		return "rental";
	}
}
