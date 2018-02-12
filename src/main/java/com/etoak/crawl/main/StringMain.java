package com.etoak.crawl.main;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author shiyonghai
 * @version 1.0
 * @create 2018/2/11
 */
public class StringMain {

    final private static String STATIC_IP = "http://122.228.240.77/v.cctv.com/flash/mp4video31/TMS/cc/27797/5019703/";
    final private static String STATIC_END = "_MP3_128.mp3";

    static String[] arr = {
            "e8022d35e146485c8f7d20309adec67e",
            "6fb1f54b3c7a4ec8a89cdfd6f16b1196",
            "1c43e60664504ff8bd50158c52692b93",
            "51d54b48ee404459b10092694bf6db3e",
            "9482db58dd954351b76cb44ad6e9ab0b",
            "3ce737264eb34f228cf469f3b87b851a",
            "a0e3e5a70bda4b0f81870b7807ab6e8d",
            "5d3244f7ae8a4130b248bc9450f805d7",
            "933d31eed4ae44429dcac88d1167f0df",
            "6a64b49b8f6f4bc78812dec2e50cdc31",
            "523ee09792b14473a4d5bb928432797d"};

    public static void main(String[] args) {
        String result = "";
        for (int i =0; i<arr.length;i++){
            result = STATIC_IP + arr[i] + STATIC_END ;
            System.out.println(result);
        }


    }
}

