package com.example.java.gettingstarted.mongo;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
public class User {

	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId _id;
	
//	@Field(value="mobileNumber")
//	@Indexed(unique=true)
	private String mobileNumber;
	
//	@Field(value="username")
//	@Indexed(unique=true)
	private String username;
	
	/*
	 * TODO: This must be annotated 
	 * with nested document - Hem 
	 */
	private Role role;

//	@JsonIgnore
	private String password;

}
