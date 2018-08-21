package com.example.java.gettingstarted.mongo;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class IndSubCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1818909937087154172L;

	private String id;

	private String name;
	
	private List<String> images;
	
	private List<String> videos;
	
	private List<String> links;
}
