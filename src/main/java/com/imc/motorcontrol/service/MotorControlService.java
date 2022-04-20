package com.imc.motorcontrol.service;

import java.io.IOException;
import java.sql.Timestamp;

public interface MotorControlService {
    void setVelocity(short velocity) throws IOException;
    void setPosition(short position) throws IOException;
    void setCurrentId(short currentId) throws IOException;
    void setCurrentIq(short currentIq) throws IOException;
    void setModeCode(short modeCode) throws IOException;

    void setPositionRingKp(short positionRingKp) throws IOException;
    void setPositionRingKd(short positionRingKd) throws IOException;
    void setSpeedRingKp(short speedRingKp) throws IOException;
    void setSpeedRingKi(short speedRingKi) throws IOException;
    void setCurrentRingKp(short currentRingKp) throws IOException;
    void setCurrentRingKi(short currentRingKi) throws IOException;

    Timestamp startSample(String name);
    Timestamp endSample() throws InterruptedException;

}
