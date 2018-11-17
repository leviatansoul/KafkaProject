package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer2 {
	public static void main(String[] args) {

		SimpleConsumer consumer2  = new SimpleConsumer();

	    //TopicPartition topicPartition1 = new TopicPartition("Stations", 1);
	    //List<TopicPartition> partition1 = Arrays.asList(topicPartition1);
	    
		List<TopicPartition> topics = Arrays.asList(new TopicPartition(SimpleConsumer.TOPIC_UNO, 1));
		
	    consumer2.suscribePartitions(topics);

	    consumer2.consume();
	    
	    consumer2.stop();		
	}
}
