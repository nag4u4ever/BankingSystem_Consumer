package com.nag.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.nag.soap.api.bankreg.BankerDetails;
import com.nag.soap.api.bankreg.BankingEnrollment;
import com.nag.soap.banking.consumer.SoapClient;

@Component
public class BankingEnrollmentRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		from("direct:start").bean(setBasicInfo.class).bean(SoapClient.class).end()
		.choice().when(exchange -> {

//			BankingEnrollment request = exchange.getIn().getBody(BankingEnrollment.class);
			BankerDetails response = (BankerDetails) exchange.getProperty("response");
			if (response.getCardtype().equals("credit")) {
				System.out.println(response.getCardtype());
				return true;
			}
			return false;
		}).bean(setCreditCardUpdate.class).end()
		.choice().when(exchange -> {

//			BankingEnrollment request = exchange.getIn().getBody(BankingEnrollment.class);
			BankerDetails response = (BankerDetails) exchange.getProperty("response");
			if (response.getCardtype().equals("debit")) {
				System.out.println(response.getCardtype());
				return true;
			}
			return false;
		}).bean(setDeditCardUpdate.class).end();
	}
}
