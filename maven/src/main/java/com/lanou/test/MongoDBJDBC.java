package com.lanou.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBJDBC {
	public static void main(String args[]) {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("lanous");
			System.out.println("Connect to database successfully");
			// mongoDatabase.createCollection("test");
			// System.out.println("集合创建成功");
			MongoCollection<Document> collection = mongoDatabase.getCollection("test");
			System.out.println("集合 test 选择成功");
			// 插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document>
			 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
			 * 插入单个文档可以用 mongoCollection.insertOne(Document)
			 */
			Document document = new Document("姓名", "张老二").append("年龄", 66).append("性别", "男").append("存款", 8);
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功");
			// 检索所有文档
			/**
			 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
			 * 通过游标遍历检索出的文档集合
			 */
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
			// 更新文档 将文档中likes=100的文档修改为likes=200
			System.out.println("即将进行修改");
			collection.updateMany(Filters.eq("姓名", "张老二"), new Document("$set", new Document("姓名", "李老大")));
			// 检索查看结果
			FindIterable<Document> findIterable1 = collection.find();
			MongoCursor<Document> mongoCursor1 = findIterable1.iterator();
			while (mongoCursor1.hasNext()) {
				System.out.println(mongoCursor1.next());
			}
			// System.out.println("即将进行删除");
			// //删除符合条件的第一个文档
			// collection.deleteOne(Filters.eq("姓名", "李老大"));
			// //删除所有符合条件的文档
			// collection.deleteMany (Filters.eq("姓名", "李老大"));
			// //检索查看结果
			// FindIterable<Document> findIterable11 = collection.find();
			// MongoCursor<Document> mongoCursor11 = findIterable11.iterator();
			// while(mongoCursor11.hasNext()){
			// System.out.println(mongoCursor11.next());
			// }
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
