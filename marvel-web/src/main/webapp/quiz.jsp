<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ethan.marvel.*" %>
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
<title><%=LocalizationHelper.getInstance().getText(lang, "TAB_DAILY_QUIZ") %></title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LocalizationHelper.getInstance().getText(lang, "TAB_DAILY_QUIZ") %></h1>



<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LocalizationHelper.getInstance().getText(lang, "TAB_DAILY_QUIZ") %></td>
<td><%=LocalizationHelper.getInstance().getText(lang, "TITLE_QUIZ_RESULT_ANSWER") %> </td>

</tr>

    <%

    
    List<QuizDB.Quiz> quizlist = QuizDB.getInstance().getQuizs();
    
    for(QuizDB.Quiz q:quizlist) {
        
    %>
    <tr>
    <td>
    
    <div>
    <p><%=LocalizationHelper.getInstance().getText(lang, "QUIZ_"+q.quiz) %></p>
    </div>

    </td>
    <td>
    <div>
    <p>
    <%=LocalizationHelper.getInstance().getText(lang, "QUIZ_"+q.quiz+"_ANSWER_"+q.ans) %>
    </p>
    </div>

    </td>
    
    </tr>
<%

    }
%>

</table>
</div>
</div>
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



<jsp:include page="./footer_include.jsp" />
</body>
</html>