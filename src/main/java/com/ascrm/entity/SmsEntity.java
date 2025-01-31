package com.ascrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: ascrm
 * @Date: 2025/1/31
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsEntity {
    private String phoneNumber;
    private String signName;
    private String templateCode;
    private String templateParam;

    @Value("${sms.access-key}")
    private String accessKey;

    @Value("${sms.secret-key}")
    private String secretKey;
}
