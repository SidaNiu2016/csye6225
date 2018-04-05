package sida.csye6225.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sida.csye6225.dao.Professor;
import sida.csye6225.database.DynamoDB;

@Path("professors")
public class ProfessorResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professor createProfessor(Professor professor) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(dynamoDB.isContain("Professors", professor.id))
			return null;
		
		dynamoDB.save(professor);
		return professor;
	}
	
	@GET
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		return (Professor)dynamoDB.get("Professors", professorId);
	}
	
	@PUT
	@Path("{professorId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(Professor professor
			, @PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.save(professor);
		return professor;
	}
	
	@DELETE
	@Path("{professorId}")
	public void deleteProfessor(@PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(!dynamoDB.isContain("Professors", professorId))
			return;
		dynamoDB.delete("Professors", professorId);
	}
	
}
