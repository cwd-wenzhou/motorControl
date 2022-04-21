package com.imc.motorcontrol.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StateMachineServiceTest {

    @Autowired
    StateMachineService stateMachineService;

    @Test
    void getState() throws IOException {
        stateMachineService.printState(stateMachineService.getState(0));
        stateMachineService.printState(stateMachineService.getState(1));

    }

    @Test
    void changeToOp() throws IOException {
        stateMachineService.changeToOp(0);
    }

    @Test
    void changeToPoweredLock() throws IOException {
        stateMachineService.changeToPoweredLock(0);
    }
}