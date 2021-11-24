package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Admin{
	
	private Queue<Driver> pendingDrivers = new LinkedList<Driver>();
	
	public void addToPendingDrivers(Driver iDriver) {
		pendingDrivers.add(iDriver);
	}
	public void takeAction() {
		Scanner scanner = new Scanner(System.in);
		while(!pendingDrivers.isEmpty()) {
			pendingDrivers.peek().toString();
			System.out.println("1- Accept, 2- Deny");
			int userInput = scanner.nextInt();
			if(userInput == 1) {
				DataBase.getData().addDriverToSystem(pendingDrivers.poll());
			}
			else if(userInput == 2) {
				pendingDrivers.poll();				
			}
		}
	}
	public void suspendDriver(String name) {
		DataBase.getData().removeDriverFromSystem(name);
	}
	public void suspendClient(String name) {
		DataBase.getData().removeClientFromSystem(name);
	}
	public void DisplayAllClients() {
		DataBase.getData().displayAllDrivers();
	}
	public void DisplayAllDrivers() {
		DataBase.getData().displayAllClients();
	}

}
