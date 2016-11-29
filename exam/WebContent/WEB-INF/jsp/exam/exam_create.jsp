<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.augmentun.exam.model.Exam"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@page import="com.augmentun.exam.model.eto.ExamETO"%>
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
      <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/exam/exam_create.js"></script>
  </head>
  <body>
    <div class="disablie_screen" id="exam_disablie_screen"></div>
    <!-- warning message -->  
    <%String warning_class = "";
      String warningMesage = (String)request.getAttribute(Constants.FLASH_MESSAGE); 
      if (StringUtil.validateParam(warningMesage)) {
          warning_class = "style='display:block;'";
      }
     %>
    <div class="delete_confirm" id="exam_delete_confirm" <%=warning_class%>>
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

          <div class="content">
          <form action="<%=request.getContextPath()%>/page/exam/createExam" method="post" id="create_form">
               <ul class="show_ul">

                 <li class="li_common">
                   <div class="show_label">Exam Name:*</div>
                   <div class="show_content">
                     <input type="text" class="exam_name" name="examName" value="${EXAMETO.examName}"/>
                   </div>
                   <div id="tip_exam_name">${ERROR_MESSAGE.tip_exam_name}</div>
                 </li>

                 <li class="li_description">
                     <div class="label_description">Description:</div>
                     <div class="content_description">
                      <textarea class="textarea_description" name="description">${EXAMETO.description}</textarea>
                     </div>
                     <div id="tip_exam_description">${ERROR_MESSAGE.tip_exam_description}</div>
                 </li>
                 <li class="li_common">
                     <div class="show_label">Effective Time:*</div>
                     <div class="show_content">
                      <input type="datetime-local" class="text_date" name="effcientTime" value="${EXAMETO.effcientTime}"/>
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
                         ExamETO examETO = (ExamETO)request.getAttribute(Constants.EXAMETO); 
                            if (examETO != null) {
                                String stringExamDuration = examETO.getStringExamDuration();
                                if (stringExamDuration.equals("30")) {
                                    choiceOne = "selected='seleted'";
                                }
                                if (stringExamDuration.equals("60")) {
                                    choiceTwo = "selected='seleted'";
                                }
                                if (stringExamDuration.equals("90")) {
                                    choiceThree = "selected='seleted'";
                                }
                                if (stringExamDuration.equals("120")) {
                                    choiceFour = "selected='seleted'";
                                }
                                if (stringExamDuration.equals("150")) {
                                    choiceFive = "selected='seleted'";
                                }
                                if (stringExamDuration.equals("180")) {
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
                          <input type="text" class="settings_text" id="question_quantity" name="stringExamQuantity" value="${EXAMETO.stringExamQuantity}"/>
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
                          <input type="text" class="settings_text" id="question_points" name="stringExamPoints" value="${EXAMETO.stringExamPoints}"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_exam_points" <%=exam_points_class %>>
                      </div>

                      <div class="settings_score">Total Score</div>
                      <div class="score_input">
                          <input type="text" class="settings_text" id="total_score" name="stringExamScore" value="${EXAMETO.stringExamScore}" readonly="readonly"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_total_score" <%=total_score_class%>>
                      </div>
                      <div class="settings_criteria">Pass Criteria</div>
                      <div class="criteria_input">
                          <input type="text" class="settings_text" id="pass_criteria" name="stringExamCriteria" value="${EXAMETO.stringExamCriteria}"/>
                          <img src="/exam/static/images/ICN_Client_Login_Wrong_20X20.png" id="tip_exam_criteria" <%=exam_criteria_class%>>
                      </div>

                     </div>
                 </li>
               </ul>
            </form>

             <input type="button" class="exam_create_btn" value="Create" id="create"/>
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