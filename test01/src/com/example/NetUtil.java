package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class NetUtil {

    public void addNewPerson(String localFile) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://10.201.102.48/api/subject/");
            FileBody image = new FileBody(new File(localFile));

            /**
             *  image: 人像照片
             *  rect: 可选，照片人脸框
             *  group: 目标人像库名称
             *  name: 可选，人员姓名
             *  gender: 可选，人员性别（1 男，2 女，0 未知）
             *  cert_type: 可选，证件类别（0 其他，1 身份证，2 护照，3 军官证）
             *  cert_id: 可选，证件号码
             *  remark: 可选，人员备注
             */
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("image", image)
                    .addBinaryBody("group", "公司".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("name", "小王".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("gender", "1".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("cert_id", "270482497516286890".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("cert_type", "1".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("remark", "来参观公司".getBytes(Charset.forName("UTF-8")))
                    .setMode(HttpMultipartMode.STRICT)
                    .setCharset(Charset.forName("UTF-8"))
                    .build();
            httpPost.setEntity(reqEntity);
            response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String responseStr = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                GroupItem item = JSON.parseObject(jsonObject.toJSONString(), GroupItem.class);
                System.out.println(jsonObject.toString() + "\n" + item.toString());
            }

            EntityUtils.consume(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
