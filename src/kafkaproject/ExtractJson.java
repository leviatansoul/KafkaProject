package kafkaproject;
import com.google.gson.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.jetty.client.util.InputStreamResponseListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ExtractJson {
	//CARLOS ES UN POCO RETARDER
	public static String getFile(String url) {
		//-----------------------------------------------------//
	      //  Step 1:  Start creating a few objects we'll need.
	      //-----------------------------------------------------//

	      URL u;
	      InputStream is = null;
	      DataInputStream dis;
	      String s;
	      String res = ""; //

	      try {

	         //------------------------------------------------------------//
	         // Step 2:  Create the URL.                                   //
	         //------------------------------------------------------------//
	         // Note: Put your real URL here, or better yet, read it as a  //
	         // command-line arg, or read it from a file.                  //
	         //------------------------------------------------------------//

	         u = new URL(url);

	         //----------------------------------------------//
	         // Step 3:  Open an input stream from the url.  //
	         //----------------------------------------------//

	         is = u.openStream();         // throws an IOException

	         //-------------------------------------------------------------//
	         // Step 4:                                                     //
	         //-------------------------------------------------------------//
	         // Convert the InputStream to a buffered DataInputStream.      //
	         // Buffering the stream makes the reading faster; the          //
	         // readLine() method of the DataInputStream makes the reading  //
	         // easier.                                                     //
	         //-------------------------------------------------------------//

	         dis = new DataInputStream(new BufferedInputStream(is));

	         //------------------------------------------------------------//
	         // Step 5:                                                    //
	         //------------------------------------------------------------//
	         // Now just read each record of the input stream, and print   //
	         // it out.  Note that it's assumed that this problem is run   //
	         // from a command-line, not from an application or applet.    //
	         //------------------------------------------------------------//

	         while ((s = dis.readLine()) != null) {
	            //System.out.println(s);
	            res = res + s;
	         }

	      } catch (MalformedURLException mue) {

	         System.out.println("Ouch - a MalformedURLException happened.");
	         mue.printStackTrace();
	         System.exit(1);

	      } catch (IOException ioe) {

	         System.out.println("Oops- an IOException happened.");
	         ioe.printStackTrace();
	         System.exit(1);

	      } finally {

	         //---------------------------------//
	         // Step 6:  Close the InputStream  //
	         //---------------------------------//

	         try {
	            is.close();
	            
	         } catch (IOException ioe) {
	            // just going to ignore this one
	         }

	      } // end of 'finally' clause

	      System.out.println(res);
	      return res;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		URL XMLUrl = null;
		Document document = null;
       // HttpsURLConnection urlConnection = null;
        URLConnection urlC = null;

        try {
        	String url = ""; //Url del json (añadir la de BiciMad)
        	String jsonString = ExtractJson.getFile(url); //Obtiene Json en String
        	
        	Gson gson = new Gson();
        	JsonElement jelem = gson.fromJson(jsonString, JsonElement.class);
        	JsonObject jobj = jelem.getAsJsonObject(); //Obtenemos Json de la web
        	
        	//DE AQUI PARA ABAJO NO SIRVE
        	
        	XMLUrl = new URL(url);
           // urlConnection = (HttpsURLConnection) XMLUrl.openConnection();
           // InputStream is = urlConnection.getInputStream();
        		
        	urlC =  XMLUrl.openConnection();
        	
            InputStream is = urlC.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setExpandEntityReferences(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            document = builder.parse(is);
           // urlConnection.disconnect();
          //  urlC.

        }
        catch (Exception e) {

            e.printStackTrace();
        }
        
        NodeList placemarkList = document.getElementsByTagName("data");
        
            Element placemark = (Element) placemarkList.item(0);
            
            System.out.println(placemark.getTextContent());
        
	}

}
