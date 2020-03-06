package com.nag.soap.banking.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConsumerConfig {

	
	@Bean
	public Jaxb2Marshaller marshaller()
	{
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.nag.soap.api.bankreg");
		return marshaller;
	}
}
