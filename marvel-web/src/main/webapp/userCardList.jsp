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

<table>



<%
   List<UserCard> cards = (List<UserCard>) request.getAttribute("cards");


   for(UserCard card:cards) {
       
       
       %>
       
       <tr>
         <td>
         <img src="img/ncc_<%=card.getCardId() %>.png" style="width:120px" /><br/>
         <%= LocalizationHelper.getInstance().getText(lang, "ITEM_"+CardDB.getInstance().getCard(card.getCardId()).getCardItemId()) %>
         </td>
        <td>
         <%
           for(int i=1;i<=6;i++) {
               out.write("<div><p>");
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
               
               out.write("</p></div>");
               
           }
         
         
         
         %>
        </td>
       
       
       </tr>
       
       <%
       
       
   }

%>
</table>

</div>
</div>
</div>





<jsp:include page="./footer_include.jsp" />
</body>
</html>