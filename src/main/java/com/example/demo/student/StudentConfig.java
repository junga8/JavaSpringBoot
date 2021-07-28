package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner( StudentRepository repository){
        return args -> {

          Student prabhat =   new Student(
                    1L,
                    "Prabhat",
                    LocalDate.of(1998, Month.SEPTEMBER, 19),
                    "pthapa@gmail.com"

            );

           Student alex =  new Student(

                    "Alex",
                    LocalDate.of(2004, Month.SEPTEMBER, 19),
                    "pthapa2@gmail.com"
            );

           //this is how we save to our databse
            //this repository is the instance of our studentrepo interface which is being
            repository.saveAll(

                    List.of(prabhat , alex)
            );



        };
    }

}
