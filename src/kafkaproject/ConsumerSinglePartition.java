package kafkaproject;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.TopicPartition;

public class ConsumerSinglePartition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleConsumer consumer  = new SimpleConsumer();
		
	    consumer.suscribe(Arrays.asList(SimpleConsumer.TOPIC_UNO));
	    
	    consumer.consumeSinglePartition();
	    
	    consumer.stop();

	}

}
