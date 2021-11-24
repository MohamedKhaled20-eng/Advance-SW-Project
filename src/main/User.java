package main;

public class User {
	
	/**
	 * Class's Instance Variables
	 */
	private String userName;
	private String mobile;
	private String email;
	private String password;
	
	/**
	 * Public Class's Methods 
	 */
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param mobile
	 * Parameterized Constructor to set instance variables
	 */
	public User(String userName, String password, String mobile) {
		this.userName = userName;
		this.mobile = mobile;
		this.password = password;
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param mobile
	 * @param email
	 * Parameterized Constructor to set the email with the rest variables;
	 */
	public User(String userName, String password, String mobile, String email) {
		this(userName, password, mobile);
		this.email = email;
	}
	
	// Setters Methods
	
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	// Getters Methods
	/**
	 * 
	 * @return String
	 */
	public String getUserName() {
		return this.userName;
	}
	/**
	 * 
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * 
	 * @return String
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * 
	 * @return String
	 */
	public String getMobile() {
		return this.mobile;
	}
}
