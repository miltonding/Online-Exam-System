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
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/exam/exam_create.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/exam/exam_validate.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/exam/exam_edit.js"></script>
    
  </head>
  <body>
    <div class="disablie_screen" id="exam_disablie_screen"></div>

    <div class="delete_confirm" id="exam_edit_confirm">
        <div class="warning_words">Sorry, the questions are not enough in the system!</div>
        <img id="close_img"src="<%=PropertyUtil.getStaticUrl()%>/images/BTN_Close_16x16.png.png"></img>
        <div class="ok_btn" id="ok_btn">OK</div>
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

          <div class="content">
          <form action="<%=request.getContextPath()%>/page/exam/editExam" method="post" id="edit_form">
               <input type="hidden" name="id" value="${EXAM.id}"/>
               <ul class="show_ul">
                 <li class="li_common">
                   <div class="show_label">Exam Name:*</div>
                   <div class="show_content">
                     <input type="text" class="exam_name" name="examName" value="${EXAM.examName}"/>
                   </div>
                   <div id="tip_exam_name">${ERROR_MESSAGE.tip_exam_name}</div>
                 </li>

                 <li class="li_description">
                     <div class="label_description">Description:</div>
                     <div class="content_description">
                      <textarea class="textarea_description" name="description">${EXAM.description}</textarea>
                     </div>
                     <div id="tip_exam_description">${ERROR_MESSAGE.tip_exam_description}</div>
                 </li>
                 <li class="li_common">
                     <div class="show_label">Effective Time:*</div>
                     <div class="show_content">
                      <input type="datetime-local" class="text_date" name="effcientTime" value="${EXAM.effcientTime}" readonly="readonly"/>
                     </div>
                     <div id="tip_exam_time">${ERROR_MESSAGE.tip_exam_time}</div>
                 </li>
                 <li class="li_common">
                     <div class="show_label">Duration:*</div>
                     <div class="show_content">
                      <select class="select_duration" name="stringExamDuration" >
                      <% String choiceOne = "";
                         String choiceTwo = "";
                         String choiceThree = "";
                         String choiceFour = "";
                         String choiceFive = "";
                         String choiceSix = "";
                         Exam exam = (Exam)request.getAttribute(Constants.EXAM); 
                            if (exam != null) {
                                int duration = exam.getExamDuration();
                                if (duration == 30) {
                                    choiceOne = "selected='seleted'";
                                }
                                if (duration == 60) {
                                    choiceTwo = "selected='seleted'";
                                }
                                if (duration == 90) {
                                    choiceThree = "selected='seleted'";
                                }
                                if (duration == 120) {
                                    choiceFour = "selected='seleted'";
                                }
                                if (duration == 150) {
                                    choiceFive = "selected='seleted'";
                                }
                                if (duration == 180) {
                                    choiceSix = "selected='seleted'";
                                }
                            }
                                %>
                        <option></option>
                        <option <%=choiceOne%>>30</option>
                        <option <%=choiceTwo%>>60</option>
                        <option <%=choiceThree%>>90</option>
                        <option <%=choiceFour%>>120</option>
                        <option <%=choiceFive%>>150</option>
                        <option <%=choiceSix%>>180</option>
                      </select>
                      <font>min</font>
                     </div>
                     <div id="tip_exam_duration">${ERROR_MESSAGE.tip_exam_duration}</div>
                 </li>
                 <li class="li_settings">
                     <div class="label_settings">Question Setting:</div>
                     <div class="content_settings">
                      <div class="settings_quantity">Question Quantity</div>
                      <div class="quantity_input">
                          <input type="text" class="settings_text" id="question_quantity" name="stringExamQuantity" value="${EXAM.quantity}"/>
                          <%//warning message
                            Map<String, String> errorMessage = (Map<String, String>)request.getAttribute(Constants.ERROR_MESSAGE);
                            String exam_quantity_class = "class='waring_img_hide'";
                            String exam_points_class = "class='waring_img_hide'";
                            String total_score_class = "class='waring_img_hide'";
                            String exam_criteria_class = "class='waring_img_hide'";
                             if (errorMessage != null && !errorMessage.isEmpty()) {
                                 //quantity
                                 String tip_exam_quantity = errorMessage.get("tip_exam_quantity");
                                   if (StringUtil.validateParam(tip_exam_quantity)) {
                                     exam_quantity_class = "class='waring_img_show'";
                                   }
                                  //points 
                                  String tip_exam_points = errorMessage.get("tip_exam_points");
                                  if (StringUtil.validateParam(tip_exam_points)) {
                                    exam_points_class = "class='waring_img_show'";
                                    }
                                  //total_score
                                  String tip_total_score = errorMessage.get("tip_total_score");
                                  if (StringUtil.validateParam(tip_total_score)) {
                                    total_score_class = "class='waring_img_show'";
                                 }
                                  
                                  String tip_exam_criteria = errorMessage.get("tip_exam_criteria");
                                  if (StringUtil.validateParam(tip_exam_criteria)) {
                                    exam_criteria_class = "class='waring_img_show'";
                                 }
                             }
                          %>
                           <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_exam_quantity" <%=exam_quantity_class%> >
                      </div>

                      <div class="settings_points">Question Points</div>
                      <div class="points_input">
                          <input type="text" class="settings_text" id="question_points" name="stringExamPoints" value="${EXAM.singleQuestionScore}"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_exam_points" <%=exam_points_class %>>
                      </div>

                      <div class="settings_score">Total Score</div>
                      <div class="score_input">
                          <input type="text" class="settings_text" id="total_score" name="stringExamScore" value="${EXAM.totalScore}" readonly="readonly"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_total_score" <%=total_score_class%>>
                      </div>
                      <div class="settings_criteria">Pass Criteria</div>
                      <div class="criteria_input">
                          <input type="text" class="settings_text" id="pass_criteria" name="stringExamCriteria" value="${EXAM.passCriteria}"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_exam_criteria" <%=exam_criteria_class%>>
                      </div>

                     </div>
                 </li>
               </ul>
            </form>

             <input type="button" class="exam_create_btn" value="Save" id="save"/>
             <input type="button" class="exam_cancel_btn"  value="Cancel" id="cancel"/>
          </div>

          <!-- main copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>
          <!-- main end -->
       </div>
      <!-- wrapper end -->
    </div>
  </body>
</html>