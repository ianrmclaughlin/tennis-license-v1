package com.imglicense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO full refactor - no redundant local variables in any file

// TODO make everything private where possible

@SpringBootApplication
public class Application {
    public static void main ( String[] args ) {
        SpringApplication.run ( Application.class, args );
    }
}
