package com.nag.soap.banking.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.bankreg.BankingEnrollment;
import com.nag.soap.api.entity.Bank;
import com.nag.soap.api.service.bankconsumerservice;
import com.nag.utility.BankUtilityConstants;

@SpringBootApplication
@RestController
@ComponentScan("com.*")
public class BankingSystemConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemConsumerApplication.class, args);
	}
	
	@Autowired
	private bankconsumerservice service;
	@GetMapping("/nag")
	public String check()
	{
		return "its working";
	}
	
	@PostMapping("/addBankCustomer")
	public BankerDetails getdetails(@RequestBody BankingEnrollment request)
	{
		//BankUtilityConstants.opflag="create";
		return service.postDetails(request);
	}
	
	@GetMapping("/getallbankers")
	public ResponseEntity<List<Bank>> getallbankdetails()
	{
		List<Bank> list=service.getbankerdetails();
		return new ResponseEntity<List<Bank>>(list,HttpStatus.OK);
	}
	

	
	@PatchMapping("/update/{id}")
	public ResponseEntity<BankerDetails> getUpdatedDetails(@RequestBody BankingEnrollment request,@PathVariable int id) {
		BankerDetails updateddetails=service.updatingdetails(request,id);
		return new ResponseEntity<BankerDetails>(updateddetails,HttpStatus.OK);
	}

 
	}

