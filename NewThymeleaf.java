/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author ilhem
 */

public class NewThymeleaf {
    public NewThymeleaf(String encoding1,String encoding2,String encoding3,String logoEncoding,double min1, double max1, double avr1, boolean low_perf1, double min2, double max2, double avr2, boolean low_perf2, double min3, double max3, double avr3, boolean low_perf3, String perf_threshold_trrecep,String perf_threshold_opreq,String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws FileNotFoundException, DocumentException, IOException{
                ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
templateResolver.setSuffix(".html");
templateResolver.setTemplateMode("HTML");
 templateResolver.setOrder(0); 
TemplateEngine templateEngine = new TemplateEngine();
templateEngine.setTemplateResolver(templateResolver);
Context context = new Context();
if (low_perf1 == true){
  context.setVariable("interpretation1", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_trrecep + " trades processed per second");  
}
else {
    context.setVariable("interpretation1", "StreamGateway performance is above the normal threshold which is " + perf_threshold_trrecep + " trades processed per second");  

}

if (low_perf2 == true){
  context.setVariable("interpretation2", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_opreq + " trades processed per second");  
}
else {
    context.setVariable("interpretation2", "StreamGateway performance is above the normal threshold which is " + perf_threshold_opreq + " trades processed per second");  

}

if (low_perf3 == true){
  context.setVariable("interpretation3", "StreamGateway performance is low. she is below the normal threshold which is " + perf_threshold_track + " trades processed per second");  
}
else {
    context.setVariable("interpretation3", "StreamGateway performance is above the normal threshold which is " + perf_threshold_track + " trades processed per second");  

}
context.setVariable("test_type1",Test_Type1);
context.setVariable("image1", encoding1);
context.setVariable("min1", min1);
context.setVariable("max1", max1);
context.setVariable("fislogo", logoEncoding);
context.setVariable("avr1", avr1);

context.setVariable("test_type2",Test_Type2);
context.setVariable("image2", encoding2);
context.setVariable("min2", min2);
context.setVariable("max2", max2);
context.setVariable("avr2", avr2);

context.setVariable("test_type3",Test_Type3);
context.setVariable("image3", encoding3);
context.setVariable("min3", min3);
context.setVariable("max3", max3);
context.setVariable("avr3", avr3);
String html = templateEngine.process("ThymeleafTmp", context);
OutputStream outputStream = new FileOutputStream("/home/ilhem/Bureau/Report/pdfReport.pdf");
ITextRenderer renderer = new ITextRenderer();
renderer.setDocumentFromString(html);
renderer.layout();
renderer.createPDF(outputStream);
outputStream.close();
	
     }
}
