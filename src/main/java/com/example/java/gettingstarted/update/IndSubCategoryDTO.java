package com.example.java.gettingstarted.update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class IndSubCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1818909937087154172L;

	private String id;

	private String name;
	
	private List<String> images;
	
	private List<String> videos;
	
	private List<String> links;
	
	public List<String> getLinks(){
		if(null == links)
			return new ArrayList<>();
		else
			return links;
	}
	
	public List<String> getImages(){
		if(null == images)
			return new ArrayList<>();
		else
			return images;
	}
	
	public List<String> getVideos(){
		if(null == videos)
			return new ArrayList<>();
		else
			return videos;
	}
}
