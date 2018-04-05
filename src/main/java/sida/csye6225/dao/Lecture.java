package sida.csye6225.dao;

import java.util.Set;

import sida.csye6225.database.DynamoDBCoverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "Lectures")
public class Lecture extends Item{
	public Set<String> noteSet;
	
	@DynamoDBHashKey(attributeName = "LectureId")
	public String getItemId() { return this.id; }
	public void setItemId(String id) { this.id = id; }
	
	@DynamoDBAttribute(attributeName = "Notes")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getNoteSet() { return this.noteSet; } 
	
	public void setNoteSet(Set<String> noteSet) { this.noteSet = noteSet; }
}
