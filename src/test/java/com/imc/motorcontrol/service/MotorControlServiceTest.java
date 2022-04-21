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
        System.out.println(udpServer.receive(0));
        motorControlService.setVelocity((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setPosition() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setPosition((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }


    @Test
    void setCurrentId() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setCurrentId((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setCurrentIq() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setCurrentIq((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }


    @Test
    void setPositionRingKp() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setPositionRingKp((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setPositionRingKd() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setPositionRingKd((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setSpeedRingKp() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setSpeedRingKp((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setSpeedRingKi() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setSpeedRingKi((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setCurrentRingKp() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setCurrentRingKp((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }

    @Test
    void setCurrentRingKi() throws IOException {
        System.out.println(udpServer.receive(0));
        motorControlService.setCurrentRingKi((short) 1100,0);
        System.out.println(udpServer.receive(0));
    }


    @Test
    void multiTest() throws IOException, InterruptedException {
        motorControlService.setModeCode((short) 0,0);
        motorControlService.setCurrentIq((short)201,0);

        stateMachineService.changeToOp(0);


        Thread.sleep(5000);

        motorControlService.setModeCode((short) 1,0);
        motorControlService.setPosition((short) 0,0);
        stateMachineService.changeToOp(0);
        Thread.sleep(3000);

        motorControlService.setPosition((short) 16384,0);
        stateMachineService.changeToOp(0);

        Thread.sleep(3000);

        motorControlService.setPosition((short) 32768,0);
        stateMachineService.changeToOp(0);
        stateMachineService.changeToPoweredLock(0);
    }

    @Test
    void sampleTest() throws InterruptedException {
        Timestamp startTime = motorControlService.startSample(0);
        Thread.sleep(10000);
        Timestamp endTime = motorControlService.endSample(0);
        System.out.println("end sample");
        motorService.selectFromZone(startTime,endTime).forEach(System.out::println);
    }
}