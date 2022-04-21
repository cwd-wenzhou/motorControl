package com.imc.motorcontrol.service;

import java.io.IOException;
import java.sql.Timestamp;

public interface MotorControlService {
    void setVelocity(short velocity,int num) throws IOException;
    void setPosition(short position,int num) throws IOException;
    void setCurrentId(short currentId,int num) throws IOException;
    void setCurrentIq(short currentIq,int num) throws IOException;
    void setModeCode(short modeCode,int num) throws IOException;

    void setPositionRingKp(short positionRingKp,int num) throws IOException;
    void setPositionRingKd(short positionRingKd,int num) throws IOException;
    void setSpeedRingKp(short speedRingKp,int num) throws IOException;
    void setSpeedRingKi(short speedRingKi,int num) throws IOException;
    void setCurrentRingKp(short currentRingKp,int num) throws IOException;
    void setCurrentRingKi(short currentRingKi,int num) throws IOException;

    Timestamp startSample(int num);
    Timestamp endSample(int num) throws InterruptedException;

    /**
     * 开启所有电机采样
     * @return
     */
    Timestamp startAllSample();

    /**
     * 关闭所有电机采样
     * @return
     * @throws InterruptedException
     */
    Timestamp endAllSample() throws InterruptedException;
}
