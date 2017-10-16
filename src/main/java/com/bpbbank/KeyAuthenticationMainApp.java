package com.bpbbank;

<<<<<<< HEAD

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;

//import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> eb33deb52d1aa719d23cde8234478afc02b35d0a
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KeyAuthenticationMainApp {

<<<<<<< HEAD
	public static void main(String[] args) throws FileNotFoundException, MalformedURLException, ParseException {
		SpringApplication.run(KeyAuthenticationMainApp.class, args);
		
		
		
		
//		Dega dega = new Dega
//		
//		String locationForPdf = "C:\\Users\\rinor.jashari\\Documents\\2017_11_08\\rinorTest\\";
//		String dayOfModification = "12.10.2017";
//		String user = "Partin Halimi";
//		
//		GjeneroPdf gjeneroPdf = new GjeneroPdf(locationForPdf, user, dayOfModification);
//		
//		gjeneroPdf.gjeneroPdf(dega);
		
//		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		
//		KeyReport keyReport = (KeyReport)context.getBean("keyreport");
//		System.out.println(keyReport);
//		((FileSystemXmlApplicationContext)context).close();
		
		
}
=======
	@Autowired
	private ApplicationContext applicationContext;

	public KeyAuthenticationMainApp(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		for(String s: this.applicationContext.getBeanDefinitionNames()) {
			System.out.println("bean definition name: " + s);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("password00: " + new BCryptPasswordEncoder().encode("password00"));
		SpringApplication.run(KeyAuthenticationMainApp.class, args);

	}

>>>>>>> eb33deb52d1aa719d23cde8234478afc02b35d0a
}