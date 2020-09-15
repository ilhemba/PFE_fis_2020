package View;
import Controller.JsonParser;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
 

 
public class WritingCSV
{
   public WritingCSV(double max, double min, double avr, boolean low_perf,JsonParser j,String Test_Type) throws IOException
   {
      String csv = "data.csv";
      String csv2= "/home/ilhem/Bureau/Report/" + Test_Type + ".csv";
      
      CSVWriter writer = new CSVWriter(new FileWriter(csv2));
     String s1=new String();
            s1="time "+" , " + " nb of treated trade "+ " , " + " max "+","+"min" + "," + "avr";
       String [] record1 = s1.split(",");
        writer.writeNext(record1);
      
        String s2=new String();
              s2=j.arraytime [0] + "," +  j.arraycount[0][0] + "," + max + " , " + min + "," + avr ;
                      String [] record2 = s2.split(",");
        writer.writeNext(record2);
    
        String s5=new String();
        s5="";
       
        for (int i=1; i<j.arraycount[0].length;i++){
            s5=j.arraytime [i] + "," + j.arraycount[0][i];
            String [] record5 = s5.split(",");
        writer.writeNext(record5);
        }
          
     
      writer.close();
   }
}
