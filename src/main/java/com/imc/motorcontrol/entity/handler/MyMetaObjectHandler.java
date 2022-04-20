package com.imc.motorcontrol.entity.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        LocalDateTime now = LocalDateTime.now();
//        Timestamp sampleTime = Timestamp.valueOf(now);
//        this.setFieldValByName("sampleTime", sampleTime, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
