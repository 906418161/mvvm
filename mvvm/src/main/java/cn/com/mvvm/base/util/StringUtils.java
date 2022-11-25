package cn.com.mvvm.base.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * 判断字符串不为空
     *
     * @param str 字符串
     * @return true 不为空 false 为空
     */
    public static boolean isEmpty(String str) {
        boolean result = true;
        if (str != null && !"".equals(str) && !"null".equals(str))
            result = false;
        return result;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     * @return int 得到的字符串长度
     */
    public static double getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return  Math.ceil(valueLength);
    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 去除首尾空格
     * @return
     */
    public static String removeSpaces(String str){
        if (!isEmpty(str)&&!str.trim().equals("")){
            if (str.substring(0,1).equals(" ")){
                str = str.substring(1,str.length());
            }
            if (str.substring(str.length()-1,str.length()).equals(" ")){
                str = str.substring(0,str.length()-1);
            }
        }
        return str;
    }

    /**
     * 实现语句中的关键词变色
     * @param context  上下文
     * @param name     语句
     * @param key      关键字
     * @return  得到关键字变色后的语句
     */
    public static SpannableString setColor(Context context, int color, String name, String key){
        //key = "关键字";//关键词
        //name = "我不是关键字，他才是关键字";//语句
        SpannableString s = new SpannableString(name);
        Pattern p = Pattern.compile(key);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

}
