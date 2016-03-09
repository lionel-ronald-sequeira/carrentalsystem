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
import com.uta.crs.bo.Owner;
import com.uta.crs.service.CarService;
import com.uta.crs.service.OwnerService;
import com.uta.crs.service.impl.CarServiceImpl;
import com.uta.crs.service.impl.OwnerServiceImpl;


@Controller
public class CarController {
	
	@RequestMapping(value="/addcarinfo",method=RequestMethod.POST)
	public String addCar(@ModelAttribute("carinfo")Car car,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		List<Car>carList=null;
		List<Owner>ownerList=null;
		boolean result=false;
		OwnerService ownerService=new OwnerServiceImpl();
		CarService carService=new CarServiceImpl();
		if(car !=null){
			result=carService.addCarService(car);
			if(result){
				model.addAttribute("msg","Car information with vehicle id " +car.getVehicleId()+" added successfully");
			}else{
				model.addAttribute("error","Duplicate Vehicle Id " +car.getVehicleId());
			}
		}
		ownerList=ownerService.getOwner();
		carList=carService.getCarList();
				model.addAttribute("ownerList",ownerList);
				model.addAttribute("carList",carList);
		return "home";
	}
	
	@RequestMapping(value="/addcarinfo",method=RequestMethod.GET)
	public String  viewCarList(Model model,HttpServletRequest request){
		String view=null;
		Car car=new Car();
		model.addAttribute("carinfo", car);
		view=addCar(null, null, model,request);
		return view;
	}
	
	@RequestMapping(value="/editcarinfo",method=RequestMethod.GET)
	public @ResponseBody String editCarInfo(@RequestParam("vehicleId")String vehicleId,HttpServletRequest request){
		String response=null;
		CarService carService=new CarServiceImpl();
		response=carService.getCarInfo(vehicleId);
		return response;
	}
	
	@RequestMapping(value="/updatecarinfo",method=RequestMethod.POST)
	public String updateCarRates(@ModelAttribute("carinfo")Car car,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		List<Car>carList=null;
		List<Owner>ownerList=null;
		OwnerService ownerService=new OwnerServiceImpl();
		CarServiceImpl carService=new CarServiceImpl();
		carService.updateCarRates(car.getVehicleId(), car.getDailyRate(), car.getWeeklyRate());
		carList=carService.getCarList();
		ownerList=ownerService.getOwner();
		model.addAttribute("ownerList",ownerList);
		model.addAttribute("carList",carList);
		model.addAttribute("msg", "Car information with vehicle id"+" " +car.getVehicleId()+" "+"updated successfully");
		return "home";
	}

}
