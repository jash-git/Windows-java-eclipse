package com.example;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/10.
 */

public class GroupItem {

    private int id;
    private String cert_id;
    private int cert_type;
    private String gender;
    private String name;
    private Photo[] photos;
    private String pinyin;
    private String remark;
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

    public int getCert_type() {
        return cert_type;
    }

    public void setCert_type(int cert_type) {
        this.cert_type = cert_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class Photo {
        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "id=" + id +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GroupItem{" +
                "id=" + id +
                ", cert_id='" + cert_id + '\'' +
                ", cert_type=" + cert_type +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", photos=" + Arrays.toString(photos) +
                ", pinyin='" + pinyin + '\'' +
                ", remark='" + remark + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
