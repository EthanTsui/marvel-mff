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
<title><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_MENU_10") %></title>
<jsp:include page="./head_include.jsp" />
<style>
  .alliso8 {
  background: url("img/alliso8_36.png") no-repeat;
  width:36px;
  height:36px;
  margin: 2px;
  display:block;
  }

</style>
</head>
<body>
<jsp:include page="./header.jsp" />
<h1><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_MENU_10") %></h1>



<div class="panel-body">
<div class="panel panel-default">
<div class="panel-body">

<table class="table table-striped">
<tr>
<td style="width:200px"><%=LocalizationHelper.getInstance().getText(lang, "GUIDE_INFO_11") %></td>
<td> </td>

</tr>

    <%

    
    List<ISOSet> isosetList = ISOSetDB.getInstance().getList();
    
    for(ISOSet iso:isosetList) {
        
    %>
    <tr>
    <td>
    
    <div>
    <%
    for(int isoid:iso.getIsoList()) {
        %>
    <div class="alliso8" style="float:left;background-position: 0px -<%=isoid*36 %>px;"></div>
    <%
    }
    %>
    </div>

    </td>
    <td>
    <div style="font-size:16px;height:36px;color:#3366FF"><p><%=LocalizationHelper.getInstance().getText(lang, "ISO_SET_NAME_"+iso.getISOSetId()) %><p></div>
    <%
    HashMap<String, Unit> abilities = iso.getAbilities();
    for(String key:abilities.keySet()) {
    %>
    <div style="color:#FF6600"><%=LanguageHelper.getInstance().getAbilityName(lang, key)%>: <%=abilities.get(key).getData() %></div>


<%

}

%>
<div style="height:12px"> </div>
    <div><%= LanguageHelper.getInstance().getInterfaceName(lang, "qs.autotime." + iso.getAutoType()) %>, <%=iso.getAutoAbilityRate() %>%</div>
    <div>
    <%=LocalizationHelper.getInstance().getText(lang, "ABILITY_"+iso.getConditionAbilityId()) %> <%=iso.getConditionUnit().getData() %> <%=(iso.getConditionTime()>0f?"("+iso.getConditionTime()+"s)":"") %>
    </div>
    </td>
    
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



<jsp:include page="./footer_include.jsp" />
</body>
</html>