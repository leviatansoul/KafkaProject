package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer1 {

	public static void main(String[] args) {
		SimpleConsumer consumer1  = new SimpleConsumer();
		
		List<TopicPartition> topics = Arrays.asList(new TopicPartition(SimpleConsumer.TOPIC_DOS, 0));
		
	    consumer1.suscribePartitions(topics);
	    
	    consumer1.consumePartitions();
	    
	    consumer1.stop();

	}
}
