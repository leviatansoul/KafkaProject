package kafkaproject;

public class ProducerRealTime {

	public static void main(String[] args) {
		
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceRealTimeStations();
		//myProducer.stop();

	}

}
