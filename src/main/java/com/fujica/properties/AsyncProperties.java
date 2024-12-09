package com.fujica.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("async")
public class AsyncProperties {
    /**
     * 配置核心线程数
     */
    private int corePoolSize;

    /**
     * 配置最大线程数
     */
    private int maxPoolSize;

    /**
     * 配置队列大小
     */
    private int queueCapacity;

    /**
     * 配置线程池中的线程的名称前缀
     */
    private String namePrefix;

}
