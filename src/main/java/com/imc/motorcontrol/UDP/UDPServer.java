package com.imc.motorcontrol.UDP;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;

@Component
public class UDPServer extends Thread{


    public static InetAddress hostAddress;
    private final MotorService motorService;
    private boolean running;
    private static DatagramSocket socket ;  // 创建服务器端DatagramSocket，指定端口
    private final byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
    private final DatagramPacket packet = new DatagramPacket(data, data.length);


    public UDPServer(MotorService motorService) throws SocketException, UnknownHostException {
        running = true;
        this.motorService = motorService;
        InetAddress locIP = InetAddress.getByName("192.168.0.3");
        socket = new DatagramSocket(8080,locIP);
        //socket = new DatagramSocket(8080);
    }

    public void setRunning(boolean running_){
        running = running_;
    }
    public byte[] receive() throws IOException {
        socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
        return data;
    }

    public void send(int port, @NotNull String ip, @NotNull Motor motor) throws IOException {
        // 1.定义客户端的地址、端口号、数据
//        System.out.println("ip = "+ip);
//        System.out.println("port = "+port);
        String[] iip = ip.split("\\.");
        byte[] ips = new byte[4];
        for (int i = 0; i < 4; i++) {
            ips[i] = (byte) Integer.parseInt(iip[i]);
//            System.out.println(ips[i]);
        }
        InetAddress address = InetAddress.getByAddress(ips);//1获取FPGA端的地址
        byte[] data2 = motor.getBytes();//将接收到的数据转换为字节数组
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length,address,port);// 2.创建数据报，包含响应的数据信息
        socket.send(packet2); // 3.发送udp报文
    }

    @Override
    public void run(){
        byte[] data;

        while (running){
            try {
                data = receive();
                Motor motor = new Motor(data);
                //System.out.println("from running: "+motor);
                motorService.save(motor);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //System.out.println("stop thread");
    }
}
