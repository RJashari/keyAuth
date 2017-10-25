package com.bpbbank;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;

import org.springframework.stereotype.Component;

import com.bpbbank.domain.Dega;

//import org.apache.log4j.Logger;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
@Component

public class GjeneroAddPdf {
	
	private Principal principal;

	Date dateNow = new Date();
	SimpleDateFormat ft1 = new SimpleDateFormat("dd.MM.yyyy");
	private String locationForPdf = "C:\\Users\\rinor.jashari\\Documents\\2017_11_08\\rinorTest\\shtoDegen\\";
	private String dayOfModification = ft1.format(dateNow);

	private String user;// = "Rinor Jashari";
	
	
	 private static final Logger LOGGER = Logger.getLogger(GjeneroAddPdf.class.getName());

	public GjeneroAddPdf(String user) {
		
		this.user = user;
	}
	public GjeneroAddPdf() {
		
	}
	
	
	public String gjeneroAddPdf(Dega dega) throws ParseException, IOException {
		
		String fileName = new StringBuilder()
		.append(locationForPdf)
		.append(dayOfModification)
		.append("_")
		.append("Dega")
		.append("_")
		.append(dega.getDega())
		.append(".pdf")
		.toString();
		
		
		PdfWriter pdfWriter;
		pdfWriter = new PdfWriter(fileName);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		Image bpbLogo = new Image(ImageDataFactory.create("src\\main\\java\\com\\bpbbank\\bpblogo.bmp"));
		bpbLogo.scaleToFit(150,150);
		
		Table header = new Table(2);
		header.addCell(new Cell().add(bpbLogo).setBorder(Border.NO_BORDER));
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		String subjectEmail = "Shtim i degës";
		String bodyEmail ="Shtimi i Degës \""+dega.getDega()+"\" në datën: "+dayOfModification; 
		
		String dataEShtypjes = "Data e shtypjes";
		document.add(new Paragraph(dataEShtypjes + "              " + ft.format(dNow))
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(2.0f)
                .setPaddingRight(10.0f)
                .setFontSize(8.0f));
		
		Paragraph headerParagraf = new Paragraph();
        headerParagraf.setWidthPercent(100);
        headerParagraf.setTextAlignment(TextAlignment.CENTER);
        headerParagraf.add("Shtimi i Degës "+dega.getDega());//statement
        headerParagraf.setBold();
        document.add(header);
        document.add(new LineSeparator(new SolidLine()));
        document.add(headerParagraf);
		
        Table t = new Table(new float[]{80.0f, 100.0f});
        Table t1 = new Table(new float[]{180.0f,180.0f,180.0f});
        Table t2 = new Table(new float[]{80.0f, 200.0f});
//        Table t3 = new Table(new float[]{265.0f,65.0f,65.0f,65.0f,65.0f});
//        Table t4 = new Table(new float[]{80.0f, 100.0f, 40.0f, 80.0f});
        Table t5 = new Table(new float[]{65.0f, 80.0f});
        
    	String pershkrimi = "Përshkrimi: ";
    	Cell cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(pershkrimi)
				.setPaddingTop(10.0f)
				.setBold()
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t2.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("Shtimi i degës: "+dega.getDega())
				.setPaddingTop(10.0f)
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t2.addCell(cell);

		String labelUser = "Shtoi: ";
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(labelUser)
				.setBold()
				.setBorder(Border.NO_BORDER)
				.setFontSize(8.0f));
		t.addCell(cell);
		
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(user)
				.setBorder(Border.NO_BORDER)
				.setPaddingBottom(10.0f)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8.0f));
		t.addCell(cell);
		
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z.Partin Halimi")
				.setPaddingTop(30.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z/znj. ___________________")
				.setPaddingTop(30.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z/znj. ___________________")
				.setPaddingTop(30.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("______________________")
				.setBorder(Border.NO_BORDER)
				.setPaddingTop(5.0f)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("  ________________________")
				.setPaddingTop(5.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("  ________________________")
				.setPaddingTop(5.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		
		document.add(t2);
		document.add(t);
		
			
		document.add(this.getTransactionsTable(dega));		
		
		document.add(t1);
		document.close();
		
		
		SendMail sendMail = new SendMail();
		sendMail.sendEmail(fileName,dega.getDega(), dayOfModification, subjectEmail, bodyEmail);
		System.out.println("u qu");
		
		
		return fileName;
	}
	protected Table getTransactionsTable(Dega dega) {
        LOGGER.info("Writing transactions in pdf.");
        Table table = new Table(new float[]{250, 250});
        table
                .addHeaderCell(this.getCellWithDefaultParameters().add("Përshkrimi").setFontSize(11.0f).setBold().setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Personi/at e autorizuar").setFontSize(11.0f).setBold().setTextAlignment(TextAlignment.RIGHT))
                
//                	.addCell(this.getCellWithDefaultParameters().add("Dega: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
//                	.addCell(this.getCellWithDefaultParameters().add(dega.getDega()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Përgjegjësi i Degës: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getPergjegjesiIDeges()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi i hyrjes Degë: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIHyrjesDege()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi i Server Room: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIServerRoom()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi i Kasafortës ATM: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIDeresAtm()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi i Alarmit Degë: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitDege()).setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelesi Trezor: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiTrezor()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi Kasafortë 1: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef1()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi Kasafortë 2  ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef2()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Alarmit Trezor: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitTrezor()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Shifër i Kasafortës: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiShiferSef()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodet digjitale kasafortë 1: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodetDigjitaleKasaforte1()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodet digjitale kasafortë 2: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodetDigjitaleKasaforte2()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT));;
                    
        
        table.setBorderBottom(new SolidBorder(0.5f));
        return table;
    }
	
	
	
	Cell getCellWithDefaultParametersUpper() {
        Cell cell = new Cell();
        cell.setPaddings(1.0f, 0f, 1.0f, 0.0f);
        cell.setHeight(2.0f);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }
    Cell getCellWithDefaultParameters() {
        Cell cell = new Cell();
        cell.setPaddings(0.1f, 0f, 0f, 0f);
        cell.setHeight(2.0f);
        cell.setBorder(Border.NO_BORDER);
        cell.setBorderTop(new SolidBorder(0.5f));
        return cell;
    }
    private MimeMessage getMailMessage(Session session, String extraMessage,Dega dega) throws IOException {
        MimeMessage message = new MimeMessage(session);
        
        Properties properties = System.getProperties();
        properties.load(GjeneroAddPdf.class.getClassLoader().getResourceAsStream("application.properties"));
        try {
            message.setFrom("menaxhimicelsave@bpbbank.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("rinor.jashari@bpbbank.com")));
            message.setSubject("Pranimi/Dorezimi i kodeve ose celsave");
 
            MimeBodyPart attachment = new MimeBodyPart();
            String fileSource = getFileName(dega);
            DataSource source = new FileDataSource(fileSource);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(dayOfModification+"_Dega_"+dega.getDega());
 
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(extraMessage, "utf-8", "html");
             
            Multipart wholeMessage = new MimeMultipart();
            wholeMessage.addBodyPart(attachment);
            wholeMessage.addBodyPart(textPart);
            message.setContent(wholeMessage);
        } catch (MessagingException e) {
            LOGGER.info("Failed to generate a mime message for  " + principal.getName()  + " for Branch " + dega.getDega()+ e);
        }
        return message;
    }
    public void sendReport(Session session, GjeneroAddPdf filename, String extraMessage, String user, String password, Principal principal, Dega dega) throws IOException {
        LOGGER.info("Sending email for changes in KeyAuthentification by: " + principal.getName()+"for Branch: " + dega.getDega());
        MimeMessage message = this.getMailMessage(session, extraMessage, dega);
        try {
            Transport.send(message, user, password);
            
        } catch (MessagingException e) {
           
            LOGGER.info("Failed to send report for modification by: " +principal.getName() + " for Branch " + dega.getDega()+ e);
        }
      
    				
        }
    public String getFileName(Dega dega) {
    	
		String fileName = new StringBuilder()
				.append(locationForPdf)
				.append(dayOfModification)
				.append("_")
				.append("Dega")
				.append("_")
				.append(dega.getDega())
				.append(".pdf")
				.toString();
		
		return fileName;
    }
	
	
	
}
