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
<%! static String[] QUERY_SKILLS={"9_26", "9_25", "11", "103", "10_27", "10_28", "19", "4", "5", "6", "7", "8", "20"}; %>
<%! static String[] QUERY_ABI_SKILLS={"26", "25", "11", "103", "27", "28", "19", "4", "5", "6", "7", "8", "20"}; %>
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
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.suggestion") %></title>
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
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "user.card.suggestion") %></h1>

<jsp:include page="./userCard_include.jsp" />


<div class="row">
<div class="col">
<form method="GET" action="./UserCardSuggest">
  <input type="hidden" name="lang" value="<%=lang %>" />
  <div class="form-group">
  <label for="sortby">Sort by</label>
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
  </div>

  <div class="form-group">
  <select name="opt1" id="opt1">
    <option value="none">Select</option>
    <%
        for(int i=0,size=QUERY_SKILLS.length;i<size;i++) {
      %>
      <option value="<%=QUERY_SKILLS[i] %>"
      <%
      if(request.getParameter("opt1")!=null && QUERY_SKILLS[i].equals(request.getParameter("opt1"))) {
      out.write("selected");
      }
      %>
      ><%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+QUERY_ABI_SKILLS[i]) %></option>

      <%
        }
      %>

  </select>
  <input type="text" name="lower1" value="<%=request.getParameter("lower1")==null?"0":request.getParameter("lower1") %>" /> ~ <input type="text" name="upper1" value="<%=request.getParameter("upper1")==null?"999":request.getParameter("upper1") %>" />

  </div>

  <div class="form-group">
    <select name="opt2" id="opt2">
      <option value="none">Select</option>
      <%
          for(int i=0,size=QUERY_SKILLS.length;i<size;i++) {
        %>
        <option value="<%=QUERY_SKILLS[i] %>"
        <%
        if(request.getParameter("opt2")!=null && QUERY_SKILLS[i].equals(request.getParameter("opt2"))) {
        out.write("selected");
        }
        %>
        ><%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+QUERY_ABI_SKILLS[i]) %></option>

        <%
          }
        %>

    </select>
    <input type="text" name="lower2" value="<%=request.getParameter("lower2")==null?"0":request.getParameter("lower2") %>" /> ~ <input type="text" name="upper2" value="<%=request.getParameter("upper2")==null?"999":request.getParameter("upper2") %>" />



    </div>

   <div class="form-group">
    <select name="opt3" id="opt3">
      <option value="none">Select</option>
      <%
          for(int i=0,size=QUERY_SKILLS.length;i<size;i++) {
        %>
        <option value="<%=QUERY_SKILLS[i] %>"
        <%
        if(request.getParameter("opt3")!=null && QUERY_SKILLS[i].equals(request.getParameter("opt3"))) {
        out.write("selected");
        }
        %>
        ><%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+QUERY_ABI_SKILLS[i]) %></option>

        <%
          }
        %>

    </select>
    <input type="text" name="lower3" value="<%=request.getParameter("lower3")==null?"0":request.getParameter("lower3") %>" /> ~ <input type="text" name="upper3" value="<%=request.getParameter("upper3")==null?"999":request.getParameter("upper3") %>" />



    </div>

   <div class="form-group">
    <select name="opt4" id="opt4">
      <option value="none">Select</option>
      <%
          for(int i=0,size=QUERY_SKILLS.length;i<size;i++) {
        %>
        <option value="<%=QUERY_SKILLS[i] %>"
        <%
        if(request.getParameter("opt4")!=null && QUERY_SKILLS[i].equals(request.getParameter("opt4"))) {
        out.write("selected");
        }
        %>
        ><%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+QUERY_ABI_SKILLS[i]) %></option>

        <%
          }
        %>

    </select>
    <input type="text" name="lower4" value="<%=request.getParameter("lower4")==null?"0":request.getParameter("lower4") %>" /> ~ <input type="text" name="upper4" value="<%=request.getParameter("upper4")==null?"999":request.getParameter("upper4") %>" />



    </div>
<button type="submit" class="btn btn-primary">Submit</button>

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