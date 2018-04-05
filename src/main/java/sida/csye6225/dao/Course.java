package sida.csye6225.dao;

import java.util.HashSet;
import java.util.Set;

import sida.csye6225.database.DynamoDBCoverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
@DynamoDBTable(tableName = "Courses")
public class Course extends Item{
	
	public String courseName;
	public String professorId;
	public Set<String> announcementSet;
	public Set<String> studentSet;
	public Set<String> lectureSet;
	
	@DynamoDBHashKey(attributeName = "CourseId")
	public String getItemId() { return this.id; }
	public void setItemId(String courseId) { this.id =  courseId; } 
	
	@DynamoDBAttribute(attributeName = "CourseName")
	public String getCourseName() { return this.courseName; }
	public void setCourseName(String courseName) { this.courseName = courseName; }
	
	@DynamoDBAttribute(attributeName = "ProfessorId")
	public String getProfessor() { return this.professorId; }
	public void setProfessor(String professorId) { this.professorId = professorId; }
	
	@DynamoDBAttribute(attributeName = "AnnouncementSet")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getAnnouncementSet() { return this.announcementSet; } 
	
	public void setAnnouncementSet(Set<String> announcementSet) { this.announcementSet = announcementSet; }
	
	@DynamoDBAttribute(attributeName = "StudentSet")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getStudentSet() { return this.studentSet; } 
	
	public void setStudentSet(Set<String> studentSet) { this.studentSet = studentSet; }
	
	@DynamoDBAttribute(attributeName = "LectureSet")
	@DynamoDBTypeConverted(converter = DynamoDBCoverter.class)
	public Set<String> getLectureSet() { return this.lectureSet; } 
	
	public void setLectureSet(Set<String> lectureSet) { this.lectureSet = lectureSet; }
}
