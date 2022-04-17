package com.imc.motorcontrol;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class UDPServerTest {
    @Autowired
    private UDPServer udpServer;

    @Test
    public void receiveTest() throws IOException {
        int i = 0 ;
        while (i++<10){
            byte[] data = udpServer.receive();
            Motor motor = new Motor(data);
            System.out.println(motor);
        }
    }

    @Test
    public void sendTest(){
        Motor motor = new Motor();

    }


}
