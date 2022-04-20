package com.imc.motorcontrol.controller;

import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.entity.Sample;
import com.imc.motorcontrol.service.MotorControlService;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/motordata/history")
public class MotorDataController {
    final SampleService sampleService;

    final MotorControlService motorControlService;

    final MotorService motorService;

    public MotorDataController(SampleService sampleService, MotorControlService motorControlService, MotorService motorService) {
        this.sampleService = sampleService;
        this.motorControlService = motorControlService;
        this.motorService = motorService;
    }

    @GetMapping("/{name}")
    List<Motor> getSampleNames(@PathVariable String name){
        Sample sample = sampleService.getById(name);
        if (sample==null)
            return null;
        return motorService.selectFromZone(sample.getStartTime(), sample.getEndTime());
    }
}
