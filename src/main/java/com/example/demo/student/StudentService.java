package com.example.demo.student;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


//this class is meant to be a a service class adding the service or
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    //think of auto wired as binding in react
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    //to have this method as one of our end points we have to annotyate with getMapping or postmapping
    @GetMapping
    public List<Student> getStudents(){

     return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
       boolean exists =  studentRepository.existsById(studentId);

       if(!exists){
           throw new IllegalStateException(
                   "Student with id" + studentId + "does not exists");

       }

       studentRepository.deleteById(studentId);

    }

    //it means we don;t have to implement any jpql queries
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id"
        + studentId + "does not exist")) ;

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);

        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");

            }
            student.setEmail(email);

        }

    }
}
