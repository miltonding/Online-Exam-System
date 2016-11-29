<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.augmentun.exam.util.PropertyUtil"%>
<%@taglib prefix="res" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl()%>/css/user/login.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"> </script>
     <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/validate.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/user/login.js"></script>
  </head>

  <body>
    <div class="wrapper" id="login_wrapper">
      <div class="logo">
          <img alt="can't show" src="<%=PropertyUtil.getStaticUrl()%>/images/OES_Web_Slice/LOGO_Web_Login_90x180.png">
      </div>
      <div class="describe"><res:message code="oes.system.name"/></div>
      <div class="welcome"><res:message code="oes.system.welcome"/></div>
      <!-- login_form start -->  
      <div class="login_form">
        <div class="warning">${TIP_MESSAGE}</div>
        <!-- loginForm -->
        <form action="<%=request.getContextPath()%>/page/user/login" method="POST" id="loginForm">
          <div class="field">
            <img class="username_pic" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Usename_20x20.png.png"/>
            <input type="text" name="userName" id="userName" placeholder="Username"/>
            <img class="error" id="tip_userName" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Client_Login_Wrong_20X20.png">
          </div>
          <div class="field" style="margin-top:20px;">
          <img class="username_pic" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Password_20x15.png.png"/>
            <input type="password" name="password" id="password" placeholder="Password"/>
             <img class="error" id="tip_password" src="<%=PropertyUtil.getStaticUrl()%>/images/ICN_Client_Login_Wrong_20X20.png">
          </div>

          <div class="btn" ><res:message code="oes.login.name"/></div>
          <div class="field" style="margin-top: 20px;">
            <div id="remeber">
               <div class="check_unSeleted" id="remeber_checkbox"></div>
               <label class="remeber_me"><res:message code="oes.remember.me"/></label>
             </div>
           <label class="forget_me"><res:message code="oes.forget.password"/></label>
          </div>
        </form>
      <!-- login_form end -->  
      </div>
      <div class="declare">Copyright @ 2014 Augmentun.Inc.All Rights Reserved.</div>
      <!-- wrapper end -->
    </div>
  </body>
</html>