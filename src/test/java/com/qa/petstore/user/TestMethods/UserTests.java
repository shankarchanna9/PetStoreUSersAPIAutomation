package com.qa.petstore.user.TestMethods;

import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.petstore.endpoints.UserRoutes;
import com.qa.petstore.excelutilities.DataProviders;
import com.qa.petstore.httpRequests.HTTPRequests;
import com.qa.petstore.user.pojo.User;
import com.qa.petstore.user.pojo.createUser;

import io.restassured.response.Response;

public class UserTests extends HTTPRequests {

	//To read the data from properties file
	public ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	@Test(priority=1)
	void postRequestTest() {	
		String postUrl=getURL().getString("post_url").trim();
		//String postUrl= UserRoutes.postRequestURL;
		System.out.println(postUrl);
		Response response=HTTPRequests.postRequest(User.createData(), postUrl);
		response.then().log().all();
		System.out.println(response.getBody().toString());
	}
	
	@Test(priority=2)
	void getRequestTest() {
		Response response=HTTPRequests.getRequest(User.getUsername(), UserRoutes.getRequestURL);
		response.then().log().all();
		response.then().statusCode(200);
	}
	@Test(priority=3)
	void putRequestTest() {		
		Response response=HTTPRequests.putRequest(User.getUsername(),User.createData(), UserRoutes.putRequestURL);
		response.then().log().all();
	}
	@Test(priority=4)
	void deleteRequestTest() {		
		Response response=HTTPRequests.deleteRequest(User.getUsername(), UserRoutes.deleteRequestURL);
		response.then().log().all();
	}
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void postRequestUsingDataDriven(String userID,String fname, String lname,String email,String password, String phone) {
		createUser payload=new createUser();
		payload.setId(Integer.parseInt(userID));
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response response=HTTPRequests.postRequest(payload.toString(), UserRoutes.postRequestURL);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
}
