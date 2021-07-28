package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;


    //dependency injection
    //Autowired wires or magically instianciate our final StudentService above with the below method

    //if we weren't using autowired our code will look like
    //this.studentService = new StudentService();

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents(){

        return studentService.getStudents();


    }

    //post is used when you want to add new resource to your system

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){

        studentService.addNewStudent(student);

    }

    @DeleteMapping(path = "{studentId}")

    //we can grab the above studentId by using @PathVariable..
    public void deleteStudent(
            @PathVariable("studentId")Long id){
        studentService.deleteStudent(id);

    }

    @PutMapping(path = {"studentId"})
    public void updateStudent(
            @PathVariable("studentId")Long studentId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String email) {

        studentService.updateStudent(studentId, name , email);


    }





}

