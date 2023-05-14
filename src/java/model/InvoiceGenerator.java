package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerator extends PdfPageEventHelper {    
    private String generatingUser;
    
    private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    private static Font generalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
    private static Font generalFontBold = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static Font genInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.DARK_GRAY);
    private static Font pageInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
    
    private Document report;
    private PdfWriter writer;
    private PdfTemplate totalPages;
    
    public void printInvoice(){
        
    }
}