package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.State;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.StateMachineService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StateMachineServiceImpl implements StateMachineService {
    final
    MotorService motorService;
    final
    UDPServer udpServer;

    public StateMachineServiceImpl(MotorService motorService, UDPServer udpService) {
        this.motorService = motorService;
        this.udpServer = udpService;
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
                System.out.println("unknownState");
        }
    }
    @Override
    public State getState(int num) throws IOException {
        Short statusCode = udpServer.receive(num).getStatusCode();
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
                return State.unknownState;
        }
    }


    @Override
    public void changeToOp(int num) throws IOException {
        Motor motor = udpServer.receive(num);
        State state = getState(num);
        while (state!=State.op){
            //printState(state);
            switch (state){
                case server_op:
                case error:
                    motor.setControlCode((short) 0x0100);
                    udpServer.send(motor,num);
                    break;
                case powered_lock:
                    motor.setControlCode((short) 0x0200);
                    udpServer.send(motor,num);
                    break;
            }
            state = getState(num);
        }
        //printState(state);
    }

    @Override
    public void changeToPoweredLock(int num) throws IOException {
        Motor motor = udpServer.receive(num);
        State state = getState(num);
        while (state!=State.powered_lock){
            switch (state){
                case server_op:
                case op:
                case error:
                    motor.setControlCode((short) 0x0100);
                    udpServer.send(motor,num);
                    break;
            }
            state = getState(num);
        }
        //printState(state);
    }


}
