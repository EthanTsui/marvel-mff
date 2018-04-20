<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ethan.marvel.*" %>
<%
String lang = "en";
if (request.getParameter("lang") != null && request.getParameter("lang").length() > 0) {
    lang = request.getParameter("lang").trim();
}

%>
<h3>
<span><a href='./UserCardCollection?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.title") %></a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></a></span> |
<span><a href='./userAddNewCard.jsp?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a></span> |
<span><a href='./UserCollectionTop?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></a></span> |
<span><a href='./UserCardSuggest?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.suggestion") %></a></span> |
<!-- <span><a href='./UserLoadProfile?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></a></span> -->
</h3>