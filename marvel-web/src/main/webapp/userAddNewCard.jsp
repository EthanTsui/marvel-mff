<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ethan.marvel.*" %>
<%@ page import="com.ethan.marvel.usercards.*" %>
<%@ page import="java.text.DecimalFormat" %>
    <%
    String lang="en";
    if(request.getParameter("lang")!=null && request.getParameter("lang").length()>0) {
        lang=request.getParameter("lang").trim();
    }
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></h1>

<div>
<span><a href='./UserCardCollection?lang=<%=lang%>'>User Card Collection</a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'>User Card List</a></span> |
<span><a href='./LoadProfile?lang=<%=lang%>'>Load Profile</a></span>
</div>

<div>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- rectangle ad1 -->
<ins class="adsbygoogle"
     style="display:inline-block;width:300px;height:250px"
     data-ad-client="ca-pub-7688138317444669"
     data-ad-slot="8380513834"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</div>

<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">
<c:out value='${sessionScope.tknid }' />





</div>
</div>
</div>





<jsp:include page="./footer_include.jsp" />
</body>
</html>