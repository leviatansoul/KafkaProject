package kafkaproject;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class SimpleProducer {

	Properties props;
	
	KafkaProducer<String, String> producer;
	
	SimpleProducer(){
		Properties props = new Properties();
		
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);

	}
	
	void produceAndPrint() {
		
		for (int i = 1; i < 100; i++)
		// Fire-and-forget send(topic, key, value)
		// Send adds records to unsent records buffer and return
		producer.send(new ProducerRecord<String, String>("SDTF", Integer
		.toString(i), Integer.toString(i)));
		}
	
	void produceSinlgeStation() {
		ExtractJson.fillStationList();
		producer.send(new ProducerRecord<String, String>("Stations", "station", ExtractJson.stationList.get(0).toString()));
	}
	
	void produceValuesStations() {
		ExtractJson.fillStationList();
		
		for(Station station : ExtractJson.stationList)
		{
			
			producer.send(new ProducerRecord<String, String>("Stations", "id", ""+station.getId()));
			
		}
		
	}
	
	void stop() {
		System.out.println("FIN");
		producer.close();
		
		}
	
	public static void main(String[] args) {
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceValuesStations();
		myProducer.stop();
	}

	
}
