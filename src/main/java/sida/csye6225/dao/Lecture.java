package sida.csye6225.dao;

import java.util.List;

public class Lecture {
	
	private String lectureId;
    private String courseId;
    private String lectureName;
    private List<String> notes;
    private List<String> materials;
    
    public Lecture() {
    }

    public Lecture(String lectureId, String courseId, String lectureName) {
        this.setLectureId(lectureId);
        this.setCourseId(courseId);
        this.setLectureName(lectureName);
    }

	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public List<String> getMaterials() {
		return materials;
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}
    
}
