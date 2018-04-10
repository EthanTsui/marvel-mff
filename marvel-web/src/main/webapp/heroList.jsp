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
<div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.c") %></div>
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership") %></td>
</tr>

    <%

    
    List<Hero> cheros = HeroDB.getInstance().listByHeroType("c");
    
    for(Hero hero:cheros) {
        
    %>
    <tr>
    <td><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hl_c"><div class="allhero" style=" background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></a></td>
    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type."+hero.getHeroType()) %>
    </td>
    
    <td>
    <%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type."+hero.getAttackType()) %>
    </td>
    
    <td><%=hero.getAbilityString(lang) %></td>
    </tr>
<%

    }
%>

</table>
</div>
</div>
</div>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.b") %></div>
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership") %></td>
</tr>

    <%
    List<Hero> bheros = HeroDB.getInstance().listByHeroType("b");
    
    for(Hero hero:bheros) {
        
    %>
    <tr>
    <td><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hl_b"><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></a></td>
    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type."+hero.getHeroType()) %>
    </td>
    
    <td>
    <%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type."+hero.getAttackType()) %>
    </td>
    
    <td><%=hero.getAbilityString(lang) %></td>
    </tr>
<%

    }
%>

</table>
</div>
</div>
</div>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.s") %></div>
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership") %></td>
</tr>

    <%
    List<Hero> sheros = HeroDB.getInstance().listByHeroType("s");
    
    for(Hero hero:sheros) {
        
    %>
    <tr>
    <td><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hl_s"><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></a></td>
    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type."+hero.getHeroType()) %>
    </td>
    
    <td>
    <%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type."+hero.getAttackType()) %>
    </td>
    
    <td><%=hero.getAbilityString(lang) %></td>
    </tr>
<%

    }
%>

</table>
</div>
</div>
</div>



<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.u") %></div>
<div class="panel-body">

<table class="table table-striped">
<tr>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership") %></td>
</tr>

    <%
    List<Hero> uheros = HeroDB.getInstance().listByHeroType("u");
    
    for(Hero hero:uheros) {
        
    %>
    <tr>
    <td><a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>" data-ref="hl_u"><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></a></td>
    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type."+hero.getHeroType()) %>
    </td>
    
    <td>
    <%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type."+hero.getAttackType()) %>
    </td>
    
    <td><%=hero.getAbilityString(lang) %></td>
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