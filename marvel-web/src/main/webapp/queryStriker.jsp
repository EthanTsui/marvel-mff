<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ethan.marvel.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%!
DecimalFormat df2 = new DecimalFormat("##0.0");
%>

<%
String lang="en";
if(request.getParameter("lang")!=null && request.getParameter("lang").length()>0) {
    lang=request.getParameter("lang").trim();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.information") %>, Marvel Future Fight</title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.information") %></h3>
  </div>
  <div class="panel-body">
    <table class="table table-striped">
    <tr><td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero") %></td><td><%=LanguageHelper.getInstance().getInterfaceName(lang, "striker") %></td><td><%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.probability") %></td></tr>
<%
if(request.getParameter("heroteam")!=null && request.getParameter("heroteam").length()>0) {
    
    String[] str = request.getParameter("heroteam").split("_");
    


    Helper helper = new Helper();
    helper.setLanguage(lang);
    HeroTeam heroteam = new HeroTeam();
    for(String s:str) {
        heroteam.addHero(helper.getHero(Integer.parseInt(s)));
    }
    
    
    for(int i=0,size=heroteam.getHeros().size();i<size;i++) {
        Hero heroi = heroteam.getHeros().get(i);
        for(int j=0;j<size;j++) {
            if(i==j) {
                continue;
            }
            Hero heroj=heroteam.getHeros().get(j);
            if(heroi.containsHelper(heroj.getHeroId())) {
                // out.println("<tr><td>"+LanguageHelper.getInstance().getHeroName(lang,heroi.getHeroId())+"</td><td>"+LanguageHelper.getInstance().getHeroName(lang,heroj.getHeroId())+"</td><td class='"+((heroi.getHelper(heroj.getHeroId()).getProb()-heroi.getAverageHelperPercentage())>=0 ? "success":"danger")+"'>");
                %>
                <tr>
                <td><a href='./heroDetail.jsp?heroid=<%=heroi.getHeroId() %>&lang=<%=lang %>' data-ref='qs_hero'>
                <div class="allhero36" style="float:left; background-position: -<%= ((heroi.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((heroi.getHeroId()-1)/10))*36 %>px;"></div>
                </a>
                </td>
                <td>
                <a href='./heroDetail.jsp?heroid=<%=heroj.getHeroId() %>&lang=<%=lang %>' data-ref='qs_str'>
                <div class="allhero36" style="float:left; background-position: -<%= ((heroj.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((heroj.getHeroId()-1)/10))*36 %>px;"></div>
                </a>
                </td>
                <td class='<%=((heroi.getHelper(heroj.getHeroId()).getProb()-heroi.getAverageHelperPercentage())>=0 ? "success":"danger") %>'>
                <%=LanguageHelper.getInstance().getInterfaceName(lang,"qs.autotime."+heroi.getHelper(heroj.getHeroId()).getAutoType()) %>,
                <%=df2.format(heroi.getHelper(heroj.getHeroId()).getProb()*100f) %>% (<%=df2.format((heroi.getHelper(heroj.getHeroId()).getProb()-heroi.getAverageHelperPercentage())*100f) %>%, 
                <%=LanguageHelper.getInstance().getInterfaceName(lang,"qs.cooltime") %> <%=heroi.getHelper(heroj.getHeroId()).getCoolTime() %>s</span>)</td></tr>
                <%
            }
            
            
        }
    }
    
    
%>



<%

}

%>
</table>
  </div>
</div>
<jsp:include page="./footer_include.jsp" />
</body>
</html>