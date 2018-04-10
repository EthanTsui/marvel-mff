<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ethan.marvel.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%!
DecimalFormat df2 = new DecimalFormat("##0.0");

String getHeroId(List<Hero> heroes) {
    StringBuilder sb = new StringBuilder(128);
    sb.append(heroes.get(0).getHeroId());
    for(int i=1,size=heroes.size();i<size;i++) {
        sb.append(","+heroes.get(i).getHeroId());
    }
    return sb.toString();
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Marvel Future Fight Team Combination and Team Up Suggestion</title>

<jsp:include page="./head_include.jsp" />

<style>
 div#layout {width:98%;height:100%;}
        div.cell {float:left;width:240px;height:70px;border:solid 1px gray;margin:4px;}
        div.cell2 {float:left;width:50px;height:40px; margin:5px;}
img {
vertical-align:middle;
}
.helper {
    display: inline-block;

overflow: auto;
    height: 100%;
    vertical-align: middle;
    line-height:20px;
}
</style>


<script>

<%
List<Hero> cheroes=HeroDB.getInstance().listByHeroType("c");
List<Hero> bheroes=HeroDB.getInstance().listByHeroType("b");
List<Hero> sheroes=HeroDB.getInstance().listByHeroType("s");
List<Hero> uheroes=HeroDB.getInstance().listByHeroType("u");


%>

   var cHeroIds = [<%=getHeroId(cheroes) %>];
   var bHeroIds = [<%=getHeroId(bheroes) %>];
   var sHeroIds = [<%=getHeroId(sheroes) %>];
   var uHeroIds = [<%=getHeroId(uheroes) %>];
   
   
 function query() {
	 var checkboxes = document.getElementsByName('herochk');
	 var herolist="";
	 var heroCounter=0;
	 for (var i=0; i<checkboxes.length; i++) {
	     if (checkboxes[i].checked) {
	         herolist+=checkboxes[i].value+"_";
	         heroCounter++;
	     }
	 }
	 
	 if(heroCounter>50) {
		 alert("Please select <= 50 heroes, thanks~");
		 return;
	 }
	 
	 
	 if(herolist.length>0) {
		 herolist=herolist.substring(0,herolist.length-1);
	 }
	 
	 
	 
	 document.getElementById("herolist").value=herolist;
	 
	 var prioheros = document.getElementsByName("priohero");
	 var priohero = "";
	 for(var i=0;i<prioheros.length;i++) {
		 if(prioheros[i].checked && document.getElementById("priohero"+(i+1)).style.display!="none") {
			 priohero+=prioheros[i].value+"_";
		 }
	 }
	 
	 if(priohero.length>0) {
		 priohero=priohero.substring(0,priohero.length-1);
	 }
	 document.getElementById("heroteam").value=priohero;
	 
	 document.getElementById("form1").submit();
 }

 function uncheckAll() {
	 var checkboxes = document.getElementsByName('herochk');
     for (var i=0; i<checkboxes.length; i++) {
         checkboxes[i].checked=false;
         document.getElementById("herodiv"+(i+1)).style.background="";
         document.getElementById("priohero"+(i+1)).style.display="none";
         document.getElementById("leaderhero"+(i+1)).style.display="none";
         
     }
 }
 
 function checkAll(herotype) {
	 var herolist = uHeroIds;
	 if(herotype=="c") {
		 herolist=cHeroIds;
	 }
	 else if(herotype=="b") {
		 herolist=bHeroIds;
	 }
	 else if(herotype=="s") {
		 herolist=sHeroIds;
	 }
	 var checkboxes = document.getElementsByName('herochk');
	 for(var i=0;i<herolist.length;i++) {
		 console.log(herolist[i]);
		 document.getElementById("chkhero"+herolist[i]).checked=true;
		 document.getElementById("herodiv"+herolist[i]).style.background="#dff0d8";
         document.getElementById("priohero"+herolist[i]).style.display="";
         document.getElementById("leaderhero"+herolist[i]).style.display="";
	 }
	 
 }
 
 function divclick(chkid) {
	 var d = document.getElementById("chkhero"+chkid);
	 d.checked=!d.checked;
	 if(d.checked) {
		  document.getElementById("herodiv"+chkid).style.background="#dff0d8";
		  document.getElementById("priohero"+chkid).style.display="";
		  document.getElementById("leaderhero"+chkid).style.display="";
          
	 }
	 else {
		 document.getElementById("herodiv"+chkid).style.background="";
		 document.getElementById("priohero"+chkid).style.display="none";
		 //document.getElementById("leaderhero"+chkid).checked=false;
		 document.getElementById("leaderhero"+chkid).style.display="none";
         
	 }
 }

 function cancelBubble(e) {
	 var evt = e ? e:window.event;
	 if (evt.stopPropagation)    evt.stopPropagation();
	 if (evt.cancelBubble!=null) evt.cancelBubble = true;
	}
 
 function generateHeroTeam() {
	 var checkboxes = document.getElementsByName('herochk');
	 
	 for (var i=0; i<checkboxes.length; i++) {
         if (document.getElementById("chkhero"+(i+1)).checked) {
        	 
        	 
             document.getElementById("priohero"+(i+1)).style.display="";
             document.getElementById("leaderhero"+(i+1)).style.display="";
             
         }
         else {
        	 document.getElementById("priohero"+(i+1)).style.display="none";
        	 document.getElementById("leaderhero"+(i+1)).style.display="none";
         }
     }
 }
 
 
</script>

</head>
<body>

<%
String lang="en";
if(request.getParameter("lang")!=null && request.getParameter("lang").length()>0) {
    lang=request.getParameter("lang").trim();
}

%>
<jsp:include page="./header.jsp" />

<%
  if(request.getAttribute("heroteam")!=null) {


%>
<div class="alert alert-success" role="alert"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.see.result.below") %></div>
<%
}%>

<input type="button" class="btn btn-default" name="uncheck" value="<%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.unselect.all") %>" onclick="uncheckAll()"/>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.heroes") %></h3>
  </div>
  <div class="panel-body">
  <div class="panel panel-default">
  <div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.c") %></div>
  <div class="panel-body">
  <div><input type="button" name="bsac" onclick="checkAll('c')" value="Select All"/></div>
  <%
  List<Hero> heroes = HeroDB.getInstance().getHeros();
  HashMap<Integer, Integer> list = new HashMap<Integer, Integer>();
  if(request.getParameter("herolist")!=null && request.getParameter("herolist").length()>0) {
      String[] str = request.getParameter("herolist").split("_");
      for(String s:str) {
          list.put(Integer.parseInt(s), Integer.parseInt(s));
      }
  }

  
  for(Hero hero:cheroes) {
      %>



      <div class="cell" onclick="divclick(<%=hero.getHeroId() %>);" id="herodiv<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      style="background:#dff0d8"
      <%
      } %>
      
      ><div class="media"><div class="media-left"><input type="checkbox" class="helper" name="herochk" id="chkhero<%=hero.getHeroId() %>" onclick="cancelBubble(event)" value="<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      checked="checked"
      
      <%
      }
      %>
      
      ><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> </div><div class="media-right" style="width:120px;"><span style="font-size:12px;"> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></span></div></div></div>
      
     

             <%

     }
     
     %>
    
  </div>
