package com.qa.petstore.user.pojo;

import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class User {

	static Faker faker = new Faker();
	static createUser userPayload;
	static String username=faker.name().username();
	static String lastname="chanass";
	//lastname="channas";
	public static String getLastname() {
		return lastname;
	}
	public static void setLastname(String lastname) {
		User.lastname = lastname;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		User.username = username;
	}
	@BeforeMethod
	public static String  createData() {
		setUsername(faker.name().username());
		userPayload = new createUser();
		userPayload.setId(faker.hashCode());
		userPayload.setFirstName(faker.address().firstName());
		userPayload.setLastName(getLastname());
		setLastname("chanass");
		
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setUsername(getUsername());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(1);
		
		Gson gson= new Gson();
		String data=gson.toJson(userPayload).toString();
		return data;
	}
	
}
