package sida.csye6225.storage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import sida.csye6225.dao.Course;
import sida.csye6225.dao.Lecture;
import sida.csye6225.dao.Program;
import sida.csye6225.dao.Student;

@Singleton
public class Storage {
	
	private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Program> programs = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Lecture> lectures = new HashMap<>();
    
    public Storage() {
    	initiallizeStorage();
	}
    
    public void initiallizeStorage(){
//    	Set<Student> courseStudents = new HashSet();
//    	Set<Student> porgramStudents = new HashSet();
//    	Set<Course> studentCourses = new HashSet();
//    	Set<Course> programCourses = new HashSet();
//    	Set<Lecture> courseLectures = new HashSet();
    	
    	Student sida = new Student("001stu","sida", "imagePath", "001pro");
    	Student ludi = new Student("002stu","ludi", "imagePath", "002pro");    	
    	Program is = new Program("001pro", "information systems");
    	Program cs = new Program("002pro", "computer science");
    	Course cc = new Course("001cou", "001pro", "cloud computing");
    	Course wd = new Course("002cou", "002pro", "web development");
    	Lecture ccWeek1 = new Lecture("001lec", "001cou", "week1");
    	Lecture wdWeek1 = new Lecture("002lec", "002cou", "week1");
    	
    	sida.setCourseIds(Arrays.asList("001cou"));
    	ludi.setCourseIds(Arrays.asList("002cou"));
    	cc.setLectures(Arrays.asList(ccWeek1));
    	wd.setLectures(Arrays.asList(wdWeek1));
    	is.setProgramCourses(Arrays.asList(cc));
    	is.setProgramStudents(Arrays.asList(sida));
    	cs.setProgramCourses(Arrays.asList(wd));
    	cs.setProgramStudents(Arrays.asList(ludi));
    	
    	students.put(sida.getStudentId(), sida);
    	students.put(ludi.getStudentId(), ludi);
    	programs.put(is.getProgramId(), is);
    	programs.put(cs.getProgramId(), cs);
		courses.put(cc.getCourseId(), cc);
		courses.put(wd.getCourseId(), wd);
		lectures.put(ccWeek1.getCourseId(), ccWeek1);
		lectures.put(wdWeek1.getCourseId(), wdWeek1);
    }
    
    public Map<String, Student> getAllStudents(){
    	return students;
    }
    
    public Map<String, Program> getAllPrograms(){
    	return programs;
    }
    
    public Map<String, Course> getAllCourses(){
    	return courses;
    }
    
    public Map<String, Lecture> getAllLectures(){
    	return lectures;
    }  

}
