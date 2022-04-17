package com.imc.motorcontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/motor")
public class MotorController {

    @ResponseBody
    @RequestMapping("/")
    public void GetData(){

    }
}
