package sida.csye6225.dao;

import java.util.Set;

import sida.csye6225.database.DynamoDBCoverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "Professors")
public class Professor extends Item{
	
	public String name;
	public Set<String> courseSet;
	
	@DynamoDBHashKey(attributeName = "ProfessorId")
	public String getItemId() { return this.id; }
	public void setItemId(String id) { this.id = id; }
	
	@DynamoDBAttribute(attributeName = "ProfessorName")
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	@DynamoDBAttribute(attributeName = "Courses")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getCourseSet() { return this.courseSet; } 
	
	public void setCourseSet(Set<String> courseSet) { this.courseSet = courseSet; }
	
}