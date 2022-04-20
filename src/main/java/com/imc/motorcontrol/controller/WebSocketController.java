package com.imc.motorcontrol.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imc.motorcontrol.entity.Motor;
import com.imc.motorcontrol.service.MotorService;
import com.imc.motorcontrol.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@EnableScheduling
@RequestMapping("/websocket")
public class WebSocketController {

    final
    MotorService motorService;

    private boolean sampling;

    public WebSocketController(MotorService motorService) {
        this.motorService = motorService;
        sampling = false;
    }


    @Scheduled(fixedRate = 3000)
    public void pushMotor() {
        if (sampling){
            QueryWrapper<Motor> motorQueryWrapper = new QueryWrapper<>();
            motorQueryWrapper.orderByDesc("sample_time").last("limit 1");
            Motor motor = motorService.getOne(motorQueryWrapper);

            CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.getWebSocketSet();
            webSocketSet.forEach(c->{
                try {
                    c.sendMessage(motor.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public boolean isSampling() {
        return sampling;
    }

    public void setSampling(boolean sampling) {
        this.sampling = sampling;
    }
}
