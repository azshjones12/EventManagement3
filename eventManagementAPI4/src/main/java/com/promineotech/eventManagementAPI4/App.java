package com.promineotech.eventManagementAPI4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.promineotech.eventManagementAPI4")
@SpringBootApplication
public class App {
        public static void main( String[] args )
        {
            SpringApplication.run(App.class, args);
        }
}
