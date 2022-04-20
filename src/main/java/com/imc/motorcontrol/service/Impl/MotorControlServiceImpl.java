package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.Sample;
import com.imc.motorcontrol.entity.Thread.SampleThread;
import com.imc.motorcontrol.service.MotorControlService;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.SampleService;
import com.imc.motorcontrol.service.StateMachineService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MotorControlServiceImpl implements MotorControlService {
    final MotorService motorService;
    final UDPServer udpServer;
    final StateMachineService stateMachineService;
    final SampleService sampleService;

    private SampleThread sampleThread;

    private Timestamp startTime;

    private Timestamp endTime;

    private String name;

    public MotorControlServiceImpl(MotorService motorService, UDPServer udpService, StateMachineService stateMachineService, SampleService sampleService) {
        this.motorService = motorService;
        this.udpServer = udpService;
        this.stateMachineService = stateMachineService;
        this.sampleService = sampleService;
    }

    @Override
    public void setVelocity(short velocity) throws IOException {

        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getReferenceMechanicalSpeed()!=velocity){
            motor.setReferenceMechanicalSpeed(velocity);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setPosition(short position) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getReferenceMechanicalPosition()!=position){
            motor.setReferenceMechanicalPosition(position);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setCurrentId(short currentId) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getReferenceCurrentId()!=currentId){
            motor.setReferenceCurrentId(currentId);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setCurrentIq(short currentIq) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getReferenceCurrentIq()!=currentIq){
            motor.setReferenceCurrentIq(currentIq);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }

    }

    @Override
    public void setModeCode(short modeCode) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getModeCode()!=modeCode){
            motor.setModeCode(modeCode);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setPositionRingKp(short positionRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getPositionRingKp()!=positionRingKp){
            motor.setPositionRingKp(positionRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setPositionRingKd(short positionRingKd) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getPositionRingKd()!=positionRingKd){
            motor.setPositionRingKd(positionRingKd);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setSpeedRingKp(short speedRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getSpeedRingKp()!=speedRingKp){
            motor.setSpeedRingKp(speedRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setSpeedRingKi(short speedRingKi) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getSpeedRingKi()!=speedRingKi){
            motor.setSpeedRingKi(speedRingKi);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setCurrentRingKp(short currentRingKp) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getCurrentRingKp()!=currentRingKp){
            motor.setCurrentRingKp(currentRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public void setCurrentRingKi(short currentRingKi) throws IOException {
        stateMachineService.changeToPoweredLock();
        Motor motor = udpServer.receive();
        while (motor.getCurrentRingKi()!=currentRingKi){
            motor.setCurrentRingKi(currentRingKi);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor);
        }
    }

    @Override
    public Timestamp startSample(String name) {
        this.name = name;
        sampleThread = new SampleThread(motorService,udpServer,stateMachineService);
        sampleThread.setRunning(true);
        LocalDateTime now = LocalDateTime.now();
        sampleThread.start();
        startTime = Timestamp.valueOf(now);
        return startTime;
    }

    @Override
    public Timestamp endSample() throws InterruptedException {
        sampleThread.setRunning(false);
        sampleThread.join();
        LocalDateTime now = LocalDateTime.now();
        endTime = Timestamp.valueOf(now);
        sampleService.save(new Sample(startTime,endTime,name));
        return Timestamp.valueOf(now);
    }


}
