package com.etoak.crawl.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * 获取页面内容方法1
     * @param url
     * @return
     */
    public static String getHtmlContent1(String url){
        String content = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            content = EntityUtils.toString(httpEntity,"utf-8");
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 获取页面内容方法2
     * @param url
     */
    public static StringBuilder getHtmlContent2(String url){
        StringBuilder entityStringBuilder = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        // 执行请求
        HttpResponse response;
        String line;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpEntity.getContent(), "utf-8"), 8 * 1024);

            while ((line = bufferedReader.readLine()) != null) {
                entityStringBuilder.append(line + "\n");
            }
            //  System.out.println(entityStringBuilder.toString());
            // appendMethodB("f:/中医基础理论.html",entityStringBuilder.toString());
            savaFile("f:/中医基础理论.html",entityStringBuilder.toString(),"UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityStringBuilder;
    }
    /**
     * 获取页面内容方法3
     * http://www.self.org.cn/self_yj/201801/t20180125_29755.html
     * @param url
     */
    public static void getHtmlContent3(String url){
        String request_full_url = "http://www.self.org.cn/self_yj/201801/t20180125_29755.html";
        try{
            URLConnection conn = new URL(url).openConnection();
            conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
            InputStream is = conn.getInputStream();
            FileOutputStream out = new FileOutputStream("test0213.html");
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

    public static ExecutorService getFileWithThreadPool(String urlLocation,String filePath,String savePath,int poolLength){
    ExecutorService threadPool = Executors.newFixedThreadPool(poolLength);
        try {
            long len = 1L;
            for (int i =0; i < poolLength;i++){
                long start = i*len/poolLength;
                long end = (i+1)*len/poolLength-1;
                if(i == poolLength - 1){
                    end = len;
                }
                PicDownload download = new PicDownload(urlLocation,savePath,start,end);
            }
        }catch(Exception e){
            System.out.println(e);
        }

        return threadPool;
}

    /**
     * 保存文件
     * @param fileName 文件名称：绝对路径
     * @param content 要保存的内容
     * @param format 以某种格式保存文件
     */
    public static void savaFile(String fileName, String content,String format) {
        BufferedWriter rd=null;
        OutputStream out=null;
        File file = new File(fileName);
        try {
            out = new FileOutputStream(file);
            rd = new BufferedWriter(new OutputStreamWriter(out,format));
            rd.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=rd){
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 中国科学院网络化科普平台-科普演讲
     * 获取真实的下载Mp4地址
     * @param url
     * @return
     */
    public static String getDetailUrl(String url){
        String whole_detail_url = "";
        Document doc = null;
        Document doc2 = null;
        Document doc3 = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("pic_item");
            for (Element element :elements){
                Element e_value = element.getElementsByTag("a").get(1);
                /** 1. 获取列表页中每个详情页的地址*/
                String linkSrc = e_value.attr("href");
                logger.error("====linkSrc======" + linkSrc);
                String fileName = e_value.text();
                logger.error("======fileName=====" +fileName);
                /** 2. 拼接成完整地址*/
                whole_detail_url = url+linkSrc.replace("./","");
                logger.error("======完整详情地址=====" + whole_detail_url);
                doc2 = Jsoup.connect(whole_detail_url).get();
                Elements elements2 = doc2.getElementsByClass("index_02_left_tit_l2");
                Element element2 = elements2.select("a").first();
                String mp4ReqUrl = element2.attr("href");
                logger.error("======mp4ReqUrl=====" +mp4ReqUrl);
                /** 3.保存文件*/
                String format = ".mp4";
                savaFile(fileName,mp4ReqUrl,format);
                logger.error(fileName +"======下载成功=====");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("======e=====" +e);
        }
        return whole_detail_url;
    }

    /**
     * 听叶思芬说金瓶梅
     * 获取真实的下载Mp3地址
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

