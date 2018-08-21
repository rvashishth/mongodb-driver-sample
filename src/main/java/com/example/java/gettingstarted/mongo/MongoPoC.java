package com.example.java.gettingstarted.mongo;


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.MongoClientSettings;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
//import com.mongodb.MongoClientOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

//@Service
public class MongoPoC {
	
	private  MongoClient mongoClient;
	
	private MongoDatabase database;
	
	private MongoCollection<Document> companyCollection;
	
	private MongoCollection<Document> userCollection;
	
	private MongoCollection<Document> individualCollection;
	
	ObjectMapper mapper = new ObjectMapper();
	
	Block<Document> printBlock = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};
	
	/*** A MongoClient instance represents a pool of connections to the database; 
	 * you will only need one instance of class MongoClient even with multiple threads.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException */
	@PostConstruct
	public void resourceInitialization() throws JsonParseException, JsonMappingException, IOException{
		// connect to atlas cluster
		
		// connect to localhost
		mongoClient = MongoClients.create();
		
		System.out.println("===========DB Names ========");
		MongoIterable<String> listDatabaseNames = mongoClient.listDatabaseNames();
		for(String names:listDatabaseNames){
			System.out.println(names);
		}
		
		database = mongoClient.getDatabase("tempemp");
		companyCollection = database.getCollection("company");
		individualCollection = database.getCollection("individual");
		userCollection = database.getCollection("users");
		
		updateOne();
	}
	
	public void updateOne(){
		UpdateResult updateOne = userCollection.updateOne(
                eq("_id", "5b79363285a4fd6410fb3931"),
                combine(Updates.set("role.rolename", "Individual"), Updates.set("role.description", "Individual user")));
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
		System.out.println(updateOne.wasAcknowledged());
	}
	
	public void createIndividual() throws JsonGenerationException, JsonMappingException, IOException{
		String indJson = CreateIndividual.main();
		Document parse = Document.parse(indJson);
		individualCollection.insertOne(parse);
	}
	
	public Optional<User> findByUsername(String msisdn) throws JsonParseException, JsonMappingException, IOException{
		userCollection = database.getCollection("users");
		
		Document first = userCollection.find(eq("username", msisdn)).first();
		
		ObjectMapper userMapper = new ObjectMapper();
		userMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		User readValue = userMapper.readValue(first.toJson(), User.class);
		System.out.println(readValue);
		return null;
	}
	
	// done
	public void findCompanyByCompanyId(){
		System.out.println("=======findCompanyByCompanyId=====");
		FindIterable<Document> companies = companyCollection.find(eq("companyId", "5b6d8144f8b8e48a27c38a52"));
		companies.forEach(printBlock);
		
	}
	
	// done
	public void findCompanyIdAndJobsByCompanyId(){
		System.out.println("=======findCompanyByCompanyId=====");
		FindIterable<Document> companies = companyCollection.find(eq("companyId", "5b6d8144f8b8e48a27c38a52"))
									.projection(include("_id","jobs","companyId"));
		companies.forEach(printBlock);	
	}
	
	// done - this will return only one element from the job array.
	public void findCompanyIdAndJobsByJobsId() throws JsonParseException, JsonMappingException, IOException{
		System.out.println("=======findCompanyIdAndJobsByJobsId=====");
		/*Document companyJob = companyCollection.find(eq("jobs.jobId", "5b6dad49f8b8e4ac8873039b"))
										.projection(fields(include("_id","companyId","jobs"),elemMatch("jobs")))
										.first();
		*/
		Document companyJob = companyCollection.find(eq("companyId", "5b6d8144f8b8e48a27c38a52"))
				.projection(fields(include("_id","jobs","companyId"),elemMatch("jobs")))
				.first();
		String compnayJobJson = companyJob.toJson();
		System.out.println("=======compnayJobJson=====");
		System.out.println(compnayJobJson);
		
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//mapper.add
		Company readValue = mapper.readValue(compnayJobJson, Company.class);
		
		System.out.println(readValue);
		System.out.println("======================readValue.getJobs().size()==================="+readValue.getJobs().size());
		//companies.forEach(printBlock);	
	}
	
	public void findCompanyIdAndJobsByJobsIdAndCompanyId() throws JsonParseException, JsonMappingException, IOException{
		System.out.println("=======findCompanyIdAndJobsByJobsIdAndCompanyId=====");
		Document companies = companyCollection.find(eq("companyId", "5b6d8144f8b8e48a27c38a52"))
										.projection(include("_id","jobs","companyId"))
										.first();
		String compnayJobJson = companies.toJson();
		System.out.println("=======compnayJobJson=====");
		System.out.println(compnayJobJson);
		Company readValue = mapper.readValue(compnayJobJson, Company.class);
		System.out.println(readValue);
		System.out.println(readValue.getJobs());
		//companies.forEach(printBlock);
		
	}
	
	// returns empty documents - failed
	public void findCompanyById(){
		System.out.println("=======findCompanyById=====");
		FindIterable<Document> companies = companyCollection.find(eq("_id", "5b6d8144f8b8e48a27c38a52"));
		companies.forEach(printBlock);
		
	} 
	
	public void includeExcludeCompanyByMobileNumber() throws JsonParseException, JsonMappingException, IOException{
		System.out.println("==============includeExcludeCompanyByMobileNumber===============");
		Document first = companyCollection.find(eq("mobileNumber", "9953503835"))
							.projection(fields(include("companyName","companyId","firstName","lastName","mobileNumber")))
							.first();
		
		Company readValue = mapper.readValue(first.toJson(), Company.class);
		System.out.println(readValue);
	}
	
	public void findAndMap(){
		
	}
	// done
	public void printCompanyCollection(){
		/* To dispose of an instance, call MongoClient.close() to clean up resources. */
		companyCollection.find().forEach(printBlock);
	}
	
	@PreDestroy
	public void resourceCleanup(){
		mongoClient.close();
	}
	
	public void findIndByMobileNumber(){
		Document first = individualCollection.find(eq("mobileNumber", "9810936551"))
				.projection(fields(include("_id")))
				.first();
		System.out.println(first.toJson());
		System.out.println(first.get("_id"));
		
	}
	
}
