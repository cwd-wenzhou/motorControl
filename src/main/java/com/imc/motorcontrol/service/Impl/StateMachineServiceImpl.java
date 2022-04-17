package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.State;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.StateMachineService;
import com.imc.motorcontrol.service.UDPService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StateMachineServiceImpl implements StateMachineService {
    final
    MotorService motorService;
    final
    UDPService udpService;

    public StateMachineServiceImpl(MotorService motorService, UDPService udpService) {
        this.motorService = motorService;
        this.udpService = udpService;
    }

    @Override
    public void printState(@NotNull State statusCode)  {
        System.out.print("Now motor state is ");
        switch (statusCode){
            case power:
                System.out.println("power");
                break;
            case init:
                System.out.println("init");
                break;
            case server_op:
                System.out.println("server_op");
                break;
            case powered_lock:
                System.out.println("powered_lock");
                break;
            case op:
                System.out.println("op");
                break;
            case error:
                System.out.println("error");
                break;
            default:
                System.out.println("unknowState");
        }
    }
    @Override
    public State getState() throws IOException {
        Short statusCode = udpService.receive().getStatusCode();
        switch (statusCode){
            case 0:
                return State.power;
            case 1:
                return State.init;
            case 2:
                return State.server_op;
            case 3:
                return State.powered_lock;
            case 4:
                return State.op;
            case 255:
                return State.error;
            default:
                return State.unknowState;
        }
    }


    @Override
    public void changeToOp() throws IOException {
        Motor motor = udpService.receive();
        State state = getState();
        while (state!=State.op){
            //printState(state);
            switch (state){
                case server_op:
                case error:
                    motor.setControlCode((short) 0x0100);
                    udpService.SendMessage(motor);
                    break;
                case powered_lock:
                    motor.setControlCode((short) 0x0200);
                    udpService.SendMessage(motor);
                    break;
            }
            state = getState();
        }
        //printState(state);
    }

    @Override
    public void changeToPoweredLock() throws IOException {
        Motor motor = udpService.receive();
        State state = getState();
        while (state!=State.powered_lock){
            switch (state){
                case server_op:
                case op:
                case error:
                    motor.setControlCode((short) 0x0100);
                    udpService.SendMessage(motor);
                    break;
            }
            state = getState();
        }
        //printState(state);
    }


}
