package View;
import Controller.JsonParser;
import View.NewFreeMarker;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.FontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import freemarker.template.TemplateException;
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.PopupMenu;


import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.HORIZONTAL;
import static org.elasticsearch.search.suggest.completion.context.ContextBuilder.category;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ExtendedCategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.util.TextUtils;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBox;




public class NewChart {
      public static final double DEFAULT_AXIS_MARGIN=0.00;
      private double lowerMargin = 0.0D;
      private double upperMargin = 0.0D;
      public  double CategoryMargin=0.1;
      //public String encoding;
      //public String interpretation;
      protected TextBox tb;
     
      public BufferedImage image1;
      public BufferedImage image2;
      public BufferedImage image3;
    
      private String interpretation1,interpretation2,interpretation3;
      private double avr1,min1,max1,avr2,min2,max2,avr3,min3,max3;
      private boolean low_perf1,low_perf2,low_perf3;
    public NewChart(String index_name_trrecep, String index_name_opreq, String index_name_track, String perf_threshold_trrecep, String perf_threshold_opreq, String perf_threshold_track, String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException, TemplateException {

       

        CategoryDataset dataset1 = createDataset(index_name_trrecep,Test_Type1);
        CategoryDataset dataset2 = createDataset(index_name_opreq,Test_Type2);
        CategoryDataset dataset3 = createDataset(index_name_track,Test_Type3);
       
        JFreeChart chart1 = createChart(dataset1);
        JFreeChart chart2 = createChart(dataset2);
        JFreeChart chart3 = createChart(dataset3);
        
        image1=draw(chart1,1200,1200);  
        image2=draw(chart2,1200,1200);  
        image3=draw(chart3,1200,1200); 
        
        JsonParser j1=new JsonParser(index_name_trrecep);
       
        JsonParser j2=new JsonParser(index_name_opreq);
        JsonParser j3=new JsonParser(index_name_track);
        
        avr1=j1.calculate_average();
      
        max1=j1.MaxMinArraycount()[0];
   
        min1=j1.MaxMinArraycount()[1];
        
        low_perf1=j1.IsPerformant();
        
        avr2=j2.calculate_average();
        max2=j2.MaxMinArraycount()[0];
        min2=j2.MaxMinArraycount()[1];
        low_perf2=j2.IsPerformant();
        
        avr3=j3.calculate_average();
        max3=j3.MaxMinArraycount()[0];
        min3=j3.MaxMinArraycount()[1];
        low_perf3=j3.IsPerformant();
        
        


WritingCSV w1=new WritingCSV(max1, min1, avr1, low_perf1, j1,Test_Type1);

WritingCSV w2=new WritingCSV(max2, min2, avr2, low_perf2, j2,Test_Type2);
        
WritingCSV w3=new WritingCSV(max3, min3, avr3, low_perf3, j3,Test_Type3);
                
generatePDFReport(perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2,Test_Type3);
generateHTMLReport(perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2,Test_Type3);
      
         
 
  }
    public String encodelogo() throws IOException{
    BufferedImage fislogo = ImageIO.read(new File("/home/ilhem/NetBeansProjects/DataAnalyser/OtherSources/fis.png"));
ImageIO.write(fislogo,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

String encoding2 = "data:image/png;base64," + encoder.encodeToString(imageBytes);
          return encoding2;
    }
    public String encodechart(BufferedImage image) throws IOException{
         
 ImageIO.write(image,"png",new File("tmpImage.png"));
byte[] imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));

Base64.Encoder encoder = Base64.getEncoder();

String encoding = "data:image/png;base64," + encoder.encodeToString(imageBytes);
//getIntrepretation();


          return encoding;
    }
    
    
    public void generatePDFReport(String perf_threshold_trrecep,String perf_threshold_opreq, String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException{
         String encode1=encodechart(image1);
         String encode2=encodechart(image2);
         String encode3=encodechart(image3);
         String logoEncoding=encodelogo();
                      
         NewThymeleaf nt=new NewThymeleaf(encode1,encode2,encode3,logoEncoding,min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2, Test_Type3);
             
    }
      public void generateHTMLReport(String perf_threshold_trrecep,String perf_threshold_opreq, String perf_threshold_track,String Test_Type1,String Test_Type2,String Test_Type3) throws IOException, FileNotFoundException, DocumentException, TemplateException{
         String encode1=encodechart(image1);
         String encode2=encodechart(image2);
         String encode3=encodechart(image3);
         String logoEncoding=encodelogo();
         
         NewFreeMarker nf=new NewFreeMarker(encode1,encode2,encode3,logoEncoding,min1,max1,avr1,low_perf1,min2,max2,avr2,low_perf2,min3,max3,avr3,low_perf3,perf_threshold_trrecep,perf_threshold_opreq,perf_threshold_track,Test_Type1,Test_Type2, Test_Type3);
                       
   
    }
      
   
  
    private CategoryDataset createDataset(String index_name, String Test_Type) throws IOException {
JsonParser j=new JsonParser(index_name);


WritingCSV w1=new WritingCSV(max1, min1, avr1, low_perf1, j,Test_Type);
        double[][] data = j.arraycount;
       CategoryDataset dataset;
        String[] arraytime=j.arraytime;
        int k=0;
        dataset = DatasetUtilities.createCategoryDataset(
                new String[]{"time"},arraytime,data
        );
       

       return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) throws IOException, FileNotFoundException, DocumentException, TemplateException {




        JFreeChart chart = ChartFactory.createAreaChart(
                "SGW Performance Chart",
                "Time",
                "Trade count",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                true
        );
        
  


         


        CategoryPlot plot = (CategoryPlot) chart.getPlot();
   
        CategoryAxis axis = plot.getDomainAxis();

axis.setMaximumCategoryLabelLines(10000);

axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

    CategoryPlot p = chart.getCategoryPlot(); 
         ValueAxis axis2 = p.getRangeAxis();

        Font font = new Font("Dialog", Font.PLAIN, 10);
        axis.setTickLabelFont(font);
        
        Font font2 = new Font("Dialog", Font.PLAIN, 15);
        //axis2.setTickLabelFont(font2);
        plot.setForegroundAlpha(0.3f);
        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        
        renderer.setEndType(AreaRendererEndType.LEVEL);
       
        //renderer.setBaseVisible(false);
       
        //renderer.seteBaseShape(-3.0,-3.0,6.0,6.0);
        chart.setTitle(new TextTitle("SGW Performance",
                new Font("Serif", java.awt.Font.BOLD, 22))
        );
  
 return chart;

    }
    
    
    
       // Export the chart to an image. ------2
      protected static BufferedImage draw(JFreeChart chart, int width, int height) 
    { 
        BufferedImage img = 
        new BufferedImage(width , height, 
        BufferedImage.TYPE_INT_RGB); 
        Graphics2D g2 = img.createGraphics(); 
                        
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height)); 
 
        g2.dispose(); 
        return img; 
    }
    public static void writeChartAsPDF(OutputStream out, JFreeChart chart, int width, int height, FontMapper mapper) throws IOException, DocumentException {
Rectangle pagesize = new Rectangle(width, height); 
Document document = new Document(pagesize, 50, 50, 50, 50);

try { PdfWriter writer = PdfWriter.getInstance(document, out);
document.addAuthor("JFreeChart");
document.addSubject("Demonstration");
document.open();
PdfContentByte cb = writer.getDirectContent(); 
PdfTemplate tp = cb.createTemplate(width, height);
Graphics2D g2 = tp.createGraphics(width, height, mapper);
Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height); 
chart.draw(g2, r2D, null); 
g2.dispose(); 
cb.addTemplate(tp, 0, 0);
} catch(DocumentException de) { System.err.println(de.getMessage()); }
document.close();
}
    
    public static void saveChartAsPDF(File file, JFreeChart chart, int width, int height, FontMapper mapper) throws IOException, DocumentException {
OutputStream out = new BufferedOutputStream(new FileOutputStream(file)); 
writeChartAsPDF(out, chart, width, height, mapper); 
out.close();
    
}


}
