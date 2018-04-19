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
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></h1>

<h3>
<span><a href='./UserCardCollection?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.title") %></a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></a></span> |
<span><a href='./userAddNewCard.jsp?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a></span> |
<span><a href='./UserCollectionTop?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></a></span> |
<!-- <span><a href='./UserLoadProfile?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></a></span> -->
</h3>

<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">


<form method='get' action='./UserLoadProfile'>
<input type='hidden' name='lang' value='<%=lang %>' />
<div class="form-group">
  <label for="tokenid">Token Id: </label>
  <input type="text" class="form-control" id="tokenid" name="tokenid" value="<c:out value='${sessionScope.tknid }' />">
</div>
<button type="submit" class="btn btn-primary btn-lg"><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile.load") %></button>
</form>

</div>
</div>
</div>

<jsp:include page="./footer_include.jsp" />
</body>
</html>