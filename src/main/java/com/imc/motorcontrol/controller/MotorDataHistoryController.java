package com.imc.motorcontrol.controller;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.Sample;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/motordata/history")
public class MotorDataHistoryController {
    final SampleService sampleService;

    final MotorService motorService;

    public MotorDataHistoryController(SampleService sampleService, MotorService motorService) {
        this.sampleService = sampleService;
        this.motorService = motorService;
    }

    @GetMapping("/name")
    List<Sample> getSampleNames(){
        return sampleService.list();
    }

    @GetMapping("/{name}")
    List<Motor> getSampleData(@PathVariable String name){
        Sample sample = sampleService.getById(name);
        if (sample==null)
            return null;
        return motorService.selectFromZone(sample.getStartTime(), sample.getEndTime());
    }
}
