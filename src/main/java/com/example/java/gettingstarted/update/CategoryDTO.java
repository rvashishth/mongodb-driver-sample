package com.example.java.gettingstarted.update;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1899515698962383662L;

	private String id;

	private String name;

	private boolean enabled;

	private String icon;
	
	private boolean enableLink;
	
	private boolean enableImage;
	
	private boolean enableVideo;

	private List<CategoryDTO> subcategory;

}
