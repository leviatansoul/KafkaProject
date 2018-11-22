package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer2 {
	public static void main(String[] args) {

		SimpleConsumer consumer2  = new SimpleConsumer();
	    
		List<TopicPartition> topics = Arrays.asList(new TopicPartition(SimpleConsumer.TOPIC_DOS, 1));
		
	    consumer2.suscribePartitions(topics);

	    consumer2.consumePartitions();
	    
	    consumer2.stop();		
	}
}
