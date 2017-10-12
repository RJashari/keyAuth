package com.bpbbank;



import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

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

public class GjeneroPdf {

	private String locationForPdf;
	private String dayOfModification;
	private String user;
	 private static final Logger LOGGER = Logger.getLogger(GjeneroPdf.class.getName());

	public GjeneroPdf(String locationForPdf, String user, String dayOfModification) {
		
		this.locationForPdf = locationForPdf;
		this.user = user;
		this.dayOfModification = dayOfModification;
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
		
		Image bpbLogo = new Image(ImageDataFactory.create("bpblogo.bmp"));
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
//        Table t1 = new Table(new float[]{370.0f,100.0f, 50.0f});
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
		
		user = "Rinor Jashari";
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
		
				
		document.add(t);
		
		document.add(this.getTransactionsTable(dega));
		
		document.close();
		
		return fileName;
	}
	protected Table getTransactionsTable(Dega dega) {
        LOGGER.info("Writing transactions in pdf.");
        Table table = new Table(new float[]{150, 150});
        table
                .addHeaderCell(this.getCellWithDefaultParameters().add("Pershkrimi").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Personi/at e autorizuar").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                
                	.addCell(this.getCellWithDefaultParameters().add("Dega: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                	.addCell(this.getCellWithDefaultParameters().add(dega.getDega()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                	.addCell(this.getCellWithDefaultParameters().add("Pergjegjesi i Deges: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getPergjegjesiIDeges()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi i hyrjes Dege: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiIHyrjesDege()).setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add("Kodi i Alarmit Dege: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getKodiAlarmitDege()).setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi i Deres Atm: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiIDeresAtm()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi i Server Room: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiIServerRoom()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi Trezor: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiTrezor()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add("Kodi Alarmit Trezor: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getKodiAlarmitTrezor()).setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi Sef : ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiSef1()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add("Kodi Shifer Sef: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getKodiShiferSef()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add("Celesi Sef 2: ").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(dega.getCelesiSef2()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT));
        
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
//        cell.setBorderBottom(new SolidBorder(0.5f));
        return cell;
    }
	
	
	
}
