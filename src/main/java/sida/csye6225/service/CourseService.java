package sida.csye6225.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Lecture;
import sida.csye6225.dao.Student;
import sida.csye6225.storage.Storage;

public class CourseService {
	
	private Storage storage = new Storage();
	private Map<String, Course> allCourses = storage.getAllCourses();
	
	public  Map<String, Course> getAllCourses() {
        return allCourses;
    }

    /**
     * @param courseId
     * @return A specific course
     */
    public Course getCourse(String courseId) {
        return allCourses.get(courseId);
    }

    public Course addCourse(Course course) {
        allCourses.put(course.getCourseId(), course);
        return course;
    }

    public Course updateCourse(Course course) {
        allCourses.put(course.getCourseId(), course);
        return course;
    }

    public Map<String, Course> removeCourse(String courseId) {
    	allCourses.remove(courseId);
        return allCourses;
    }

    /**
     * @param courseId 
     * @return List of students of one course
     */
    public List<Student> getCourseStudents(String courseId) {
        List<Student> courseStudents = new ArrayList<>();
        Map<String, Student> allStudents = storage.getAllStudents();
        
        for (Entry<String, Student> student : allStudents.entrySet()){
            if(student.getValue().getCourseIds().contains(courseId)) {
            	courseStudents.add(student.getValue());
            }
        }
        
        return courseStudents;
    }
    
    /**
     * @param courseId 
     * @return List of lectures of one course
     */
    public List<Lecture> getCourseLectures(String courseId) {
        return storage.getAllCourses().get(courseId).getLectures();
    }

}
