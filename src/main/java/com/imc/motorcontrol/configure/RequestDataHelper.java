package com.imc.motorcontrol.configure;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

import java.util.Map;

/**
 * 请求参数传递辅助类
 */
public class RequestDataHelper implements TableNameHandler {

    @Override
    public String dynamicTableName(String sql, String tableName) {
        return tableName+"_"+SampleNum.getNum();
    }
}