package com.kanawish.codelab.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	public static List<User> buildMockUserList() {
		ArrayList<User> users = new ArrayList<>();

		users.add(new User("Alice", "Alguard"));
		users.add(new User("Bob", "Barker"));
		users.add(new User("Charles", "Colander"));
		users.add(new User("Damien", "Delany"));

		return users;
	}

	String firstName;
	String lastName;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
