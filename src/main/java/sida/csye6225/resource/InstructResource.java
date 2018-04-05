package sida.csye6225.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sida.csye6225.dao.Course;
import sida.csye6225.database.DynamoDB;

@Path("courses/{courseId}/professors")
public class InstructResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getInstructor(@PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null)
			return null;
		return course.getProfessor();
	}
	
	@POST
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String instructCourse(@PathParam("courseId") String courseId
			, @PathParam("professorId") String proId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(!dynamoDB.isContain("Courses", courseId) 
				|| !dynamoDB.isContain("Professors", proId))
			return null;
		
		Course course = (Course)dynamoDB.get("Courses", courseId);
		course.setProfessor(proId);
		dynamoDB.save(course);
		return proId;
	} 
}
