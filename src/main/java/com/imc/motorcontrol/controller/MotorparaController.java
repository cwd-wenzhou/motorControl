package com.imc.motorcontrol.controller;

import com.imc.motorcontrol.service.MotorControlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/motorpara")
public class MotorparaController {

    final
    MotorControlService motorControlService;

    public MotorparaController(MotorControlService motorControlService) {
        this.motorControlService = motorControlService;
    }

    @GetMapping("/mode/{mode}")
    public void setModecode(@PathVariable Short mode) throws IOException {
        motorControlService.setModeCode(mode);
    }

    @GetMapping("/vel/{velocity}")
    public void setVelocity(@PathVariable Short velocity) throws IOException {
        motorControlService.setVelocity(velocity);
    }

    @GetMapping("/pos/{position}")
    public void setPosition(@PathVariable Short position) throws IOException {
        motorControlService.setPosition(position);
    }

    @GetMapping("/iq/{currentIq}")
    public void setCurrentIq(@PathVariable short currentIq) throws IOException{
        motorControlService.setCurrentIq(currentIq);
    }


    @GetMapping("/id/{currentId}")
    public void setCurrentId(@PathVariable short currentId) throws IOException{
        motorControlService.setCurrentId(currentId);
    }


    @GetMapping("/velkp/{speedRingKp}")
    public void setSpeedRingKp(@PathVariable short speedRingKp) throws IOException{
        motorControlService.setSpeedRingKp(speedRingKp);
    }

    @GetMapping("/velki/{speedRingKi}")
    public void setSpeedRingKi(@PathVariable short speedRingKi) throws IOException{
        motorControlService.setSpeedRingKi(speedRingKi);
    }

    @GetMapping("poskp/{positionRingKp}")
    public void setPositionRingKp(@PathVariable short positionRingKp) throws IOException{
        motorControlService.setPositionRingKp(positionRingKp);
    }

    @GetMapping("poskp/{positionRingKd}")
    void setPositionRingKd(@PathVariable short positionRingKd) throws IOException{
        motorControlService.setPositionRingKd(positionRingKd);
    }

    @GetMapping("curkp/{currentRingKp}")
    public void setCurrentRingKp(@PathVariable short currentRingKp) throws IOException{
        motorControlService.setCurrentRingKp(currentRingKp);
    }

    @GetMapping("curki/{currentRingKi}")
    public void setCurrentRingKi(@PathVariable short currentRingKi) throws IOException{
        motorControlService.setCurrentRingKi(currentRingKi);
    }
}
