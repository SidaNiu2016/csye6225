package sida.csye6225.dao;

import java.util.List;

public class Course {
	
    private String courseId;
    private String programId;
    private String courseName;
    private List<String> board;
    private List<String> roster;
    private List<Lecture> lectures;
    private List<Student> studentsEnrolled;

    public Course() {
    }

    public Course(String courseId, String programId, String courseName) {
        this.setCourseId(courseId);
        this.setProgramId(programId);
        this.setCourseName(courseName);
    }

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}

	public List<String> getRoster() {
		return roster;
	}

	public void setRoster(List<String> roster) {
		this.roster = roster;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public List<Student> getStudents() {
		return studentsEnrolled;
	}

	public void setStudents(List<Student> studentsEnrolled) {
		this.studentsEnrolled = studentsEnrolled;
	}

}
