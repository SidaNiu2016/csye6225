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

import sida.csye6225.dao.Lecture;
import sida.csye6225.dao.Note;
import sida.csye6225.database.DynamoDB;


@Path("lectures/{lectureId}/notes")
public class NoteResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getNotes(@PathParam("lectureId") String lectureId){
		DynamoDB dynamoDB = DynamoDB.getDB();
		Lecture lecture = (Lecture)dynamoDB.get("Lectures", lectureId);
		if(lecture == null)
			return null;
		return lecture.noteSet;
	} 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Note createNote(Note note, @PathParam("lectureId") String lectureId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Lecture lecture = (Lecture)dynamoDB.get("Lectures", lectureId);
		if(dynamoDB.isContain("Notes", note.id) || lecture == null)
			return null;
		
		dynamoDB.save(note);
		lecture.getNoteSet().add(note.id);
		dynamoDB.save(lecture);
		return note;
	}
	
	@GET
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Note getNote(@PathParam("noteId") String noteId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		return (Note)dynamoDB.get("Notes", noteId);
	}
	
	@PUT
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Note updateNote(Note note
			, @PathParam("lectureId") String lectureId
			, @PathParam("noteId") String noteId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Lecture lecture = (Lecture)dynamoDB.get("Lectures", lectureId);
		if(lecture == null)
			return null;
		lecture.getNoteSet().add(note.id);
		dynamoDB.save(lecture);
		dynamoDB.save(note);
		return note;
	}
	
	@DELETE
	@Path("{noteId}")
	public void deleteNote(@PathParam("lectureId") String lectureId
			, @PathParam("noteId") String noteId) {
		DynamoDB dynamoDB = DynamoDB.getDB();
		Lecture lecture = (Lecture)dynamoDB.get("Lectures", lectureId);
		if(lecture == null)
			return;
		lecture.getNoteSet().remove(noteId);
		dynamoDB.save(lecture);
		dynamoDB.delete("Notes", noteId);
	}
}
