package com.etoak.crawl.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 〈一句话功能简述〉<br>
 *
 * @author shiyonghai
 * @version 1.0
 * @create 2018/2/11
 */
public class ParserHtml {

    private static Logger logger = LoggerFactory.getLogger(ParserHtml.class);

    /**
     * 获取真实的下载地址
     * @param url
     * @return
     */
    public static String getTitleAndTruePath(String url){
        String title_linkSrc = "";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.title();
            logger.error("======title=====" +title);
            String uri = doc.baseUri();
//            logger.info("=====uri=====" +uri);
            Element audio = doc.getElementById("audio");
            Elements links = audio.select("source");
            Element link =links.first();
            String linkSrc = link.attr("src");
//            logger.error("====linkSrc======" + linkSrc);
            title_linkSrc = title +','+linkSrc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title_linkSrc;
    }

    /**
     * 根据音频的URL下载到本地的filePath
     * @param filePath 文件夹
     * @param mp3Url mp3的真实地址
     */
    public static void downLoadMp3(String filePath,String mp3Url){
        // 文件名称
        String fileName = mp3Url.substring(mp3Url.lastIndexOf("/")+1);
//        logger.error("------fileName------" + fileName);
        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            URL url = new URL(mp3Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件
            File file = new File(filePath+fileName);
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

}

