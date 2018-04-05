package sida.csye6225.resource;

import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Student;
import sida.csye6225.database.DynamoDB;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.UnsubscribeRequest;

@Path("courses/{courseId}/students")
public class RegisterResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getEnrolledStudents(@PathParam("courseId") String courseId){
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		return course.getStudentSet();
	} 
	
	@POST
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> enrollCourse(String text
			, @PathParam("courseId") String courseId
			, @PathParam("studentId") String studentId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		Student student = (Student)dynamoDB.get("Students", studentId);
		if(course == null || student == null)
			return null;
		course.getStudentSet().add(studentId);
		dynamoDB.save(course);
		// Subscribe SNS Topic
		subscribeTopic(courseId, student.getEmail());
		return course.getStudentSet();
	} 
	
	@DELETE
	@Path("{studentId}")
	public void dropCourse(@PathParam("courseId") String courseId
			, @PathParam("studentId") String studentId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null || !dynamoDB.isContain("Students", studentId))
			return;
		Student student = (Student)dynamoDB.get("Students", studentId);
		course.getStudentSet().remove(studentId);
		dynamoDB.save(course);
		unsubscribeTopic(courseId, student.getEmail());
	} 
	
	public void subscribeTopic(String courseId, String email) {
		AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
				.withRegion(Regions.US_WEST_2).build();
		String arn = "arn:aws:sns:us-west-2:616733119568:" + courseId;
		SubscribeRequest subscribeRequest = new SubscribeRequest(arn, "email", email);
		SNS_CLIENT.subscribe(subscribeRequest);
	} 
	
	public void unsubscribeTopic(String courseId, String email) {
		AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
				.withRegion(Regions.US_WEST_2).build();
		String arn = "arn:aws:sns:us-west-2:616733119568:" + courseId;
		ListSubscriptionsByTopicRequest lRequest = new ListSubscriptionsByTopicRequest(arn);
		ListSubscriptionsByTopicResult lResult = SNS_CLIENT.listSubscriptionsByTopic(lRequest);
		List<Subscription> subscriptions = lResult.getSubscriptions();
		for(Subscription s : subscriptions) {
			if(s.getEndpoint().equals(email)) {
				UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest(s.getSubscriptionArn());
				SNS_CLIENT.unsubscribe(unsubscribeRequest);
			}
		}
	} 
}
