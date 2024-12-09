package com.fujica.listener;

import com.alibaba.fastjson.JSON;
import com.fujica.dto.MqMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic.ivsResult}",consumerGroup = "${rocketmq.consumer.group.ivsResult}")
@Slf4j
public class IvsResultListener extends AbstractRocketMQListener {


    @Override
    public void onMessage(String message) {
        log.info("识别鉴权-收到M-Q消息：{}",message);
        //1.解析消息
        MqMsgDto mqMsgDto  = JSON.parseObject(message, MqMsgDto.class);


    }
}
