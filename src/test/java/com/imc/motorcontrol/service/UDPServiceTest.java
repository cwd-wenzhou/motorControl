package com.imc.motorcontrol.service;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.UDPService;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.Timestamp;

@SpringBootTest
public class UDPServiceTest {
    @Autowired
    private UDPService udpService;

    @Autowired
    private MotorService motorService;

    @Test
    public void receiveTest() throws IOException {
        int i = 0 ;
        while (i++<20){
            Motor motor = udpService.receive();
            System.out.println(motor);
        }
    }

    @Test
    public void SampleTest() throws InterruptedException {
        Timestamp start = udpService.StartReceive();
        System.out.println(start);
        Thread.sleep(10000);//采样时间5秒
        Timestamp stop = udpService.EndReceive();
        System.out.println(stop);
        motorService.selectFromZone(start,stop).forEach(System.out::println);
    }
    @Test
    public void sendTest() throws IOException, InterruptedException {
        Motor motor = new Motor();
        while (true){
            Thread.sleep(1000);
            udpService.SendMessage(motor);
        }

    }
}
