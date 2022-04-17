package com.imc.motorcontrol.service.Impl;

import com.imc.motorcontrol.UDP.UDPServer;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.UDPService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class UDPServiceImpl implements UDPService {
    final
    UDPServer udpServer;

    public UDPServiceImpl(UDPServer udpServer) {
        this.udpServer = udpServer;
    }

    @Override
    public Timestamp StartReceive() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        udpServer.setRunning(true);
        udpServer.start();
        return timestamp;
    }

    @Override
    public Timestamp EndReceive() {
        udpServer.setRunning(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    @Override
    public void SendMessage(Motor motor) throws IOException {
        //udpServer.setRunning(true);
        //System.out.println(motor);
        udpServer.send(8080,"192.168.0.2",motor);

    }

    @Override
    public Motor receive() throws IOException {
        Motor motor = new Motor(udpServer.receive());
        return motor;
    }
}