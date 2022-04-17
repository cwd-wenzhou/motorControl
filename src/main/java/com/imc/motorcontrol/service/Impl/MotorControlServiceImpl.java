package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorControlService;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.StateMachineService;
import com.imc.motorcontrol.service.UDPService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MotorControlServiceImpl implements MotorControlService {
    final
    MotorService motorService;
    final
    UDPService udpService;
    final
    StateMachineService stateMachineService;

    public MotorControlServiceImpl(MotorService motorService, UDPService udpService, StateMachineService stateMachineService) {
        this.motorService = motorService;
        this.udpService = udpService;
        this.stateMachineService = stateMachineService;
    }

    @Override
    public void setVelocity(short velocity) throws IOException {

        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getReferenceMechanicalSpeed()!=velocity){
            motor.setReferenceMechanicalSpeed(velocity);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setPosition(short position) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getReferenceMechanicalPosition()!=position){
            motor.setReferenceMechanicalPosition(position);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setCurrentId(short currentId) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getReferenceCurrentId()!=currentId){
            motor.setReferenceCurrentId(currentId);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setCurrentIq(short currentIq) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getReferenceCurrentIq()!=currentIq){
            motor.setReferenceCurrentIq(currentIq);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }

    }

    @Override
    public void setModeCode(short modeCode) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getModeCode()!=modeCode){
            motor.setModeCode(modeCode);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setPositionRingKp(short positionRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getPositionRingKp()!=positionRingKp){
            motor.setPositionRingKp(positionRingKp);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setPositionRingKd(short positionRingKd) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getPositionRingKd()!=positionRingKd){
            motor.setPositionRingKd(positionRingKd);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setSpeedRingKp(short speedRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getSpeedRingKp()!=speedRingKp){
            motor.setSpeedRingKp(speedRingKp);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setSpeedRingKi(short speedRingKi) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getSpeedRingKi()!=speedRingKi){
            motor.setSpeedRingKi(speedRingKi);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setCurrentRingKp(short currentRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getCurrentRingKp()!=currentRingKp){
            motor.setCurrentRingKp(currentRingKp);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }

    @Override
    public void setCurrentRingKi(short currentRingKi) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpService.receive();
        while (motor.getCurrentRingKi()!=currentRingKi){
            motor.setCurrentRingKi(currentRingKi);
            motor.setControlCode((short) 0xff00);
            udpService.SendMessage(motor);
        }
    }
}
