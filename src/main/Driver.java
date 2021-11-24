package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver extends User implements OnlyDrivers{

	private String license;
	private String nationalId;
	private HashMap<Ratable, Integer> myRatings = new HashMap<Ratable, Integer>();
	private int sumOfRate;
	private double averageRating;
	private ArrayList<Ride> completeRides= new ArrayList<Ride>();
	private Queue<Ride> pendingRides= new LinkedList<Ride>();
	private ArrayList<String> favAreas = new ArrayList<String>();
	
	Driver(String userName, String password, String mobile, String license, String nationalId){
		super(userName, password, mobile);
		this.license = license;
		this.nationalId = nationalId;
	}
	Driver(String userName, String password, String mobile, String email, String license, String nationalId){
		super(userName, password, mobile, email);
		this.license = license;
		this.nationalId = nationalId;
	}
	
	// Setters
	public void setLicense(String license) {
		this.license = license;
	}
	
	public void setId(String id) {
		this.nationalId = id;
	}
	
	public void addCompleteRide(Ride myRide) {
		completeRides.add(myRide);
	}
	
	public void clacAvergae(int rate) {
		this.sumOfRate += rate;
		this.averageRating = (double) this.sumOfRate /  myRatings.size();
	}
	// Getters 
	/**
	 * 
	 * @return
	 */
	public String getNationalId() {
		return this.nationalId;
	}
	/**
	 * 
	 * @return
	 */
	public String getLicense() {
		return this.license;
	}
	/**
	 * 
	 * @return
	 */
	public double getAvgRate() {
		
		return this.averageRating;
	}
	public void addToMyRatings(Ratable iClient, int rate){
		 myRatings.put(iClient, rate);
	}
	/**
	 * 
	 * @param source
	 * @return
	 */

	public boolean isInMyArea(String source) {
		return favAreas.contains(source);
	}
	public void setFavAreas() {
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		while(true) {
			System.out.print("Enter your favorite area: ");
			// add here function to check if the area is correct or not
			userInput = scanner.next();
			if(userInput.equals("0"))
				break;
			favAreas.add(userInput);		
		}
	}
	/**
	 * 
	 */
	public void setOffer() {
		Scanner scanner = new Scanner(System.in);
		double cost;
		while(!pendingRides.isEmpty()) {
			System.out.println(pendingRides.peek().toString());
			System.out.println("Enter your offer for this Ride: ");
			cost = scanner.nextDouble();
			pendingRides.peek().addToMap(this, cost);
			pendingRides.poll();
		}
	}
	public void displayRatings() {
		System.out.println(this.myRatings.toString());
	}
	public void displayFavAreas() {
		System.out.println(this.favAreas.toString());
	}
	/**
	 * 
	 */
	@Override
	public void newRide(Ride myRide) {
		if(isInMyArea(myRide.getSource())) {
			pendingRides.add(myRide);
		};
	}
	
	@Override 
	public String toString() {
		return "userName= " + this.getUserName() + ", AvgRate= " + this.getAvgRate();
	}
	public String displayData() {
		return "userName= " + this.getUserName() + "\nNationalId= " + this.getNationalId() + "\nLicense= " + this.getLicense() + "\nMobile= " + this.getMobile() +
				"\nAvgRate= " + this.getAvgRate();
	}
}
