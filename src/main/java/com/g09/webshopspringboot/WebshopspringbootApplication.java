package com.g09.webshopspringboot;

import com.g09.webshopspringboot.repository.PopulateDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopspringbootApplication {

    // Behövs ej???
  //  @Autowired
  //  PopulateDatabase populateDatabase;

    public static void main(String[] args) {
        SpringApplication.run(WebshopspringbootApplication.class, args);
    }



}
