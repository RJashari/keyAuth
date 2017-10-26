package com.bpbbank;

import java.io.IOException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.bpbbank.domain.Dega;

public class SendMail 
{ 
    public void sendEmail(String fileName, String dega, String date, String subject, String body) throws IOException, NoSuchProviderException
    {    
        
        Properties properties = System.getProperties();
        properties.load(SendMail.class.getClassLoader().getResourceAsStream("application.properties"));
        String [] emailTo = properties.getProperty("mail.toEmail").split(",");//change accordingly   
        String user = properties.getProperty("mail.smtp.user");//change accordingly   
        String fromEmail = properties.getProperty("mail.sender.email");
        String password = properties.getProperty("mail.sender.password");//change accordingly     

        //1) get the session object      
          
        properties.setProperty("mail.smtp.host", "online.bpbbank.com");   
//        properties.put("mail.smtp.auth", "true");   
        properties.setProperty("mail.smtps.ssl.enable", "true");

//        Session session = Session.getDefaultInstance(properties);  
       
        
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                   user, password);
             }
          });    

        //2) compose message      
        try{    
            MimeMessage message = new MimeMessage(session);    
            message.setFrom(new InternetAddress(fromEmail));     
            int i=0;
//            while(i<=emailTo.length) {
            System.out.println("MAKAKI");
            for(String s : emailTo) {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo[i++]));    
            System.out.println(s);
            }
            message.setSubject(subject);         

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText(body);          

            //4) create new MimeBodyPart object and set DataHandler object to this object        

            MimeBodyPart attachment = new MimeBodyPart();      
            //change accordingly     
            DataSource source = new FileDataSource(fileName);    
            attachment.setDataHandler(new DataHandler(source));    
            attachment.setFileName(dega+"_"+date+".pdf");             


            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
            multipart.addBodyPart(messageBodyPart1);     
            multipart.addBodyPart(attachment);      

            //6) set the multiplart object to the message object    
            message.setContent(multipart);        

            //7) send message    
//            Transport transport = session.getTransport("smtp");
//            transport.connect("online.bpbbank.com", user, password);
            Transport.send(message);      
            System.out.println("message sent....");   

        }catch (MessagingException ex) {ex.printStackTrace();}  
    }
} 
 