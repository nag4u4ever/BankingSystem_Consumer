

package com.nag.soap.api.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.bankreg.BankingEnrollment;
import com.nag.soap.api.entity.Bank;
import com.nag.soap.api.entity.CreditCard;
import com.nag.soap.api.entity.DebitCard;

@Service
public class bankconsumerservice {

	@Produce("direct:start")
	private ProducerTemplate producerTemplate;

	
	private RestTemplate restTemplate;
	
	
	

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}

	public BankerDetails getDetails(BankingEnrollment user) {
		System.out.println(user.getName());
		return producerTemplate.requestBody(user, BankerDetails.class);
	}

	public List<Bank> getbankerdetails() {
		List<Bank> bankerslist = restTemplate.getForObject("http://localhost:8081/bankerdetails", List.class);
		return bankerslist;
	}

	public BankerDetails storetocreditcard(CreditCard request) {
		String url = "http://localhost:8081/creditcard/save";
		System.out.println(restTemplate);
		return restTemplate.postForObject(url, request, BankerDetails.class);

	}
	
	public BankerDetails postDetails(BankingEnrollment user) {
		return producerTemplate.requestBody(user, BankerDetails.class);
	}

	public BankerDetails storetodebitcard(DebitCard request) {
		String url = "http://localhost:8081/debitcard/save";
		System.out.println(restTemplate);
		return restTemplate.postForObject(url, request, BankerDetails.class);
	}

	public BankerDetails updatingdetails(BankingEnrollment request,int id) {
		String url="http://localhost:8081/update/"+id;
		BankerDetails request1=restTemplate.patchForObject(url, request, BankerDetails.class);
		return request1;
	}

}
