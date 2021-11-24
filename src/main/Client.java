package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User implements Ratable{

	private ArrayList<Ride> pendingRides = new ArrayList<Ride>();
	private ArrayList<Ride> completeRides = new ArrayList<Ride>();
	
	Client(String userName, String password, String mobile){
		super(userName, password, mobile);
	}
	
	Client(String userName, String password, String mobile, String email){
		super(userName, password, mobile, email);
	}
	
	@Override
	public void rateDriver(Driver iDriver) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your rate from 1-5: ");
		int rate = scanner.nextInt();
		iDriver.addToMyRatings(this, rate);
		iDriver.clacAvergae(rate);
	}
	
	public Ride getPendingRide(int index) {
		return pendingRides.get(index);
	}
	public Ride getCompleteRide(int index) {
		return completeRides.get(index);
	}
	public void addRideToPending(Ride iRide) {
		pendingRides.add(iRide);
	}
	public void addRideToComplete(Ride iRide) {
		pendingRides.remove(iRide);
		completeRides.add(iRide);
	}
	public int getSizeOfPending() {
		return pendingRides.size();
	}
	public int getSizeOfCompleted() {
		return completeRides.size();
	}
	public void displayCompleted() {
		for(Ride iRide:this.completeRides) {
			System.out.println(iRide.displayRide());
		}
	}
	public void displayPending() {
		System.out.println(this.pendingRides.toString());
	}

	@Override
	public String toString() {
		return "userName= " + this.getUserName();
	}
}