</div>
</div>

  <div class="panel-body">
  <div class="panel panel-default">
  <div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.b") %></div>
  <div class="panel-body">
  <div><input type="button" name="bsab" onclick="checkAll('b')" value="Select All"/></div>
  <%
  for(Hero hero:bheroes) {
      %>



      <div class="cell" onclick="divclick(<%=hero.getHeroId() %>);" id="herodiv<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      style="background:#dff0d8"
      <%
      } %>
      
      ><div class="media"><div class="media-left"><input type="checkbox" class="helper" name="herochk" id="chkhero<%=hero.getHeroId() %>" onclick="cancelBubble(event)" value="<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      checked="checked"
      
      <%
      }
      %>
      
      ><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></div><div class="media-right" style="width:120px;"><span style="font-size:12px;"> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></span></div></div></div>
      
     

             <%

     }
     
     %>
    
  </div>
</div>
</div>

  <div class="panel-body">
  <div class="panel panel-default">
  <div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.s") %></div>
  <div class="panel-body">
  <div><input type="button" name="bsas" onclick="checkAll('s')" value="Select All"/></div>
  <%
  for(Hero hero:sheroes) {
      %>



      <div class="cell" onclick="divclick(<%=hero.getHeroId() %>);" id="herodiv<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      style="background:#dff0d8"
      <%
      } %>
      
      ><div class="media"><div class="media-left"><input type="checkbox" class="helper" name="herochk" id="chkhero<%=hero.getHeroId() %>" onclick="cancelBubble(event)" value="<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      checked="checked"
      
      <%
      }
      %>
      
      ><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></div><div class="media-right" style="width:120px;"><span style="font-size:12px;"> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></span></div></div></div>
      
     

             <%

     }
     
     %>
    
  </div>
