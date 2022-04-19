package com.imc.motorcontrol.entity.Thread;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import lombok.Data;
import java.util.List;

@Data
public class SQLSavingThread implements Runnable{

    private List<Motor> motors;

    public final MotorService motorService;

    public SQLSavingThread(MotorService motorService,List<Motor> motors) {
        this.motorService = motorService;
        this.motors = motors;
    }

    @Override
    public void run(){
        motorService.saveBatch(motors);
    }
}
