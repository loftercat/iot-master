package com.fujica.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fujica.dto.DeviceReply;
import com.fujica.dto.DeviceTimeReplyPayload;
import com.fujica.dto.MqMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic.getDeviceTimestamp}",consumerGroup = "${rocketmq.consumer.group.getDeviceTimestamp}")
@Slf4j
public class GetDeviceTimestampListener extends AbstractRocketMQListener {


    @Override
    public void onMessage(String message) {
        log.info("获取设备时间-收到M-Q消息：{}",message);
        //1.解析消息
        MqMsgDto mqMsgDto  = JSON.parseObject(message, MqMsgDto.class);
        DeviceReply<DeviceTimeReplyPayload> deviceReply = JSON.parseObject(mqMsgDto.getData(), new TypeReference<DeviceReply<DeviceTimeReplyPayload>>() {});
        log.info("设备时间：{}",JSON.toJSONString(deviceReply));

    }
}
