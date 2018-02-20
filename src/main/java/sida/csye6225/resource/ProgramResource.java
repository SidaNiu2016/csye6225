package sida.csye6225.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Program;
import sida.csye6225.dao.Student;
import sida.csye6225.service.ProgramService;

@Path("programs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProgramResource {
	
	private ProgramService programService = new ProgramService();
	
	@GET
    public Map<String, Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GET
    @Path("/{programId}")
    public Program getProgram(@PathParam("programId") String programId) {
        return programService.getProgram(programId);
    }

    @GET
    @Path("/{programId}/courses")
    public List<Course> getProgramCourses(@PathParam("programId") String programId) {
        return programService.getProgramCourses(programId);
    }

    @GET
    @Path("/{programId}/students")
    public List<Student> getProgramStudents(@PathParam("programId") String programId) {
        return programService.getProgramStudents(programId);
    }

    @POST
    public Map<String, Program> addProgram(Program program) {
        return programService.addProgram(program);
    }

    @PUT
    @Path("/{programId}")
    public Program updateProgram(@PathParam("programId") String programId, Program program) {
        return programService.updateProgram(program);
    }

    @DELETE
    @Path("/{programId}")
    public void deleteProgram(@PathParam("programId") String programId) {
        programService.removeProgram(programId);
    }

}
