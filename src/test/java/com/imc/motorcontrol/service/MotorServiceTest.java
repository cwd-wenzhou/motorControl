package com.imc.motorcontrol.service;

import com.imc.motorcontrol.entity.Motor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.Timestamp;

@SpringBootTest
class MotorServiceTest {

    @Autowired
    private MotorService motorService;

    @Test
    void selectFromZone() {
        Timestamp stop  = new Timestamp(2022,4,15,10,56,00,000);
        Timestamp start = new Timestamp(2022,4,15,10,16,00,000);
        start.setYear(2022-1900);
        start.setMonth(3);
        start.setDate(15);
        stop.setYear(2022-1900);
        stop.setMonth(3);
        stop.setDate(15);

        motorService.selectFromZone(start,stop).forEach(System.out::println);
    }
    @Test
    void motorTest() throws IOException {
        Motor motor = new Motor();
        motor.setStatusCode((short) 25);
        System.out.println(motor);
        byte[] bytes = motor.getBytes();
        for (int i = 0; i < 44; i++) {
            System.out.print(bytes[i]+" ");
        }

    }

}