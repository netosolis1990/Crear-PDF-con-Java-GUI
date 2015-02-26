package netosolis.com;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Ernesto
 */
public class PDF {
    PdfWriter writer = null;

    public PDF() {
        super();
    }
    
    public boolean crearPDF(String archivo,String titulo,String texto){
        //Creamos un documento, Hoja tamaño carta y los margenes
        Document documento = new Document(PageSize.LETTER, 70, 70, 70, 70);
        
        try {      
            //Instanciamos el archivo a utilizar
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            //Agregamos un titulo y un autor al documento
                        
            //Abrimos el documento para la edicion
            documento.open();
            
            //Vamos a agregar una imagen en la primera parte del documento
             try{
                Image imagen = Image.getInstance("netosolis.png");
                //Alineamos la imagen al centro
                imagen.setAlignment(Image.ALIGN_CENTER);
                documento.add(imagen);      
              }catch(IOException | DocumentException  e){
                e.printStackTrace();
                return false;
              }

            try {
              //Para agregar texto al documento se utilizan el Paragrahp,
              //Primero creamos uno para poner un titulo centrado en color rojo
              Paragraph parrafo = new Paragraph();
              parrafo.setAlignment(Paragraph.ALIGN_CENTER);
              parrafo.setFont(FontFactory.getFont("Sans",18,Font.BOLD, BaseColor.RED));
              parrafo.add("\n"+titulo);
              //Agregamos el titulo y el texto al documento
              documento.add(parrafo);
              documento.add(new Paragraph(texto));
            } catch (DocumentException ex) {
              ex.printStackTrace();
              return false;
            }
            //Se debe cerrar el documento y el writter
            documento.close();
            writer.close();
            return true;
            
        } catch (FileNotFoundException | DocumentException   ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
