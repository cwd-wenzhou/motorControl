package com.imc.motorcontrol;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
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
            Timestamp timestamp = motor.getTimestamp();
            System.out.println(timestamp);
        }
    }

    @Test
    void testInsertTime() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            motorService.save(new Motor());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
