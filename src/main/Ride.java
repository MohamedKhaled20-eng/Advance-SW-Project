package main;

import java.util.HashMap;

public class Ride {
	private boolean taken = false;
	private String source;
	private String destination;
	private Driver mainDriver;
	private double cost;
	private HashMap<Double, Driver> availableDrivers;
	
	Ride(String s, String d){
		this.source = s;
		this.destination = d;
		availableDrivers = new HashMap<Double, Driver>();
	}
	
	public void completeTheRide(Driver iDriver, double cost) {
		this.taken = true;
		this.availableDrivers= null;
		iDriver.addCompleteRide(this);
		this.mainDriver = iDriver;
		this.setCost(cost);
		
	}
	public void addToMap(Driver iDriver, double cost) {
		availableDrivers.put(cost, iDriver);
	}
	
	// Setter Methods 
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setDriver(Driver iDriver) {
		this.mainDriver = iDriver;
	}
	// Getters Methods
	public Driver getOffer(double cost) {
		return availableDrivers.get(cost);
	}
	public String getSource() {
		return this.source;
	}
	public String getDestination() {
		return this.destination;
	}
	public Driver getDriver() {
		return this.mainDriver;
	}
	public boolean getState() {
		return this.taken;
	}
	public double getCost() {
		return this.cost;
	}
	public int getSizeOfAvailable() {
		return availableDrivers.size();
	}
	public void displayAvailable() {
		
		System.out.println(availableDrivers.toString());
	}
	public String toString() {
		return "Source= " + this.getSource() + ", Destination= " + this.getDestination();
	}
	public String displayRide() {
		return "Source= " + this.getSource() + "\nDestination= " + this.getDestination() + "\nCost= " + this.getCost() + "\nDriver= " + this.getDriver().getUserName();
	}
}
