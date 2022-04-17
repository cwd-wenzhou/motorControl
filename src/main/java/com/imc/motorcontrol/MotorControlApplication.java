package com.imc.motorcontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.imc.motorcontrol.mapper"})
public class MotorControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotorControlApplication.class, args);
    }

}
