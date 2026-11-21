package com.javarush.mashin.projectjstl.entity;

public class UserEntity {
	int id;
	String name;
	String password;
	String role ;

	public UserEntity(int id , String name, String password, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

}
