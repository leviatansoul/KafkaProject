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
	public static HashMap<String, Station> stationMap = new HashMap<String,Station>();
	
	private final static String URL = "https://rbdata.emtmadrid.es:8443/BiciMad/get_stations/WEB.SERV.diego2.gd@gmail.com/9933C03A-C88F-4222-8556-6431A1D0D84A/";

	public static String getFile(String url) {

	      URL u;
	      InputStream is = null;
	      BufferedReader dis;
	      String s, res = "";

	      try {

	         //Create the URL.
	         u = new URL(url);

	         // Step 3:  Open an input stream from the url.
	         is = u.openStream();

	         // Convert the InputStream to a buffered DataInputStream.
	         dis = new BufferedReader(new InputStreamReader(is));

	         // read each record of the input stream
	         while ((s = dis.readLine()) != null) {
	        	 
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

	         try {
	            is.close();
	            
	         } catch (IOException ioe) {
	        	 
	         }
	      }

	      //System.out.println(res);
	      return res;
	}
	
	public static JsonObject getJson(String url) {
		
		JsonObject jobj = new JsonObject();
		
		 try {
	        	//Returns the Json in string
	        	String jsonString = ExtractJson.getFile(url);
	        	
	        	Gson gson = new Gson();
	        	
	        	JsonElement jelem = gson.fromJson(jsonString, JsonElement.class);
	        	jobj = jelem.getAsJsonObject();      	
	        	
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
    		stationList.add(st);
    	}
     
        //System.out.println(URL);
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
    		map.put(Integer.toString(st.getId()), st);
    	}
    	
    	System.out.println(URL);
    	return map;       
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	
        }
}
