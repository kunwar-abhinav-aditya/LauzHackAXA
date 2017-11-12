package com.lauzhack.axa;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Login {
	static MongoClient mongo;
	static MongoDatabase db;
	static MongoCollection<Document> collection;
	static MongoCollection<Document> collection_ds;
	
	public String checkLogin(String username, String password) {
		System.out.println(username);
		System.out.println(password);
		Document details = new Document();
		String toReturn = "";
		try {
			MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017));
	        db = mongo.getDatabase("homely");
	        collection = db.getCollection("user");
	        Document cond = new Document();
	        cond.append("userName", username);
	        cond.append("password", password);
	        details = collection.find(cond).first();
	        if (details == null) {
	        	System.out.println("null");
	        	toReturn = "Error";
	        }
	        else {
	        	System.out.println(username);
	        	System.out.println(password);
	        	Document update = new Document();
	        	Document updateField = new Document();
	        	updateField.append("lastAccessed", new Date());
	        	update.append("$set", updateField);
	        	collection.findOneAndUpdate(cond, update);
	        	System.out.println((String)details.get("type"));
	        	toReturn = (String)details.get("type");
	        }
		}
		catch(MongoSocketOpenException e) {
			e.printStackTrace();
		}
        return toReturn;
	}
}