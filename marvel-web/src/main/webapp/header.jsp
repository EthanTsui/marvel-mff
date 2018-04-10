<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ethan.marvel.*" %>
<%
String lang = "en";
if (request.getParameter("lang") != null && request.getParameter("lang").length() > 0) {
    lang = request.getParameter("lang").trim();
}

%>    
<h1><%= LanguageHelper.getInstance().getInterfaceName(lang, "header.header") %> <span style="font-size:12pt">(game version 3.9.0, 2018/03/21)</span></h1>

<h3><a href="./QueryTeam?lang=<%=lang %>"><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.team.combination") %></a> 
| <a href="./heroList.jsp?lang=<%=lang %>"><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.herolist") %></a> 
| <a href="./iso8List.jsp?lang=<%=lang %>"><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_MENU_10") %></a>  
| <a href="./cardList.jsp?lang=<%=lang %>"><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></a> 
| <a href="./uniformList.jsp?lang=<%=lang %>"><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_MENU_1002006") %></a> 
| <a href="./quiz.jsp?lang=<%=lang %>"><%=LocalizationHelper.getInstance().getText(lang, "TAB_DAILY_QUIZ") %></a> 
| <a href="http://goo.gl/forms/61WsFVF9XT">Feedback here!</a>

</h3>
<div align="center">
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- banner -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-7688138317444669"
     data-ad-slot="4006176637"
     data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</div>
<div align="center" style="color:red;font-size:24px;"><%= LanguageHelper.getInstance().getInterfaceName(lang, "header.clickad") %> :)</div>
<p>Language: <a href="<%=request.getContextPath()+request.getServletPath() %>?lang=en">English</a> | <a href="<%=request.getContextPath()+request.getServletPath() %>?lang=zhtw">中文</a> | <a href="<%=request.getContextPath()+request.getServletPath() %>?lang=jp">日本語</a> | <a href="<%=request.getContextPath()+request.getServletPath() %>?lang=ko">한국어</a></p>
