package com.bpbbank;


//import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class KeyAuthenticationMainApp {

	public static void main(String[] args) {
		SpringApplication.run(KeyAuthenticationMainApp.class, args);
		
//		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		
//		KeyReport keyReport = (KeyReport)context.getBean("keyreport");
//		System.out.println(keyReport);
//		((FileSystemXmlApplicationContext)context).close();
		
		
}
}