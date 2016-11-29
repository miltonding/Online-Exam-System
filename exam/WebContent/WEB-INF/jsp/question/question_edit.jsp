<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@page import="com.augmentun.exam.model.Question"%>
<%@page import="com.augmentun.exam.Constants"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>question_edit</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_list.css">
     <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_create.css">
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_edit.js"></script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_validate.js"></script>
  </head>
  <body>
    <div class="wrapper">
    <!-- header -->
      <jsp:include page="../common/header.jsp"/>

      <!-- menu -->
      <div class="menu">
        <ul>
          <li class="first_unseleted" id="question_management">Question Management</li>
          <li class="second_unseleted" id="exam_management">Exam Management</li>
        </ul>
      </div>

      <!-- main -->
      <div class="main">
            <div class="guide">
            <span class="menu">Question Management</span>
            <span class="menu">&gt;<a href="<%=request.getContextPath()%>/page/question/questionList">Question List</a></span>
            <span class="menu">&gt;<a href="#">${QUESTION.questionNumber}</a></span>
          </div>
          <div class="content">
           <!-- FlashMessage -->
          <%String FLASH_MESSAGE = (String)session.getAttribute(Constants.FLASH_MESSAGE); 
            if (StringUtil.validateParam(FLASH_MESSAGE)) {
                session.removeAttribute(Constants.FLASH_MESSAGE);%>
           <div class="red_warning_message" style="display: block;"><%=FLASH_MESSAGE %></div>
           <%} else { %>
                <div class="red_warning_message"></div>
           <%} %>
          <script type="text/javascript">
          setTimeout(function() {
              $(".red_warning_message").hide();
          }, 3000);
          </script>

              <%Question question = (Question)request.getAttribute(Constants.QUESTION);
               String correctChoice = question.getCorrectChoice();
              %>
              <!-- form -->
              <form action="<%=request.getContextPath()%>/page/question/editQuestion" method="post" id="editForm">
                <input type="hidden" name="id" id="question_id"  value="${QUESTION.id}"/>
                <label class="form_label">Question ID:</label>
                   <input type="text" name="questionNumber" id="questionNumber" readonly="readonly" value="${QUESTION.questionNumber}"/>
                <span class="waring_one" id="tip_question_number">${ERROR_MESSAGE.TIP_QUESTION_NUMBER}</span>
                <br/>

                <label class="form_label">Question:</label>
                <textarea name="questionName" id="questionName">${QUESTION.questionName }</textarea>
                <span class="waring_one" id="tip_question_name">${ERROR_MESSAGE.TIP_QUESTION_NAME}</span>
                <br/>

                <label class="form_answer">Answer:</label> 
                <span class="waring_correct" id="tip_correct_choice">${ERROR_MESSAGE.TIP_CORRECT_CHOICE}</span>
                <ul class="choice">

                    <!-- choiceA -->
                    <li class="li_first">
                    <% if (correctChoice.equals("A")) { %>
                    <div class="seleted_radio"></div>
                    <label class="choice_name">A</label>
                    <input type="text" class="seleted_input" name="choiceA" id="choiceA" value="${QUESTION.choiceA}"/>
                    <%} else { %>
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">A</label>
                    <input type="text" class="unseleted_input" name="choiceA" id="choiceA" value="${QUESTION.choiceA}"/>
                    <%} %>
                    <span class="waring_choice" id="tip_choice_a">${ERROR_MESSAGE.TIP_CHOICE_A}</span>
                    </li>

                    <!-- choiceB -->
                    <li class="li_second">
                    <% if (correctChoice.equals("B")) { %>
                    <div class="seleted_radio"></div>
                    <label class="choice_name">B</label>
                    <input type="text" class="seleted_input" name="choiceB" id="choiceB" value="${QUESTION.choiceB }"/>
                    <%} else { %>
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">B</label>
                    <input type="text" class="unseleted_input" name="choiceB" id="choiceB" value="${QUESTION.choiceB }"/>
                    <%} %>
                     <span class="waring_choice" id="tip_choice_b">${ERROR_MESSAGE.TIP_CHOICE_B}</span>
                    </li>

                    <!-- choiceC -->
                    <li class="li_second">
                    <% if (correctChoice.equals("C")) { %>
                    <div class="seleted_radio"></div>
                    <label class="choice_name">C</label>
                    <input type="text" class="seleted_input" name="choiceC" id="choiceC" value="${QUESTION.choiceC }"/>
                    <%} else { %>
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">C</label>
                    <input type="text" class="unseleted_input" name="choiceC" id="choiceC" value="${QUESTION.choiceC }"/>
                    <%} %>
                     <span class="waring_choice" id="tip_choice_c">${ERROR_MESSAGE.TIP_CHOICE_C}</span>
                    </li>

                    <!-- choiceD -->
                    <li class="li_second">
                    <% if (correctChoice.equals("D")) { %>
                    <div class="seleted_radio"></div>
                    <label class="choice_name">D</label>
                    <input type="text" class="seleted_input" name="choiceD" id="choiceD" value="${QUESTION.choiceD }"/>
                    <%} else { %>
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">D</label>
                    <input type="text" class="unseleted_input" name="choiceD" id="choiceD" value="${QUESTION.choiceD }"/>
                    <%} %>
                     <span class="waring_choice" id="tip_choice_d">${ERROR_MESSAGE.TIP_CHOICE_D}</span>
                    </li>
                </ul>

                <input type="hidden" name="correctChoice" id="correctChoice" value="${QUESTION.correctChoice}"/>
                <input type="button" class="btn_create" value="Save" id="edit"/>
                <input type="button" class="btn_cancel"  value="Cancel" id="cancel"/>
            </form>
          </div>

          <!-- main copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>

          <!-- main end -->
        </div>
      <!-- wrapper end -->
    </div>
  </body>
</html>