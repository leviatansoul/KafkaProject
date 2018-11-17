package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class ConsumerRealTime {

	public static void main(String[] args) {
		SimpleConsumer consumer3  = new SimpleConsumer();

	    //TopicPartition topicPartition3 = new TopicPartition("Stations", 2);
	    //List<TopicPartition> partition3 = Arrays.asList(topicPartition3);	
	    
		List<String> topics = Arrays.asList(SimpleConsumer.TOPIC_REALTIME);
		
	    consumer3.suscribe(topics);

	    consumer3.consume();

	    consumer3.stop();

	}
	
}
