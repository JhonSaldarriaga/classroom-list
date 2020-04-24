package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Classroom {

	private ArrayList<UserAccount> accounts;
	
	public Classroom() {
		accounts = new ArrayList<UserAccount>();
	}
	
	public void add(String userName, String password, String gender, String carrer, LocalDate birthday, 
				String favoriteBrowser, String routProfileImage) {
		UserAccount newUser = new UserAccount(userName, password, gender, carrer, birthday, favoriteBrowser, routProfileImage);
		accounts.add(newUser);
	}
	
	public ArrayList<UserAccount> getAccounts(){
		return accounts;
	}
	
	public boolean exist(String name, String password) {
		
		if(!accounts.isEmpty()) {
			for(int i = 0; i<accounts.size(); i++) {
				if(accounts.get(i).getUserName().equals(name) && accounts.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public UserAccount search(String name, String password) {
		if(!accounts.isEmpty()) {
			for(int i = 0; i<accounts.size(); i++) {
				if(accounts.get(i).getUserName().equals(name) && accounts.get(i).getPassword().equals(password)) {
					return accounts.get(i);
				}
			}
		}
		return null;
	}
}
