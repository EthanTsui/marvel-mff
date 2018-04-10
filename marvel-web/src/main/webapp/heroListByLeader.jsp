<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.herolist") %></title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />

<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.herolist") %></h1>

<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getAbilityName("patk") %> + 5%</div>
<div class="panel-body">

    <%

    
    List<Hero> cheros = HeroDB.getInstance().getHeros();
    
    for(Hero hero:cheros) {
        if(hero.getAbility("patk")==null) {
            continue;
        }
        
    %>
<div class="allhero" style="float:left;margin:3px"><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hll_patk"><div class="allhero" style=" background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></a></div>
<%

    }
%>
<div style="float:none"></div>
</div>
</div>
</div>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getAbilityName("eatk") %> + 5%</div>
<div class="panel-body">

    <%

    
    cheros = HeroDB.getInstance().getHeros();
    
    for(Hero hero:cheros) {
        if(hero.getAbility("eatk")==null) {
            continue;
        }
        
    %>
<div class="allhero" style="float:left;margin:3px"><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hll_patk"><div class="allhero" style=" background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></a></div>
<%

    }
%>
<div style="float:none"></div>
</div>
</div>
</div>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getAbilityName("aatk") %> + 4%</div>
<div class="panel-body">

    <%

    
    cheros = HeroDB.getInstance().getHeros();
    
    for(Hero hero:cheros) {
        if(hero.getAbility("aatk")==null) {
            continue;
        }
        
        Unit u = (Unit) hero.getAbility("aatk");
        
        
        System.out.println("xx: "+hero.getAbility("aatk")+", "+u.getValue());
    %>
<div class="allhero" style="float:left;margin:3px"><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hll_patk"><div class="allhero" style=" background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></a></div>
<%

    }
%>
<div style="float:none"></div>
</div>
</div>
</div>


<jsp:include page="./footer_include.jsp" />
</body>
</html>