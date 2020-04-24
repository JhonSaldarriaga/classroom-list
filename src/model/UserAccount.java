package model;

import java.time.LocalDate;

public class UserAccount {
	
	public static final String BROWSER_1 = "Google Chrome";
	public static final String BROWSER_2 = "Opera";
	public static final String BROWSER_3 = "Firefox";
	
	private String userName;
	private String password;
	private String gender;
	private String carrer;
	private LocalDate birthday; 
	private String favoriteBrowser;
	private String routProfileImage;
	
	public UserAccount(String userName, String password, String gender, String carrer, LocalDate birthday,
			String favoriteBrowser, String routProfileImage) {
		
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.carrer = carrer;
		this.favoriteBrowser = favoriteBrowser;
		this.birthday = birthday;
		this.routProfileImage = routProfileImage;
	}
	
	
	///////////////////////////////////////
	//GETTERS
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getCarrer() {
		return carrer;
	}

	public String getBirthday() {
		 
		 return birthday.toString();
	}

	public String getFavoriteBrowser() {
		return favoriteBrowser;
	}
	
	public String getRoutProfileImage() {
		return routProfileImage;
	}
}
