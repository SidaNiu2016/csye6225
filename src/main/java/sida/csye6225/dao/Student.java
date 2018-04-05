package sida.csye6225.dao;

import java.util.Set;

import sida.csye6225.database.DynamoDBCoverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "Students")
public class Student extends Item{
	public String studentName;
	public String studentEmail;
	public Set<String> courseSet;
	
	@DynamoDBHashKey(attributeName = "StudentId")
	public String getItemId() { return this.id; }
	public void setItemId(String id) { this.id = id;}
	
	@DynamoDBAttribute(attributeName = "StudentName")
	public String getName() { return this.studentName; } 
	public void setName(String studentName) { this.studentName = studentName; }
	
	@DynamoDBAttribute(attributeName = "StudentEmail")
	public String getEmail() { return this.studentEmail; }
	public void setEmail(String studentEmail) { this.studentEmail = studentEmail; }
	
	@DynamoDBAttribute(attributeName = "Courses")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getCourseSet() { return this.courseSet; }
	
	public void setCourseSet(Set<String> courseSet) { this.courseSet = courseSet; }
}