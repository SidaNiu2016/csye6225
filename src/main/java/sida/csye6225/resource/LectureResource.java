package sida.csye6225.resource;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Lecture;
import sida.csye6225.database.DynamoDB;

@Path("courses/{courseId}/lectures")
public class LectureResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getLectures(@PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null)
			return null;
		return course.lectureSet;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture createLecture(Lecture lecture
			, @PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null || dynamoDB.isContain("Lectures", lecture.id))
			return null;
		
		dynamoDB.save(lecture);
		course.getLectureSet().add(lecture.id);
		dynamoDB.save(course);
		return lecture;
	} 
	
	@GET
	@Path("{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture getLecture(@PathParam("lectureId") String lectureId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Lecture lecture = (Lecture)dynamoDB.get("Lectures", lectureId);
		return lecture;
	} 
	
	@PUT
	@Path("{lectureId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture updateLecture(Lecture lecture
			, @PathParam("lectureId") String lectureId
			, @PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null)
			return null;
		dynamoDB.save(lecture);
		course.getLectureSet().add(lectureId);
		dynamoDB.save(course);
		return lecture;
	} 
	
	@DELETE
	@Path("{lectureId}")
	public void deleteLecture(@PathParam("courseId") String courseId
			, @PathParam("lectureId") String lectureId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		course.getLectureSet().remove(lectureId);
		dynamoDB.save(course);
		dynamoDB.delete("Lectures", lectureId);
	} 
}
