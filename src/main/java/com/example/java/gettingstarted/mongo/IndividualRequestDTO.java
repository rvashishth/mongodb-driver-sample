package com.example.java.gettingstarted.mongo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class IndividualRequestDTO implements Serializable{
	
	private static final long serialVersionUID = -8246381576754658756L;
	
	private String _id;
	
	private String mobileNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String locationString;
	
	private String latitude;
	
	private String longitude;
	
	private String categoryId;

	private String categoryName;
	
	private String yearOfExp;
	
	private List<IndSubCategoryDTO> subCategories;
}
