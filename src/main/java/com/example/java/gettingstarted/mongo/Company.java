package com.example.java.gettingstarted.mongo;

import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@ToString(callSuper=true)
public class Company extends Signup{
	
	private static final long serialVersionUID = 2238967059596738438L;

	//@JsonIgnore
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId _id;
	
	private String companyId;
	
	private String companyName;
	
	private String registrationNumber;
	
	private String websiteUrl;
	
	private int numOfEmpRangeStart;
	
	private int numOfEmpRangeEnd;
	
	private String typeOfCompany;
	
	private List<CompanyJob> jobs;
}