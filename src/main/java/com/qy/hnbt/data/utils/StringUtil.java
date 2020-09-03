package com.qy.hnbt.data.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static Integer subStringCode(String str){
        String resultStr = null;
        if(str != null ) {
            resultStr = !str.isEmpty() ? str.split("-")[0] : null;
        }
        return resultStr == null?null:Integer.valueOf(resultStr);
    }
    public static String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("\\s*|\t|\r|\n", "");
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 驼峰转下划线 */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String secretIDN(String idn) {
        idn = trim(idn);
        if (idn == null) {
            return null;
        } else if ("".equals(trim(idn))) {
            return null;
        } else if (idn.length() == 15) {
            return idn.substring(0, 6) + "******" + idn.substring(12, 15);
        } else if (idn.length() == 18) {
            return idn.substring(0, 6) + "********" + idn.substring(14, 18);
        } else {
            return "身份证号码位数错误";
        }
    }

    public static String secretBankCard(String bankCard) {
        bankCard = trim(bankCard);
        int length = bankCard.length();
        if (length > 10) {
            return bankCard.substring(0, 6) + getLengthStr(length - 10) + bankCard.substring(length - 4, length);
        } else {
            return "不合格的银行卡/存折号";
        }
    }

    public static String secretMoblie(String moblie) {
        moblie = trim(moblie);
        int length = moblie.length();
        if (length > 7) {
            return moblie.substring(0, 3) + getLengthStr(length - 7) + moblie.substring(length - 4, length);
        } else {
            return "不合格的银行卡/存折号";
        }
    }

    private static String getLengthStr(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "*";
        }
        return result;
    }
}
