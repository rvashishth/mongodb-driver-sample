package com.example.java.gettingstarted.mongo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AbstractDocument implements Serializable{

	private static final long serialVersionUID = 3150829428837394682L;
	
	private String createdOn;
	
	private String updatedOn;
	
	private String createdBy;
	
	private String updatedBy;
}
