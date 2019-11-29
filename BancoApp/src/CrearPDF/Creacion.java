package CrearPDF;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import ObjetoProducto.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication17.ComprobanteDomiciliacion;

public class Creacion {

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    //ArrayList<Producto> comprados = new ArrayList<Producto>();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    String fechAct = df.format(today);
    ComprobanteDomiciliacion comprobDom;

    public Creacion(ComprobanteDomiciliacion comp) {
        this.comprobDom = comp;
    }

    public void createPDF(File pdfNewFile) throws IOException {
        File miDir = new File(".");
        System.out.println("PDF creado en: " + miDir.getAbsolutePath());
        String iTextExampleImage = miDir.getCanonicalPath() + "\\bbva.png";
        // Creamos el documento e indicamos el nombre del fichero.
        try {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se encontró el fichero para generar el pdf" + fileNotFoundException);
            }
            document.open();
            // Añadimos los metadatos del PDF
            document.addTitle("Comprobante digital");
            document.addSubject("Tienda BBVA Bilbao Vizcaya ");
            document.addAuthor("ALFA & OMEGA");
            //Borde del documento

            float[] columnWidths = {5, 1, 5};
            PdfPTable table = new PdfPTable(columnWidths);

            table.setWidthPercentage(80);
            table.getDefaultCell().setUseAscender(true);
            table.getDefaultCell().setUseDescender(true);

            Image image = null;
            try {
                image = Image.getInstance(iTextExampleImage);
                image.setAbsolutePosition(document.topMargin(), document.leftMargin());
                image.scaleAbsoluteHeight(150);
                image.scaleAbsoluteWidth(400);
                ////chapter.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" + ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " + ex);
            }

            Font f = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            PdfPCell cell = new PdfPCell(image);

            //Color del background de la celda
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.WHITE);
            //Alineación del contenido de la celda
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //Aquí va el colspan
            cell.setColspan(3);
            //Se añade la celda
            table.addCell(cell);

            table.getDefaultCell().setColspan(3);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell("Nombre del titular de la cuenta");

            table.getDefaultCell().setColspan(3);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell("Nombre operación");

            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(comprobDom.toString1());

            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.addCell("     ");

            table.getDefaultCell().setColspan(1);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);;
            table.addCell(comprobDom.toString2());

            table.getDefaultCell().setColspan(3);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);;
            table.addCell("En caso de no reconocer esta operación, comunícate al telefono 01 800 22 62 66 3.\n Este correo electrónico constituye"
                    + "una modificación en los términos en que se realizó la operación, el único comprobante oficial es el estado de cuenta qure emite BBVA Bancomer.");

            Anchor mylink = new Anchor("Mis Videos!");
            mylink.setReference("http://www.mydomain.coo/myVideo.mp4");

            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase("Alertas Bancomer lo protege, te mantiene informado de la actividad de tus cuentas, conoce más el: "));
            paragraph.add(mylink);

            table.getDefaultCell().setColspan(3);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);;
            table.addCell(paragraph);

            document.add(table);
            document.close();

            System.out.println("Se ha generado el documento PDF");
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento" + documentException);
        } catch (ParseException ex) {
            Logger.getLogger(Creacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
