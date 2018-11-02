package kafkaproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;


public class SimpleConsumer {

	Properties props;
	
	KafkaConsumer<String, String> consumer;
	
	SimpleConsumer(){
		Properties props = new Properties();
		
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "Group1");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,	StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		consumer = new KafkaConsumer<>(props);
	}
	
	public void suscribe(List<String> topics, List<TopicPartition> partitions) {
		System.out.println("suscribe");

		//this.consumer.subscribe(topics);
		this.consumer.assign(partitions);
	}
	
	
	public void stop() {
		System.out.println("FIN");
		this.consumer.close();	
	}
	
	 public void consume() {
		 System.out.println("consume enter");
			
		while(true) {
			 
			ConsumerRecords<String, String> records = this.consumer.poll(400);
			
			for (ConsumerRecord<String, String> record : records) { 

				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
			}
		}
	}
	
	public static void main(String[] args) {
		SimpleConsumer consumerp = new SimpleConsumer();
		
		List<String> topics = Arrays.asList("Stations");
		List<TopicPartition> partitions = new ArrayList<TopicPartition>();
		
		partitions.add(new TopicPartition ("Stations", 0));
		
		consumerp.suscribe(topics, partitions);
		consumerp.consume();
		consumerp.stop();		
	}	
}
