package com.imc.motorcontrol.controller;

import com.imc.motorcontrol.entity.Sample;
import com.imc.motorcontrol.service.MotorControlService;
import com.imc.motorcontrol.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/motordata/rtdata")
public class MotorDataRTController {
    final MotorControlService motorControlService;
    final WebSocketController webSocketController;
    final SampleService sampleService;

    private Timestamp startTime;
    private String name;

    public MotorDataRTController(MotorControlService motorControlService, WebSocketController webSocketController, SampleService sampleService) {
        this.motorControlService = motorControlService;
        this.webSocketController = webSocketController;
        this.sampleService = sampleService;
    }

    @GetMapping("/start/{name}")
    public void startSample(@PathVariable String name){
        if (sampleService.getById(name) != null){
            name+="_new";
        }
        startTime = motorControlService.startSample();
        webSocketController.setSampling(true);
        this.name = name;
    }

    @GetMapping("/end")
    public void endSample() throws InterruptedException {
        Timestamp endTime = motorControlService.endSample();
        sampleService.save(new Sample(startTime, endTime,name));
        webSocketController.setSampling(false);
    }

}
