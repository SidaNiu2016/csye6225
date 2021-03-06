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

import sida.csye6225.dao.Item;
import sida.csye6225.dao.Program;
import sida.csye6225.database.DynamoDB;

@Path ("programs")
public class ProgramResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getProgramList() {
		return DynamoDB.getDB().getAll("Programs");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Program createProgram(Program program) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(dynamoDB.isContain("Programs", program.id))
			return null;
		
		dynamoDB.save(program);
		return program;
	}
	
	@GET
	@Path("{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Item object = dynamoDB.get("Programs", programId);
		return (Program)object;
	}
	
	@PUT
	@Path("{programId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") String programId
			, Program program) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.save(program);	
		return program;
	}
	
	@DELETE
	@Path("{programId}")
	public void deleteProgram(@PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.delete("Programs", programId);
	}
}
