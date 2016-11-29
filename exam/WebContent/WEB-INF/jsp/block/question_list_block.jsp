<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.model.Pagination"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="com.augmentun.exam.model.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.augmentun.exam.Constants"%>
 <div class="list" id="question_list">
    <div class="title_id">ID</div>
    <% Pagination PAGINATION = (Pagination)request.getAttribute(Constants.PAGINATION);
      int currentpage = PAGINATION.getCurrentPage();
      int pageSize = PAGINATION.getPageSize();
      String order = PAGINATION.getOrder();
      if (order.equals("ASC")) {
    %>
    <div class="up" id="up_down"></div>
    <%} else {%>
    <div class="down" id="up_down"></div>
    <%} %>
    <div class="title_description" id="change_order">Description</div>
    <div class="title_edit" >Edit</div>
    <div class="list_unSeleted" id="check_all"></div>
    <div id="bottom_line"></div>

    <!-- list_show -->
    <div class="show_list" >
    <!-- delete Form -->
      <form action="<%=request.getContextPath()%>/page/question/deleteQuestion" method="POST" id="delete_form">
          <ul> 
            <%  List<Question> questionList = (List<Question>)request.getAttribute("QUESTION_LIST"); 
                int count = 0;
                if (questionList != null) {
                    for (Question question : questionList) {
                        count++;
            %>
            <li>
              <span class="column_index"><%=count%></span>
              <a href="<%=request.getContextPath()%>/page/question/queryQuestion?id=<%=question.getId()%>"><span class="column_one"><%=question.getquestionNumber()%></span></a>
              <span class="column_two"><a href="<%=request.getContextPath()%>/page/question/queryQuestion?id=<%=question.getId()%>" title="<%=question.getQuestionName()%>"><%=question.getQuestionName()%></a></span>
              <a href="<%=request.getContextPath()%>/page/question/confirmQuestion?confirmId=<%=question.getId()%>"><img class="list_edit" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Edit_15x15.png.png"></a>
              <label class="delete_unSeleted custom_checkbox"></label>
              <input type="checkbox" name="deleteArray" class="hide_checkbox" value="<%=question.getId()%>"/>
            </li>
           <%   } 
             }
           %>  
          </ul>

        </form>

      <!-- paging -->
      <div class="page_wrapper">
      <!-- previous page -->

        <%if (PAGINATION.isFirstPage()) {%> 
             <img class="pre_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageLeft_20x15.png.png"/>
        <%   
        } else {%>
        <a href="javascript:void(0);" id="previous_page">
        <img class="pre_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageLeft_20x15.png.png"/>
        </a>
        <% } %>

        <!-- pageIndex -->
        <% Pagination pagination = (Pagination)request.getAttribute("PAGINATION");
           int currentPage = pagination.getCurrentPage();
           if (currentPage <= 0) {
         %>
         <a href="#"><span id="current_page">1</span></a>

        <%  } else {%>
          <a href="#"><span id="current_page">${PAGINATION.currentPage}</span></a>
        <% }
           int pageCount = pagination.getPageCount();
           for (int i = currentPage+1;i <= currentPage+2;i++  ) {
                 if (i <= pageCount) {
           %>
                <span class="page_count"><%=i%></span>
          <%    } 
            }%>
        <span class="page_omit">...</span>
         <% if (pageCount <= 0) {
         %>
          <span class="page_count">1</span>
        
        <%  } else {%>
          <span class="page_count">${PAGINATION.pageCount}</span>
        <% } %>
         
        <!-- next page -->
         <%if (PAGINATION.isLastPage()) {%> 
             <img class="nxt_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageRight_20x15.png .png"/>
        <%   
        } else {%>
        <a href="javascript:void(0);" id="next_page">
        <img class="nxt_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageRight_20x15.png .png"/>
        </a>
        <% } %>
        <span id="select_block">

          <select id="select_size">
        <%for (int i = 1;i <= 10; i++) {
              if (pageSize == i) {%>
                  <option selected="selected"><%=pageSize%></option>
        <%    } else {%>
                  <option ><%=i%></option>
        <%    } 
          }%>
          </select> 

        </span>
        <label class="per_page">Per page</label>
        <input type="text" id="goal_page">
        <button class="go_btn" id="go_btn">Go</button>
        </div>
      </div>
      <input type="button" id="del_btn" value="Delete"/>
    <!-- list end -->  
    </div>
