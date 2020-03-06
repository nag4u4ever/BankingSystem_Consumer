package com.nag.router;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import com.nag.soap.api.bankreg.BankingEnrollment;
import com.nag.utility.BankUtilityConstants;

@Component
public class setBasicInfo {
	
	@Handler
	public void process(Exchange exchange) {
//		exchange.setProperty("flag", BankUtilityConstants.opflag);
//		exchange.setProperty("idflag", BankUtilityConstants.idflag);
		BankingEnrollment enroll=exchange.getIn().getBody(BankingEnrollment.class);
		//BankUtilityConstants.bankingEnrollment=enroll;
		exchange.setProperty("request", enroll);
		
	}

}
