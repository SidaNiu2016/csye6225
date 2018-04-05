package sida.csye6225.dao;

import java.util.Set;

import sida.csye6225.database.DynamoDBCoverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "Programs")
public class Program extends Item{
	
	public String programName;
	public Set<String> courseSet;
	public Set<String> studentSet;
	
	@DynamoDBHashKey(attributeName = "ProgramId")
	public String getItemId() { return this.id; } 
	public void setItemId(String programId) { this.id = programId; } 
	
	@DynamoDBAttribute(attributeName = "ProgramName")
	public String getName() { return this.programName; }
	public void setName (String programName) { this.programName = programName; }
	
	@DynamoDBAttribute(attributeName = "CourseSet")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getCourseSet() { return this.courseSet; }
	
	public void setCourseSet(Set<String> courseSet) { this.courseSet = courseSet; } 
	
	@DynamoDBAttribute(attributeName = "StudentSet")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getStudentSet() { return this.studentSet; }
	
	public void setStudentSet(Set<String> studentSet) { this.studentSet = studentSet; } 
}