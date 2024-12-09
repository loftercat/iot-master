package com.fujica.listener;

import com.alibaba.fastjson.JSON;
import com.fujica.dto.MqMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic.keepAlive}",consumerGroup = "${rocketmq.consumer.group.keepAlive}")
@Slf4j
public class keepAliveListener extends AbstractRocketMQListener {


    @Override
    public void onMessage(String message) {
        log.info("设备心跳-收到M-Q消息：{}",message);
        //1.解析消息
        MqMsgDto mqMsgDto  = JSON.parseObject(message, MqMsgDto.class);


    }
}
