package com.example.java.gettingstarted.mongo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class CompanyJob extends AbstractDocument{
	
	private static final long serialVersionUID = -2904133715796090420L;

	private String jobId;
	
	private String jobTitle;
	
	private Integer minRequiredExp;
	
	private Integer maxRequiredExp;
	
	private String jobLocation;
	
	private String minOfferedSalary;
	
	private String maxOfferedSalary;
	
	/* PERANUM,PERMONTH */
	private MiscEnum ctcType;
	
	/* LAKH,THOUSANDS*/
	private MiscEnum salaryUnit;
	
	private List<String> skills;
	
	private String description;
	
	private String employerDetails;
	
	private String functionalArea;
	
	private String industry;
	
	private List<String> otherSkills;
	
	/*NEW or DRAFT, ACTIVATE, DEACTIVATE, EXPIRED*/
	private MiscEnum status;
	
	private String jobActivatedOn;
	
	private String jobExpiredOn;
	
	private String postedOn;
	
}