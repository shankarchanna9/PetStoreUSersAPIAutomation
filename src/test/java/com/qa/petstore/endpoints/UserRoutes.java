package com.qa.petstore.endpoints;


public class UserRoutes {
	public static String base_URI="https://petstore.swagger.io/v2";
	public static String postRequestURL=base_URI+"/user";
	public static String getRequestURL=base_URI+"/user/{username}";
	public static String putRequestURL=base_URI+"/user/{username}";
	public static String deleteRequestURL=base_URI+"/user/{username}";
}
