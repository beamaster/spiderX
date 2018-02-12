package com.etoak.crawl.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etoak.crawl.util.APIUtils;

public class Main {

    public static void main(String[] args) {
//        String ip = "117.89.51.229";
//        String ip = "61.128.101.10";//新疆维吾尔自治区乌鲁木齐市
//        String ip = "49.77.196.229";//南京
//        String ip = "123.125.71.38";//北京
//        String ip = "10.10.6.204";//内网IP
//        String ip = "67.210.200.63";//美国IP
        String ip = "120.27.15.229";//测试
//        String ip = "84.19.173.194";//德国IP
//        String ip = "49.77.178.207";//invaild ip
//        String interfaceIp = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
        String interfaceIp = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
        String jsonStr = APIUtils.loadJSON(interfaceIp);
        System.out.println("jsonStr========" + jsonStr);
        System.out.println("***========" + jsonStr.contains("code"));
        JSONObject jsonObject = null;
        if(jsonStr != null && !"".equals(jsonStr) ){
            jsonObject = JSON.parseObject(jsonStr);
        }
        System.out.println("========" + jsonObject);
        System.out.println("====data====" +jsonObject.get("data"));
        System.out.println("====city====" +JSON.parseObject(jsonObject.get("data").toString()).get("city").toString());
    }
}
