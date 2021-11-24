package main;

import java.util.Scanner;

public class ControlPanel {
	private Client currentClient;
	private Driver currentDriver;
	private Admin currentAdmin;;
	private RideController myController;
	private DataBase myDataBase;
	private Scanner scanner;
	private String userInput;
	
	ControlPanel(){
		currentAdmin = new Admin();
		myController = new RideController();
		myDataBase = DataBase.getData();
		scanner = new Scanner(System.in);
	}
	public void mainMenu() {
		System.out.println("Welcome to Rakabny, Enter as:");
		System.out.println("1- Client");
		System.out.println("2- Driver");
		System.out.println("3- Admin");
		System.out.println("Enter 0 to Exit");
	}
	public void EnteringMenu() {
		System.out.println("1- Sign in");
		System.out.println("2- Sign up");
		System.out.println("Enter 0 to return");
	}
	public void clientMainMenu() {
		System.out.println("Welcome Client " + currentClient.getUserName() + " to Rakabny");
		System.out.println("1- Take a ride");
		System.out.println("2- Display my complete rides");
		System.out.println("3- Display my pending rides");
		System.out.println("4- Sign out");
	}
	public void driverMainMenu() {
			System.out.println("Welcome Driver " + currentDriver.getUserName() + " to Rakabny");
			System.out.println("1- Show available rides");
			System.out.println("2- Show Client Ratings");
			System.out.println("3- Show Favorite Areas");
			System.out.println("4- Add favortite Area");
			System.out.println("5- Sign out");	
	}
	public void adminMainMenu() {
		System.out.println("Welcome Admin to Rakabny");
		System.out.println("1- Accept pending drivers");
		System.out.println("2- Delete driver");
		System.out.println("3- Delete client");
		System.out.println("Enter 0 to return");
	}
	public void clientLogIn(String userName,String password) {
		System.out.println("Enter your userName and password space separated");
		userName = scanner.next();
		password = scanner.next();
		if(myDataBase.ClientExists(userName, password) != null) {
			this.currentClient = myDataBase.ClientExists(userName, password);
			while(true) {
				clientMainMenu();
				userInput = scanner.next();
				if(userInput.equals("1")) {
					String source, dest;
					System.out.println("Enter your source and destination space separated");
					source = scanner.next();
					dest = scanner.next();
					Ride curr = new Ride(source,dest);
					this.currentClient.addRideToPending(curr);
					myController.notifyDrivers(curr);
					System.out.println("Searching for available Drivers...");
				}
				else if(userInput.equals("2")) {
					currentClient.displayCompleted();
					System.out.println("Choose a ride to rate its driver: ");
					userInput = scanner.next();
					int index = Integer.parseInt(userInput);
					if(index >= 1 && index <= currentClient.getSizeOfCompleted()) {
						Ride iRide = currentClient.getCompleteRide(index-1);
						Driver iDriver = iRide.getDriver();
						currentClient.rateDriver(iDriver);
					}else {
						System.out.println("Wrong input");
					}
					
				}
				else if(userInput.equals("3")) {
					currentClient.displayPending();
					System.out.println("Choose your Ride");
					userInput = scanner.next();
					int index = Integer.parseInt(userInput);
					if( index >= 1 && index <= currentClient.getSizeOfPending()) {
						Ride curr =currentClient.getPendingRide(index-1);
						curr.displayAvailable();
						System.out.println("Choose your Offer");
						double cost = scanner.nextDouble();
						Driver currDriver = curr.getOffer(cost);
						if(currDriver != null) {
							curr.completeTheRide(currDriver, cost);
							currentClient.addRideToComplete(curr);
						}
						else {
							System.out.println("Wrong Input of cost.");
						}
					}else {
						System.out.println("Wrong input of rides number.");
					}
				}
				else if(userInput.equals("4")) {
					this.currentClient = null;
					break;
				}
				else {
					System.out.println("Wrong Input");
				}
			}
		} 
		else
			System.out.println("There's no such a client");
	}
	public void clientSignUp(String userName, String pass, String mobile, String email) {
		System.out.println("Enter your userName, passwrod, mobile, spaced separated");
		userName = scanner.next();
		pass = scanner.next();
		mobile = scanner.next();
		if(myDataBase.clientNameExists(userName) == null) {
			System.out.println("Want to add email? enter 0 to add");
			userInput = scanner.next();
			if(userInput.equals("0")) {
				System.out.print("Enter your email: ");
				email = scanner.next();
				myDataBase.addClientToSystem(new Client(userName, pass, mobile, email));
			}else
				myDataBase.addClientToSystem(new Client(userName, pass, mobile));
			System.out.println("You have created your account succefuly;");
		}
		else
			System.out.println("this Client already exists");
	}
	
