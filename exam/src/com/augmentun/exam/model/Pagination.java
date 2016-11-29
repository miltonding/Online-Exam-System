package com.augmentun.exam.model;
public class Pagination {
    //input
    private int totalCount;
    private int pageSize;
    private int currentPage;
    private String order;
    private String keyword;
    //calculate
    private int pageCount;
    private int offset;

    public Pagination() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Pagination(int totalCount, int pageSize, String order, String keyword, int currentPage) {
        super();
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.order = order;
        this.keyword = keyword;
        this.currentPage = currentPage;
    }

    //Init params
    public static int initPageSize(String stringPageSize) {
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(stringPageSize);
        } catch (Exception e) {
            pageSize = 10;
        }
        if (pageSize > 10 || pageSize <= 0) {
            pageSize = 10;
        }
        return pageSize;
    }

    public static int initCurrentPage(String stringCurrentPage) {
        int currentPage = 0;
        try {
            currentPage = Integer.parseInt(stringCurrentPage);
        } catch (Exception e) {
            currentPage = 1;
        }
        return currentPage;
    }

    public static String initOrder(String order) {
        if (order.equals("DESC")) {
            order = "DESC";
        } else {
            order = "ASC";
        }
        return order;
    }

    //calculate
    public int getPageCount() {
        if (totalCount == 0) {
            pageCount = 0;
        } else {
            if (totalCount % pageSize == 0){
                pageCount = totalCount / pageSize;
            } else {
                pageCount = totalCount / pageSize + 1;
            }
        }
        return pageCount;
    }

    public int getOffset() {
        offset = (currentPage - 1) * pageSize;
        if (offset < 0) {
            offset = 0;
        }
        return offset;
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

    // common
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public void setOffset(int offset) {
        this.offset = offset;
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

}
