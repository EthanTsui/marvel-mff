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
<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.herolist") %> - <%=LocalizationHelper.getInstance().getText(lang, "HERO_SUBTYPE_202") %> - Marvel Future Fight</title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LanguageHelper.getInstance().getInterfaceName(lang, "header.func.herolist") %> - <%=LocalizationHelper.getInstance().getText(lang, "HERO_SUBTYPE_202") %></h1>
<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">|
<%
   for(int htypeidx = 0;htypeidx<=3;htypeidx++) {
%>
   <a href="#type_<%= htypeidx%>"><%=LocalizationHelper.getInstance().getText(lang, "HEROCLASS_"+htypeidx) %></a> | 

<%
   }
%>
</div>
</div>
</div>
<%
   for(int htypeidx = 0;htypeidx<=3;htypeidx++) {
%>


<div class="panel-body">
<div class="panel panel-default">
<div class="panel-heading"><a name="type_<%= htypeidx%>"></a><%=LocalizationHelper.getInstance().getText(lang, "HEROCLASS_"+htypeidx) %></div>
<div class="panel-body">
<table class="table table-striped">
<tr>
<th><%=LocalizationHelper.getInstance().getText(lang, "BTN_SORT_TYPE_HERO") %></th>
<th><%=LocalizationHelper.getInstance().getText(lang, "COMMON_LEADERSKILL") %></th>
<th>S1</th>
<th>S2</th>
<th>S3</th>
<th><%=LocalizationHelper.getInstance().getText(lang, "SKILL_TYPE_2") %></th>
<th>S4</th>
<th>S5</th>
<th>T2 <%=LocalizationHelper.getInstance().getText(lang, "HERO_SUBTYPE_202") %></th>
</tr>

<%
  for(HeroSkill ks:HeroSkillDB.getInstance().getHeroesByType(""+htypeidx)) {

%>

<tr>
<td><div><div class="allhead" style=" background-position: -<%= ((ks.getImagePositionId()-1)%10)*64 %>px -<%= ((int)Math.floor((ks.getImagePositionId()-1)/10))*64 %>px;"></div>
<%
if("0".equals(ks.getUniformId())) {
    out.write(LocalizationHelper.getInstance().getText(lang, "HERO_"+ks.getMffHeroId())+"\t");

}
else {
    out.write(LocalizationHelper.getInstance().getText(lang, "ITEM_"+ks.getUniformId())+"\t");
}
%>
</div>
</td>
     
     <%
        for(int i=0;i<=7;i++) {
  
     %>
     <td>
     <%
     
     String skill = ks.getSkills().get(i);
     if(skill.contains(",")) {
         String[] ss = skill.split(",");
         for(int x=0,size=ss.length;x<size;x++) {
             out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+ss[x])+", ");
             
         }
     }
     else {
         out.write(LocalizationHelper.getInstance().getText(lang, "ABILITY_"+ks.getSkills().get(i)));
     }
     %>
     </td>

<%

        }
%>

    </tr>
<%

  }
%>

</table>
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

<%
   }
%>





<jsp:include page="./footer_include.jsp" />
</body>
</html>