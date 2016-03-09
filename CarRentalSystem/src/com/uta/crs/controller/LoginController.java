package com.uta.crs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uta.crs.bo.Car;
import com.uta.crs.bo.LoginCredential;
import com.uta.crs.bo.Owner;
import com.uta.crs.service.CarService;
import com.uta.crs.service.OwnerService;
import com.uta.crs.service.impl.CarServiceImpl;
import com.uta.crs.service.impl.OwnerServiceImpl;

@Controller
public class LoginController {
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getLoginPage(Model model){
		LoginCredential loginCredential=new LoginCredential();
		model.addAttribute("loginCredential", loginCredential);
		return "login";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	public String checkLoginUser(@ModelAttribute("loginCredential")LoginCredential loginCredential,
			BindingResult bindingResult,Model model,HttpServletRequest request){
		boolean result=false;
		HttpSession session;
		CarService carService=new CarServiceImpl();
		result=carService.checkUserExists(loginCredential.getUserName(), loginCredential.getPassword());
		if(result){
			session=request.getSession(false);
			List<Car>carList=null;
			List<Owner>ownerList=null;
			OwnerService ownerService=new OwnerServiceImpl();
			ownerList=ownerService.getOwner();
			Car car=new Car();
			carList=carService.getCarList();
			model.addAttribute("ownerList",ownerList);
			model.addAttribute("carinfo", car);
			model.addAttribute("carList",carList);
			session.setAttribute("user", loginCredential.getUserName());
			return "home";
		}else{
			model.addAttribute("error","Invalid username or password");
			return "login";
		}
	}
	
	@RequestMapping(value="/signout",method=RequestMethod.GET)
	public ModelAndView signOut(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:login");
	}
}
