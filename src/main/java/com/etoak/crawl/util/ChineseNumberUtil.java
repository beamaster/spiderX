package com.etoak.crawl.util;

/**
 * 从字符串中获取中文数字并转换为阿拉伯数字
 *
 * @author shiyonghai
 * @version 1.0
 * @create 2018/2/12
 * Created by huomengyuan on 2017/4/14.
 */
public class ChineseNumberUtil {
    /**
     * @param value 需要转换的字符串
     * @return
     */
    public static long getNumbers(String value) {
        value = value.replace("零", "");
        ChineseNumber1x chineseNumber1x = new ChineseNumber1x(value);
        ChineseNumber10000 chineseNumber10000 = new ChineseNumber10000(value = value.replace(chineseNumber1x.getData(), ""));
        ChineseNumber1000 chineseNumber1000 = new ChineseNumber1000(value = value.replace(chineseNumber10000.getData(), ""));
        ChineseNumber100 chineseNumber100 = new ChineseNumber100(value = value.replace(chineseNumber1000.getData(), ""));
        ChineseNumber10 chineseNumber10 = new ChineseNumber10(value = value.replace(chineseNumber100.getData(), ""));
        ChineseNumber1 chineseNumber1 = new ChineseNumber1(value.replace(chineseNumber10.getData(), ""));

        long number1x = chineseNumber1x.getNumber();
        long number10000 = chineseNumber10000.getNumber();
        long number1000 = chineseNumber1000.getNumber();
        long number100 = chineseNumber100.getNumber();
        long number10 = chineseNumber10.getNumber();
        long number1 = chineseNumber1.getNumber();

        long total = number1 + number10 + number100 + number1000 + number10000 + number1x;
        return total;
    }
}

