package com.augmentun.exam.util.paper;

import java.util.List;
public class PaperUtil {

    public static String idListToString(List<Integer> questionIdArr) {
        String idArr = "";
        for (int id : questionIdArr) {
            idArr += id + ",";
        }
        idArr = idArr.substring(0, idArr.length() - 1);
        return idArr;
    }

}
