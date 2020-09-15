/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ilhem
 */
public class JsonParser {
       public double[][] arraycount;
    public String[] arraytime;
    boolean low_performance=false;
    
    public JsonParser(String index_name) throws IOException{
    ElasticsearchQuery eq= new ElasticsearchQuery();
        String s1= new String();
        s1 = eq.AggQuery(index_name);
        JSONParser jsonParser = new JSONParser();
		Object object;
                String s2= "{\n" +
"  \"took\" : 9,\n" +
"  \"timed_out\" : false,\n" +
"  \"_shards\" : {\n" +
"    \"total\" : 1,\n" +
"    \"successful\" : 1,\n" +
"    \"skipped\" : 0,\n" +
"    \"failed\" : 0\n" +
"  },\n" +
"  \"hits\" : {\n" +
"    \"total\" : {\n" +
"      \"value\" : 10000,\n" +
"      \"relation\" : \"gte\"\n" +
"    },\n" +
"    \"max_score\" : null,\n" +
"    \"hits\" : [ ]\n" +
"  },\n" +
"  \"aggregations\" : {\n" +
"    \"group_by_state\" : {\n" +
"      \"doc_count_error_upper_bound\" : 0,\n" +
"      \"sum_other_doc_count\" : 174975,\n" +
"      \"buckets\" : [\n" +
"        {\n" +
"          \"key\" : \"15:14:56\",\n" +
"          \"doc_count\" : 500\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:54\",\n" +
"          \"doc_count\" : 497\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:48\",\n" +
"          \"doc_count\" : 487\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:52\",\n" +
"          \"doc_count\" : 483\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:50\",\n" +
"          \"doc_count\" : 462\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:49\",\n" +
"          \"doc_count\" : 446\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:53\",\n" +
"          \"doc_count\" : 440\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:55\",\n" +
"          \"doc_count\" : 430\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"15:14:51\",\n" +
"          \"doc_count\" : 429\n" +
"        },\n" +
"        {\n" +
"          \"key\" : \"14:57:29\",\n" +
"          \"doc_count\" : 398\n" +
"        }\n" +
"      ]\n" +
"    }\n" +
"  }\n" +
"}";

		try {

			object = jsonParser.parse(s1);
			JSONObject jsonObject = (JSONObject) object;

			long took = (long) jsonObject.get("took");
			//System.out.println("took: " + took);

			Boolean timeout = (Boolean) jsonObject.get("timed_out");
        //                System.out.println("timed_out: " + timeout);


			JSONObject aggregations = (JSONObject) jsonObject.get("aggregations");
	                JSONObject agg1 = (JSONObject) aggregations.get("agg1");
			//System.out.println("agg1: " + agg1);
			JSONArray buckets = (JSONArray) agg1.get("buckets");
		//	System.out.println("\tbuckets: " + buckets);

			Object composeObj = jsonObject.get("compose");
			JSONObject jsonObject1 = (JSONObject) composeObj;
			Iterator itr1 = buckets.iterator();
                        int size=0;
                        while (itr1.hasNext()) {
                  //          System.out.println("entrer dans la boucle");
                        size++;
                        
                        Object obj=itr1.next();
                        }
                        
                 //       System.out.println("size" + size);
                       
                         Iterator itr2 = buckets.iterator();
      arraycount = new double[1][size];
       
       arraytime = new String[size];
int i=0;
			while (itr2.hasNext()) {

				Object slide = itr2.next();
				JSONObject jsonObject2 = (JSONObject) slide;
				
                               long doc_count = (long) jsonObject2.get("doc_count");

                               String key = (String) jsonObject2.get("key");
		                arraycount[0][i]=doc_count;
                                
				//System.out.println("\t\tkey: " + key);
                                arraytime[i]=key;
                                i++;
                	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
    }
    
    
    
    public double[] MaxMinArraycount(){
        double[] MaxMin = new double[2];
        low_performance=false;
        double max = arraycount[0][0];
    double min = arraycount[0][0];
    for(int i = 1; i < arraycount[0].length; i++){
        if(max < arraycount[0][i]){
        max = arraycount[0][i];
        
        }else if(min > arraycount[0][i]){
        min = arraycount[0][i];
           }		   		   
    }
    
    MaxMin[0]=max;
        MaxMin[1]=min;

    if (min < 200.0){
       // System.out.println("low performance ");
        low_performance=true;
    }
    return MaxMin;
    }
    
    public boolean IsPerformant(){
        return low_performance;
    }
    
    
    public double calculate_average(){
        int i;
        double s=0.0;
        for( i=0;i<arraycount[0].length;i++){
            s=s+arraycount[0][i];
        }
        double a=s / arraycount[0].length;
        return a ;
        
    }
   
}
