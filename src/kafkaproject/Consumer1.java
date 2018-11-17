package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer1 {

	public static void main(String[] args) {
		SimpleConsumer consumer1  = new SimpleConsumer();
		
	    //TopicPartition topicPartition0 = new TopicPartition("Stations", 0); 
	    //List<TopicPartition> partition0 = Arrays.asList(topicPartition0);	
		
		List<TopicPartition> topics = Arrays.asList(new TopicPartition(SimpleConsumer.TOPIC_UNO, 0));
		
	    consumer1.suscribePartitions(topics);
	    
	    consumer1.consume();
	    
	    consumer1.stop();

	}
}
