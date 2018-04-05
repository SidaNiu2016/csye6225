package sida.csye6225.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Announcements")
public class Announcement extends Item{
	
	public String courseId;
	public String message;
	
	@DynamoDBHashKey(attributeName = "AnnouncementId")
	public String getItemId() { return this.id; }
	public void setItemId(String id) { this.id = id;}
	
	@DynamoDBAttribute(attributeName = "CourseId")
	public String getCourseId() { return this.courseId; }
	public void setCourseId(String courseId) { this.courseId = courseId; }
	
	@DynamoDBAttribute(attributeName = "Message")
	public String getMessage() { return this.message; }
	public void setMessage(String message) { this.message = message; }
}
