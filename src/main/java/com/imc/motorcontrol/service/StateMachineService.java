package com.imc.motorcontrol.service;

import com.imc.motorcontrol.entity.State;

import java.io.IOException;

public interface StateMachineService {
    State getState(int num) throws IOException;

    void changeToOp(int num) throws IOException;

    void changeToPoweredLock(int num) throws IOException;

    void printState(State statusCode) throws IOException;
}
