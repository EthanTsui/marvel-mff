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

   int cid = 1;
   if(request.getParameter("cardid")!=null) {
      try {
         cid = Integer.parseInt(request.getParameter("cardid"));
      } catch(Exception ignore) { }
   }
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></title>
<jsp:include page="./head_include.jsp" />

<style>
input[type=radio] {
    display:none;
    margin:10px;
}
input[type=radio] + label {
    display:inline-block;
    margin:-2px;
    padding: 4px 12px;
    background-color: #e9e9e9;
    border-color: #ddd;
}
input[type=radio]:checked + label {
   background-image: none;
    background-color:#3a9bc1;
}
</style>

</head>
<body>
<div class="container">
<jsp:include page="./header.jsp" />
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></h1>

<h3>
<span><a href='./UserCardCollection?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.title") %></a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></a></span> |
<span><a href='./userAddNewCard.jsp?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a></span> |
<span><a href='./UserCollectionTop?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></a></span> |
<!-- <span><a href='./UserLoadProfile?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></a></span> -->
</h3>

<div class="card">
  <div class="card-body">
    Token Id: <c:out value='${sessionScope.tknid }' />
  </div>
</div>

<form action="./UserAddNewCard">

<input type='hidden' name='lang' value='<%=lang %>' />
<input type='hidden' name='cardid' value='<%=cid %>' />
<%
  if(request.getParameter("slotid")!=null && request.getParameter("collectionuid")!=null) {
%>
<input type='hidden' name='slotid' value='<%=request.getParameter("slotid") %>' />
<input type='hidden' name='collectionuid' value='<%=request.getParameter("collectionuid") %>' />
<%
}
%>
<div class="row justify-content-start">
<div class="col">

<%
  String link="";
  if(request.getParameter("slotid")!=null && request.getParameter("collectionuid")!=null) {
     link="&slotid="+request.getParameter("slotid")+"&collectionuid="+request.getParameter("collectionuid");
  }
%>

<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card.select") %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
<%
    for (Card c : CardDB.getInstance().getCards().values()) {
%>
   <a class="dropdown-item" href="./userAddNewCard.jsp?lang=<%=lang %>&cardid=<%=c.getCardId() %><%=link %>"><img src="img/ncc_<%=c.getCardId() %>.png" style="width:64px"/><%= LocalizationHelper.getInstance().getText(lang, "ITEM_"+c.getCardItemId()) %></a>

<%
    }
%>

  </div>
</div>

</div>
</div>


  <div class="card" style="width: 100%;">
<img class="card-img-top" src="img/ncc_<%=cid %>.png" style="width: 200px" />
<div class="card-body">
<div class="row">

<%
   UserCard usercard = null;
   if(request.getAttribute("usercard")!=null) {
     usercard = (UserCard) request.getAttribute("usercard");
   }

   if(usercard!=null) {
%>
  <input type='hidden' name='carduid' value='<%=usercard.getCardUId() %>' />

<%

   }

  for(int i=1;i<=7;i++) {
%>
<div class="col">
<input class="form-check-input" id="level_<%=i %>" type="radio" name="level" value="<%= i %>"
<%
   if(usercard!=null) {

        if(i==usercard.getLevel()) {
        out.write("checked=\"checked\"");
        }
   }
   else {
       if(i==3) {
          out.write("checked=\"checked\"");
       }
   }
%>
>
<label for="level_<%=i %>">Level-<%=i %></label>
</div>
<%
  }
%>
</div>
</div>


<%
   Card card = CardDB.getInstance().getCard(cid);

   for(int i=1;i<=6;i++) {
       List<CardAbility> abilities = card.getLevelAbility(i);

%>
<div class="card-body">
<div class="row">
<%

       for(int j=0,size=abilities.size();j<size;j++) {
       CardAbility cabi = abilities.get(j);
%>
<div class="col">
<input type="radio" id="radio_<%=i %>_<%=j %>" name="opt<%=i %>" value="<%= cabi.getFullId() %>"
<%
   if(usercard!=null) {

        if(cabi.getFullId().equals(usercard.getOptions(i))) {
            out.write("checked=\"checked\"");
        }
   }
   else {
       if(j==0) {
          out.write("checked=\"checked\"");
       }
   }
%>
>
  <label for="radio_<%=i %>_<%=j %>"><%=cabi.getDescription(lang) %></label>
</div>
<%
    }

%>
</div>
</div>
<%
  }
%>


</div>

<button type="submit" class="btn btn-primary btn-lg">
<%
  if(usercard!=null) {
%>
<%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.modify") %>
<%
  }
  else {
%>
<%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card.add") %>
<%
  }
%>
</button>

</form>






<jsp:include page="./footer_include.jsp" />
</div>
</body>
</html>