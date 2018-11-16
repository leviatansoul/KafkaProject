package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer1 {

	public static void main(String[] args) {
		SimpleConsumer consumer1  = new SimpleConsumer();
		
	    //TopicPartition topicPartition0 = new TopicPartition("Stations", 0); 
	    //List<TopicPartition> partition0 = Arrays.asList(topicPartition0);	
		
		List<String> topics = Arrays.asList("Stations");
		
	    consumer1.suscribe(topics);
	    
	    consumer1.consume();
	    
	    consumer1.stop();

	}
}
