package com.imc.motorcontrol.entity;

public enum State {
    power,//上电 0x0000
    init,//初始化 0x0001
    server_op,//伺服无故障 0x0002
    powered_lock,//电机使能锁死 0x0003
    op,//电机运行 0x0004
    error,//故障停机 0x00ff
    unknowState // 未知故障
}
