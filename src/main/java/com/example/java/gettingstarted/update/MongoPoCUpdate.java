package com.example.java.gettingstarted.update;


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
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
//import com.mongodb.MongoClientOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class MongoPoCUpdate {
	
	private  MongoClient mongoClient;
	
	private MongoDatabase database;
	
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
		mongoClient = MongoClients.create("mongodb+srv://tempemp:tempemp123@tempempdevelopmentcluster-vuzoa.gcp.mongodb.net/tempemp?retryWrites=true");
		// connect to localhost
		//mongoClient = MongoClients.create();
		
		System.out.println("===========DB Names ========");
		MongoIterable<String> listDatabaseNames = mongoClient.listDatabaseNames();
		for(String names:listDatabaseNames){
			System.out.println(names);
		}
		
		database = mongoClient.getDatabase("tempemp");
		// Check reference json in src/main/resource
		individualCollection = database.getCollection("individual");
		
		removeMultipleSkill();
		//http://www.javahotchocolate.com/notes/mongodb-crud.html  === best examples 
	}
	
	
	// TODO: Remove a sub category || Remove a document from the array
	public void removeSkill(){
		BasicDBObject match = new BasicDBObject();
		match.put("_id", "5b7c6b612612242a6d34ebb6");
		
		BasicDBObject skill = new BasicDBObject();
		skill.put("id", "5b7c6b612612242a6d34ebb7");
		
		BasicDBObject update = new BasicDBObject();
		update.put("$pull", new BasicDBObject("subCategories",skill));
		
		UpdateResult updateOne = individualCollection.updateOne(match, update);
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	// TODO: Add a sub category - done  || Add a new document in the array   ---- Done
	public void addSkill() {
		BasicDBObject match = new BasicDBObject();
		match.put("_id", "5b7c6b612612242a6d34ebb6");

		BasicDBObject addressSpec = new BasicDBObject();
		addressSpec.put("id", new ObjectId().toString());
		addressSpec.put("name", "Daily Wages Worker ssss ");

		BasicDBObject update = new BasicDBObject();
		update.put("$push", new BasicDBObject("subCategories", addressSpec));

		UpdateResult updateOne = individualCollection.updateOne(match, update);
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	
	// TODO: Add multiple sub category with empty array - done  || Add a new document in the array   ---- Done
	public void addMultipleSkill() {
		BasicDBObject addressSpec = new BasicDBObject();
		addressSpec.put("id", new ObjectId().toString());
		addressSpec.put("name", "one");
		addressSpec.put("images", new ArrayList<>());
		
		BasicDBObject addressSpec2 = new BasicDBObject();
		addressSpec2.put("id", new ObjectId().toString());
		addressSpec2.put("name", "two");
		addressSpec2.put("images", new ArrayList<>());

		List<BasicDBObject> list = new ArrayList<>();
		list.add(addressSpec); list.add(addressSpec2);
		
		UpdateResult updateOne = individualCollection.updateOne(Filters.eq("_id", "5b7c6b612612242a6d34ebb6"), 
				Updates.pushEach("subCategories", list));
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	//TODO: Remove multiple skills 
	public void removeMultipleSkill(){
		List<BasicDBObject> list = new ArrayList<>();
		
		BasicDBObject addressSpec = new BasicDBObject();
		addressSpec.put("id", "5b7db195261224bee58f7a96");
		
		BasicDBObject addressSpec2 = new BasicDBObject();
		addressSpec2.put("id", "5b7db195261224bee58f7a97");
		
		list.add(addressSpec); list.add(addressSpec2);
		
		UpdateResult updateOne = individualCollection.updateOne(Filters.eq("_id", "5b7c6b612612242a6d34ebb6"), 
				Updates.pullAll("subCategories", list));
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	// TODO: Remove a image or link || Remove a element from the array of document of an array
	public void removeImage(){
		BasicDBObject match = new BasicDBObject();
		match.put("_id", "5b7c6b612612242a6d34ebb6"); 
		match.put("subCategories.id", "5b7c6b612612242a6d34ebb8");
		
		BasicDBObject update = new BasicDBObject();
		update.put("$pull", new BasicDBObject().append("subCategories.$.images", "image_two"));
		UpdateResult updateOne = individualCollection.updateOne(match, update);
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	// TODO: Add a new image or link || Add a new element from the array of document of an array ---- Done 
	public void addImage() {
		BasicDBObject match = new BasicDBObject();
		match.put("_id", "5b7c6b612612242a6d34ebb6");
		match.put("subCategories.id", "5b7c6b612612242a6d34ebb8");
	
		BasicDBObject update = new BasicDBObject();
		update.put("$push", new BasicDBObject().append("subCategories.$.images", "image_two"));

		UpdateResult updateOne = individualCollection.updateOne(match, update);
		System.out.println("=======================");
		System.out.println(updateOne.getMatchedCount());
		System.out.println(updateOne.getModifiedCount());
	}
	
	
	@PreDestroy
	public void resourceCleanup(){
		mongoClient.close();
	}
	
	//TODO: Add multiple images at once 
	//TODO: Remove multiple images at once
}
