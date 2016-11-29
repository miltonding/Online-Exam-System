package com.augmentun.exam.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static boolean validateParam(String param) {
        if (param == null || "".equals(param)) {
            return false;
        } else {
            return true;
        }
    }

    public static String htmlEncode(String txt) {
        if (txt != null || !"".equals(txt)) {
            txt = txt.replace("&", "&amp;");
            txt = txt.replace("&amp;amp;", "&amp;");
            txt = txt.replace("&amp;quot;", "&quot;");
            txt = txt.replace("\"", "&quot;");
            txt = txt.replace("&amp;lt;", "&lt;");
            txt = txt.replace("<", "&lt;");
            txt = txt.replace("&amp;gt;", "&gt;");
            txt = txt.replace(">", "&gt;");
            txt = txt.replace("&amp;nbsp;", "&nbsp;");
        }
        return txt;
    }

    //Deal with '%', '\', '_', '\%', '''
    public static String dealWithSpecialCharacter(String keyword) {
        if (!StringUtil.validateParam(keyword)) {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        char[] keyArray = keyword.toCharArray();
        for (char key: keyArray) {
            String perKey = String.valueOf(key);
            if (perKey.equals("\\")) {
                perKey = "\\\\";
            }
            if (perKey.equals("%")) {
                perKey = "\\%";
            }
            if (perKey.equals("_")) {
                perKey = "\\_";
            }
            stringBuffer.append(perKey);
        }
        return stringBuffer.toString();
    }

    public static String encodingToUtf8(String txt) {
        try {
            txt = new String(txt.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static boolean parseIntSuccess(String id) {
        boolean result = true;
        if (id.equals("")) {
            result = false;
        }

        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            result = false;
        }
        return result;

    }

}
