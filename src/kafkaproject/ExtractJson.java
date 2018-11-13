package kafkaproject;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import java.lang.reflect.Type;

public class ExtractJson {
	
	public static ArrayList<Station> stationList = new ArrayList<Station>();
	private final static String URL = "https://rbdata.emtmadrid.es:8443/BiciMad/get_stations/WEB.SERV.diego2.gd@gmail.com/9933C03A-C88F-4222-8556-6431A1D0D84A/";

	public static HashMap<String, Station> stationMap = new HashMap<String,Station>();
	
	public static String getFile(String url) {
		//-----------------------------------------------------//
	      //  Step 1:  Start creating a few objects we'll need.
	      //-----------------------------------------------------//

	      URL u;
	      InputStream is = null;
	      BufferedReader dis;
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

	         dis = new BufferedReader(new InputStreamReader(is));

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
	
	public static JsonObject getJson(String url) {
		
		JsonObject jobj = new JsonObject();
		 try {
	        	 //Url del json (añadir la de BiciMad)
	        	String jsonString = ExtractJson.getFile(url); //Obtiene Json en String
	        	Gson gson = new Gson();
	        	JsonElement jelem = gson.fromJson(jsonString, JsonElement.class);
	        	jobj = jelem.getAsJsonObject(); //Obtenemos Json de la web       	
	        	
	        }
	        catch (Exception e) {

	            e.printStackTrace();
	        }
		 return jobj; 
	}
	
	public static void fillStationList() {
		JsonObject json = ExtractJson.getJson(URL);
		
		String data = json.get("data").getAsString();
		
		
		Gson gson = new Gson();
    	JsonElement jelem = gson.fromJson(data, JsonElement.class);
    	JsonObject dataJson = jelem.getAsJsonObject();
    	
    	JsonArray stationsJson = dataJson.getAsJsonArray("stations");
    	
    	for(int i = 0; i<stationsJson.size(); i++) {
    		Station st = gson.fromJson(stationsJson.get(i), Station.class);
    		//System.out.println(st.getLatitude());
    		stationList.add(st);
    	}

        
        System.out.println(URL);
	}
	
	public static void fillStationMap() {
		
		JsonObject json = ExtractJson.getJson(URL);		
		String data = json.get("data").getAsString();		
		
		Gson gson = new Gson();
    	JsonElement jelem = gson.fromJson(data, JsonElement.class);
    	JsonObject dataJson = jelem.getAsJsonObject();
    	
    	JsonArray stationsJson = dataJson.getAsJsonArray("stations");
    	
    	for(int i = 0; i<stationsJson.size(); i++) {
    		Station st = gson.fromJson(stationsJson.get(i), Station.class);
    		//System.out.println(st.getLatitude());
    		stationMap.put(Integer.toString(st.getId()), st);
    	}

        
        System.out.println(URL);
	}
	
	public static HashMap<String, Station> getStationMap() {
		
		JsonObject json = ExtractJson.getJson(URL);		
		String data = json.get("data").getAsString();		
		
		Gson gson = new Gson();
    	JsonElement jelem = gson.fromJson(data, JsonElement.class);
    	JsonObject dataJson = jelem.getAsJsonObject();
    	
    	JsonArray stationsJson = dataJson.getAsJsonArray("stations");
    	
    	HashMap<String, Station> map = new HashMap<String,Station>();
    	
    	for(int i = 0; i<stationsJson.size(); i++) {
    		Station st = gson.fromJson(stationsJson.get(i), Station.class);
    		//System.out.println(st.getLatitude());
    		map.put(Integer.toString(st.getId()), st);
    	}
    	
    	System.out.println(URL);
    	return map;

        
       
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
    	
    	
        }

}
