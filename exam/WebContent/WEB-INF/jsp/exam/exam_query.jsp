<%@page import="com.augmentun.exam.model.Exam"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>exam_create</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/exam/exam_common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/exam/exam_query.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/exam/exam_validate.js"></script>
  </head>
  <body>
    <div class="disablie_screen" id="exam_disablie_screen"></div>

    <div class="delete_confirm" id="exam_delete_confirm">
        <div class="warning_words">${FLASH_MESSAGE}</div>
        <img id="close_img"src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_Close_16x16.png.png"></img>
        <div class="confirm_btn" id="save_btn">Save</div>
        <div class="cancel_btn" id="cancel_btn">Cancel</div>
    </div>

    <div class="wrapper">

    <!-- header -->
       <jsp:include page="../common/header.jsp"/>

      <!-- menu -->
      <div class="menu">
        <ul>
         <li class="first_unseleted"><a href="<%=request.getContextPath()%>/page/question/questionList">Question Management</a></li>
          <li class="second_unseleted"><a href="<%=request.getContextPath()%>/page/exam/listExam">Exam Management</a></li>
        </ul>
      </div>

      <!-- main -->
      <div class="main">
          <div class="guide">
            <span class="menu">Exam Management</span>
            <span class="menu">&gt;<a href="<%=request.getContextPath()%>/page/exam/showCreateExam">Create Exam</a></span>
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

          <div class="content">
               <ul class="show_ul">

                 <li class="li_common">
                   <div class="show_label">Exam Name:*</div>
                   <div class="show_content">
                     <div class="exam_name">${EXAM.examName}</div>
                   </div>
                 </li>

                 <li class="li_description">
                     <div class="label_description">Description:</div>
                     <div class="content_description">
                          ${EXAM.description}
                     </div>
                 </li>
                 <li class="li_common">
                     <div class="show_label">Effective Time:*</div>
                     <div class="show_content">
                       <label id="time_show">${EXAM.effcientTime}</label>
                       <label id="show_creator">creator</label>
                       <label id="creator_name">${EXAM.creatorName}</label>
                     </div>
                 </li>
                 <li class="li_common">
                     <div class="show_label">Duration:*</div>
                     <div class="show_content">
                        <div class="duration_show" >
                            ${EXAM.examDuration}&nbsp;min
                        </div>
                     </div>
                 </li>
                 <li class="li_settings">
                     <div class="label_settings">Question Setting:</div>
                     <div class="content_settings">
                      <div id="question_quantity">
                          <font>Question Quantity</font>
                          <div class="show_information">${EXAM.quantity}</div>
                      </div>

                      <div id="question_points" >
                        <font>Question Points</font>
                        <div class="show_information">${EXAM.singleQuestionScore}</div>
                      </div>
                      
                      <div id="total_score">
                        <font>Total Score</font>
                        <div class="show_information">${EXAM.totalScore}</div>
                      </div>

                      <div id="pass_criteria">
                        <font>Pass Criteria</font>
                        <div class="show_information">${EXAM.passCriteria}</div>
                      </div>
                      <!-- warning effective -->
                     <c:if test="${EXAM.canBeEdit == 0 && EXAM.isDraft == 0}">
	                      <div id="warning_effective">
	                        The exam is effective
	                      </div>
                      </c:if>
                   </div>
                 </li>
               </ul>
             <c:if test="${EXAM.canBeEdit == 1 || EXAM.isDraft == 1}">
               <a href="<%=request.getContextPath()%>/page/exam/confirmExam?id=${EXAM.id}"><input type="button" class="can_edit_btn" value="Edit"/></a>
             </c:if>
             <c:if test="${EXAM.canBeEdit == 0 && EXAM.isDraft == 0}">
               <input type="button" class="cannot_edit_btn" value="Edit"/>
             </c:if>
             <a href="<%=request.getContextPath()%>/page/exam/listExam">
               <input type="button" class="exam_cancel_btn" value="Cancel" id="cancel"/>
             </a>

          </div>

          <!-- main copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>
          <!-- main end -->
       </div>
      <!-- wrapper end -->
    </div>
  </body>
</html>