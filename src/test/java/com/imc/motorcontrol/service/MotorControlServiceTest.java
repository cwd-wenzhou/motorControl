package com.imc.motorcontrol.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MotorControlServiceTest {
    @Autowired
    MotorControlService motorControlService;
    @Autowired
    StateMachineService stateMachineService ;

    @Autowired
    UDPService udpService;

    @Test
    void setVelocity() throws IOException {
        System.out.println(udpService.receive());
        motorControlService.setVelocity((short) 1100);
        System.out.println(udpService.receive());
    }

    @Test
    void setPosition() throws IOException {
        System.out.println(udpService.receive());
        motorControlService.setPosition((short) 1100);
        System.out.println(udpService.receive());
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
    void setCurrentId() {
    }

    @Test
    void setCurrentIq() {
    }


    @Test
    void setPositionRingKp() {
    }

    @Test
    void setPositionRingKd() {
    }

    @Test
    void setSpeedRingKp() {
    }

    @Test
    void setSpeedRingKi() {
    }

    @Test
    void setCurrentRingKp() {
    }

    @Test
    void setCurrentRingKi() {
    }
}