<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.StringUtil"%>
<%@page import="com.augmentun.exam.model.Question"%>
<%@page import="com.augmentun.exam.Constants"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@taglib uri="/WEB-INF/block.tld" prefix="block"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>question_query</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_list.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_create.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/question/question_query.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/question/question_query.js"></script>
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

      <block:display name="queryQuestionBlock"/>
      <!-- wrapper end -->
    </div>
  </body>
</html>