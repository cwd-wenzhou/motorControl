package com.imc.motorcontrol.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.mapper.MotorMapper;
import com.imc.motorcontrol.service.MotorService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MotorServiceImpl extends ServiceImpl<MotorMapper, Motor> implements MotorService {

    @Override
    public List<Motor> selectFromZone(Timestamp start, Timestamp stop) {

        QueryWrapper<Motor> motorQueryWrapper = new QueryWrapper<>();
        motorQueryWrapper.between("sampleTime", start, stop);
        return baseMapper.selectList(motorQueryWrapper);
    }

    @Override
    public Long CountFromZone(Timestamp start, Timestamp stop) {

        QueryWrapper<Motor> motorQueryWrapper = new QueryWrapper<>();
        motorQueryWrapper.between("sampleTime", start, stop);
        return baseMapper.selectCount(motorQueryWrapper);
    }

}
