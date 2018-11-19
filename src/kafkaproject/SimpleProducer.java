package kafkaproject;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field.Str;

public class SimpleProducer {

	Properties props;

	KafkaProducer<String, String> producer;

	SimpleProducer() {

		Properties props = new Properties();

		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// props.put("num.partitions","3");
		// props.put("default.replication.factor", "3");

		producer = new KafkaProducer<>(props);
	}

	void produceAndPrint() {

		for (int i = 1; i < 100; i++)
			// Fire-and-forget send(topic, key, value)
			// Send adds records to unsent records buffer and return
			producer.send(new ProducerRecord<String, String>("prueba2", 0, Integer.toString(i),
					Integer.toString(i) + " " + Integer.toString(i).getBytes().length));
	}

	void produceSinlgeStation(int id) {

		ExtractJson.fillStationList();
		producer.send(
				new ProducerRecord<String, String>("Stations", "station", ExtractJson.stationList.get(id).toString()));

	}

	void produceSinglePartition() {

		ExtractJson.fillStationList();

		Date myDate = new Date();
		long time = myDate.getTime();
		ProducerSinglePartition.initial_timeSinglePartition = time;
		
		System.out.println("Tiempo producer inicial: " + time);

		for (Station station : ExtractJson.stationList) {

			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_UNO,  "dock_bikes", "" +station.getId()+","+ station.getDock_bikes()));
			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_UNO,  "free_bases", "" +station.getId()+","+  station.getFree_bases()));
			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_UNO,  "no_available", "" +station.getId()+","+  station.getNo_available()));

		}

		long timef = myDate.getTime();

		long res = timef - time;
		

	}

	void produceValuesStations() {

		ExtractJson.fillStationList();

		Date myDate = new Date();
		long time = myDate.getTime();
		SimpleConsumer.initial_timePartitions = time;
		System.out.println("Time initial: "+time);
		
		for (Station station : ExtractJson.stationList) {
			// producer.send(new ProducerRecord<String, String>("Stations", "id",
			// ""+station.getId()));
			// producer.send(new ProducerRecord<String, String>("Stations", "latitude",
			// ""+station.getLatitude()));
			// producer.send(new ProducerRecord<String, String>("Stations", "longitude",
			// ""+station.getLongitude()));
			// producer.send(new ProducerRecord<String, String>("Stations", "name",
			// ""+station.getName()));
			// producer.send(new ProducerRecord<String, String>("Stations", "light",
			// ""+station.getLight()));
			// producer.send(new ProducerRecord<String, String>("Stations", "number",
			// ""+station.getNumber()));
			// producer.send(new ProducerRecord<String, String>("Stations", "address",
			// ""+station.getAddress()));
			// producer.send(new ProducerRecord<String, String>("Stations", "activate",
			// ""+station.getActivate()));
			// producer.send(new ProducerRecord<String, String>("Stations", "no_available",
			// ""+station.getNo_available()));
			// producer.send(new ProducerRecord<String, String>("Stations", "total_bases",
			// ""+station.getTotal_bases()));

	

			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_DOS, 0, "dock_bikes",
					""+station.getId()+"," + station.getDock_bikes()));
			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_DOS, 1, "free_bases", ""+station.getId()+"," + station.getFree_bases()));
			producer.send(new ProducerRecord<String, String>(SimpleConsumer.TOPIC_DOS, 2, "reservations_count",
					""+station.getId()+"," +  + station.getReservations_count()));
		}
		System.out.println("producido");
	}

	/**
	 * Sends the Station in a String with the ID, Dock_Bikes or Free_Bases if they
	 * change. First the map with the station is update if it is empty.
	 */
	void produceRealTimeStations() {

		if (ExtractJson.stationMap.isEmpty()) {
			ExtractJson.fillStationMap();
			for (Map.Entry<String, Station> entry : ExtractJson.stationMap.entrySet()) {

				// System.out.println("clave=" + entry.getKey() + ", valor=" +
				// entry.getValue());
				producer.send(new ProducerRecord<String, String>("Stations", "station",
						"" + ExtractJson.stationMap.get(entry.getKey()).toString()));
			}
		}

		RealTimeThread t = new RealTimeThread();
		t.start();
	}

	void stop() {

		System.out.println("FIN");
		producer.close();

	}

	public static void main(String[] args) {
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceAndPrint();
		myProducer.stop();
	}

	public class RealTimeThread extends Thread {

		public void run() {
			while (true) {

				System.out.println("RealTimeThread running");
				int cnt = 0;
				HashMap<String, Station> map = ExtractJson.getStationMap();

				for (Map.Entry<String, Station> entry : map.entrySet()) {
					// System.out.println("clave=" + entry.getKey() + ", valor=" +
					// entry.getValue());
					String key = entry.getKey();

					if (map.get(key).getDock_bikes() != ExtractJson.stationMap.get(key).getDock_bikes()
							|| map.get(key).getFree_bases() != ExtractJson.stationMap.get(key).getFree_bases()) {
						producer.send(new ProducerRecord<String, String>("realtime", "station",
								"" + ExtractJson.stationMap.get(entry.getKey()).toString()));
						ExtractJson.stationMap.put(key, entry.getValue());
						cnt++;
					}

				}

				System.out.println("Estaciones cambiadas: " + cnt);

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
