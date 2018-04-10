<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
<title><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></h1>

<div class="alert alert-info">
  <strong>New Cards! </strong> Thanks for all your helps and all the resources on the Internet everyone co-contributed. 
  <br/>Referenced by https://docs.google.com/spreadsheets/d/1AisOfO4Xwef0KPKO1Ka7CYhL_loOFMNUbKNC0x7WOZA/edit#gid=0, especially thanks to this spreadsheets' owner, this can't be done without your contribution.
  
</div>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">
<form id="f1" name="f1" method="get" action="cardList.jsp">
<input type="hidden" name="lang" id="lang" value="<%=(request.getParameter("lang")==null?"en":request.getParameter("lang")) %>">

<select class="form-control" id="level" name="level">
    <%
    int level=3;
    if(request.getParameter("level")!=null) {
        level=Integer.parseInt(request.getParameter("level"));
    }

    out.write("<option selected='selected'>"+level+"</option>");

    for(int i=1;i<=6;i++) {
        out.write("<option>"+i+"</option>");
    }
    
    %>


</select>

<%

String[] allabi = null;

if(request.getParameterValues("filter")!=null) {
    allabi=request.getParameterValues("filter");    
}
else if(request.getParameter("filter")!=null) {
    allabi=new String[1];
    allabi[0]=request.getParameter("filter").trim();
}
else {
    allabi=new String[0];
}

List<String> strinput = new ArrayList<String>();
for(String ss:allabi) {
    strinput.add(AbilityMappingDB.getInstance().abilityIdByAbbr(ss));
}
String[] abiidList = strinput.toArray(new String[strinput.size()]);



String[] allAbilities = {"aatk","patk","eatk","adef","pdef","edef","do","ct","hp","rcr","ctr","ctd","dp", "as", "ms"};

for(String sabi:allAbilities) {
    if(sabi.equals("adef") || sabi.equals("do") || sabi.equals("ctr")|| sabi.equals("as")) {
        out.write("<br/>");
    }
    
%>
<label class="checkbox-inline"><input type="checkbox" name="filter" id="filter" value="<%=sabi%>"
<%
  for(String ass:allabi) {
      if(sabi.equals(ass)) {
          out.write(" checked=\"checked\"");
          break;
      }
  }
%>
><%= LocalizationHelper.getInstance().getText(lang, "ABILITY_"+ AbilityMappingDB.getInstance().abilityIdByAbbr(sabi)) %></label>


<%
}
%>
<div>
<input type="submit" name="s1" value="<%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.query") %>"/>
</div>
</form>
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

<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LocalizationHelper.getInstance().getText(lang, "TITLE_CARD_EFFECT") %></td>
<td> </td>

</tr>


    <%

    List<Card> cards = null;
    if(abiidList.length>0) {

        cards = CardDB.getInstance().getFilteredCards(abiidList, level);
    }
    else {
        cards = CardDB.getInstance().getFilteredCards(CardDB.getInstance().getAllAbilities(), level);
    }
    
    //out.println("card size: " + cards.size()+", "+CardDB.getInstance().getCards().size());
    
    for(Card card:cards) {
    %>
    <tr>
    <td>
    <img src="img/ncc_<%=card.getCardId() %>.png" style="width:120px" /><br/>
    <%= LocalizationHelper.getInstance().getText(lang, "ITEM_"+card.getCardItemId()) %>
    <%
    if(card.getObtainStageId()!=null) {
        %>
        <br/>
       <%= LocalizationHelper.getInstance().getText(lang, "STAGE_TYPE_"+card.getObtainStageType()) %>: 
       <%= LocalizationHelper.getInstance().getText(lang, "STAGE_NAME_"+card.getObtainStageId()) %> 
     <%   
    }
   
    %>
   

    
    
    </td>
    <td>
    <%
    for(int j=1;j<=6;j++) {
        List<CardAbility> abilities = card.getLevelAbility(j);
        
        %>
        <div style="font-size:16px;color:#3366FF"><p>
        <%
        
        for(CardAbility cabi:abilities) {
           
        %>
    <%
    
    if(!cabi.getAbilityId().equals("0")) {
        boolean found=false;
        for(String abii:abiidList) {
            if(abii.equals(cabi.getAbilityId())) {
                found=true;
                break;
            }
        }
        if(found) {
            out.write("<span style='color:red'>");
        }
        out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+cabi.getAbilityId()));
        if(found) {
            out.write("</span>");
        }
    }
    else {
        boolean found=false;
        for(String abii:allabi) {
            if(abii.equals(cabi.getAutoAbilityId())) {
                found=true;
                break;
            }
        }
        if(found) {
            out.write("<span style='color:red'>");
        }
        out.write(LocalizationHelper.getInstance().getText(lang, "AUTO_ABILITY_DESC"));
        out.write(LocalizationHelper.getInstance().getText(lang, "AUTO_ABILITY_DESC_"+cabi.getAbilityDescId()).replace("{0}", cabi.getAutoAbilityRate()));
        out.write(": "+LocalizationHelper.getInstance().getText(lang, "ABILITY_" + cabi.getAutoAbilityId()));
        out.write(" "+cabi.getAutoValue()+"%");
        out.write(" "+LocalizationHelper.getInstance().getText(lang, "ABILITY_DESC_TIME").replace("{0}", cabi.getAutoTime()));
        if(found) {
            out.write("</span>");
        }
    }
    out.write(", ");
%>
    <%
    
    %>
<%
        }
        
        %>
           <br/></p></div>
        <%
}
%></td>
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