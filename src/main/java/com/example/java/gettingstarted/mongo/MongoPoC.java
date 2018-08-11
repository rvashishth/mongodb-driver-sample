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
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;

import com.mongodb.MongoClientSettings;
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
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class MongoPoC {
	
	private  MongoClient mongoClient;
	
	private MongoDatabase database;
	
	private MongoCollection<Document> companyCollection;
	
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
		//mongoClient = MongoClients.create("mongodb+srv://username:password3@atlasdevelopmentcluster-vuzoa.gcp.mongodb.net/databasename?retryWrites=true");
		// connect to localhost
		mongoClient = MongoClients.create();
		
		System.out.println("===========DB Names ========");
		MongoIterable<String> listDatabaseNames = mongoClient.listDatabaseNames();
		for(String names:listDatabaseNames){
			System.out.println(names);
		}
		
		database = mongoClient.getDatabase("tempemp");
		companyCollection = database.getCollection("company");
		
		findCompanyIdAndJobsByJobsId();
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
		Document companyJob = companyCollection.find(eq("jobs.jobId", "5b6dad49f8b8e4ac8873039b"))
										.projection(fields(include("_id","companyId","jobs"),elemMatch("jobs")))
										.first();
		String compnayJobJson = companyJob.toJson();
		System.out.println("=======compnayJobJson=====");
		System.out.println(compnayJobJson);
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//mapper.add
		Company readValue = mapper.readValue(compnayJobJson, Company.class);
		
		System.out.println(readValue);
		System.out.println("======================readValue.getJobs().size()==================="+readValue.getJobs().size());
		//companies.forEach(printBlock);	
	}
	
	public void findCompanyIdAndJobsByJobsIdAndCompanyId(){
		System.out.println("=======findCompanyIdAndJobsByJobsIdAndCompanyId=====");
		FindIterable<Document> companies = companyCollection.find(and(eq("companyId", "5b6d8144f8b8e48a27c38a52"),
											eq("jobs.jobId", "5b6dad49f8b8e4ac8873039b")))
										.projection(include("_id","jobs","companyId"));
		companies.forEach(printBlock);
		
	}
	
	// returns empty documents - failed
	public void findCompanyById(){
		System.out.println("=======findCompanyById=====");
		FindIterable<Document> companies = companyCollection.find(eq("_id", "5b6d8144f8b8e48a27c38a52"));
		companies.forEach(printBlock);
		
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
}
