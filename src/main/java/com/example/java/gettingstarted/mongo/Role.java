package com.example.java.gettingstarted.mongo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rvashishth
 *
 */
@Data
@NoArgsConstructor
public class Role implements Serializable {

	private static final long serialVersionUID = 2864434740955553168L;

	// TODO: it should have short instead of long
	@JsonDeserialize(using=LongDeserializer.class)
	//@JsonIgnore
	private long _id;

	private String rolename;

	private String description;

	public Role(long id, String rolename, String description) {
		super();
		this._id = id;
		this.rolename = rolename;
		this.description = description;
	}

}