	public void driverLogIn(String userName, String password) {
		System.out.println("Enter your userName and password space separated");
		userName = scanner.next();
		password = scanner.next();
		if(myDataBase.DriverExists(userName, password) != null) {
			this.currentDriver = myDataBase.DriverExists(userName, password);
			while(true) {
				driverMainMenu();
				userInput = scanner.next();
				if(userInput.equals("1")) {
					this.currentDriver.setOffer();
				}
				else if(userInput.equals("2")) {
					this.currentDriver.displayRatings();
				}
				else if(userInput.equals("3")) {
					this.currentDriver.displayFavAreas();
				}	
				else if(userInput.equals("4")) {
					this.currentDriver.setFavAreas();
				}	
				else if(userInput.equals("5")) {
					this.currentDriver = null;
					break;
				}	
				else {
					System.out.println("Wrong Input");
				}
			}
		}
	}
	public void driverSignUp(String userName, String pass, String mobile, String email, String license, String id) {
		System.out.println("Enter your userName, passwrod, mobile, driverLicense, nationalId spaced separated");
		userName = scanner.next();
		pass = scanner.next();
		mobile = scanner.next();
		license = scanner.next();
		id = scanner.next();
		if(myDataBase.driverNameExists(userName) == null) {
			System.out.println("Want to add email? enter 0 to add");
			userInput = scanner.next();
			if(userInput.equals("0")) {
				System.out.print("Enter your email: ");
				email = scanner.next();
				currentAdmin.addToPendingDrivers(new Driver(userName, pass, mobile, email, license, id));
			}else
				currentAdmin.addToPendingDrivers(new Driver(userName, pass, mobile, license, id));
			System.out.println("You have created your account succefuly;");
		}
		else
			System.out.println("this driver already exists");
	}
	
	public void adminActions() {
		while(true) {
			adminMainMenu();
			this.userInput = scanner.next();
			if(userInput.equals("1")) {
				currentAdmin.takeAction();
			}
			else if(userInput.equals("2")) {
				System.out.println("Enter the driver userName: ");
				userInput = scanner.next();
				currentAdmin.suspendDriver(userInput);
			}
			else if(userInput.equals("3")) {
				System.out.println("Enter the client userName: ");
				userInput = scanner.next();
				currentAdmin.suspendClient(userInput);
			}
			else if(userInput.equals("0")) {
				currentAdmin=null;
				break;
			}
		}
	}
	public void executeProgram() {
		
		while(true) {
			mainMenu();
			userInput = scanner.next();
			if(userInput.equals("1")) {
				while(true) {
					EnteringMenu();
					userInput = scanner.next();
					if(userInput.equals("1")) {
						String user="", pass="";
						clientLogIn(user, pass);	
					}
					else if(userInput.equals("2")) {
						String userName="", pass="", mobile="", email="";
						clientSignUp(userName, pass, mobile, email);
					}
					else if(userInput.equals("0")) {
						break;						
					}
					else {
						System.out.println("Wrong Input");						
					}
				}
			}
			else if(userInput.equals("2")) {
				while(true) {
					EnteringMenu();
					userInput = scanner.next();
					if(userInput.equals("1")) {
						String user="", pass="";
						driverLogIn(user, pass);	
					}
					else if(userInput.equals("2")) {
						String userName="", pass="", mobile="", email="", license="",id="";
						driverSignUp(userName, pass, mobile, email, license, id);
					}
					else if(userInput.equals("0")) {
						break;						
					}
					else {
						System.out.println("Wrong Input");						
					}
				}
			}
			else if(userInput.equals("3")) {
					adminActions();
			}
			else if(userInput.equals("0"))
				break;
		}
		
	}
	
	public static void main(String[] args) {
		DataBase.getData().addClientToSystem(new Client("mido","123456","01235478"));
		DataBase.getData().addClientToSystem(new Client("nido","123456","01235478"));
		DataBase.getData().addClientToSystem(new Client("fido","123456","01235478"));
		
		DataBase.getData().addDriverToSystem(new Driver("hoda","123456","01235478","fgdsgdfgdg","2454687"));
		DataBase.getData().addDriverToSystem(new Driver("noda","123456","01235478","fgdsgdfgdg","2454687"));
		DataBase.getData().addDriverToSystem(new Driver("foda","123456","01235478","fgdsgdfgdg","2454687"));
		new ControlPanel().executeProgram();
	}
}