</div>
</div>


  <div class="panel-body">
  <div class="panel panel-default">
  <div class="panel-heading"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type.u") %></div>
  <div class="panel-body">
  <div><input type="button" name="bsau" onclick="checkAll('u')" value="Select All"/></div>
  <%
  for(Hero hero:uheroes) {
      %>



      <div class="cell" onclick="divclick(<%=hero.getHeroId() %>);" id="herodiv<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      style="background:#dff0d8"
      <%
      } %>
      
      ><div class="media"><div class="media-left"><input type="checkbox" class="helper" name="herochk" id="chkhero<%=hero.getHeroId() %>" onclick="cancelBubble(event)" value="<%=hero.getHeroId() %>"
      <%
      if(list.containsKey(hero.getHeroId())) {
          
      
      %>
      checked="checked"
      
      <%
      }
      %>
      
      ><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div></div><div class="media-right" style="width:120px;"><span style="font-size:12px;"> <%=LanguageHelper.getInstance().getHeroName(lang,hero.getHeroId()) %></span></div></div></div>
      
     

             <%

     }
     
     %>
    
  </div>
</div>
</div>



  </div>
</div>


<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.must.exist") %></h3>
  </div>
  <div class="panel-body">
<%
HashMap<Integer, Integer> listprio = new HashMap<Integer, Integer>();
if(request.getParameter("heroteam")!=null && request.getParameter("heroteam").length()>0) {
    //StringTokenizer st = new StringTokenizer(request.getParameter("heroteam").trim(),"_");
    
    String[] str = request.getParameter("heroteam").split("_");

    for(String s:str) {
        listprio.put(Integer.parseInt(s), Integer.parseInt(s));
    }
    
}


for(Hero hero:heroes) {
%>

<div class="cell2" id="priohero<%= hero.getHeroId() %>"><label><div style="float:left;"><input type="checkbox" name="priohero" value="<%= hero.getHeroId() %>"
<%
if(listprio.containsKey(hero.getHeroId())) {
%>
checked="checked"
<%
}%>
></div><div class="allhero36" style="float:left;background-position: -<%= ((hero.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*36 %>px;"></div></label></div>

<%
}
%>
  </div>
</div>



<form method="get" action="/marvel/QueryTeam" id="form1">
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.leader") %></h3>
  </div>
  <div class="panel-body">
  
  
  <div class="cell2" id="leaderhero0">
<label><input type="radio" name="leaderhero" value="0"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.any") %></label>
</div>


  
<%

