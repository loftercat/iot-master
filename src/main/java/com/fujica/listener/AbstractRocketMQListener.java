package com.fujica.listener;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.fujica.constant.HeaderConstant;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.MDC;

import java.nio.charset.StandardCharsets;

public abstract class AbstractRocketMQListener implements RocketMQListener<MessageExt> {

    abstract void onMessage(String msg);

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            String traceId = messageExt.getProperty(HeaderConstant.TRACE_ID);
            if (StrUtil.isNotBlank(traceId)) {
                MDC.put(HeaderConstant.TRACE_ID, traceId);
            } else {
                MDC.put(HeaderConstant.TRACE_ID, IdUtil.fastSimpleUUID());
            }
            String msg = new String(messageExt.getBody(), StandardCharsets.UTF_8);
            onMessage(msg);
        } finally {
            MDC.clear();
        }

    }
}
