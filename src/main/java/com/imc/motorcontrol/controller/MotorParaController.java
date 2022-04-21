package com.imc.motorcontrol.controller;

import com.imc.motorcontrol.service.MotorControlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/motorpara")
public class MotorParaController {

    final
    MotorControlService motorControlService;

    public MotorParaController(MotorControlService motorControlService) {
        this.motorControlService = motorControlService;
    }

    @GetMapping("/mode/{mode}/{num}")
    public void setModecode(@PathVariable Short mode,@PathVariable Integer num) throws IOException {
        System.out.println(mode);
        motorControlService.setModeCode(mode,num);
    }

    @GetMapping("/vel/{velocity}/{num}")
    public void setVelocity(@PathVariable Short velocity,@PathVariable Integer num) throws IOException {
        motorControlService.setVelocity(velocity,num);
    }

    @GetMapping("/pos/{position}/{num}")
    public void setPosition(@PathVariable Short position,@PathVariable Integer num) throws IOException {
        motorControlService.setPosition(position,num);
    }

    @GetMapping("/iq/{currentIq}/{num}")
    public void setCurrentIq(@PathVariable short currentIq,@PathVariable Integer num) throws IOException{
        motorControlService.setCurrentIq(currentIq,num);
    }


    @GetMapping("/id/{currentId}/{num}")
    public void setCurrentId(@PathVariable short currentId,@PathVariable Integer num) throws IOException{
        motorControlService.setCurrentId(currentId,num);
    }


    @GetMapping("/velkp/{speedRingKp}/{num}")
    public void setSpeedRingKp(@PathVariable short speedRingKp,@PathVariable Integer num) throws IOException{
        motorControlService.setSpeedRingKp(speedRingKp,num);
    }

    @GetMapping("/velki/{speedRingKi}/{num}")
    public void setSpeedRingKi(@PathVariable short speedRingKi,@PathVariable Integer num) throws IOException{
        motorControlService.setSpeedRingKi(speedRingKi,num);
    }

    @GetMapping("poskp/{positionRingKp}/{num}")
    public void setPositionRingKp(@PathVariable short positionRingKp,@PathVariable Integer num) throws IOException{
        motorControlService.setPositionRingKp(positionRingKp,num);
    }

    @GetMapping("poskd/{positionRingKd}/{num}")
    void setPositionRingKd(@PathVariable short positionRingKd,@PathVariable Integer num) throws IOException{
        motorControlService.setPositionRingKd(positionRingKd,num);
    }

    @GetMapping("curkp/{currentRingKp}/{num}")
    public void setCurrentRingKp(@PathVariable short currentRingKp,@PathVariable Integer num) throws IOException{
        motorControlService.setCurrentRingKp(currentRingKp,num);
    }

    @GetMapping("curki/{currentRingKi}/{num}")
    public void setCurrentRingKi(@PathVariable short currentRingKi,@PathVariable Integer num) throws IOException{
        motorControlService.setCurrentRingKi(currentRingKi,num);
    }
}
