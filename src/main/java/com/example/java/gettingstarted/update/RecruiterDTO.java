package com.example.java.gettingstarted.update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RecruiterDTO implements Serializable{

	private static final long serialVersionUID = -1123400612027801864L;
	
	private String _id;
	
	// Recruiter or Company
	//@NotEmpty(message="role must be either Recruiter or Company")
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	private String companyName;
	
	//@NotEmpty(message="Email is not present in request")
	private String email;
	
	private String locationString;
	
	private String city;
	
	private String latitude;
	
	private String longitude;
	
	//@NotEmpty(message="Mobile number is not present in request")
	private String mobileNumber;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	@JsonDeserialize(using=DateDeserializer.class)
	private Date lastModified;
	
	private String summary;
	
	private String yearsOfExp;
	
	private String uniqueIdType;
	
	private String uniqueIdNumber;
	
	private String website;
	
	private List<JobDTO> jobs = new ArrayList<>();
	
	private List<IndSubCategoryDTO> addNewCategories;
	
	private List<String> removeCategories;
	
	private String gstin;
	
	private String officeAddress;
	
}
