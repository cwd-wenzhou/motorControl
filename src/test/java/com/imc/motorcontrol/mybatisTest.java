package com.imc.motorcontrol;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.Thread.SQLSavingThread;
import com.imc.motorcontrol.service.MotorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class mybatisTest {
    @Autowired
    private MotorService motorService;


    @Test
    void tdengineTest() {
        try {
            Class.forName("com.taosdata.jdbc.TSDBDriver");
            String jdbcUrl = "jdbc:TAOS://localhost:6030/imc?user=root&password=taosdata";
            Connection conn = DriverManager.getConnection(jdbcUrl);
            System.out.println(conn);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void insertMotor() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        Motor motor = new Motor();
        motorService.save(motor);

    }

    @Test
    void getMotor() {
        List<Motor> list = motorService.list();
        for (Motor motor : list) {
            Timestamp timestamp = motor.getSampleTime();
            System.out.println(timestamp);
        }
    }

    /**
     * 38243ms
     */
    @Test
    void insertTimeTest1() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            motorService.save(new Motor());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 18691ms
     */
    @Test
    void insertTimeTest2() {
        long start = System.currentTimeMillis();
        int j = 0;
        List<Motor> motors = Arrays.asList(new Motor[10000]);
        for (int i = 0; i < 100000; i++) {
            motors.set(j, new Motor());
            if (++j == 10000) {
                j = 0;
                motorService.saveBatch(motors);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 100023 有问题，试一下多线程
     *
     */
    @Test
    void insertTimeTest3() throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        int j = 0;
        List<Motor> motors = Arrays.asList(new Motor[10000]);
        for (int i = 0; i < 100000; i++) {
            motors.set(j, new Motor());
            if (++j == 10000) {
                j = 0;
                SQLSavingThread sqlSavingThread = new SQLSavingThread(motorService, motors);
                es.submit(sqlSavingThread);
                motors = Arrays.asList(new Motor[10000]);
            }
        }
        es.awaitTermination(100, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 18809 ok
     *
     */
    @Test
    void insertTimeTest4() throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread t =null;
        int j = 0;
        List<Motor> motors = Arrays.asList(new Motor[1000]);
        for (int i = 0; i < 100000; i++) {
            motors.set(j, new Motor());
            if (++j == 1000) {
                if (t!=null) t.join();
                j = 0;
                t = new Thread(new SQLSavingThread(motorService,motors));
                t.start();
                motors = Arrays.asList(new Motor[1000]);
            }
        }
        assert t != null;
        t.join();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 5765 ms okkkkk!
     */
    @Test
    void insertTimeTest5() {

        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        int j = 0;
        List<Motor> motors = Arrays.asList(new Motor[1000]);
        for (int i = 0; i < 100000; i++) {
            motors.set(j, new Motor());
            if (++j == 1000) {
                j = 0;
                SQLSavingThread sqlSavingThread = new SQLSavingThread(motorService, motors);
                Thread t = new Thread(sqlSavingThread);
                t.start();
                threads.add(t);
                motors = Arrays.asList(new Motor[1000]);
            }
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    @Test
    void arrayTest(){
        List<Integer> list = Arrays.asList(new Integer[1000]);
        //list.set(23,12);
        System.out.println(list.get(23));
    }

    @Test
    /**
     * 两者无明显差距
     */
    void arrayTimeTest(){
        long start,end;

        start = System.nanoTime();
        List<Motor> motors1 = Arrays.asList(new Motor[100000]);
        for (int i = 0; i < 100000; i++) {
            motors1.set(i,new Motor());
        }
        end = System.nanoTime();
        System.out.println(end-start);

        start = System.nanoTime();
        List<Motor> motors2 = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            motors2.add(new Motor());
        }
        end = System.nanoTime();
        System.out.println(end-start);


    }
}
