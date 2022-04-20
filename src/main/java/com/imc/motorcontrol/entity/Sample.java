package com.imc.motorcontrol.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@TableName("sample")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {
    private Timestamp startTime;
    private Timestamp endTime;
    @TableId
    private String sampleName;
}
