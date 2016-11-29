package com.augmentun.exam.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.augmentun.exam.Constants;
import com.augmentun.exam.util.DateUtil;

public class ExamPagination {

    private int offset;
    private int currentPage;
    private int pageSize;
    private int pageCount;

    //order
    private String order;

    //keyword
    private String keyword;

    //Date
    private String startDate;
    private String endDate;

    public int getOffset() {
        int offset = 0;
        offset = (currentPage - 1) * pageSize;
        if (offset < 0) {
            offset = 0;
        }
        return offset;
    }

    public static int calculatePageCount(int quantity, int pageSize) {
        int pageCount = 1;
        if (quantity % pageSize == 0) {
            pageCount = quantity / pageSize;
        } else {
            pageCount = quantity / pageSize + 1;
        }
        return pageCount;
    }

    //init order
    public static String initOrder(String order) {
        if (order.equals("idASC")) {
            order = "idASC";
        } else if (order.equals("idDESC")) {
            order = "idDESC";
        } else if (order.equals("nameASC")){
            order = "nameASC";
        } else if (order.equals("nameDESC")){
            order = "nameDESC";
        } else if (order.equals("timeASC")){
            order = "timeASC";
        } else if (order.equals("timeDESC")){
            order = "timeDESC";
        } else {
            order = "idASC";
        }
        return order;
    }

    //Init date
    public static String initDate(String dateTime) {
        if (!dateTime.matches(Constants.REG_EFFECTIVE_YEAR)) {
            dateTime = "";
        }
        return dateTime;
    }

    //start date > end date
    public static Map<String, String> initDate(String startDate, String endDate) {
        startDate = initDate(startDate);
        endDate = initDate(endDate);
        Map<String, String> map = new HashMap<String, String>();
        if (startDate != "" && endDate != "") {
            String dateFormat = Constants.DATE_FOMAT_PART;
            Date beforeDate = DateUtil.stringToDate(startDate, dateFormat);
            Date afterDate = DateUtil.stringToDate(endDate, dateFormat);
            if (beforeDate.getTime() > afterDate.getTime()) {
                startDate = "";
                endDate = "";
            }
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }
    //the first or the last page ?
    public boolean isFirstPage() {
        boolean result = false;
        if (currentPage <= 1){
            result = true;
        }
        return result;
    }

    public boolean isLastPage() {
        boolean result = false;
        if (currentPage >= pageCount){
            result = true;
        }
        return result;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
