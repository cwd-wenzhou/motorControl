package com.imc.motorcontrol.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imc.motorcontrol.entity.Sample;
import com.imc.motorcontrol.mapper.SampleMapper;
import com.imc.motorcontrol.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements SampleService {
}
