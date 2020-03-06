package com.nag.router;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.entity.CreditCard;
import com.nag.soap.api.service.bankconsumerservice;

@Component
public class setCreditCardUpdate {
	
	@Autowired
	private bankconsumerservice bankconsumerservice;
	
	public void process(Exchange exchange)
	{ 
		BankerDetails enroll=(BankerDetails) exchange.getProperty("response");
		CreditCard cc=new CreditCard();
		cc.setAge(enroll.getAge());
		cc.setBankid(enroll.getId());
		cc.setCardtype(enroll.getCardtype());
		cc.setName(enroll.getName());
		cc.setState(enroll.getState());
		cc.setZipcode(enroll.getZipcode());
		BankerDetails response1=bankconsumerservice.storetocreditcard(cc);
		exchange.getIn().setBody(response1);
	}
	
	

}
