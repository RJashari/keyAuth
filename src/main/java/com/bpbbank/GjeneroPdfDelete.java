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

public class GjeneroPdfDelete {
	
	private Principal principal;

	Date dateNow = new Date();
	SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
	private String locationForPdf ;//= "C:\\Users\\rinor.jashari\\Documents\\2017_11_08\\rinorTest\\gjithaDeget\\";
	private String dayOfModification = ft1.format(dateNow);
	

	private String user;
	public String email;
	public String emailPergjegjesi;
	 private static final Logger LOGGER = Logger.getLogger(GjeneroPdfDelete.class.getName());

	public GjeneroPdfDelete(String user, String email, String emailPergjegjesi) {
		
		this.user = user;
		this.email = email;
		this.emailPergjegjesi =emailPergjegjesi;
	}
	public GjeneroPdfDelete() {
		
	}
	
	
	public String gjeneroPdf(Dega dega) throws ParseException, IOException, NoSuchProviderException {
		
		Properties properties = System.getProperties();
        properties.load(GjeneroPdf.class.getClassLoader().getResourceAsStream("application.properties"));
		locationForPdf = properties.getProperty("locationForPdfDeleteDegen");
		System.out.println(locationForPdf);
		
		String fileName = new StringBuilder()
		.append(locationForPdf)
		.append(dayOfModification)
		.append("_")
		.append("AllBranches")
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
		
		String subjectEmail = "U fshi dega \""+ dega.getDega()+"\"";
		String bodyEmail ="Fshirja e deges: "+dega.getDega()+ " ne daten: "+dayOfModification; 
		
		String dataEShtypjes = "Data e shtypjes";
		document.add(new Paragraph(dataEShtypjes + "              " + ft.format(dNow))
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(2.0f)
                .setPaddingRight(5.0f)
                .setFontSize(8.0f));
		
		Paragraph headerParagraf = new Paragraph();
        headerParagraf.setWidthPercent(100);
        headerParagraf.setTextAlignment(TextAlignment.CENTER);
        headerParagraf.add("Fshirja e degës \""+dega.getDega()+"\"");//statement
        String emriAdmin = properties.getProperty("admin.full.name");
        headerParagraf.setBold();
        document.add(header);
        document.add(new LineSeparator(new SolidLine()));
        document.add(headerParagraf);

		
        Table t = new Table(new float[]{80.0f, 100.0f});
        Table t1 = new Table(new float[]{180.0f,180.0f,180.0f});
        Table t2 = new Table(new float[]{80.0f, 200.0f});
        Table t3 = new Table(new float[]{270.0f, 280.0f});
//        Table t3 = new Table(new float[]{265.0f,65.0f,65.0f,65.0f,65.0f});
//        Table t4 = new Table(new float[]{80.0f, 100.0f, 40.0f, 80.0f});
        Table t5 = new Table(new float[]{65.0f, 80.0f});
        
    	String pershkrimi = "Përshkrimi: ";
    	Cell cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(pershkrimi)
				.setPaddingTop(15.0f)
				.setBold()
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t2.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("Fshirja e degës")
				.setPaddingTop(15.0f)
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t2.addCell(cell);

		String labelUser = "Përdoruesi: ";
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(labelUser)
				.setBorder(Border.NO_BORDER)
				.setPaddingBottom(15.0f)
				.setBold()
				.setFontSize(8.0f));
		t2.addCell(cell);
		
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(emriAdmin)
				.setBorder(Border.NO_BORDER)
				.setPaddingBottom(15.0f)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8.0f));
		t2.addCell(cell);
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
		cell.add(new Paragraph("z/znj. "+emriAdmin)
				.setPaddingTop(30.0f)
				.setBorder(Border.NO_BORDER)
				.setBold()
				.setHeight(20.0f)
                .setFontSize(10.0f));
		t1.addCell(cell);
		
		String pergjegjesiIDeges = dega.getPergjegjesiIDeges();
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("z/znj. "+this.getEmriMbiemri(pergjegjesiIDeges))
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
		sendMail.sendEmail(fileName,dega.getDega(), dayOfModification, subjectEmail, bodyEmail, email, emailPergjegjesi,user);
		
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
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodetDigjitaleKasaforte2()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Data e Krijimit: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKrijimiDeges()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Data e Modifikimit: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getModifikimiDeges()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT));;
        
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
    
    public String getEmriMbiemri(String username) {
    	
    	String [] fjalet = username.split("\\.");
    	System.out.println(fjalet);
    	String emri = fjalet[0];
    	String mbiemri = fjalet[1];
    		
    		
        	
        	char first = Character.toUpperCase(emri.charAt(0));
        	emri = first + emri.substring(1);
        	char second = Character.toUpperCase(mbiemri.charAt(0));
        	mbiemri = second + mbiemri.substring(1);
        	
        	String usernameReplaced = emri+" "+mbiemri;
        	
        	System.out.println(usernameReplaced);
    	
    	return usernameReplaced;
    		
    }
}
