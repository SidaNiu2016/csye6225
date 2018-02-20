package sida.csye6225.service;

import java.util.List;
import java.util.Map;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Program;
import sida.csye6225.dao.Student;
import sida.csye6225.storage.Storage;

public class ProgramService {
	
	private Storage storage = new Storage();
	private Map<String, Program> allPrograms = storage.getAllPrograms();
	
	public  Map<String, Program> getAllPrograms() {
        return allPrograms;
    }

    public Program getProgram(String programId) {
        return allPrograms.get(programId);
    }

    public Map<String, Program> addProgram(Program program) {
        allPrograms.put(program.getProgramId(), program);
        return allPrograms;
    }

    public Program updateProgram(Program program) {
        allPrograms.put(program.getProgramId(), program);
        return program;
    }

    public Program removeProgram(String programId) {
        return allPrograms.remove(programId);
    }

    /**
     * @param programId 
     * @return List of courses of one program
     */
    public List<Course> getProgramCourses(String programId) {
//        List<Course> programCourses = new ArrayList<>();
//        for (Map.Entry<String, Course> course : storage.getAllCourses().entrySet()) {
//            if (course.getValue().getProgramId() == programId) {
//                programCourses.add(course.getValue());
//            }
//        }
//        return programCourses;
    	return storage.getAllPrograms().get(programId).getProgramCourses();
    }

    /**
     * @param programId 
     * @return List of students of one program
     */
    public List<Student> getProgramStudents(String programId) {
//        List<Student> programStudents = new ArrayList<>();
//        for (Map.Entry<String, Student> student : storage.getAllStudents().entrySet()) {
//            if (student.getValue().getProgramId() == programId) {
//                programStudents.add(student.getValue());
//            }
//        }
//        return programStudents;
    	return storage.getAllPrograms().get(programId).getProgramStudents();
    }

}
