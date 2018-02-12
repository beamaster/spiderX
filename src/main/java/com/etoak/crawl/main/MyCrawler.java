package com.etoak.crawl.main;

import org.junit.Test;

import java.io.File;

import static com.etoak.crawl.util.ParserHtml.chineseNumber2Int;
import static com.etoak.crawl.util.ParserHtml.downLoadMp3;
import static com.etoak.crawl.util.ParserHtml.getTitleAndTruePath;

public class MyCrawler {


    @Test
    public void chinese2Number() {
        String title = "第二十七讲丨妓女是古代的时尚爱豆";
        String replaceStr = title.substring(1,title.indexOf("讲"));
        int temp  = chineseNumber2Int(replaceStr);
        System.out.println(replaceStr +"----" + temp);

        String result = title.replace(replaceStr,String.valueOf(temp)) ;
        System.out.println(result);
    }



    /**
     * uri=====
     * http://dhl.taichanggame.com/g/20171226_jpm_tui/index.php?token=26&str=1234
     */
    private static String head_url = "http://dhl.taichanggame.com/g/20171226_jpm_tui/";
    private static String index_url = "index.php?token=";
    private static String end_url = "&str=1234";
    //main 方法入口
    public static void main(String[] args) {

        /** 文件存储路径*/
        String filePath = "E:\\Download\\LC\\2018 GoldBottleFlower\\";

        String request_full_url = "";
        String download_full_url = "";
        Long statTime = System.currentTimeMillis();
        int count = 0;
        /** 1. 拼接请求地址*/
        for (int i = 30;i < 60; i++){
            request_full_url = head_url + index_url + i + end_url;
            count++;
            /** 2. 解析真实的下载地址*/
            String title_linkSrc =  getTitleAndTruePath(request_full_url);
            String title = title_linkSrc.split(",")[0];
            String truePath =  title_linkSrc.split(",")[1];

            /** 3. 再次拼接*/
            download_full_url = head_url+truePath.replace("./","");
            System.out.println("----拼接地址---" + download_full_url);

            /** 4. 下载*/
            downLoadMp3(filePath,download_full_url);
            String fileName = download_full_url.substring(download_full_url.lastIndexOf("/")+1);
            System.out.println( fileName + "-----下载成功---");


            /** 5. 重命名*/
            //1.将中文数字转为阿拉伯数字
            String replaceStr = title.substring(1,title.indexOf("讲"));
            int number  = chineseNumber2Int(replaceStr);
            String result = title.replace(replaceStr,String.valueOf(number));
            System.out.println( result + "-----数字转换成功-----");
            //2.重命名
            new File(filePath +fileName).renameTo(new File(filePath + result + ".m4a"));

            System.out.println( title + "-----重命名成功-----");
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("-----所有操作全部完成");
        System.out.println("-----共计下载-----" + (count) + "个文件");
        System.out.println("-----耗时-----" + (endTime-statTime)/1000 + "秒");

    }
}
