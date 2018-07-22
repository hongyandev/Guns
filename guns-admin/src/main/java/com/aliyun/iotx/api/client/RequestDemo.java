package com.aliyun.iotx.api.client;
/*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.UnsupportedEncodingException;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiRequest;
import com.stylefeng.guns.aliyun.iotx.api.client.SyncApiClient;

/**
 * @author zhongfu.xiezf
 */
public class RequestDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        SyncApiClient syncClient = SyncApiClient.newBuilder()
            .appKey("24841931")
            .appSecret("0239e7dfaca52308001c0e95f689314f")
            .build();

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("1.0.0");

        //如果需要登陆，设置当前的会话的token
//        request.setIoTToken("xxxxxxxxxxxxxxx");

        //设置参数
        //授权类型
        request.putParam("grantType", "project");
        //资源项目ID
        request.putParam("res", "a124YO0oU3Qm4SGF");

        //请求参数域名、path、request
        String host = "api.link.aliyun.com";
        String path = "/cloud/token";
        ApiResponse response = syncClient.postBody(host, path, request, true);
        

        System.out.println(
            "response code = " + response.getStatusCode() + " response content = " + new String(response.getBody(),
                "utf-8"));
    }
}

