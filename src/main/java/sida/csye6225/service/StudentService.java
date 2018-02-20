package sida.csye6225.service;

import java.util.HashMap;
import java.util.Map;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Student;
import sida.csye6225.storage.Storage;

public class StudentService {
	
	private Storage storage = new Storage();
	private Map<String, Student> allStudents = storage.getAllStudents();

    public Map<String, Student> getAllStudents() {
        return allStudents;
    }

    public Student getStudent(String studentId) {
        return allStudents.get(studentId);
    }

    public Map<String, Student> addStudent(Student student) {
        allStudents.put(student.getStudentId(), student);
        return allStudents;
    }

    public Student updateStudent(Student student) {
        allStudents.put(student.getStudentId(), student);
        return student;
    }

    public Student removeStudent(String studentId) {
        return allStudents.remove(studentId);
    }

    /**
     * ../students/{studentId}/courses
     *
     * @param studentId
     * @return List of courses of one student
     */
    public Map<String, Course> getStudentCourses(String studentId) {
        Map<String, Course> studentCourses = new HashMap<>();
        for (Map.Entry<String, Course> course : storage.getAllCourses().entrySet()) {
            for (String courseId : allStudents.get(studentId).getCourseIds()) {
                if (course.getKey() == courseId) {
                    studentCourses.put(course.getKey(),course.getValue());
                }
            }
        }
        return studentCourses;
    }

}
