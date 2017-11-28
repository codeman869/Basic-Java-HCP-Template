package com.codydeckard.tasks.services;

public class UserManager {
	private static final ThreadLocal<String> context = new ThreadLocal<String>();
	
	public static void setCurrentUser(String username) {
		
		context.set(username);
		
	}
	
	public static String getCurrentUser() {
		String username;
		
		username = context.get();
		
		return username == null ? "anonymous" : username;
	}
	
	public static void remove() {
		context.remove();
	}

}
