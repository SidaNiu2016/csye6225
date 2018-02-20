package sida.csye6225.resource;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Student;
import sida.csye6225.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
	
	private StudentService studentService = new StudentService();
	
	@GET
    public Map<String, Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GET
    @Path("/{studentId}")
    public Student getStudent(@PathParam("studentId") String studentId) {
        return studentService.getStudent(studentId);
    }

    @POST
    public Map<String, Student> addStudent(Student student) {
        return studentService.addStudent(student);
    }

    @PUT
    @Path("/{studentId}")
    public Student updateStudent(@PathParam("studentId") String studentId, Student student) {
        return studentService.updateStudent(student);
    }

    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId") String studentId) {
        studentService.removeStudent(studentId);
    }

    @GET
    @Path("/{studentId}/courses")
    public Map<String, Course> getStudentCourses(@PathParam("studentId") String studentId) {
        return studentService.getStudentCourses(studentId);
    }

}
