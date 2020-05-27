package com.gidipot.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;

import com.gidipot.model.LoginModel;


public class CallRestService implements CommandLineRunner {
	
	public static void callRestService() {
		RestTemplate restTemplate = new RestTemplate();
		LoginModel loginModel = restTemplate.getForObject("http://localhost:8080/GidiPot-API/controller/getMenu", LoginModel.class);
		System.out.println("jjj");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
		// TODO Auto-generated method stub
		callRestService();
		
	}

}
