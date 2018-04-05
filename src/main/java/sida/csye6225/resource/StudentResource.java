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

import sida.csye6225.dao.Program;
import sida.csye6225.dao.Student;
import sida.csye6225.database.DynamoDB;

@Path("programs/{programId}/students")
public class StudentResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getStudentsInProgram(@PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Program program = (Program)dynamoDB.get("Programs", programId);
		return program.getStudentSet();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student createStudent(@PathParam("programId") String programId, Student student) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Program program = (Program)dynamoDB.get("Programs", programId);
		if(dynamoDB.isContain("Students", student.id) || program == null)
			return null;
		
		program.getStudentSet().add(student.id);
		dynamoDB.save(program);
		dynamoDB.save(student);
		return student;
	}
	
	@GET
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") String studentId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		return (Student)dynamoDB.get("Students", studentId);
	}
	
	@PUT
	@Path("{studentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student, @PathParam("studentId") String studentId
			, @PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Program program = (Program)dynamoDB.get("Programs", programId);
		if(program == null)
			return null;
		
		program.getStudentSet().add(studentId);
		dynamoDB.save(program);
		dynamoDB.save(student);
		return student;
	}
	
	@DELETE
	@Path("{studentId}")
	public void deleteStudent(@PathParam("programId") String programId
			, @PathParam("studentId") String studentId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Program program = (Program)dynamoDB.get("Programs", programId);
		if(program == null)
			return;
		
		program.getStudentSet().remove(studentId);
		dynamoDB.save(program);
		dynamoDB.delete("Students", studentId);
	}
}
