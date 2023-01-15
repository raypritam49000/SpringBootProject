package com.telemune.soap.api.client.config;

import java.time.Duration;

import org.springframework.boot.webservices.client.HttpWebServiceMessageSenderBuilder;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.telemune.soap.api.client.interfaces");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate(WebServiceTemplateBuilder builder) {
		WebServiceTemplate webServiceTemplate = builder.messageSenders(new HttpWebServiceMessageSenderBuilder().setConnectTimeout(Duration.ofDays(9l))
				.setReadTimeout(Duration.ofDays(9l)).build()).build();
		
		webServiceTemplate.setMarshaller(marshaller());
		webServiceTemplate.setUnmarshaller(marshaller());
		
		return webServiceTemplate;
	}

}