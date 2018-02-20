package sida.csye6225.resource;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Lecture;
import sida.csye6225.dao.Student;
import sida.csye6225.service.CourseService;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

	private CourseService courseService = new CourseService();
	
    @GET
    public Map<String, Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GET
    @Path("/{courseId}")
    public Course getCourse(@PathParam("courseId") String courseId) {
        return courseService.getCourse(courseId);
    }

    @GET
    @Path("/{courseId}/students")
    public List<Student> getProgramStudents(@PathParam("courseId") String courseId) {
        return courseService.getCourseStudents(courseId);
    }

    @POST
    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

    @PUT
    @Path("/{courseId}")
    public Course updateCourse(@PathParam("courseId") String courseId, Course course) {
        course.setCourseId(courseId);
        return courseService.updateCourse(course);
    }

    @DELETE
    @Path("/{courseId}")
    public void deleteCourse(@PathParam("courseId") String courseId) {
        courseService.removeCourse(courseId);
    }

    @GET
    @Path("/{courseId}/lectures")
    public List<Lecture> getCourseLectures(@PathParam("courseId") String courseId) {
        return courseService.getCourseLectures(courseId);
    }

}
