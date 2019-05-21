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
             *  image: ������Ƭ
             *  rect: ��ѡ����Ƭ������
             *  group: Ŀ�����������
             *  name: ��ѡ����Ա����
             *  gender: ��ѡ����Ա�Ա�1 �У�2 Ů��0 δ֪��
             *  cert_type: ��ѡ��֤�����0 ������1 ���֤��2 ���գ�3 ����֤��
             *  cert_id: ��ѡ��֤������
             *  remark: ��ѡ����Ա��ע
             */
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("image", image)
                    .addBinaryBody("group", "��˾".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("name", "С��".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("gender", "1".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("cert_id", "270482497516286890".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("cert_type", "1".getBytes(Charset.forName("UTF-8")))
                    .addBinaryBody("remark", "���ι۹�˾".getBytes(Charset.forName("UTF-8")))
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
