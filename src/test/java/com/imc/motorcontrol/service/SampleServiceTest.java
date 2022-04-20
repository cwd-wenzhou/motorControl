package com.imc.motorcontrol.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imc.motorcontrol.entity.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleServiceTest {
    @Autowired
    private SampleService sampleService;

    @Test
    void insertTest(){
        Sample sample = new Sample();
        sample.setSampleName("测试1");
        sample.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        sample.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        sampleService.save(sample);
    }

    @Test
    void selectTest(){
        List<Sample> samples = sampleService.list();
        samples.forEach(System.out::println);
    }

    @Test
    void selectOneTest(){
        //QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq()
        System.out.println(sampleService.getById("测试1"));
    }
}