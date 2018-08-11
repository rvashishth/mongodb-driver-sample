package com.example.java.gettingstarted.mongo;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public abstract class Signup extends AbstractDocument{

	private static final long serialVersionUID = 1836845439330389578L;

	private String firstName;
	
	private String lastName;
		
	private String email;
	
	private String mobileNumber;
	
	private String currentLocation;
	
	private String gender;
	
	private boolean receiveSmsAlerts;
	
	private String role;
	
	private boolean active;
}
