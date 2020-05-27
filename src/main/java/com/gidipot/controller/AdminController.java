package com.gidipot.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.gidipot.ResponseModel.ResponseModel;
import com.gidipot.client.Config;
import com.gidipot.client.HttpResponse;
import com.gidipot.client.WebServiceCall;
import com.gidipot.model.AdminModel;
import com.gidipot.model.SubmitForm;
import com.google.gson.Gson;



@Controller
public class AdminController {
	WebServiceCall webserviceCall = new WebServiceCall();
	
	//Goto to adminPage
	  @RequestMapping("/addAdmin")
		public String login(HttpServletRequest request, Model model) {
			
					return "addAdmin";
		}
	  
	  
	  //Admin Registration
		@RequestMapping(value="/create" , method = RequestMethod.POST)
		public RedirectView register(HttpServletRequest request, Model model,AdminModel ad,
			 @ModelAttribute SubmitForm submitform,  RedirectAttributes redirectAttrs)throws Exception {
			 String heading = "Spring MVC: How to Access ModelMap Values in a JSP?";
			 model.addAttribute("head",heading);
			
			//Read params from Html 
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String phoneno = request.getParameter("phoneno");
			String  role = request.getParameter("role");
			String pass1 = request.getParameter("pass1");
			String pass2 = request.getParameter("pass2");
			//System.out.println(username + firstname + lastname + email + job + pass1+pass2);
			ad.setFirstname(firstname);
			ad.setUsername(username);
			ad.setPhoneNumber(phoneno);
			ad.setRole(role);
			ad.setLastname(lastname);
			if (pass1.equals(pass2)) {
				ad.setPassword(pass1);
			}
			
			String requestBody = new Gson().toJson(ad);
			//System.out.println(requestBody);
			
			HashMap<String, String> extraHeaders = new HashMap<String, String>();
			extraHeaders.put("Content-Type", "application/json");
			
			HttpResponse httpResponse =  webserviceCall.postMethod(Config.baseUrl+"/admin/addAdmin",requestBody , extraHeaders);
			Gson gson = new Gson();
			ResponseModel rs = new ResponseModel();
			//System.out.println("Http response ===>"+httpResponse.getResponseBody1());
			
			 rs=gson.fromJson(httpResponse.getResponseBody1(), ResponseModel.class);
			
			 System.out.println("message==>"+rs.getResponseMessage()+" code==>" +rs.getResponseCode() );
			
//			if (rs.getResponseCode().equalsIgnoreCase("00") ) {
//				System.out.println("Successfully registered" + rs.getResponseMessage());
//			
//				model.addAttribute("message",rs.getResponseMessage() );
//				
//			
//				 Random random = new Random(10);
//				 submitform.setConfirmationNumber(random.nextInt(10));
//				 	submitform.setResponseMessage("Successful");
//				    redirectAttrs.addFlashAttribute(submitform);
//
//				    return new RedirectView("/addAdmin", true);
//				    
//			}else if(rs.getResponseCode().equalsIgnoreCase("-1")) {
//				model.addAttribute("message",rs.getResponseMessage() );
//			
//				 Random random = new Random(10);
//				 submitform.setConfirmationNumber(random.nextInt(10));
//				 submitform.setResponseMessage("failed");
//				    redirectAttrs.addFlashAttribute(submitform);
//
//				    return new RedirectView("/addAdmin", true);
//				
//			}
			 
		
			  if(rs.getResponseCode().equalsIgnoreCase("-2")) {
					model.addAttribute("message",rs.getResponseMessage()  );
					//request.setAttribute("message",rs.getResponseMessage() );
				  System.out.println("user exists" + rs.getResponseMessage());

				 Random random = new Random(10);
				 submitform.setConfirmationNumber(random.nextInt(10));
				 submitform.setResponseMessage("User Already Exist");
				    redirectAttrs.addFlashAttribute(submitform);
				
				    return new RedirectView("/addAdmin", true);
				
			}
			
			else {
				 Random random = new Random(10);
				 submitform.setConfirmationNumber(random.nextInt(10));
				 submitform.setResponseMessage("Not Found");
				    redirectAttrs.addFlashAttribute(submitform);
				 
				 return new RedirectView("/addAdmin",true);
			}
			
		}
}
