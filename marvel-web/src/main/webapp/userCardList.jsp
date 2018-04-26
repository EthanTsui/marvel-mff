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
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></title>
<jsp:include page="./head_include.jsp" />

<style>
.card-columns {
  @include media-breakpoint-only(lg) {
    column-count: 4;
  }
  @include media-breakpoint-only(xl) {
    column-count: 5;
  }
}

.thumbnail {
    position: relative;
}

.caption {
    position: absolute;
    top: 90%;
    left: 88%;
    width: 20px;
    background-color: #0b44ef;
    color: white;
    text-align: center;
}

</style>

</head>
<body>
<div class="container">
<jsp:include page="./header.jsp" />
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></h1>

<jsp:include page="./userCard_include.jsp" />

<%
  if(request.getParameter("error")!=null) {
%>
<div class="card">
  <div class="card-body">
    <div class="alert alert-danger" role="alert">
    <p> </p>
     <p> <%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.error."+request.getParameter("error")) %> </p>
      <p></p>
    </div>
  </div>
</div>
<%
 }
%>

<div class="card">
  <div class="card-body">
    Token Id: <c:out value='${sessionScope.tknid }' />
  </div>
</div>

<div class="card">
<div class="row">
</div class="col">
<%
String link = "";
if(request.getParameter("slotid")!=null && request.getParameter("collectionuid")!=null) {
    link="&slotid="+request.getParameter("slotid")+"&collectionuid="+request.getParameter("collectionuid");
}
%>

<a class="btn btn-primary" href="./userAddNewCard.jsp?lang=<%=lang %><%=link %>" role="button"><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a>
</div>
</div>
</div>

<div class="container-fluid mt-4">
    <div class="row justify-content-center">

<%
   List<UserCard> cards = (List<UserCard>) request.getAttribute("cards");
   for(UserCard card:cards) {
%>
       <div class="col-auto mb-3">
       <div class="card" style="width: 18rem;">
         <div class="thumbnail">
         <img class="card-img-top" src="img/ncc_<%=card.getCardId() %>.png" style="max-width:100%"/>
         <div class="caption"><%=card.getLevel() %></div>
         </div>
         <div class="card-body">

         <h5 class="card-title"><%= LocalizationHelper.getInstance().getText(lang, "ITEM_"+CardDB.getInstance().getCard(card.getCardId()).getCardItemId()) %></h5>
         </div>
         <div class="card-body"><p>
         <%

           for(int i=1;i<=6;i++) {
               if(!card.getOptions(i).startsWith("0:")) {
                   out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+card.getOptions(i))+" "+card.getOptionsRatio(i)+"%");
               }
               else {
                   String s = card.getOptions(i);
                   String[] s2 = s.substring(2).split("_");
                   
                   out.write(LocalizationHelper.getInstance().getText(lang, "AUTO_ABILITY_DESC"));
                   out.write(LocalizationHelper.getInstance().getText(lang, "AUTO_ABILITY_DESC_"+s2[1]).replace("{0}", s2[2]));
                   out.write(": "+LocalizationHelper.getInstance().getText(lang, "ABILITY_" + s2[0]));
                   out.write(" "+s2[3]+"%");
                   out.write(" "+LocalizationHelper.getInstance().getText(lang, "ABILITY_DESC_TIME").replace("{0}", s2[4]));
               }
%>
             <br/>
<%

           }
         

         
         %>
         </div>
         <div class="card-footer">

         <%
         if(request.getParameter("slotid")==null) {
         %>
               <a class="btn btn-primary" href="./UserModifyCard?cuid=<%=card.getCardUId() %>&lang=<%=lang %>" role="button"><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.modify") %></a>
               <a class="btn btn-outline-danger float-right"  href="./UserDeleteCard?cuid=<%=card.getCardUId() %>&lang=<%=lang %>" role="button" onclick="return confirm('Are you sure?')"><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.delete") %></a>
         <%
         }
         else {
         %>
         <a class='btn btn-primary' href='./UserSelectCard?cuid=<%=card.getCardUId() %>&slotid=<%=request.getParameter("slotid") %>&collectionuid=<%=request.getParameter("collectionuid") %>&lang=<%=lang %>' role='button'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.select") %></a>

         <%
         }

         %>

             </div>
       </div>
       </div>
       <%
       
       
   }

%>
</div>
</div>

<div class="card">
<div class="row">
</div class="col">


<a class="btn btn-primary" href="./userAddNewCard.jsp?lang=<%=lang %><%=link %>" role="button"><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a>
</div>
</div>
</div>


<jsp:include page="./footer_include.jsp" />
</div>
</body>
</html>