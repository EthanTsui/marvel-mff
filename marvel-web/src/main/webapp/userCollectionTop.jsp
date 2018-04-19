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
<%! static String[] ATTACK_SKILLS={"4", "6", "7", "9", "25", "26", "103"}; %>
<%! static String[] DEFENSE_SKILLS={"8", "10", "11", "27", "28", "29", "32", "33", "34", "35", "36"}; %>
<%! static String[] ABNORMAL_SKILLS={"5", "19", "20"}; %>
<%! static String[] QUERY_SKILLS={"sk_9_26", "sk_9_25", "sk_11", "sk_103", "sk_10_27", "sk_10_28", "sk_19"}; %>
<%! static String[] QUERY_ABI_SKILLS={"26", "25", "11", "103", "27", "28", "19"}; %>
<%! static DecimalFormat FORMATTER = new DecimalFormat("##.#"); %>
    <%
    String lang="en";
    if(request.getParameter("lang")!=null && request.getParameter("lang").length()>0) {
        lang=request.getParameter("lang").trim();
    }
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></title>
<jsp:include page="./head_include.jsp" />

<style>

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
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></h1>

<h3>
<span><a href='./UserCardCollection?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.title") %></a></span> |
<span><a href='./UserCardList?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.list.title") %></a></span> |
<span><a href='./userAddNewCard.jsp?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.new.card") %></a></span> |
<span><a href='./UserCollectionTop?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.collection.top.title") %></a></span> |
<!-- <span><a href='./UserLoadProfile?lang=<%=lang%>'><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.load.profile") %></a></span> -->
</h3>


<div class="row">
<div class="col">
<form method="GET" action="./UserCollectionTop">
  <input type="hidden" name="lang" value="<%=lang %>" />
  <select name="sortby" id="sortby" class="custom-select">
  <%
    for(int i=0,size=QUERY_SKILLS.length;i<size;i++) {
  %>
  <option value="<%=QUERY_SKILLS[i] %>"
  <%
  if(request.getParameter("sortby")!=null && QUERY_SKILLS[i].equals(request.getParameter("sortby"))) {
  out.write("selected");
  }
  %>
  ><%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+QUERY_ABI_SKILLS[i]) %></option>

  <%
    }
  %>
  </select>

<script>
  $('#sortby').on('change', function() {
      location.href='./UserCollectionTop?lang=<%=lang%>&sortby=' + $(this).val();
  });
</script>

</form>
</div>
</div>


<%
  List<UserCollection> collections = (List<UserCollection>) request.getAttribute("collections");

  for(UserCollection collection:collections) {


%>
<div class="card card border-primary">
<div class="container">

<div class="row">
<div class="col">
</div>
</div>

<div class="row justify-content-center">

  <%
     for(int i=1;i<=5;i++) {
       if(collection.getSlotCard(i)!=null) {

  %>
  <div class="col">
  <div class="card">
  <div class="thumbnail">
     <img class="card-img-top" src="img/ncc_<%=collection.getSlotCard(i).getCardId() %>.png" style="max-width:100%" />
     <div class="caption"><%=collection.getSlotCard(i).getLevel() %></div>
  </div>
      <%
        }
       else {
      %>
        <div class="col">
        <div class="card">
<img class="card-img-top" src="img/empty.png" style="max-width:100%" />
      <%
       }
      %>
  </div>
  </div>

  <%
    } // for slots
  %>

</div>
<div class="row justify-content-center">
  <div class="col-6 col-md-4">
  <%
  out.write("<p>Total "+LocalizationHelper.getInstance().getText(lang, "ABILITY_26")+": "+FORMATTER.format(collection.getSkill("9_26"))+"%</p>");
    out.write("<p>Total "+LocalizationHelper.getInstance().getText(lang, "ABILITY_25")+": "+FORMATTER.format(collection.getSkill("9_25"))+"%</p>");
  out.write("<hr/>");
  out.write("<p>");
  for(String skillId:ATTACK_SKILLS) {
    out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+skillId)+": "+FORMATTER.format(collection.getSkill(skillId))+"%<br/>");
  }
  out.write("</p>");


  %>

  </div>
  <div class="col-6 col-md-4">
  <%
    out.write("<p>Total "+LocalizationHelper.getInstance().getText(lang, "ABILITY_27")+": "+FORMATTER.format(collection.getSkill("10_27"))+"%</p>");
    out.write("<p>Total "+LocalizationHelper.getInstance().getText(lang, "ABILITY_28")+": "+FORMATTER.format(collection.getSkill("10_28"))+"%</p>");
    out.write("<hr/>");
    out.write("<p>");
    for(String skillId:DEFENSE_SKILLS) {
      out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+skillId)+": "+FORMATTER.format(collection.getSkill(skillId))+"%<br/>");
    }
    out.write("</p>");

    %>
  </div>

  <div class="col-6 col-md-4">
  <p>
  <%

    for(String skillId:ABNORMAL_SKILLS) {
      out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+skillId)+": "+FORMATTER.format(collection.getSkill(skillId))+"%<br/>");
    }
    %>
    </p>
    </div>

</div>
</div>
</div>
<div class="card"><p> </p></div>
<%

  } // for collection
%>


<jsp:include page="./footer_include.jsp" />
</div>
</body>
</html>