<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@taglib uri="/WEB-INF/block.tld" prefix="block"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>question_create</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_list.css">
     <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_create.css">
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_create.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_validate.js"> </script>
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
            <span class="menu">&gt;<a href="#">Create Question</a></span>
          </div>
          <div class="content">

              <!-- form -->
              <form action="<%=request.getContextPath()%>/page/question/createQuestion" method="post" id="creatForm">
                <label class="form_label">Question ID:</label>
                    <input type="text" name="questionNumber" id="questionNumber" maxlength="10"/>
                <span class="waring_one" id="tip_question_number">${ERROR_MESSAGE.TIP_QUESTION_NUMBER}</span>
                <br/>

                <label class="form_label">Question:</label>
                    <textarea name="questionName" id="questionName" maxlength="200"></textarea>
                <span class="waring_one" id="tip_question_name">${ERROR_MESSAGE.TIP_QUESTION_NAME}</span>
                <br/>

                <label class="form_label" style="margin-top: 40px;">Answer:</label> 
                <span class="waring_correct" id="tip_correct_choice">${ERROR_MESSAGE.TIP_CORRECT_CHOICE}</span>
                <ul class="choice">
                    <li class="li_first">
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">A</label>
                    <input type="text" class="unseleted_input" name="choiceA" id="choiceA" maxlength="200"/>
                    <span class="waring_choice" id="tip_choice_a">${ERROR_MESSAGE.TIP_CHOICE_A}</span>
                    </li>

                    <li class="li_second">
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">B</label>
                    <input type="text" class="unseleted_input" name="choiceB" id="choiceB" maxlength="200"/>
                     <span class="waring_choice" id="tip_choice_b">${ERROR_MESSAGE.TIP_CHOICE_B}</span>
                    </li>

                    <li class="li_second">
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">C</label>
                    <input type="text" class="unseleted_input" name="choiceC" id="choiceC" maxlength="200"/>
                    <span class="waring_choice" id="tip_choice_c">${ERROR_MESSAGE.TIP_CHOICE_C}</span>
                    </li>

                    <li class="li_second">
                    <div class="unseleted_radio"></div>
                    <label class="choice_name">D</label>
                    <input type="text" class="unseleted_input" name="choiceD" id="choiceD" maxlength="200"/>
                        <span class="waring_choice" id="tip_choice_d">${ERROR_MESSAGE.TIP_CHOICE_D}</span>
                    </li>
                </ul>

                <input type="hidden" name="correctChoice" id="correctChoice"/>
                <input type="button" class="btn_create" value="Create" id="create"/>
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