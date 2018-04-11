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
<h1><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></h1>

<h3>
<span><a href='./UserCardCollection?lang=<%=lang%>'>User Card Collection</a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'>User Card List</a></span> |
<span><a href='./userAddNewCard.jsp?lang=<%=lang%>'>Add New Card</a></span> |
<span><a href='./LoadProfile?lang=<%=lang%>'>Load Profile</a></span>
</h3>

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


<div class="row justify-content-start">
<div class="col">
<c:out value='${sessionScope.tknid }' />
</div>
</div>

<form action="./AddNewCard">

<input type='hidden' name='lang' value='<%=lang %>' />


<div class="row justify-content-start">
<div class="col">
<select class="form-control" name="cardid" id="cardid">
   <%
   int cid = 1;
   if(request.getParameter("cardid")!=null) {
      try {
         cid = Integer.parseInt(request.getParameter("cardid"));
      } catch(Exception ignore) { }
   }

    for (Card c : CardDB.getInstance().getCards().values()) {
%>
   <option value='<%=c.getCardId() %>'
<%
      if(c.getCardId()==cid) {
        out.write("selected");
      }
%>
   ><%= LocalizationHelper.getInstance().getText(lang, "ITEM_"+c.getCardItemId()) %> </option>

<%
  }
%>

</select>
</div>
</div>

<script>
  $('#cardid').on('change', function() {
      location.href='./userAddNewCard.jsp?cardid=' + $(this).val() +'&lang=<%=lang %>';
  });

</script>


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
Modify
<%
  }
  else {
%>
Add
<%
  }
%>
</button>

</form>






<jsp:include page="./footer_include.jsp" />
</div>
</body>
</html>