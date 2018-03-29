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
		DynamoDB dynamoDB = DynamoDB.getInstance();
		Course course = (Course)dynamoDB.getItem("Courses", courseId);
		if(course == null)
			return null;
		return course.getProfessorId();
	}
	
	@POST
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String instructCourse(@PathParam("courseId") String courseId
			, @PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getInstance();
		if(!dynamoDB.contains("Courses", courseId) 
				|| !dynamoDB.contains("Professors", professorId))
			return null;
		
		Course course = (Course)dynamoDB.getItem("Courses", courseId);
		course.setProfessorId(professorId);
		dynamoDB.addOrUpdateItem(course);
		return professorId;
	} 
}
