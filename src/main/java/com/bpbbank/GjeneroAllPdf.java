package com.bpbbank;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.hibernate.property.access.spi.GetterMethodImpl;
import org.springframework.stereotype.Component;

import com.bpbbank.domain.Dega;
import com.bpbbank.domain.KeyAuthenticationUser;
//import org.apache.log4j.Logger;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
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

public class GjeneroAllPdf {
	
	private Principal principal;

	Date dateNow = new Date();
	SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
	private String locationForPdf = "C:\\Users\\rinor.jashari\\Documents\\2017_11_08\\rinorTest\\gjithaDeget\\";
	private String dayOfModification = ft1.format(dateNow);
	

	private String user;
	public String email;
	
	 private static final Logger LOGGER = Logger.getLogger(GjeneroAllPdf.class.getName());

	public GjeneroAllPdf(String user, String email) {
		
		this.user = user;
		this.email = email;
	
	}
	public GjeneroAllPdf() {
		
	}
	
	
	public String gjeneroPdf(Set <Dega> deget) throws ParseException, IOException, NoSuchProviderException {
		
		String fileName = new StringBuilder()
		.append(locationForPdf)
		.append(dayOfModification)
		.append("_")
		.append("AllBranches")
		.append(".pdf")
		.toString();
		
		KeyAuthenticationUser userEmail = new KeyAuthenticationUser();
		String email = userEmail.getEmail();
		PdfWriter pdfWriter;
		pdfWriter = new PdfWriter(fileName);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4.rotate());
		
		Image bpbLogo = new Image(ImageDataFactory.create("src\\main\\java\\com\\bpbbank\\bpblogo.bmp"));
		bpbLogo.scaleToFit(150,150);
		
		Table header = new Table(2);
		header.addCell(new Cell().add(bpbLogo).setBorder(Border.NO_BORDER));
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		String subjectEmail = "Të gjitha degët pdf file";
		String bodyEmail ="Pasqyrimi i te gjitha degëve për datën: "+dayOfModification; 
		
		String dataEShtypjes = "Data e shtypjes";
		document.add(new Paragraph(dataEShtypjes + "              " + ft.format(dNow))
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(2.0f)
                .setPaddingRight(5.0f)
                .setFontSize(8.0f));
		
		Paragraph headerParagraf = new Paragraph();
        headerParagraf.setWidthPercent(100);
        headerParagraf.setTextAlignment(TextAlignment.CENTER);
        headerParagraf.add("Pasqyrimi i të gjitha degëve");//statement
        headerParagraf.setBold();
        document.add(header);
        document.add(new LineSeparator(new SolidLine()));
        document.add(headerParagraf);

		
        Table t = new Table(new float[]{80.0f, 100.0f});
        Table t1 = new Table(new float[]{250.0f,250.0f,250.0f});
        Table t2 = new Table(new float[]{80.0f, 200.0f});
//        Table t3 = new Table(new float[]{265.0f,65.0f,65.0f,65.0f,65.0f});
//        Table t4 = new Table(new float[]{80.0f, 100.0f, 40.0f, 80.0f});
        Table t5 = new Table(new float[]{65.0f, 80.0f});
        
    	String pershkrimi = "Përshkrimi: ";
    	Cell cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(pershkrimi)
				.setPaddingTop(15.0f)
				.setPaddingBottom(15.0f)
				.setBold()
				.setBorder(Border.NO_BORDER)
                .setFontSize(10.0f));
		t2.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("Të gjithë mbajtësit e çelësave sipas degëve")
				.setPaddingTop(15.0f)
				.setPaddingBottom(15.0f)
				.setBorder(Border.NO_BORDER)
                .setFontSize(10.0f));
		t2.addCell(cell);

