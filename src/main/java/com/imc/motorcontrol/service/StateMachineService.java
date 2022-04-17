package com.imc.motorcontrol.service;

import com.imc.motorcontrol.entity.State;

import java.io.IOException;

public interface StateMachineService {
    State getState() throws IOException;

    void changeToOp() throws IOException;

    void changeToPoweredLock() throws IOException;

    void printState(State statusCode) throws IOException;
}
