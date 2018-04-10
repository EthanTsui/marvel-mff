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
<title><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_MENU_1002006") %> - Marvel Future Fight</title>
<jsp:include page="./head_include.jsp" />

</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LocalizationHelper.getInstance().getText(lang, "COSTUME") %></h1>
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
<th><%=LocalizationHelper.getInstance().getText(lang, "COSTUME") %></th>

<%
  for(int i=2;i<=6;i++) {
%>

<th><%=i %>: <%=LocalizationHelper.getInstance().getText(lang, "GRADE_NAME_"+i) %></th>

<%
  }
%>

</tr>

<%
  for(Uniform uni:UniformDB.getInstance().getUniformsByType(""+htypeidx)) {

%>

<tr>
<td><div class="bg-info"><div class="allhead" style=" background-position: -<%= ((uni.getImgidx()-1)%10)*64 %>px -<%= ((int)Math.floor((uni.getImgidx()-1)/10))*64 %>px;"></div><%=LocalizationHelper.getInstance().getText(lang, "ITEM_"+uni.getUniformId()) %>
</div>
</td>
     
     <%
        for(int i=1;i<=5;i++) {
            Uniform runi = uni.getRequiredUniforms().get(i);
            
     %>
     <td>
     <div class="allhead" style=" background-position: -<%= ((runi.getImgidx()-1)%10)*64 %>px -<%= ((int)Math.floor((runi.getImgidx()-1)/10))*64 %>px;"></div>
     <%=LocalizationHelper.getInstance().getText(lang, "ITEM_"+runi.getUniformId()) %>
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