package com.example.java.gettingstarted.mongo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CreateIndividual {

	public static String main() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper genericMapper = new ObjectMapper();
		genericMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		IndividualRequestDTO dto = new IndividualRequestDTO();
		ObjectId id = new ObjectId();
		System.out.println(id.toHexString());
		System.out.println(id.toString());
		
		dto.set_id(id.toString());
		dto.setCategoryId("1");
		dto.setCategoryName("Accountant");
		dto.setEmail("rahul.vashishth@gmail.com");
		dto.setLocationString("gurgaon");
		dto.setFirstName("rahul");
		dto.setLastName("vashishth");
		dto.setLatitude("28.457523");
		dto.setLongitude("77.026344");
		dto.setMobileNumber("9810936551");
		dto.setYearOfExp("3");
		
		List<IndSubCategoryDTO> subCategories = new ArrayList<>();
		IndSubCategoryDTO subDto = new IndSubCategoryDTO();
		subDto.setId("3");
		subDto.setName("Taxation");
		subCategories.add(subDto);
		
		IndSubCategoryDTO subDto2 = new IndSubCategoryDTO();
		subDto2.setId("1");
		subDto2.setName("Clerk/Book Keeper");
		subCategories.add(subDto2);
		
		dto.setSubCategories(subCategories);
		
		//configure Object mapper for pretty print
		genericMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//writing to console, can write to any output stream such as file
		StringWriter stringEmp = new StringWriter();
		genericMapper.writeValue(stringEmp, dto);
		System.out.println("Employee JSON is\n"+stringEmp);
		return stringEmp.toString();
	}
}