//		String labelUser = "Modifikuesi: ";
//		cell = this.getCellWithDefaultParametersUpper();
//		cell.add(new Paragraph(labelUser)
//				.setBorder(Border.NO_BORDER)
//				.setFontSize(8.0f));
//		t.addCell(cell);
//		
//		
//		cell = this.getCellWithDefaultParametersUpper();
//		cell.add(new Paragraph(user)
//				.setBorder(Border.NO_BORDER)
//				.setTextAlignment(TextAlignment.LEFT)
//				.setFontSize(8.0f));
//		t.addCell(cell);
//		
//		String labelDega = "Dega: ";
//		cell = this.getCellWithDefaultParametersUpper();
//		cell.add(new Paragraph(labelDega)
//				.setPaddingBottom(20.0f)
//				.setBorder(Border.NO_BORDER)
//                .setFontSize(8.0f));
//		t.addCell(cell);
//		
//		cell = this.getCellWithDefaultParametersUpper();
//		cell.add(new Paragraph(dega.getDega())
//				.setPaddingBottom(20.0f)
//				.setBorder(Border.NO_BORDER)
//                .setFontSize(8.0f));
//		t.addCell(cell);
//		
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z.Partin Halimi")
				.setPaddingTop(30.0f)
				.setPaddingLeft(50.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z/znj. ___________________")
				.setPaddingTop(30.0f)
				.setPaddingLeft(50.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z/znj. ___________________")
				.setPaddingTop(30.0f)
				.setPaddingLeft(50.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("______________________")
				.setBorder(Border.NO_BORDER)
				.setPaddingTop(5.0f)
				.setPaddingLeft(50.0f)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("  ________________________")
				.setPaddingTop(5.0f)
				.setPaddingLeft(50.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("  ________________________")
				.setPaddingTop(5.0f)
				.setPaddingLeft(50.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		
		document.add(t2);
		document.add(t);
		
			
		document.add(this.getTransactionsTable(deget));		
		
		document.add(t1);
		document.close();
		
		SendMail sendMail = new SendMail();
		sendMail.sendEmail(fileName,"TeGjithaDeget", dayOfModification, subjectEmail, bodyEmail, email);
		
		return fileName;
	}
	protected Table getTransactionsTable(Set<Dega> deget) {
        LOGGER.info("Writing transactions in pdf.");
        Table table = new Table(new float[]{50,50,50,50,50,50,50,50,50,50,50,50,50,50,50});
        table
         		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Dega: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Përgjegjësi i Degës: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi i hyrjes Degë: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi i Server Room: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi i Kasafortës ATM: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi i Alarmit Degë: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelesi Trezor: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi Kasafortë 1: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Çelësi Kasafortë 2  ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Alarmit Trezor: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Shifër i Kasafortës: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodet digjitale kasafortë 1: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodet digjitale kasafortë 2: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Data e krijimit: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER))
        		 .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Data e modifikimit: ").setBold().setFontSize(8.0f).setTextAlignment(TextAlignment.CENTER));
                 
                deget.forEach((dega) -> {
                	table
                	
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getDega()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getPergjegjesiIDeges()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIHyrjesDege()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIServerRoom()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIDeresAtm()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitDege()).setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiTrezor()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef1()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef2()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitTrezor()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiShiferSef()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodetDigjitaleKasaforte1()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodetDigjitaleKasaforte2()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKrijimiDeges()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getModifikimiDeges()+"").setFontSize(7.0f).setTextAlignment(TextAlignment.CENTER));
                	
                    
                });
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
    private MimeMessage getMailMessage(GjeneroAllPdf gjeneroPdf,Principal principal, Session session, String extraMessage,Dega dega) throws IOException {
        MimeMessage message = new MimeMessage(session);
        
        Properties properties = System.getProperties();
        properties.load(GjeneroAllPdf.class.getClassLoader().getResourceAsStream("application.properties"));
        try {
            message.setFrom("menaxhimicelsave@bpbbank.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("send.to.email")));
            message.setSubject("Pranimi/Dorezimi i kodeve ose celsave");
 
            MimeBodyPart attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(locationForPdf);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(locationForPdf+"");
 
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
    public void sendReport(Session session, GjeneroAllPdf filename, String extraMessage, String user, String password, Principal principal, Dega dega) throws IOException {
        LOGGER.info("Sending email for changes in KeyAuthentification by: " + principal.getName()+"for Branch: " + dega.getDega());
        MimeMessage message = this.getMailMessage(filename,principal,  session, extraMessage, dega);
        try {
            Transport.send(message, user, password);
            
        } catch (MessagingException e) {
           
            LOGGER.info("Failed to send report for modification by: " +principal.getName() + " for Branch " + dega.getDega()+ e);
        }
    }
	
	
	
}
