package sida.csye6225.dao;

import java.util.List;

public class Student {
	
    private String studentId;
    private String studentName;
    private String imagePath;
    private String programId;
    private List<String> courseIds;
    
    public Student() {
    }

    public Student(String studentId, String studentName, String imagePath, String programId) {
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setImagePath(imagePath);
        this.setProgramId(programId);
    }

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public List<String> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}

}
