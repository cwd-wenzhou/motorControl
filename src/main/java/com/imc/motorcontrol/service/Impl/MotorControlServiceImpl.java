package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.Thread.SampleThread;
import com.imc.motorcontrol.service.MotorControlService;
import com.imc.motorcontrol.service.MotorService;
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

    private SampleThread sampleThread;
    private SampleThread sampleThread_sec;


    public MotorControlServiceImpl(MotorService motorService, UDPServer udpService, StateMachineService stateMachineService) {
        this.motorService = motorService;
        this.udpServer = udpService;
        this.stateMachineService = stateMachineService;
    }

    @Override
    public void setVelocity(short velocity,int num) throws IOException {

        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getReferenceMechanicalSpeed()!=velocity){
            motor.setReferenceMechanicalSpeed(velocity);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setPosition(short position,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getReferenceMechanicalPosition()!=position){
            motor.setReferenceMechanicalPosition(position);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setCurrentId(short currentId,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getReferenceCurrentId()!=currentId){
            motor.setReferenceCurrentId(currentId);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setCurrentIq(short currentIq,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getReferenceCurrentIq()!=currentIq){
            motor.setReferenceCurrentIq(currentIq);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }

    }

    @Override
    public void setModeCode(short modeCode,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getModeCode()!=modeCode){
            motor.setModeCode(modeCode);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setPositionRingKp(short positionRingKp,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getPositionRingKp()!=positionRingKp){
            motor.setPositionRingKp(positionRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setPositionRingKd(short positionRingKd,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getPositionRingKd()!=positionRingKd){
            motor.setPositionRingKd(positionRingKd);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setSpeedRingKp(short speedRingKp,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getSpeedRingKp()!=speedRingKp){
            motor.setSpeedRingKp(speedRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setSpeedRingKi(short speedRingKi,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getSpeedRingKi()!=speedRingKi){
            motor.setSpeedRingKi(speedRingKi);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setCurrentRingKp(short currentRingKp,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getCurrentRingKp()!=currentRingKp){
            motor.setCurrentRingKp(currentRingKp);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public void setCurrentRingKi(short currentRingKi,int num) throws IOException {
        stateMachineService.changeToPoweredLock(num);
        Motor motor = udpServer.receive(num);
        while (motor.getCurrentRingKi()!=currentRingKi){
            motor.setCurrentRingKi(currentRingKi);
            motor.setControlCode((short) 0xff00);
            udpServer.send(motor,num);
        }
    }

    @Override
    public Timestamp startSample(int num) {
        sampleThread = new SampleThread(motorService,udpServer,stateMachineService);
        sampleThread.setRunning(true);
        sampleThread.setNum(num);

        LocalDateTime now = LocalDateTime.now();
        sampleThread.start();
        return Timestamp.valueOf(now);
    }

    @Override
    public Timestamp endSample(int num) throws InterruptedException {
        sampleThread.setRunning(false);
        sampleThread.join();
//      sampleService.save(new Sample(startTime,endTime,name));
        return Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public Timestamp startAllSample() {
        sampleThread = new SampleThread(motorService,udpServer,stateMachineService);
        sampleThread.setRunning(true);
        sampleThread.setNum(0);

        sampleThread_sec = new SampleThread(motorService,udpServer,stateMachineService);
        sampleThread_sec.setRunning(true);
        sampleThread_sec.setNum(1);

        LocalDateTime now = LocalDateTime.now();
        sampleThread.start();
        sampleThread_sec.start();
        return Timestamp.valueOf(now);
    }

    @Override
    public Timestamp endAllSample() throws InterruptedException {
        sampleThread.setRunning(false);
        sampleThread.join();

        sampleThread_sec.setRunning(false);
        sampleThread_sec.join();
        return Timestamp.valueOf(LocalDateTime.now());
    }

}
