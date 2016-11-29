<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.model.Pagination"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="com.augmentun.exam.model.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@taglib prefix="res" uri="http://www.springframework.org/tags"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/block.tld" prefix="block"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>question_list</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_list.css">
    <!-- JS -->
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"></script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_list.js"></script>
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
    <form id="paging_form" action="<%=request.getContextPath()%>/page/question/questionList" method="GET">
        <input type="hidden" name="currentPage" id="paging_currentPage" value="${PAGINATION.currentPage}"/>
        <input type="hidden" name="order" id="paging_order" value="${PAGINATION.order}"/>
        <input type="hidden" name="pageSize" id="paging_pageSize" value="${PAGINATION.pageSize}"/>
        <input type="hidden" name="keyword" id="paging_keyWord" value="${PAGINATION.keyword}"/>
    </form>

    <div class="wrapper">
    <!-- header -->
    <jsp:include page="../common/header.jsp"/>

      <!-- menu -->
      <div class="menu">
        <ul>
          <li class="first_seleted" id="question_management"><res:message code="oes.question.management"/></li>
          <li class="second_unseleted" id="exam_management"><res:message code="oes.exam.management"/></li>
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
              session.removeAttribute(Constants.FLASH_MESSAGE);%>
           <div class="flash_message"><%=FLASH_MESSAGE%></div>
           <%} else { %>
           <div class="flash_message" style="display: none"></div>
           <%} %>
           <script type="text/javascript">
           setTimeout(function() {
               $(".flash_message").css("display", "none");
           }, 3000);
           </script>
              <input type="text" class="search_box" id="search_box" value="${PAGINATION.keyword}" placeholder="Please input the keyword" maxlength="10"/>
              <img class="serch_icon"  id="serch_icon" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Search_15x20.png.png">
            <div class="left">
              <ul>
                <li class="li_selected" id="question_list"><res:message code="oes.question.list"/></li>
                <li class="li_unselected" id="question_create"><res:message code="oes.create.question"/></li>
              </ul>
            </div>

            <!-- no results -->
            <c:if test="${empty QUESTION_LIST}">
              <script type="text/javascript">
              $(".flash_message").html("no matched results");
              $(".flash_message").show();
              setTimeout(function() {
                  $(".flash_message").html("");
                  $(".flash_message").hide();
              }, 3000);
              </script>
            </c:if>

           <block:display name="listQuestionBlock"/>
          </div>
          <!-- copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>
      </div>
    </div>
  </body>
</html>