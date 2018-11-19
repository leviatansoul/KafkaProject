package kafkaproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;


public class SimpleConsumer {

	Properties props;
	
	public final static String TOPIC_DOS = "prueba2";
	
	public final static String TOPIC_UNO = "prueba1";

	public final static String TOPIC_REALTIME = "realtime";
	
	public static  long initial_timePartitions = 0;
	
	KafkaConsumer<String, String> consumer;
	
	SimpleConsumer(){
		Properties props = new Properties();
		
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "Group1");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,	StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		consumer = new KafkaConsumer<>(props);
	}
	
	public void suscribe(List<String> topics) {
		System.out.println("suscribe");

		this.consumer.subscribe(topics);
		//this.consumer.assign(partitions);

	}
	
	public void suscribePartitions( List<TopicPartition> partitions) {
		System.out.println("suscribe");

		this.consumer.assign(partitions);
		//this.consumer.assign(partitions);

	}
	
	public void stop() {
		System.out.println("FIN");
		this.consumer.close();	
	}
	
	 public void consume() {
		 System.out.println("consume enter");
			
		while(true) {
			 
			ConsumerRecords<String, String> records = this.consumer.poll(400);
			
			Date myDate = new Date();
			long time = myDate.getTime();
			
			for (ConsumerRecord<String, String> record : records) { 
				
				System.out.printf("offset = %d, key = %s, value = %s, partition = %s, timestamp: %d%n", record.offset(), record.key(), record.value(), record.partition(), record.timestamp());
				
			
			}
			
			Date myDate2 = new Date();
			long time2 = myDate2.getTime();
			long res = time2 -time;
			System.out.println("Tiempo consumer "+res);
		}
	}
	 
	 public void consumePartitions() {
		 
		 
		 System.out.println("consume enter");
			
			long t1 = 0;
			long t2 = 0;
			
			while(true) {
				 
				ConsumerRecords<String, String> records = this.consumer.poll(1000);

				int cnt = 0;
				
				for (ConsumerRecord<String, String> record : records) { 
					
					int size = record.toString().getBytes().length;
					//System.out.println("Tamaño record: "+i);
					
					//System.out.printf("offset = %d, forID=%d, size = %d,  key = %s, value = %s, partition = %s, timestamp: %d%n", record.offset(), cnt, size, record.key(), record.value(), record.partition(), record.timestamp());
					String[] values = record.value().split(",");
					cnt++;
					
					switch (record.partition()) {
					
					case 0:
						ProducerPartitions.freeBasesPartitionsMap.put(values[0], values[1]);
						if(values[0].trim().equals("1")) {
							t1 =  record.timestamp();
						}
						if(values[0].trim().equals("175")) {
							t2 =  record.timestamp();
						}
						break;
						
					case 1:
						ProducerPartitions.dockBikesPartitionsMap.put(values[0], values[1]);
						break;
						
					case 2:
						ProducerPartitions.noAvailablePartitionsMap.put(values[0], values[1]);
						break;
					}			
				
				}
				
				System.out.println(""+ProducerPartitions.freeBasesPartitionsMap.size());
				if(ProducerPartitions.freeBasesPartitionsMap.size() >= 172) {

					long delay = 400;
					long t = t2-t1;
					//t=425;
					
					long of1 = Integer.parseInt(ProducerPartitions.freeBasesPartitionsMap.get("1"));
					long of2 = Integer.parseInt(ProducerPartitions.freeBasesPartitionsMap.get("175"));
					long total = 171;
					
					long val = total*((1000-delay)/t);
					
					double th = (val*235*8*3)/1000;
					
					System.out.println("DELAY : "+delay);
					System.out.println("total : "+total);
					System.out.println("Tiempo : "+t);
					System.out.println("THROUGHPUT : "+th+" Kbits");
				}
				
				

				//System.out.println("Tiempo consumer ");
			}
		 
		 
	 }
	 
	 public void consumeSinglePartition() {
		 System.out.println("consume enter");
			
		while(true) {
			 
			ConsumerRecords<String, String> records = this.consumer.poll(1000);

			int cnt = 0;
			
			for (ConsumerRecord<String, String> record : records) { 
				
				int size = record.toString().getBytes().length;
				//System.out.println("Tamaño record: "+i);
				
				System.out.printf("offset = %d, forID=%d, size = %d,  key = %s, value = %s, partition = %s, timestamp: %d%n", record.offset(), cnt, size, record.key(), record.value(), record.partition(), record.timestamp());
				String[] values = record.value().split(",");
				cnt++;
				
				switch (record.key()) {
				
				case "free_bases":
					ProducerSinglePartition.freeBasesSinglePartitionMap.put(values[0], values[1]);
					break;
					
				case "dock_bikes":
					ProducerSinglePartition.dockBikesSinglePartitionMap.put(values[0], values[1]);
					break;
					
				case "no_available":
					ProducerSinglePartition.noAvailableSinglePartitionMap.put(values[0], values[1]);
					break;
				}			
			
			}
			

			//System.out.println("Tiempo consumer ");
		}
	}
	
/*	public static void main(String[] args) {
		SimpleConsumer consumer1  = new SimpleConsumer();
		//SimpleConsumer consumer2  = new SimpleConsumer();
		
		//List<String> topics = Arrays.asList("Stations");
		//List<TopicPartition> partitions = new ArrayList<TopicPartition>();
		
		//partitions.add(new TopicPartition ("Stations", 2));
		//partitions.add(new TopicPartition ("Stations", 1));
		
	    TopicPartition topicPartition0 = new TopicPartition("Stations", 0);
	    TopicPartition topicPartition1 = new TopicPartition("Stations", 1);
	    
	    List<TopicPartition> partition0 = Arrays.asList(topicPartition0);	
	    //List<TopicPartition> partition1 = Arrays.asList(topicPartition1);
		
	    consumer1.suscribe(partition0);
	    //consumer2.suscribe(partition1);
	    
	    consumer1.consume();
	    //consumer2.consume();
	    
	    consumer1.stop();
	    //consumer2.stop();		
	}	*/
}
