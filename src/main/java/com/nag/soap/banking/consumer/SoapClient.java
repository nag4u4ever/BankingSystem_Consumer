package com.nag.soap.banking.consumer;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.bankreg.BankingEnrollment;

@Component
public class SoapClient {

	@Autowired(required =true)
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Handler
	public void process(Exchange exchange) {
		
		BankingEnrollment request = (BankingEnrollment) exchange.getProperty("request");
		
		BankerDetails response = getDetails(request);
		
		exchange.setProperty("response", response);
		
	}
	
	public BankerDetails getDetails(BankingEnrollment request)
	{
		
		template=new WebServiceTemplate(marshaller);
		BankerDetails details=(BankerDetails) template.marshalSendAndReceive("http://localhost:8081/service",request);
		return details;
		
	}
	
	
}
