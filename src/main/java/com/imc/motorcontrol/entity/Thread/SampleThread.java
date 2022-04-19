package com.imc.motorcontrol.entity.Thread;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.StateMachineService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SampleThread extends Thread{
    public final MotorService motorService;

    public final UDPServer udpServer;

    public final StateMachineService stateMachineService;

    private boolean running;

    public SampleThread(MotorService motorService, UDPServer udpServer, StateMachineService stateMachineService) {
        this.motorService = motorService;
        this.udpServer = udpServer;
        this.stateMachineService = stateMachineService;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    @Override
    public void run() {
        int num=0;
        boolean firstrun = true;
        List<Motor> motors = new ArrayList<>(1000);
        List<Thread> threads = new ArrayList<>();
        while (running){
            //首次运行，即采样开始，将电机状态调整为op
            if (firstrun){
                try {
                    stateMachineService.changeToOp();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                firstrun = false;
            }
            //采样数据
            try {
                motors.add(udpServer.receive());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //1000个一组，采用多线程的方法存入数据库。
            if (++num == 1000) {
                num = 0;
                SQLSavingThread sqlSavingThread = new SQLSavingThread(motorService, motors);
                Thread t = new Thread(sqlSavingThread);
                t.start();
                threads.add(t);
                motors = new ArrayList<>(1000);
            }
        }

        //处理最后一部分的数据
        SQLSavingThread sqlSavingThread = new SQLSavingThread(motorService, motors);
        Thread t = new Thread(sqlSavingThread);
        t.start();
        threads.add(t);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
