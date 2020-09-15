/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import Controller.ElasticsearchQuery;
import Controller.JsonParser;
import View.NewChart;
import View.NewFreeMarker;
import View.NewThymeleaf;

import View.WritingCSV;
import com.itextpdf.text.DocumentException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author ilhem
 */

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TemplateException, FileNotFoundException, DocumentException{
       
        Properties prop = new Properties ();
        
        FileInputStream ip; 
        
        ip = new FileInputStream ("/home/ilhem/NetBeansProjects/DataAnalyser/newproperties.properties");
        
        prop.load(ip);

        NewChart c=new NewChart(prop.getProperty ("index_name_trrecep"), prop.getProperty ("index_name_opreq"),prop.getProperty ("index_name_track"),prop.getProperty ("perf_threshold_trrecep"),prop.getProperty ("perf_threshold_opreq"),prop.getProperty ("perf_threshold_track"), prop.getProperty ("Test_Type1"),prop.getProperty ("Test_Type2"),prop.getProperty ("Test_Type3"));
        
 
        

    
    
    
    
    
	}

}

  
  
  
  

