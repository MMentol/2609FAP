package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerator extends PdfPageEventHelper {
    private String destination, phone1, phone2, mail1, mail2;
    
    private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 28, Font.BOLD);
    private static Font detailFont = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC);
    private static Font headingFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    private static Font generalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
    private static Font generalFontBold = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static Font genInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.DARK_GRAY);
    private static Font pageInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
    
    private Document invoice;
    private PdfWriter writer;
    
    public void printInvoice(String uid, String username, String email, String address, ResultSet orderinfo, String location){        
        try {
            this.destination = location + "\\invoice.pdf";
            System.out.println("[Debug] Destination File Location: " + destination);
            createInvoice();
            invoice.open();
            
            // Add company information on top of page
            Paragraph headerText = new Paragraph("iceCOOL.co", titleFont);
            headerText.setAlignment(Element.ALIGN_CENTER);
            invoice.add(headerText);
            headerText = new Paragraph(phone1 + " | " + phone2, detailFont);
            headerText.setAlignment(Element.ALIGN_CENTER);
            invoice.add(headerText);
            headerText = new Paragraph(mail1 + " | " + mail2, detailFont);
            headerText.setAlignment(Element.ALIGN_CENTER);
            invoice.add(headerText);
            invoice.add(Chunk.NEWLINE); 
            invoice.add(new LineSeparator());
            
            // Add User's Information
            Paragraph userInfo = new Paragraph("User Information", headingFont);
            invoice.add(userInfo);
            
            userInfo = new Paragraph();
            userInfo.add(new Phrase("User ID: ", generalFontBold));
            userInfo.add(new Phrase(uid, generalFont));
            invoice.add(userInfo);
            
            userInfo = new Paragraph();
            userInfo.add(new Phrase("Username: ", generalFontBold));
            userInfo.add(new Phrase(username, generalFont));
            invoice.add(userInfo);
            
            userInfo = new Paragraph();
            userInfo.add(new Phrase("Email: ", generalFontBold));
            userInfo.add(new Phrase(email, generalFont));
            invoice.add(userInfo);
            
            userInfo = new Paragraph();
            userInfo.add(new Phrase("Billing Address: ", generalFontBold));
            userInfo.add(new Phrase(address, generalFont));
            invoice.add(userInfo);
            
            invoice.add(Chunk.NEWLINE); 
            invoice.add(new LineSeparator());
            
            // Add specific order details
            Paragraph ordertext = new Paragraph("Order Information", headingFont);
            invoice.add(ordertext);
            
            while (orderinfo.next()){
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Order ID: ", generalFontBold));
                ordertext.add(new Phrase(orderinfo.getString("ORDER_ID"), generalFont));
                invoice.add(ordertext);
                
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Item Name: ", generalFontBold));
                ordertext.add(new Phrase(orderinfo.getString("STOCK_NAME"), generalFont));
                invoice.add(ordertext);
                
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Stock ID: ", generalFontBold));
                ordertext.add(new Phrase(orderinfo.getString("STOCK_ID"), generalFont));
                invoice.add(ordertext);
                
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Item Price: ", generalFontBold));
                ordertext.add(new Phrase(orderinfo.getString("STOCK_PRICE"), generalFont));
                invoice.add(ordertext);
                
                invoice.add(Chunk.NEWLINE);
                
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Payment Method: ", generalFontBold));
                ordertext.add(new Phrase(orderinfo.getString("ORDER_PAYMENT"), generalFont));
                invoice.add(ordertext);
                
                ordertext = new Paragraph();
                ordertext.add(new Phrase("Installment: ", generalFontBold));
                if (orderinfo.getBoolean("ORDER_PAYMENT") == true) {
                    ordertext.add(new Phrase("Yes", generalFont));
                }
                else {
                    ordertext.add(new Phrase("No", generalFont));
                }
                invoice.add(ordertext);
            }
            
            invoice.close();
        } 
        catch (Exception e) {
            System.out.println("[!] Failed to generate report.");
        }
    }
    
    public void setValues(String p1, String p2, String a1, String a2) {
        this.phone1 = p1;
        this.phone2 = p2;
        this.mail1 = a1;
        this.mail2 = a2;
    }
    
    private void createInvoice() throws IOException {
        try {
            invoice = new Document(new Rectangle(504, 684).rotate(), 50, 50, 50, 50);              
            writer = PdfWriter.getInstance(invoice, new FileOutputStream(destination, false));
            writer.setPageEvent(this);
        }
        catch (IOException ioe) {
            System.out.println("[!] File could not be created.");
        }
        catch (DocumentException de){
            System.out.println("[!] Could not initialize document.");
        }
    }
        
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy/ HH:mm:ss");
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("Invoice retrieved on: " + formatter.format(LocalDateTime.now()), genInfoFont), document.right() - 2, document.bottom() - 20, 0);        
    }
}