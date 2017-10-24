package com.bpbbank;

import java.io.IOException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail 
{ 
    public void sendEmail(String fileName) throws IOException
    {    
        
        Properties properties = System.getProperties();
        properties.load(SendMail.class.getClassLoader().getResourceAsStream("application.properties"));
        String to = properties.getProperty("mail.toEmail");//change accordingly   
        String user = properties.getProperty("mail.smtp.user");//change accordingly   
        String fromEmail = properties.getProperty("mail.sender.email");
        String password = properties.getProperty("mail.sender.password");//change accordingly     

        //1) get the session object      
          
        properties.setProperty("mail.smtp.host", "localhost");   
//        properties.put("mail.smtp.auth", "true");    

        Session session = Session.getDefaultInstance(properties);       

        //2) compose message      
        try{    
            MimeMessage message = new MimeMessage(session);    
            message.setFrom(new InternetAddress(fromEmail));     
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
            message.setSubject("Message Aleart");         

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText("This is message body");          

            //4) create new MimeBodyPart object and set DataHandler object to this object        

            MimeBodyPart attachment = new MimeBodyPart();      
            //change accordingly     
            DataSource source = new FileDataSource(properties.getProperty("locationForPdf"));    
            attachment.setDataHandler(new DataHandler(source));    
            attachment.setFileName(fileName);             


            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
            multipart.addBodyPart(messageBodyPart1);     
            multipart.addBodyPart(attachment);      

            //6) set the multiplart object to the message object    
            message.setContent(multipart);        

            //7) send message    
            Transport.send(message);      
            System.out.println("message sent....");   

        }catch (MessagingException ex) {ex.printStackTrace();}  
    }
} 
 