package com.imc.motorcontrol.service;

import com.imc.motorcontrol.entity.Motor;

import java.io.IOException;
import java.sql.Timestamp;


public interface UDPService {

    /**
     * 开始采样，同步存入数据库
     * @return 开始时刻
     */
    public Timestamp StartReceive();

    /**
     * 结束采样
     * @return 结束时刻
     */
    public Timestamp EndReceive();

    /**
     * 发送命令
     * @param motor
     * @throws IOException
     */
    public void SendMessage(Motor motor) throws IOException;

    /**
     * 接受数据，但不存入数据库，用于调试
     * @return
     */
    public Motor receive() throws IOException;

}
