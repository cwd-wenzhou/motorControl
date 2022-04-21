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

    @GetMapping("/start/{num}/{name}")
    public void startSample(@PathVariable Integer num,@PathVariable String name){
        if (sampleService.getById(name) != null){
            name+="_new";
        }
        startTime = motorControlService.startSample(num);
        webSocketController.setSampling(true);
        this.name = name;
    }

    @GetMapping("/end/{num}")
    public void endSample(@PathVariable Integer num) throws InterruptedException {
        Timestamp endTime = motorControlService.endSample(num);
        sampleService.save(new Sample(startTime, endTime,name));
        webSocketController.setSampling(false);
    }

    @GetMapping("/startall/{name}")
    public void startSample(@PathVariable String name){
        if (sampleService.getById(name) != null){
            name+="_new";
        }
        startTime = motorControlService.startAllSample();
        webSocketController.setSampling(true);//todo
        this.name = name;
    }

    @GetMapping("/endall")
    public void endSample() throws InterruptedException {
        Timestamp endTime = motorControlService.endAllSample();
        sampleService.save(new Sample(startTime, endTime,name));
        webSocketController.setSampling(false);
    }
}
