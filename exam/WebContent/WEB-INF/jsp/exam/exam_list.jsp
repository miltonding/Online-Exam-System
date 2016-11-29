<%@page import="com.augmentun.exam.model.Exam"%>
<%@page import="com.augmentun.exam.model.ExamPagination"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>exam_list</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/exam/exam_common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/exam/exam_list.css">
    <!-- JS -->
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"></script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/exam/exam_list.js"></script>
  </head>

  <body>
     <div class="disablie_screen"></div>
    <div class=delete_confirm>
        <div class="warning_message">Are you sure delete the seleted options?</div>
        <img id="close_img"src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_Close_16x16.png.png"></img>
        <div class="confirm_btn">Yes</div>
        <div class="cancel_btn">No</div>
    </div>

    <!-- paging Form -->
     <form id="paging_form" action="<%=request.getContextPath()%>/page/exam/listExam" method="GET">
         <input type="hidden" name="order" id="page_order" value="${PAGINATION.order}"/>
         <input type="hidden" name="keyword" id="keyword" value="${PAGINATION.keyword}"/>
         <input type="hidden" name="pageSize" id="pageSize" value="${PAGINATION.pageSize}"/>
         <input type="hidden" name="currentPage" id="currentPage" value="${PAGINATION.currentPage}"/>
         <input type="hidden" name="startDate" id="startDate" value="${PAGINATION.startDate}"/>
         <input type="hidden" name="endDate" id="endDate" value="${PAGINATION.endDate}"/>
     </form>

    <div class="wrapper">
    <!-- header -->
    <jsp:include page="../common/header.jsp"/>

      <!-- menu -->
      <div class="menu">
        <ul>
          <li class="first_unseleted" id="menu_question">Question Management</li>
          <li class="second_seleted" id="menu_exam">Exam Management</li>
        </ul>
      </div>

      <!-- main -->
      <div class="main">
          <div class="content">
          <div class="warning_delete">
            <div class="warning_message">please choose one to delete</div>
            <input type="button" class="warning_confrim" value="OK" id="rechoose"/>
          </div>

          <div class="warning_input">
            <div class="warning_message">please input correct number!</div>
            <input type="button" class="warning_confrim" value="OK" id="reinput"/>
          </div>

          <!-- flash message -->
          <% String FLASH_MESSAGE = (String)session.getAttribute(Constants.FLASH_MESSAGE);
             if (StringUtil.validateParam(FLASH_MESSAGE)) {
              session.removeAttribute(Constants.FLASH_MESSAGE);
          %>
           <div class="flash_message"><%=FLASH_MESSAGE%></div>
           <%} else { %>
           <div class="flash_message" style="display: none"></div>
           <%} %>
           <script type="text/javascript">
           setTimeout(function() {
               $(".flash_message").css("display", "none");
            }, 3000);
           </script>
              <input type="text" class="search_box" id="search_box" value="${PAGINATION.keyword}" placeholder="Please input the keyword"/>
              <img class="serch_icon" id="serch_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Search_15x20.png.png">
             <div class="date_area">
                <input type="date" class="start_date" value="${PAGINATION.startDate}"/>
                <font class="line_between">-</font>
                <input type="date" class="end_date" value="${PAGINATION.endDate}"/>
                <div class="date_search">Date</div>
            </div>

            <!-- left -->
            <div class="left">
              <ul>
                <li class="li_selected" id="exam_list">Exam List</li>
                <li class="li_unselected" id="exam_create">Create Exam</li>
              </ul>
            </div>

           <!-- list start -->
           <div class="list" id="question_list">

           <!-- title -->
           <ul class="title" id="list_title">
              <%String idOrderClass = "id_up";
                String idOrderStyle = "";
                String nameOrderClass = "name_up";
                String nameOrderStyle = "";
                String timeOrderClass = "time_up";
                String timeOrderStyle = "";
                ExamPagination pagination = (ExamPagination)request.getAttribute(Constants.PAGINATION);
                String order = pagination.getOrder();
                if (StringUtil.validateParam(order)) {
                    if (order.equals("idASC")) {
                        idOrderClass = "id_up";
                        idOrderStyle = "style='color: green;'";
                    } else if (order.equals("idDESC")) {
                        idOrderClass = "id_down";
                        idOrderStyle = "style='color: green;'";
                    } else if (order.equals("nameASC")) {
                        nameOrderClass = "name_up";
                        nameOrderStyle = "style='color: green;'";
                    } else if (order.equals("nameDESC")) {
                        nameOrderClass = "name_down";
                        nameOrderStyle = "style='color: green;'";
                    } else if (order.equals("timeASC")) {
                        timeOrderClass = "time_up";
                        timeOrderStyle = "style='color: green;'";
                    } else if (order.equals("timeDESC")) {
                        timeOrderClass = "time_down";
                        timeOrderStyle = "style='color: green;'";
                    }
                }
              %>
              <li class="title_id" <%=idOrderStyle%>>ID</li>
              <li class="<%=idOrderClass%>" id="id_order"></li>
              <li class="title_name" <%=nameOrderStyle%>>Name</li>
              <li class="<%=nameOrderClass%>" id="name_order"></li>
              <li class="effective_time" id="change_order" <%=timeOrderStyle%>>Effective Time</li>
              <li class="<%=timeOrderClass%>" id="time_order"></li>
              <li class="duration" >Duration(Mins)</li>
              <li class="creator" >Creator</li>
              <li class="title_edit" >Edit</li>
              <li class="list_unSeleted" id="check_all"></li>
            </ul>
            <div id="bottom_line"></div>

            <!-- list_show -->
            <div class="show_list" >
            <!-- delete Form -->
               <form action="<%=request.getContextPath()%>/page/exam/deleteExam" method="POST" id="delete_form">
                   <ul> 
                     <c:if test="${!empty EXAM_LIST}">
                          <c:forEach items="${EXAM_LIST}" var="exam" varStatus="status">
                              <li>
                                <span class="column_index">${status.index + 1}</span>
                                <a href="<%=request.getContextPath()%>/page/exam/queryExam?id=${exam.id}"><span class="column_one">${exam.showId}</span></a>
                                <span class="column_name"><a href="<%=request.getContextPath()%>/page/exam/queryExam?id=${exam.id}" title="${exam.examName}">${exam.examName}</a></span>
                                <span class="column_time">${exam.effcientTime}</span>
                                <span class="column_duration">${exam.examDuration}</span>
                                <span class="column_creator">${exam.creatorName}</span>
                                <c:if test="${exam.canBeEdit == 1 || exam.isDraft == 1}">
                                  <a href="<%=request.getContextPath()%>/page/exam/confirmExam?id=${exam.id}"><img class="list_edit" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Edit_15x15.png.png"></a>
                                </c:if>
                                <label class="delete_unSeleted delete_checkbox"></label>
                                <input type="checkbox" name="deleteArray" class="hide_checkbox" value="${exam.id}"/>
                              </li>
                          </c:forEach>
                     </c:if>

                     <c:if test="${empty EXAM_LIST}">
                       <script type="text/javascript">
                       $(".flash_message").html("no matched results");
                       $(".flash_message").show();
                       setTimeout(function() {
                    	   $(".flash_message").hide();
                       }, 3000);
                       </script>
                     </c:if>
                    </ul>
                </form>

              <!-- paging -->
              <div class="page_wrapper">
              <!-- previous page -->

                <%if (pagination.isFirstPage()) {%> 
                   <img class="pre_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageLeft_20x15.png.png"/>
                <%   
                 } else {%>
                <a href="javascript:void(0);" id="previous_page">
                <img class="pre_page_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_PageLeft_20x15.png.png"/>
                </a>
                <% } %>

                 <!-- pageIndex -->
                  <%int currentPage = pagination.getCurrentPage();
                    if (currentPage <= 0) {
                  %>
                 <a href="#"><span id="current_page">1</span></a>

                  <% } else {%>
                  <a href="#"><span id="current_page">${PAGINATION.currentPage}</span></a>
                  <% }
                   int pageCount = pagination.getPageCount();
                   int pageSize = pagination.getPageSize();
                   for (int i = currentPage+1;i <= currentPage+2;i++  ) {
                       if (i <= pageCount) {%>
                           <span class="page_count"><%=i%></span>
                   <%   } 
                    } %>
                  <span class="page_omit">...</span>
                  <% if (pageCount <= 0) {%>
                  <span class="page_count">1</span>
                  <% } else {%>
                  <span class="page_count">${PAGINATION.pageCount}</span>
                  <% } %>

                 <!-- next page -->
                 <%if (pagination.isLastPage()) {%> 
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
          </div>

          <!-- copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>
      </div>

    </div>
  </body>
</html>