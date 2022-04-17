package com.imc.motorcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imc.motorcontrol.entity.Motor;

import java.sql.Timestamp;
import java.util.List;

public interface MotorService extends IService<Motor> {
    public List<Motor> selectFromZone(Timestamp start,Timestamp stop);

}