int leader=0;
if(request.getParameter("leaderhero")!=null) {
    try {
        leader=Integer.parseInt(request.getParameter("leaderhero"));
    }
    catch(Exception err) { }
}
for(Hero hero:heroes) {
%>

<div class="cell2" id="leaderhero<%= hero.getHeroId() %>" style="width:55px"><label><div style="float:left;"><input type="radio" name="leaderhero" value="<%= hero.getHeroId() %>"
<%
if(leader==hero.getHeroId()) {
%>
checked="checked"
<%
}%>
></div><div class="allhero36" style="float:left;background-position: -<%= ((hero.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*36 %>px;"></div></label></div>

<%
}
%>
  </div>
</div>




<div>

<input type="hidden" name="herolist" id="herolist">
<input type="hidden" name="lang" id="lang" value="<%=(request.getParameter("lang")==null?"en":request.getParameter("lang")) %>">
<input type="hidden" name="heroteam" id="heroteam">

<div class="form-group">
  <label for="master"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.leader.master") %></label>
  <select class="form-control" id="master" name="master">
  <%
  
  if(request.getParameter("master")==null) {
      out.println("<option selected='selected'>6</option>");
  }
  else {
      out.println("<option selected='selected'>"+request.getParameter("master")+"</option>");
  }
  
  %>
  
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
        <option>5</option>
            <option>6</option>
  </select>
</div>

<div>
<div><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.sorter") %></div>
<select class="form-control" id="sorter" name="sorter">
  <%
  
  if(request.getParameter("sorter")==null || request.getParameter("sorter").equals("atk")) {
      out.println("<option selected='selected' value='atk'>"+LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.average.attack.bonus")+"</option>");
  }
  else {
      out.println("<option value='atk'>"+LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.average.attack.bonus")+"</option>");
  }
  
  if(request.getParameter("sorter")!=null && request.getParameter("sorter").equals("ns")) {
      out.println("<option selected='selected' value='ns'>"+LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.sorter.strikers")+"</option>");
    }
    else {
        out.println("<option value='ns'>"+LanguageHelper.getInstance().getInterfaceName(lang, "tq.select.sorter.strikers")+"</option>");
    }
  
  %>
</select>
</div>


<div><input type="button" class="btn btn-default" name="sub1" value="<%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.query") %>" onclick="query()"/></div>
</form>
<br/>
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

<div class="panel panel-success">
  <div class="panel-heading">
    <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.query.result") %></h3>
  </div>
  <div class="panel-body">
  
  <%
  if(request.getAttribute("heroteam")!=null) {


%>
<table class="table table-striped">
<tr>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.team") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.average.attack.bonus") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership.bonus") %></td>
<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.number.strikers") %></td>
</tr>

    <%
     List<HeroTeam> teams = (List<HeroTeam>)request.getAttribute("heroteam");
    for(int i=0,size=teams.size();i<size;i++) {
        HeroTeam team = teams.get(i);
    %>
    <tr>
    
    <tr>
    <td style="width:150px">
    
    <%
    for(Hero h:team.getHeros()) {
        %>
        <a href='./heroDetail.jsp?heroid=<%=h.getHeroId() %>&lang=<%=lang %>' data-ref='tq_result_<%=(i+1) %>'>
       <div class="allhero36" style="float:left; background-position: -<%= ((h.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((h.getHeroId()-1)/10))*36 %>px;"></div></a>
        <%
        
    }
    %>
    </td>
    <td><%
    int master=6;
    if(request.getParameter("master")!=null) {
        try {
            master=Integer.parseInt(request.getParameter("master"));
        }
        catch(Exception err) {}
    }
 out.println(df2.format(team.averageHeroAttack(master))+"%");
    %> </td>
    <td><%
    
 out.println(team.getLeaderAbilityString(lang,master));
    %> </td>
    <td><a href="./queryStriker.jsp?lang=<%=lang %>&heroteam=<%=team.getHeroidList("_") %>"><%= team.getPairs() %> (<%= df2.format(team.getAverageProb()*100f) %>%)</a></td>
    </tr>
<%

    }
%>

</table>

<%
  }
%>
  
  
  
  </div>
</div>

<jsp:include page="./footer_include.jsp" />
<script>
generateHeroTeam();
</script>
</body>
</html>