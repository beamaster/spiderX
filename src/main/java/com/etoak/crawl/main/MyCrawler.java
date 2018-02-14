package com.etoak.crawl.main;

import org.slf4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.etoak.crawl.util.ParserHtml.chineseNumber2Int;
import static com.etoak.crawl.util.ParserHtml.downLoadMp3;
import static com.etoak.crawl.util.ParserHtml.getDetailUrl;
import static com.etoak.crawl.util.ParserHtml.getHtmlContent1;
import static com.etoak.crawl.util.ParserHtml.getTitleAndTruePath;
import static com.etoak.crawl.util.ParserHtml.savaFile;
import static com.etoak.crawl.util.ParserHtmlUtil.getHtml;

public class MyCrawler {

    private static Logger logger = LoggerFactory.getLogger(MyCrawler.class);

    @Test
    public void chinese2Number() {
        String title = "第二十七讲丨妓女是古代的时尚爱豆";
        String replaceStr = title.substring(1,title.indexOf("讲"));
        int temp  = chineseNumber2Int(replaceStr);
        logger.error(replaceStr +"----" + temp);

        String result = title.replace(replaceStr,String.valueOf(temp)) ;
        logger.error(result);
    }

    @Test
    public void testDownLoadMp4() {
        String request_full_url = "http://www.self.org.cn/self_yj/201801/t20180125_29755.html";

    }

    @Test
    public void testDownLoadMp44() {
        String fileName = "胡国平：让世界享受AI的乐趣";
        String mp4ReqUrl = "http://v.kepu.cn/video/video_download.html?videoForm.videoId=2810";
        String format = "file";


        try{
            URLConnection conn = new URL(mp4ReqUrl).openConnection();
            conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
            InputStream is = conn.getInputStream();
            FileOutputStream out = new FileOutputStream("test0213.mp4");
            int a=0;
            while((a = is.read()) != -1){
                out.write(a);
            }
            is.close();
            out.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * 中国科学院网络化科普平台
     * 科普演讲
     * 入口：http://www.self.org.cn/self_yj/
     * 详情页地址：http://www.self.org.cn/self_yj/201801/t20180125_29755.html
     */
    @Test
    public void doGetScienceTalkUrl() {
        //分类地址--入口
        String category_url = "http://www.self.org.cn/self_yj/";
        //完整请求地址
        String whole_detail_url = "";

        //完整请求地址头
        String whole_detail_head = "";
        /** 1. 获取列表页中每个详情页的地址*/
        whole_detail_url =  getDetailUrl(category_url);
        logger.info("whole_detail_url:" + whole_detail_url);
        /** 2. 拼接成完整地址*/

        /** 3. 根据拼接的完整地址当成请求地址，获取响应内容中的视频真实地址*/
        /** 1. */
        /** 1. */

    }

    /**
     *
     */
    @Test
    public void doGet() {

    }


    private static String head_url = "http://dhl.taichanggame.com/g/20171226_jpm_tui/";
    private static String index_url = "index.php?token=";
    private static String end_url = "&str=1234";

    /**
     * 听叶思芬说金瓶梅
     * http://dhl.taichanggame.com/g/20171226_jpm_tui/index.php?token=26&str=1234
     */
    @Test
    public void doDownLoadJinPinMeiMp3() {

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

    public static void main(String[] args) {

    }
}
