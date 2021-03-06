package com.etoak.crawl.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedReader;

public class ChineseToEnglish {
    // 将汉字转换为全拼
    public static String getPingYin(String src) {

        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
            // System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    // 返回中文的首字母
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    // 将字符串转移为ASCII码
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        String data = "阿坝州\n" +
                "甘孜州\n" +
                "凉山州\n" +
                "雅安市\n" +
                "乌海市\n" +
                "巴中市\n" +
                "锡林郭勒盟\n" +
                "日喀则地区\n" +
                "林芝地区\n" +
                "阿里地区\n" +
                "那曲地区\n" +
                "山南地区\n" +
                "昌都地区\n" +
                "阿拉善盟\n" +
                "拉萨市\n" +
                "滁州市\n" +
                "合肥市\n" +
                "蚌埠市\n" +
                "芜湖市\n" +
                "淮南市\n" +
                "马鞍山市\n" +
                "安庆市\n" +
                "宿州市\n" +
                "阜阳市\n" +
                "亳州市\n" +
                "黄山市\n" +
                "淮北市\n" +
                "铜陵市\n" +
                "宣城市\n" +
                "六安市\n" +
                "池州市\n" +
                "北京市\n" +
                "福州市\n" +
                "厦门市\n" +
                "宁德市\n" +
                "莆田市\n" +
                "泉州市\n" +
                "漳州市\n" +
                "龙岩市\n" +
                "三明市\n" +
                "南平市\n" +
                "临夏州\n" +
                "兰州市\n" +
                "定西市\n" +
                "平凉市\n" +
                "庆阳市\n" +
                "金昌市\n" +
                "武威市\n" +
                "张掖市\n" +
                "酒泉市\n" +
                "嘉峪关市\n" +
                "天水市\n" +
                "陇南市\n" +
                "甘南州\n" +
                "白银市\n" +
                "广州市\n" +
                "汕尾市\n" +
                "阳江市\n" +
                "揭阳市\n" +
                "茂名市\n" +
                "江门市\n" +
                "韶关市\n" +
                "惠州市\n" +
                "梅州市\n" +
                "汕头市\n" +
                "深圳市\n" +
                "珠海市\n" +
                "佛山市\n" +
                "肇庆市\n" +
                "湛江市\n" +
                "中山市\n" +
                "河源市\n" +
                "清远市\n" +
                "云浮市\n" +
                "潮州市\n" +
                "东莞市\n" +
                "防城港市\n" +
                "南宁市\n" +
                "崇左市\n" +
                "柳州市\n" +
                "来宾市\n" +
                "桂林市\n" +
                "梧州市\n" +
                "贺州市\n" +
                "贵港市\n" +
                "玉林市\n" +
                "百色市\n" +
                "钦州市\n" +
                "河池市\n" +
                "北海市\n" +
                "贵阳市\n" +
                "遵义市\n" +
                "安顺市\n" +
                "黔南州\n" +
                "黔东南州\n" +
                "铜仁市\n" +
                "毕节市\n" +
                "六盘水市\n" +
                "黔西南州\n" +
                "海口市\n" +
                "琼海市\n" +
                "文昌市\n" +
                "万宁市\n" +
                "三亚市\n" +
                "五指山市\n" +
                "儋州市\n" +
                "东方市\n" +
                "海南省直辖\n" +
                "邯郸市\n" +
                "石家庄市\n" +
                "保定市\n" +
                "张家口市\n" +
                "承德市\n" +
                "唐山市\n" +
                "廊坊市\n" +
                "沧州市\n" +
                "衡水市\n" +
                "邢台市\n" +
                "秦皇岛市\n" +
                "商丘市\n" +
                "郑州市\n" +
                "安阳市\n" +
                "新乡市\n" +
                "许昌市\n" +
                "平顶山市\n" +
                "信阳市\n" +
                "南阳市\n" +
                "开封市\n" +
                "洛阳市\n" +
                "焦作市\n" +
                "鹤壁市\n" +
                "濮阳市\n" +
                "周口市\n" +
                "漯河市\n" +
                "驻马店市\n" +
                "三门峡市\n" +
                "哈尔滨市\n" +
                "齐齐哈尔市\n" +
                "牡丹江市\n" +
                "佳木斯市\n" +
                "绥化市\n" +
                "黑河市\n" +
                "大兴安岭地区\n" +
                "伊春市\n" +
                "大庆市\n" +
                "七台河市\n" +
                "鸡西市\n" +
                "鹤岗市\n" +
                "双鸭山市\n" +
                "武汉市\n" +
                "襄阳市\n" +
                "鄂州市\n" +
                "孝感市\n" +
                "黄冈市\n" +
                "黄石市\n" +
                "咸宁市\n" +
                "荆州市\n" +
                "宜昌市\n" +
                "恩施州\n" +
                "十堰市\n" +
                "神农架\n" +
                "随州市\n" +
                "荆门市\n" +
                "湖北省直辖\n" +
                "岳阳市\n" +
                "长沙市\n" +
                "湘潭市\n" +
                "株洲市\n" +
                "衡阳市\n" +
                "郴州市\n" +
                "常德市\n" +
                "益阳市\n" +
                "娄底市\n" +
                "邵阳市\n" +
                "湘西州\n" +
                "张家界市\n" +
                "怀化市\n" +
                "永州市\n" +
                "长春市\n" +
                "吉林市\n" +
                "延边州\n" +
                "四平市\n" +
                "通化市\n" +
                "白城市\n" +
                "辽源市\n" +
                "松原市\n" +
                "白山市\n" +
                "南京市\n" +
                "无锡市\n" +
                "镇江市\n" +
                "苏州市\n" +
                "南通市\n" +
                "扬州市\n" +
                "盐城市\n" +
                "徐州市\n" +
                "淮安市\n" +
                "连云港市\n" +
                "常州市\n" +
                "泰州市\n" +
                "宿迁市\n" +
                "鹰潭市\n" +
                "新余市\n" +
                "南昌市\n" +
                "九江市\n" +
                "上饶市\n" +
                "抚州市\n" +
                "宜春市\n" +
                "吉安市\n" +
                "赣州市\n" +
                "景德镇市\n" +
                "萍乡市\n" +
                "沈阳市\n" +
                "铁岭市\n" +
                "大连市\n" +
                "鞍山市\n" +
                "抚顺市\n" +
                "本溪市\n" +
                "锦州市\n" +
                "营口市\n" +
                "阜新市\n" +
                "辽阳市\n" +
                "朝阳市\n" +
                "盘锦市\n" +
                "葫芦岛市\n" +
                "呼伦贝尔市\n" +
                "呼和浩特市\n" +
                "包头市\n" +
                "乌兰察布市\n" +
                "通辽市\n" +
                "鄂尔多斯市\n" +
                "巴彦淖尔市\n" +
                "兴安盟\n" +
                "海北州\n" +
                "西宁市\n" +
                "海东地区\n" +
                "黄南州\n" +
                "海南州\n" +
                "果洛州\n" +
                "玉树州\n" +
                "海西州\n" +
                "菏泽市\n" +
                "济南市\n" +
                "青岛市\n" +
                "淄博市\n" +
                "德州市\n" +
                "烟台市\n" +
                "潍坊市\n" +
                "济宁市\n" +
                "泰安市\n" +
                "临沂市\n" +
                "滨州市\n" +
                "东营市\n" +
                "威海市\n" +
                "枣庄市\n" +
                "日照市\n" +
                "莱芜市\n" +
                "聊城市\n" +
                "朔州市\n" +
                "忻州市\n" +
                "太原市\n" +
                "大同市\n" +
                "阳泉市\n" +
                "晋中市\n" +
                "长治市\n" +
                "晋城市\n" +
                "临汾市\n" +
                "吕梁市\n" +
                "运城市\n" +
                "西安市\n" +
                "咸阳市\n" +
                "延安市\n" +
                "榆林市\n" +
                "渭南市\n" +
                "商洛市\n" +
                "安康市\n" +
                "汉中市\n" +
                "宝鸡市\n" +
                "铜川市\n" +
                "上海市\n" +
                "成都市\n" +
                "攀枝花市\n" +
                "自贡市\n" +
                "绵阳市\n" +
                "南充市\n" +
                "达州市\n" +
                "遂宁市\n" +
                "广安市\n" +
                "泸州市\n" +
                "宜宾市\n" +
                "内江市\n" +
                "资阳市\n" +
                "乐山市\n" +
                "眉山市\n" +
                "德阳市\n" +
                "广元市\n" +
                "天津市\n" +
                "塔城地区\n" +
                "哈密地区\n" +
                "和田地区\n" +
                "阿勒泰地区\n" +
                "克孜勒苏州\n" +
                "博尔塔拉州\n" +
                "克拉玛依市\n" +
                "乌鲁木齐市\n" +
                "伊犁州\n" +
                "石河子市\n" +
                "昌吉州\n" +
                "吐鲁番地区\n" +
                "巴音郭楞州\n" +
                "阿克苏地区\n" +
                "阿拉尔市\n" +
                "喀什地区\n" +
                "西双版纳州\n" +
                "德宏州\n" +
                "昭通市\n" +
                "昆明市\n" +
                "大理州\n" +
                "红河州\n" +
                "曲靖市\n" +
                "保山市\n" +
                "文山州\n" +
                "玉溪市\n" +
                "楚雄州\n" +
                "普洱市\n" +
                "临沧市\n" +
                "怒江州\n" +
                "迪庆州\n" +
                "丽江市\n" +
                "衢州市\n" +
                "杭州市\n" +
                "湖州市\n" +
                "嘉兴市\n" +
                "宁波市\n" +
                "绍兴市\n" +
                "台州市\n" +
                "温州市\n" +
                "丽水市\n" +
                "金华市\n" +
                "舟山市\n" +
                "重庆市\n" +
                "银川市\n" +
                "石嘴山市\n" +
                "吴忠市\n" +
                "中卫市\n" +
                "丹东市\n" +
                "固原市\n" +
                "济源市\n" +
                "赤峰市\n" +
                "三沙市\n" +
                "图木舒克市\n" +
                "五家渠市\n" +
                "香港岛\n" +
                "九龙\n" +
                "新界区\n" +
                "澳门半岛\n" +
                "澳门离岛";
        System.out.println("*******" + getPingYin("西藏"));
//        System.out.println(getPinYinHeadChar(data));
//        System.out.println(getCnASCII("綦江县"));
        String[] arr = data.split("\n");
        System.out.println("+++++1" +arr[0]);
        String result = "";
        for(int i =0;i<arr.length;i++){
            result += "'"+ arr[i]+"|"+getPingYin(arr[i])+"|"+getPinYinHeadChar(arr[i])+"',";
        }
        System.out.println("======2" +result);
//        '北京|beijing|bj'
    }
}