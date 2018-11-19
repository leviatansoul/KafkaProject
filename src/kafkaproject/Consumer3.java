package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class Consumer3 {
	public static void main(String[] args) {
		SimpleConsumer consumer3  = new SimpleConsumer();

	    //TopicPartition topicPartition3 = new TopicPartition("Stations", 2);
	    //List<TopicPartition> partition3 = Arrays.asList(topicPartition3);	
	    
		List<TopicPartition> topics = Arrays.asList(new TopicPartition(SimpleConsumer.TOPIC_DOS, 2));
		
	    consumer3.suscribePartitions(topics);

	    consumer3.consumePartitions();

	    consumer3.stop();

	}
}
