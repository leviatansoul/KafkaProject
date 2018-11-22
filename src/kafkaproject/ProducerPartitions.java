package kafkaproject;

import java.util.HashMap;

public class ProducerPartitions {
	
	public static HashMap<String, String> freeBasesPartitionsMap = new HashMap<String,String>();
	public static HashMap<String, String> dockBikesPartitionsMap = new HashMap<String,String>();
	public static HashMap<String, String> noAvailablePartitionsMap = new HashMap<String,String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceValuesStations();
		myProducer.stop();

	}
}
