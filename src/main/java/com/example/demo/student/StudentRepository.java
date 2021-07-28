package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this interface is responsible for dqata access
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Select * from student WHERE email = ...

    //this is jbql not straight sql
    //Student below is the entity from our student class
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
