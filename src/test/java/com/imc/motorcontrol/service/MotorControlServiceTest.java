package com.imc.motorcontrol.service;

import com.imc.motorcontrol.UDP.UDPServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MotorControlServiceTest {
    @Autowired
    MotorControlService motorControlService;
    @Autowired
    StateMachineService stateMachineService ;
    @Autowired
    MotorService motorService;
    @Autowired
    UDPServer udpServer;

    @Test
    void setVelocity() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setVelocity((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setPosition() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setPosition((short) 1100);
        System.out.println(udpServer.receive());
    }


    @Test
    void setCurrentId() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setCurrentId((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setCurrentIq() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setCurrentIq((short) 1100);
        System.out.println(udpServer.receive());
    }


    @Test
    void setPositionRingKp() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setPositionRingKp((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setPositionRingKd() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setPositionRingKd((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setSpeedRingKp() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setSpeedRingKp((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setSpeedRingKi() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setSpeedRingKi((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setCurrentRingKp() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setCurrentRingKp((short) 1100);
        System.out.println(udpServer.receive());
    }

    @Test
    void setCurrentRingKi() throws IOException {
        System.out.println(udpServer.receive());
        motorControlService.setCurrentRingKi((short) 1100);
        System.out.println(udpServer.receive());
    }


    @Test
    void multiTest() throws IOException, InterruptedException {
        motorControlService.setModeCode((short) 0);
        motorControlService.setCurrentIq((short)201);

        stateMachineService.changeToOp();


        Thread.sleep(5000);

        motorControlService.setModeCode((short) 1);
        motorControlService.setPosition((short) 0);
        stateMachineService.changeToOp();
        Thread.sleep(3000);

        motorControlService.setPosition((short) 16384);
        stateMachineService.changeToOp();

        Thread.sleep(3000);

        motorControlService.setPosition((short) 32768);
        stateMachineService.changeToOp();
        stateMachineService.changeToPoweredLock();
    }

    @Test
    void sampleTest() throws InterruptedException {
        Timestamp startTime = motorControlService.startSample("test");
        Thread.sleep(10000);
        Timestamp endTime = motorControlService.endSample();
        //System.out.println("end sample");
        //motorService.selectFromZone(startTime,endTime).forEach(System.out::println);
    }
}