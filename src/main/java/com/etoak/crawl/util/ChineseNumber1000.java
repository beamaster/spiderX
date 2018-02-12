package com.etoak.crawl.util;

/**
 * 处理千位数
 *
 * @author shiyonghai
 * @version 1.0
 * @create 2018/2/12
 */
public class ChineseNumber1000 {
    private final long unit = 1000;
    public final static String number1000 = "千|仟";

    private final String rex = RexUtils.and(RexUtils.more(RexUtils.or(ChineseNumber1.number0, ChineseNumber1.number1, ChineseNumber1.number2, ChineseNumber1.number3,
            ChineseNumber1.number4, ChineseNumber1.number5, ChineseNumber1.number6, ChineseNumber1.number7, ChineseNumber1.number8, ChineseNumber1.number9,
            ChineseNumber10.number10, ChineseNumber100.number100)), number1000);

    private ChineseNumber1 number = new ChineseNumber1();

    private String mData = "";

    public ChineseNumber1000(String data) {
        mData = RexUtils.getFind(data, rex);
        if (!TextUtils.isEmpty(mData)) {
            number = new ChineseNumber1(String.valueOf(mData.subSequence(0, mData.length() - 1)));
        }
    }

    public ChineseNumber1000() {
        number = new ChineseNumber1();
    }

    public long getNumber() {
        return number.getNumber() * unit;
    }

    public String getData() {
        return mData;
    }
}

