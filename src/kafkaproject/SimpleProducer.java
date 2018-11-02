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
			producer.send(new ProducerRecord<String, String>("Stations", "latitude", ""+station.getLatitude()));
			producer.send(new ProducerRecord<String, String>("Stations", "longitude", ""+station.getLongitude()));
			producer.send(new ProducerRecord<String, String>("Stations", "name", ""+station.getName()));
			producer.send(new ProducerRecord<String, String>("Stations", "light", ""+station.getLight()));
			producer.send(new ProducerRecord<String, String>("Stations", "number", ""+station.getNumber()));
			producer.send(new ProducerRecord<String, String>("Stations", "address", ""+station.getAddress()));
			producer.send(new ProducerRecord<String, String>("Stations", "activate", ""+station.getActivate()));
			producer.send(new ProducerRecord<String, String>("Stations", "no_available", ""+station.getNo_available()));
			producer.send(new ProducerRecord<String, String>("Stations", "total_bases", ""+station.getTotal_bases()));
			producer.send(new ProducerRecord<String, String>("Stations", "dock_bikes", ""+station.getDock_bikes()));
			producer.send(new ProducerRecord<String, String>("Stations", "free_bases", ""+station.getFree_bases()));
			producer.send(new ProducerRecord<String, String>("Stations", "reservations_count", ""+station.getReservations_count()));
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
