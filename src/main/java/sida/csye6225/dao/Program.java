package sida.csye6225.dao;

import java.util.List;

public class Program {
	
    private String programId;
    private String programName;
    private List<Course> programCourses;
    private List<Student> programStudents;

    public Program() {}

    public Program(String programId, String programName) {
        this.setProgramId(programId);
        this.setProgramName(programName);
    }

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<Course> getProgramCourses() {
		return programCourses;
	}

	public void setProgramCourses(List<Course> programCourses) {
		this.programCourses = programCourses;
	}

	public List<Student> getProgramStudents() {
		return programStudents;
	}

	public void setProgramStudents(List<Student> programStudents) {
		this.programStudents = programStudents;
	}

}
