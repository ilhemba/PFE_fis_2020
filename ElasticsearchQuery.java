/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.action.index.IndexResponse;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;


import static org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.springframework.stereotype.Controller;


/**
 *
 * @author ilhem
 */
public class ElasticsearchQuery {
    
    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public  ElasticsearchQuery() {}
        // TODO code application logic here
        public String AggQuery(String index_name) throws UnknownHostException, IOException{
        Settings settings1;
        settings1 = Settings.builder()
               .put("search.max_buckets", "10000").build();
        TransportClient client;
        
        client = new PreBuiltTransportClient(settings1)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        
    
        
       // SearchResponse response = client.prepareSearch()
        //.get();
       
        
        /*IndexResponse response1;
        response1 = client.prepareIndex("test", "_doc", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy2")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
        */
        
        SearchResponse sr;
        sr = client.prepareSearch(index_name)
                
                .setQuery(QueryBuilders.matchAllQuery()).setSize(0)

                .addAggregation(
                        AggregationBuilders.terms("agg1").field("time.keyword").size(10000).order(BucketOrder.key(true))
                )
                .get();
        
        
        String s4 = sr.toString();
      //  System.out.println(s4);
        return (s4);
    }
    
}
