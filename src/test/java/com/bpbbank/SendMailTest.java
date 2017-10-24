package com.bpbbank;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class SendMailTest {

	SendMail sendMail = new SendMail();
	
	@Test
	public void test() {
		try {
			sendMail.sendEmail("12.10.2017_Dega_Drenas.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
