package kafkaproject;

public class Station {

	private int id;
	private float latitude;
	private float longitude;
	private String name;
	private int light;
	private String number;
	private String address;
	private int activate;
	private int no_available;
	private int total_bases;
	private int doc_bikes;
	private int free_bases;
	private int reservations_count;
	
	
	public Station() {
		
	}
	
	

	private int getId() {
		return id;
	}



	private void setId(int id) {
		this.id = id;
	}



	private float getLatitude() {
		return latitude;
	}



	private void setLatitude(float latitude) {
		this.latitude = latitude;
	}



	private float getLongitude() {
		return longitude;
	}



	private void setLongitude(float longitude) {
		this.longitude = longitude;
	}



	private String getName() {
		return name;
	}



	private void setName(String name) {
		this.name = name;
	}



	private int getLight() {
		return light;
	}



	private void setLight(int light) {
		this.light = light;
	}



	private String getNumber() {
		return number;
	}



	private void setNumber(String number) {
		this.number = number;
	}



	private String getAddress() {
		return address;
	}



	private void setAddress(String address) {
		this.address = address;
	}



	private int getActivate() {
		return activate;
	}



	private void setActivate(int activate) {
		this.activate = activate;
	}



	private int getNo_available() {
		return no_available;
	}



	private void setNo_available(int no_available) {
		this.no_available = no_available;
	}



	private int getTotal_bases() {
		return total_bases;
	}



	private void setTotal_bases(int total_bases) {
		this.total_bases = total_bases;
	}



	private int getDoc_bikes() {
		return doc_bikes;
	}



	private void setDoc_bikes(int doc_bikes) {
		this.doc_bikes = doc_bikes;
	}



	private int getFree_bases() {
		return free_bases;
	}



	private void setFree_bases(int free_bases) {
		this.free_bases = free_bases;
	}



	private int getReservations_count() {
		return reservations_count;
	}



	private void setReservations_count(int reservations_count) {
		this.reservations_count = reservations_count;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
