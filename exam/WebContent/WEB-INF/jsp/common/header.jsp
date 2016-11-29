<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@taglib prefix="res" uri="http://www.springframework.org/tags"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
      <!-- header -->
      <div class="header" >
        <div class="logo">
          <img src="<%=PropertyUtil.getStaticUrl()%>/images/OES_Web_Slice/LOGO_Web_40x240.png">
        </div>
        <div class="describe"><res:message code="oes.system.name"/></div>
        <div class="language">
          <c:if test="${LANGUAGE == 'zh'}">
            <a href="<%=request.getContextPath()%>/page/user/changeLanguage?language=en">English</a>
          </c:if>
          <c:if test="${LANGUAGE == 'en'}">
            <a href="<%=request.getContextPath()%>/page/user/changeLanguage?language=zh">中文</a>
          </c:if>
        </div>
        <div class="logout">
          <a href="<%=request.getContextPath()%>/page/user/logout" class="logout_words"><res:message code="oes.logout"/></a>
        </div>
        <div class="short_name"><a href="#">${USER.userName}</a></div>
        <div class="portrait">
          <a href="<%=request.getContextPath()%>/page/user/"><img src="<%=PropertyUtil.getStaticUrl()%>/images/OES_Web_Slice/ICN_Web_PersonalInformation_20x20.png  .png"></img></a>
        </div>
      </div>
  </body>
</html>