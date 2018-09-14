package com.example.java.gettingstarted.update;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class JobDTO implements Serializable{

	private static final long serialVersionUID = 3633916204810763616L;
	
	private String mobileNumber;
	
	/* unique jobid */
	private String jobId;
	
	private String recruiterId;
	
	private String jobTitle;
	
	/* always in years */
	private Integer minRequiredExp;
	
	private Integer maxRequiredExp;
	
	private String jobLocation;
	
	private Integer minOfferedSalary;
	
	private Integer maxOfferedSalary;
	
	/* PERANUM,PERMONTH */
	private MiscEnum ctcType;
	
	/* LAKH,THOUSAND*/
	private MiscEnum salaryUnit;
	
	private List<String> skills;
	
	private String description;
	
	private String employerDetails;
	
	private String functionalArea;
	
	private String industry;
	
	private List<String> otherSkills;
	
	/*DRAFT,POST */
	private MiscEnum status;
	
	private String createdBy;
	
	private String updatedBy;
	
	@JsonDeserialize(using=DateDeserializer.class)
	private Date createdOn;
	
	@JsonDeserialize(using=DateDeserializer.class)
	private Date updatedOn;
	
	private String event;
	
}