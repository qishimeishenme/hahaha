package com.lanou.test;

import com.google.gson.Gson;
import com.lanou.entity.User;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class Test {
	
	public static void main(String[] args) {
		// 连接到mongodb
		MongoClient mongoClient = new MongoClient();

		DB psdoc = mongoClient.getDB("lanou");
		DBCollection user = psdoc.getCollection("user");
		User u1 = new User();
		u1.setAge(11);
		u1.setName("啥");
		Gson gson = new Gson();
		// 转换成json字符串，再转换成DBObject对象
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(u1));
		user.insert(dbObject);
		DBCursor cursor = user.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			// 反转
			User u = gson.fromJson(obj.toString(), User.class);
			System.out.println(obj);
		}

		
		
		
		
		
		
		
		
		// MongoClient mongoClient=new MongoClient("localhost",27017);
		// //连接到数据库 use lanou
		// MongoDatabase mongoDatabase = mongoClient.getDatabase("lanou");
		// //拿到表（collection）---collection--操作数据库
		// MongoCollection<Document>
		// collection=mongoDatabase.getCollection("user");
		// //迭代器
		// FindIterable<Document> iterable=collection.find();
		// //游标
		//// MongoCursor<Document> cursor=iterable.iterator();
		// while(cursor.hasNext()){
		// System.out.println(cursor.next());
		// }

	}
}
