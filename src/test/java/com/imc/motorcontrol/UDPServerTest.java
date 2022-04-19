package com.imc.motorcontrol;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UDPServerTest {
    @Autowired
    private UDPServer udpServer;

    @Test
    public void receiveTest() throws IOException {
        int i = 0 ;
        while (i++<10){
            Motor motor  = udpServer.receive();
            System.out.println(motor);
        }
    }

    @Test
    public void sendTest() throws IOException {
        Motor motor = new Motor();
        udpServer.send(motor);
    }


}
