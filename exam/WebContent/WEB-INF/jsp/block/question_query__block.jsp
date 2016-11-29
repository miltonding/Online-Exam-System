<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@page import="com.augmentun.exam.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
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
                session.removeAttribute(Constants.FLASH_MESSAGE);
          %>
           <div class="flash_message"><%=FLASH_MESSAGE %></div>
           <%
            } else { %>
            <div class="flash_message" style="display: none;"></div>
           <%   } %>

          <script type="text/javascript">
              setTimeout(function() {
                 $(".flash_message").css("display", "none");
               }, 3000);
          </script>
            <ul id="query_ul">
                <li>
                    <label>Question ID:</label>
                    <label class="label_field">${QUESTION.questionNumber}</label>
                </li>
        
                <li style="margin-top: 30px;">
                    <label>Question:</label>
                    <label class="label_field">${QUESTION.questionName}</label>
                </li>
        
                <label class="answer">Answer:</label>
                <%Question question = (Question)request.getAttribute(Constants.QUESTION); 
                  String correctChoice = question.getCorrectChoice();
                     if (correctChoice.equals("A")){%>
                         <li class="strength_li">
                     <%} else {%> 
                         <li class="choice_li">
                     <%} %>
                              <label class="label_choice">A&nbsp;${QUESTION.choiceA}</label>
                         </li>
        
                     <%if (correctChoice.equals("B")){%>
                           <li class="strength_li">
                     <%} else {%> 
                           <li class="choice_li">
                     <%} %>
                                <label class="label_choice">B&nbsp;${QUESTION.choiceB}</label>
                           </li>
        
                     <%if (correctChoice.equals("C")){%>
                           <li class="strength_li">
                     <%} else {%> 
                           <li class="choice_li">
                     <%} %>
                               <label class="label_choice">C&nbsp;${QUESTION.choiceC}</label>
                           </li>
        
                     <%if (correctChoice.equals("D")){%>
                           <li class="strength_li">
                     <%} else {%> 
                           <li class="choice_li">
                     <%} %>
                               <label class="label_choice">D&nbsp;${QUESTION.choiceD}</label>
                           </li>
                </ul>
             <a href="<%=request.getContextPath()%>/page/question/confirmQuestion?confirmId=${QUESTION.id}">
               <input type="button" class="edit_btn" value="Edit" id="edit"/>
             </a>
             <input type="button" class="cancel_btn"  value="Cancel" id="cancel" onclick="goBack()"/>
          </div>

          <!-- main copy rights -->
          <div class="copy_rights">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>

          <!-- main end -->
          </div>
  </body>
</html>