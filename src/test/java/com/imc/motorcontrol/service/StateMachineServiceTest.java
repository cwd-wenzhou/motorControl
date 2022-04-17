package com.imc.motorcontrol.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateMachineServiceTest {

    @Autowired
    StateMachineService stateMachineService;

    @Test
    void getState() throws IOException {
        stateMachineService.printState(stateMachineService.getState());
    }

    @Test
    void changeToOp() throws IOException {
        stateMachineService.changeToOp();
    }

    @Test
    void changeToPoweredLock() throws IOException {
        stateMachineService.changeToPoweredLock();
    }
}