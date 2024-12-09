package com.fujica.dto;

import lombok.Data;

/**
 * @author songcao
 * @version 1.0
 * @description: TODO
 * @date 2024/12/6 14:36
 */
@Data
public class DeviceReply<P> {
    private String id;
    private String sn;
    private String name;
    private String version;
    private long timestamp;
    private P payload;
}
