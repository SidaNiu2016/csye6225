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
import sida.csye6225.dao.Course;
import sida.csye6225.dao.Program;
import sida.csye6225.database.DynamoDB;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

@Path("programs/{programId}/courses")
public class CourseResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getCourseList(@PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Item program = dynamoDB.get("Programs", programId);
		if(program == null)
			return null;
		return ((Program)program).getCourseSet();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course createCourse(Course course, @PathParam("programId") String programId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(dynamoDB.isContain("Courses", course.id))
			return null;
		
		dynamoDB.save(course);
		Item program = dynamoDB.get("Programs", programId);
		((Program)program).getCourseSet().add(course.id);
		dynamoDB.save(program);
		createSNSTopic(course.id);
		return course;
	}
	
	@GET
	@Path("{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		return (Course)dynamoDB.get("Courses", courseId);
	}
	
	@PUT
	@Path("{courseId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course updateCourse(Course course, @PathParam("courseId") String courseId) {
		if(!course.id.equals(courseId))
			return null;
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.save(course);
		return course;
	}
	
	@DELETE
	@Path("{courseId}")
	public void removeCourse(@PathParam("programId") String programId, 
			@PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(!dynamoDB.isContain("Courses", courseId))
			return;
		dynamoDB.delete("Courses", courseId);
		Item program = dynamoDB.get("Programs", programId);
		((Program)program).getCourseSet().remove(courseId);
		dynamoDB.save(program);
		removeSNSTopic(courseId);
	}
	
	public void createSNSTopic(String courseId) {
		AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
				.withRegion(Regions.US_WEST_2).build();
		
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(courseId);
		SNS_CLIENT.createTopic(createTopicRequest);
	} 
	
	public void removeSNSTopic(String courseId) {
		AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
				.withRegion(Regions.US_WEST_2).build();
		
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(
				"arn:aws:sns:us-west-2:616733119568:" + courseId);
		SNS_CLIENT.deleteTopic(deleteTopicRequest);
	}
}
