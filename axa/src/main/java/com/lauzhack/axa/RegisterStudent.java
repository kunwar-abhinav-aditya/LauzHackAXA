package com.lauzhack.axa;

import java.util.Date;

import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RegisterStudent {

	static MongoClient mongo;
	static MongoDatabase db;
	static MongoCollection<Document> collection;
	static MongoCollection<Document> collection_ds;
	
	public int register(Student student) {
		Document details = new Document();
		int result = 0;
		String toReturn = "";
		try {
			MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017));
	        db = mongo.getDatabase("homely");
	        collection = db.getCollection("students");
	        
	        Document studentDetail = new Document();
	        studentDetail.put("firstName", student.getFirstName());
	        studentDetail.put("familyName", student.getFamilyName());
	        studentDetail.put("username", student.getUserName());
	        studentDetail.put("password", student.getPassword());
	        studentDetail.put("address", student.getAddress());
	        studentDetail.put("postalCode", student.getPostalCode());
	        studentDetail.put("country", student.getCountry());
	        studentDetail.put("nationality", student.getNationality());
	        studentDetail.put("gender", student.getGender());
	        studentDetail.put("dob", student.getDob());
	        studentDetail.put("telephone", student.getTelephone());
	        studentDetail.put("email", student.getEmail());
	        studentDetail.put("religion", student.getReligion());
	        studentDetail.put("interests", student.getInterests());
	        studentDetail.put("languages", student.getLanguages());
	        studentDetail.put("reason", student.getReason());
	        studentDetail.put("registerDate", new Date());
	  
	        Document loginDetail = new Document();
	        loginDetail.put("userName", student.getUserName());
	        loginDetail.put("password", student.getPassword());
	        loginDetail.put("type", "Student");
	        loginDetail.put("lastAccessed", new Date());
	        try {
	        	collection.insertOne(studentDetail);
		        collection = db.getCollection("user");
		        collection.insertOne(loginDetail);
	        	result = 1;
	        }
	        catch (Exception e) {
	        	result = 0;
	        }
		}
		catch(MongoSocketOpenException e) {
			e.printStackTrace();
		}
        return result;
	}
}
