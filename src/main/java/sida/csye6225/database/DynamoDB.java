package sida.csye6225.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

import sida.csye6225.dao.Announcement;
import sida.csye6225.dao.Item;
import sida.csye6225.dao.Course;
import sida.csye6225.dao.Lecture;
import sida.csye6225.dao.Note;
import sida.csye6225.dao.Professor;
import sida.csye6225.dao.Program;
import sida.csye6225.dao.Student;

public class DynamoDB {
	static AmazonDynamoDB dynamoDBClient;
	static DynamoDBMapper mapper;
	static DynamoDB instance;
    private static Map<String, Class> classMap = new HashMap<>();
	
	public DynamoDB() {
			ProfileCredentialsProvider credentialsProvider = 
					new ProfileCredentialsProvider("default");
		
		    credentialsProvider.getCredentials();
		    dynamoDBClient = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion("us-west-2")
					.build();
			
			mapper = new DynamoDBMapper(dynamoDBClient);
			classMap.put("Announcements", Announcement.class);
			classMap.put("Courses", Course.class);
			classMap.put("Lectures", Lecture.class);
			classMap.put("Notes", Note.class);
			classMap.put("Professors", Professor.class);
			classMap.put("Programs", Program.class);
			classMap.put("Students", Student.class);
	}
	
	public void createTable(String tableName, String key) throws Exception {
		List<KeySchemaElement> keySchema = new ArrayList<>();
		List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
		
		keySchema.add(new KeySchemaElement().withAttributeName(key)
				.withKeyType(KeyType.HASH));
		
		attributeDefinitions.add(new AttributeDefinition().withAttributeName(key)
				.withAttributeType(ScalarAttributeType.S));
		
		CreateTableRequest createTableRequest = new CreateTableRequest()
				.withTableName(tableName)
				.withKeySchema(keySchema)
				.withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(
						new ProvisionedThroughput()
						.withReadCapacityUnits(3L)
						.withWriteCapacityUnits(3L));
		
		TableUtils.createTableIfNotExists(dynamoDBClient, createTableRequest);
		TableUtils.waitUntilActive(dynamoDBClient, tableName);
	}
	
	public void save(Item item) {
		mapper.save(item);
	}
	
	public Item get(String tableName, String key) {
		Item item = mapper.load(classMap.get(tableName), key);
		return item;
	}
	
	public Set<String> getAll(String tableName) {
		List<Item> items = mapper.scan(classMap.get(tableName)
				, new DynamoDBScanExpression());
		Set<String> result = new HashSet<>();
		for(Item item : items)
			result.add(item.id);
		return result;
	}
	
	public void delete(String tableName, String key) {
		Item item = mapper.load(classMap.get(tableName), key);
		mapper.delete(item, new DynamoDBDeleteExpression());
	}
	
	public boolean isContain(String tableName, String key) {
		Item item = mapper.load(classMap.get(tableName), key);
		return item != null;
	}
	
	public static DynamoDB getDB() {
		if(instance == null)
			instance = new DynamoDB();
		return instance;
	} 
	
	public static void main(String[] args) throws Exception{
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.createTable("Announcements", "AnnouncementId");
		dynamoDB.createTable("Courses", "CourseId");
		dynamoDB.createTable("Lectures", "LectureId");
		dynamoDB.createTable("Notes", "NoteId");
		dynamoDB.createTable("Professors", "ProfessorId");
		dynamoDB.createTable("Programs", "ProgramId");
		dynamoDB.createTable("Students", "StudentId");
	}
}
