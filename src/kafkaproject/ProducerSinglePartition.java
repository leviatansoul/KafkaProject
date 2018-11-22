package kafkaproject;

import java.util.HashMap;

public class ProducerSinglePartition {
	
	public static  long initial_timeSinglePartition;
	public static HashMap<String, String> freeBasesSinglePartitionMap = new HashMap<String,String>();
	public static HashMap<String, String> dockBikesSinglePartitionMap = new HashMap<String,String>();
	public static HashMap<String, String> noAvailableSinglePartitionMap = new HashMap<String,String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceSinglePartition();
		myProducer.stop();

	}
}
