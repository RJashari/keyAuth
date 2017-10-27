package com.bpbbank;

import java.io.IOException;

import javax.mail.NoSuchProviderException;

import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class SendMailTest {

	SendMail sendMail = new SendMail();
	
	@Test
	public void test() throws NoSuchProviderException {
		try {
			sendMail.sendEmail("24.10.2017_Dega_Milikili.pdf","","","","","");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
