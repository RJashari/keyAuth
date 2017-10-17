package com.bpbbank;



import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

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
import com.itextpdf.text.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Chunk;
@Component
public class GjeneroPdf {

	private String locationForPdf = "C:\\Users\\rinor.jashari\\Documents\\2017_11_08\\rinorTest\\";
	private String dayOfModification = "12.10.2017";
	private String user = "Rinor Jashari";
	 private static final Logger LOGGER = Logger.getLogger(GjeneroPdf.class.getName());

	public GjeneroPdf(String locationForPdf, String user, String dayOfModification) {
		
		this.locationForPdf = locationForPdf;
		this.user = user;
		this.dayOfModification = dayOfModification;
	}
	public GjeneroPdf() {
		
	}
	
	
	public String gjeneroPdf(Dega dega) throws FileNotFoundException, MalformedURLException, ParseException {
		
		String fileName = new StringBuilder()
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
		
		Image bpbLogo = new Image(ImageDataFactory.create("C:\\Users\\rinor.jashari\\eclipse-workspace\\KeyAuthentication\\src\\main\\java\\com\\bpbbank\\bpblogo.bmp"));
		bpbLogo.scaleToFit(150,150);
		
		Table header = new Table(2);
		header.addCell(new Cell().add(bpbLogo).setBorder(Border.NO_BORDER));
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		
		
		String dataEShtypjes = "Data e shtypjes";
		document.add(new Paragraph(dataEShtypjes + "              " + ft.format(dNow))
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(2.0f)
                .setPaddingRight(10.0f)
                .setFontSize(8.0f));
		
		Paragraph headerParagraf = new Paragraph();
        headerParagraf.setWidthPercent(100);
        headerParagraf.setTextAlignment(TextAlignment.CENTER);
        headerParagraf.add("Modifikimi i Deges "+dega.getDega()+" nga "+user);//statement
        headerParagraf.setBold();
        document.add(header);
        document.add(new LineSeparator(new SolidLine()));
        document.add(headerParagraf);
		
        Table t = new Table(new float[]{80.0f, 100.0f});
        Table t1 = new Table(new float[]{500.0f});
//        Table t2 = new Table(new float[]{80.0f, 100.0f});
//        Table t3 = new Table(new float[]{265.0f,65.0f,65.0f,65.0f,65.0f});
//        Table t4 = new Table(new float[]{80.0f, 100.0f, 40.0f, 80.0f});
//        Table t5 = new Table(new float[]{80.0f, 100.0f});

		String labelUser = "Modifikuesi: ";
		Cell cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(labelUser)
				.setBorder(Border.NO_BORDER)
				.setFontSize(8.0f));
		t.addCell(cell);
		
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(user)
				.setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT)
				.setFontSize(8.0f));
		t.addCell(cell);
		
		String labelDega = "Dega: ";
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(labelDega)
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(dega.getDega())
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t.addCell(cell);
		
		String id = "ID: ";
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(id)
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph(dega.getId()+"")
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("")
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("")
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t1.addCell(cell);
		
		cell = this.getCellWithDefaultParametersUpper();
		cell.add(new Paragraph("")
				.setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
		t1.addCell(cell);
		
		
		document.add(t);
		document.add(t1);
			
		document.add(this.getTransactionsTable(dega));
		
		
		
		
		
		
		
		
		
		
		document.close();
		
		return fileName;
	}
	protected Table getTransactionsTable(Dega dega) {
        LOGGER.info("Writing transactions in pdf.");
        Table table = new Table(new float[]{250, 250});
        table
                .addHeaderCell(this.getCellWithDefaultParameters().add("Pershkrimi").setFontSize(11.0f).setBold().setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Personi/at e autorizuar").setFontSize(11.0f).setBold().setTextAlignment(TextAlignment.RIGHT))
                
//                	.addCell(this.getCellWithDefaultParameters().add("Dega: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
//                	.addCell(this.getCellWithDefaultParameters().add(dega.getDega()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                	.addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Pergjegjesi i Deges: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getPergjegjesiIDeges()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi i hyrjes Dege: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIHyrjesDege()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi i Alarmit Dege: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitDege()).setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi i Deres Atm: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIDeresAtm()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi i Server Room: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiIServerRoom()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi Trezor: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiTrezor()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Alarmit Trezor: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiAlarmitTrezor()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi Sef : ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef1()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Kodi Shifer Sef: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getKodiShiferSef()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add("Celesi Sef 2: ").setFontSize(10.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().setHeight(5.0f).add(dega.getCelesiSef2()+"").setFontSize(10.0f).setTextAlignment(TextAlignment.RIGHT));
        
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
	
	
	
}
