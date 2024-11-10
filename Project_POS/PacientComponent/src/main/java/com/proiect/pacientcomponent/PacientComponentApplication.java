package com.proiect.pacientcomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.proiect.programarecomponent", "com.proiect.doctorcomponent", "com.proiect.pacientcomponent"})
public class PacientComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacientComponentApplication.class, args);
    }

}
