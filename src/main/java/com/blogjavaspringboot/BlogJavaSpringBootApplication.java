package com.blogjavaspringboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BlogJavaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogJavaSpringBootApplication.class, args);
    }

    //@PostConstruct is used to mark a method that should be run after the bean is initialized.
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+06:00"));
        System.out.println("==== Timezone set to Dhaka ====");
    }
}
