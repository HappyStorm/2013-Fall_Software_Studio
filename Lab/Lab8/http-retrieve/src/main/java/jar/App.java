package jar;

import org.apache.http.client.ClientProtocolException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClientProtocolException, Exception
    {
    	Httpretrieve httpr= new Httpretrieve("http://en.wikipedia.org/wiki/Apache_Maven");
    	httpr.retrieveHttp();
    }
}
