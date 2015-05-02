package jar;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Httpretrieve 
{
	public HttpResponse response;
	public HttpGet httpget;
	public CloseableHttpClient httpclient;
	public HttpEntity entity;
	
	public Httpretrieve(String str) throws ClientProtocolException, Exception
	{
		this.httpclient = HttpClients.createDefault();
		this.httpget = new HttpGet(str);
		this.response = this.httpclient.execute(httpget);
		this.entity = this.response.getEntity();
		//System.out.println(str);
				//http://en.wikipedia.org/wiki/Apache_Maven
	}
	
	public void retrieveHttp() throws IllegalStateException, Exception
	{
		if(this.entity != null) 
	    {
	        InputStream input = this.entity.getContent();
	        try 
	        {
	        	int data = input.read();
	        	while(data != -1) 
	        	{
	        		// data contains a char. Cast it to char and print it out
	        		System.out.print((char)data);
	        		data = input.read(); 		
	        	}
	        } 
	        finally
	        {
	        	input.close();
	        }
	    }
	}
    
} 