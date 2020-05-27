package com.gidipot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.gidipot.client.Config;
import com.gidipot.client.HttpResponse;
import com.gidipot.client.WebServiceCall;
import com.gidipot.model.LoginModel;
import com.google.gson.Gson;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class MainController  {
  WebServiceCall webserviceCall = new WebServiceCall();
  
  
  @RequestMapping("/")
	public String login(HttpServletRequest request, Model model) {
		Map<String,?> inputFlashMap = RequestContextUtils
		.getInputFlashMap(request);
		if(inputFlashMap !=null) {
			model.addAttribute("message", inputFlashMap.get("submitForm"));
			
		}
				return "login";
	}
	
	
	
	//
	//Admin Login
	@RequestMapping(value="/login" , method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model,LoginModel admin)throws Exception {
		
		
		Map<String,?> inputFlashMap = RequestContextUtils
				.getInputFlashMap(request);
				if(inputFlashMap !=null) {
					model.addAttribute("message", inputFlashMap.get("login-form"));
					
				}
				 String heading = "Spring MVC: How to Access ModelMap Values in a JSP?";
				 model.addAttribute("head",heading);
		
		// Read request param from html form
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		admin.setUsername(username);;
		admin.setPassword(password);
		// adminId = new LoginService().AdminLogin(user);
		//System.out.println("credentials=>"+ user.getEmail() +user.getPassword());
		String requestBody = new Gson().toJson(admin);
		HashMap<String, String> extraHeaders = new HashMap<String, String>();
		extraHeaders.put("Content-Type", "application/json");
		
		HttpResponse httpResponse =  webserviceCall.postMethod(Config.baseUrl+"/admin/login",requestBody , extraHeaders);
		Gson gson = new Gson();
		System.out.println("Http:"+httpResponse.getResponseBody1());
	
		
		admin=gson.fromJson(httpResponse.getResponseBody1(), LoginModel.class);
	//custUserName = customer.getUsername();
		String role = admin.getRole();
	
		if (admin.getRole().equalsIgnoreCase("admin")   ) {
			System.out.println("Logged in as adminstrator");
			return "dashboard";	
			
		}else if (admin.getRole().equalsIgnoreCase("superuser")) {
			System.out.println("Logged in as SuperUser");
			return "superdashboard";
			
		}else {
			
			System.out.println("Login failed");
			return "login";
		}
			
		
			
		
			
		
	}
	
	
	
	@RequestMapping(value= "/register")
	public String register(){
		
		return "register";
	}
//	
//	
//	
//	@GetMapping("/error")
//	public String error(){
//		
//		return "error";
//	}
	
}
