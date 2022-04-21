package com.imc.motorcontrol.UDP;

import com.imc.motorcontrol.entity.Motor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UDPServer{

    private static List<DatagramSocket> socket;  // 创建服务器端DatagramSocket，指定端口
    private final byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
    private final DatagramPacket packet = new DatagramPacket(data, data.length);

    public UDPServer() throws SocketException, UnknownHostException {
        InetAddress locIP1 = InetAddress.getByName("192.168.0.3");//电机0对应上位机ip
        InetAddress locIP2 = InetAddress.getByName("192.168.0.5");//电机1对应上位机ip
        socket.add(new DatagramSocket(8080,locIP1));
        socket.add(new DatagramSocket(8080,locIP2));
        //socket = new DatagramSocket(8080);
    }

    /**
     *
     * @param num 电机编号 当前为两个电机 0,1
     * @return 收到的Motor结构体
     * @throws IOException
     */
    public Motor receive(int num) throws IOException {

        socket.get(num).receive(packet);// 此方法在接收到数据报之前会一直阻塞
        return new Motor(data);
    }

    /**
     *
     * @param motor 需要发送的参数
     * @param num 发送的对象电机编号 当前为两个电机 0,1
     * @throws IOException
     */
    public void send(@NotNull Motor motor,int num) throws IOException {
        // 1.定义客户端的地址、端口号、数据
//        String ip = "192.168.0.2";
//        int port = 8080;
//
//        String[] iip = ip.split("\\.");
//        byte[] ips = new byte[4];
//        for (int i = 0; i < 4; i++) {
//            ips[i] = (byte) Integer.parseInt(iip[i]);
//        }
//        InetAddress address = InetAddress.getByAddress(ips);//1获取FPGA端的地址
//
//        byte[] data2 = motor.getBytes();//将接收到的数据转换为字节数组
//        DatagramPacket packet2 = new DatagramPacket(data2, data2.length,address,port);// 2.创建数据报，包含响应的数据信息

        List<InetAddress> desIP = new ArrayList<>(2);
        desIP.add(InetAddress.getByName("192.168.0.2"));//电机0对应ip
        desIP.add(InetAddress.getByName("192.168.0.4"));//电机1对应ip
        int port = 8080;

        byte[] data2 = motor.getBytes();//将接收到的数据转换为字节数组
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length,desIP.get(num),port);// 创建数据报，包含响应的数据信息

        socket.get(num).send(packet2); // 发送udp报文
    }

}
