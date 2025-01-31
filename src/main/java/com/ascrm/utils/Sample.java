package com.ascrm.utils;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.ascrm.entity.SmsEntity;
import darabonba.core.client.ClientOverrideConfiguration;

/**
 * @Author: ascrm
 * @Date: 2025/1/31
 */
public class Sample {

    public static void sendSms(SmsEntity smsEntity) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(System.getenv(smsEntity.getAccessKey()))
                .accessKeySecret(System.getenv(smsEntity.getSecretKey()))
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-chengdu") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(smsEntity.getPhoneNumber())
                .signName(smsEntity.getSignName())
                .templateCode(smsEntity.getTemplateCode())
                .templateParam(smsEntity.getTemplateParam())
                .build();

        client.sendSms(sendSmsRequest);

        client.close();
    }
}
