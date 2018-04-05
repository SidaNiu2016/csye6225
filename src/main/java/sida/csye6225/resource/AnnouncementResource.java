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

import sida.csye6225.dao.Announcement;
import sida.csye6225.dao.Course;
import sida.csye6225.database.DynamoDB;

@Path("courses/{courseId}/announcements")
public class AnnouncementResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getAnnouncementList(@PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Course course = (Course)dynamoDB.get("Courses", courseId);
		if(course == null)
			return null;
		return course.announcementSet;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement createAnnouncement(Announcement announcement
			, @PathParam("courseId") String courseId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		if(dynamoDB.isContain("Announcements", announcement.id))
			return null;
		
		announcement.setCourseId(courseId);
		dynamoDB.save(announcement);
		Course course = (Course) dynamoDB.get("Courses", courseId);
		course.getAnnouncementSet().add(announcement.id);
		dynamoDB.save(course);
		return announcement;
	}
	
	@GET
	@Path("{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAnnouncement(@PathParam("announcementId") String announcementId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		return (Announcement)dynamoDB.get("Announcements", announcementId);
	} 
	
	@PUT
	@Path("{announcementId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement updateAnnouncement(Announcement announcement
			, @PathParam("courseId") String courseId
			, @PathParam("announcementId") String announcementId) {
		if(!announcement.id.equals(announcementId))
			return null;
		DynamoDB dynamoDB = DynamoDB.getDB();
		announcement.setCourseId(courseId);
		dynamoDB.save(announcement);
		return announcement;
	}
	
	@DELETE
	@Path("{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteAnnouncement(@PathParam("courseId") String courseId
			, @PathParam("announcementId") String announcementId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		dynamoDB.delete("Announcements", announcementId);
		Course course = (Course)dynamoDB.get("Courses", courseId);
		course.getAnnouncementSet().remove(announcementId);
		dynamoDB.save(course);
	}
}
