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
.card-columns {
  @include media-breakpoint-only(lg) {
    column-count: 4;
  }
  @include media-breakpoint-only(xl) {
    column-count: 5;
  }
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

<c:out value='${sessionScope.tknid }' />

<div class="container-fluid mt-4">
    <div class="row justify-content-center">

<%
   List<UserCard> cards = (List<UserCard>) request.getAttribute("cards");


   for(UserCard card:cards) {
       
       
       %>
       <div class="col-auto mb-3">
       <div class="card" style="width: 18rem;">
         <img class="card-img-top" src="img/ncc_<%=card.getCardId() %>.png" style="max-width:100%"/>
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
               <a class="btn btn-primary" href="./UserModifyCard?cuid=<%=card.getCardUId() %>&lang=<%=lang %>" role="button">Modify</a>
               <a class="btn btn-outline-danger float-right"  href="./UserDeleteCard?cuid=<%=card.getCardUId() %>&lang=<%=lang %>" role="button" onclick="return confirm('Are you sure?')">Delete</a>
             </div>
       </div>
       </div>
       <%
       
       
   }

%>
</div>
</div>




<jsp:include page="./footer_include.jsp" />
</div>
</body>
</html>