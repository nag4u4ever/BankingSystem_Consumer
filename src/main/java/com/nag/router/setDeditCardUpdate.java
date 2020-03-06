package com.nag.router;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.entity.DebitCard;
import com.nag.soap.api.service.bankconsumerservice;

@Component
public class setDeditCardUpdate {
	
	@Autowired
	private bankconsumerservice  service;

	@Handler
	public void process(Exchange exchange)
	{
		BankerDetails enroll=(BankerDetails) exchange.getProperty("response");
		DebitCard dc=new DebitCard();
		dc.setAge(enroll.getAge());
		dc.setBankid(enroll.getId());
		dc.setCardtype(enroll.getCardtype());
		dc.setName(enroll.getName());
		dc.setState(enroll.getState());
		dc.setZipcode(enroll.getZipcode());
		BankerDetails response=service.storetodebitcard(dc);
		exchange.getIn().setBody(response);
		
		
	}
}
