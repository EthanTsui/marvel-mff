<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ethan.marvel.*"%>
<%@ page import="com.ethan.marvel.sorter.*"%>

<%@ page import="java.text.DecimalFormat"%>
<%!DecimalFormat df2 = new DecimalFormat("##0.0");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
// haha
    String lang = "en";
    if (request.getParameter("lang") != null && request.getParameter("lang").length() > 0) {
        lang = request.getParameter("lang").trim();
    }

    int heroid = 1;
    if (request.getParameter("heroid") != null) {
        try {
            heroid = Integer.parseInt(request.getParameter("heroid"));
        } catch (Exception err) {
        }
    }
    String sortby="atk";
    if (request.getParameter("sortby") != null && request.getParameter("sortby").length() > 0) {
        sortby = request.getParameter("sortby").trim();
    }
    Hero hero = HeroDB.getInstance().lookupHero(heroid);
%>

<title><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.detail")%>,
	<%=LanguageHelper.getInstance().getHeroName(lang, heroid)%>, Marvel Future Fight</title>
<jsp:include page="./head_include.jsp" />
	
</head>
<body>
	<jsp:include page="./header.jsp" />

	<div class="panel panel-success">

		<div class="panel-body">

			<table class="table table-striped">
				<tr>
					<td><div class="allhero" style="background-position: -<%= ((hero.getHeroId()-1)%10)*48 %>px -<%= ((int)Math.floor((hero.getHeroId()-1)/10))*48 %>px;"></div> <%=LanguageHelper.getInstance().getHeroName(lang, hero.getHeroId())%></td>
				</tr>

				<tr>
					<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type")%>:
						<%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.type." + hero.getHeroType())%></td>
				</tr>
				<tr>
					<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type")%>:
						<%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.attack.type." + hero.getAttackType())%>
					</td>
				</tr>
				<tr>
					<td><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.leadership")%>:
						<%=hero.getAbilityString(lang)%></td>
				</tr>

			</table>
		</div>

	</div>

	<div><a name="teamup"></a> </div>

	<div><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by")%>: 
	<a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>&sortby=atk&ref=hd_so_atk#teamup"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by.atk")%></a> | 
	<a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>&sortby=as&ref=hd_so_as#teamup"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by.as")%></a> | 
	<a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>&sortby=ms&ref=hd_so_ms#teamup"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by.ms")%></a> | 
	<a href="./heroDetail.jsp?heroid=<%=hero.getHeroId() %>&lang=<%=lang %>&sortby=do&ref=hd_so_do#teamup"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by.do")%></a>
	
	</div>
	   <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.title")%> - <%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by")%>: <%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.sort_by."+sortby)%></h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <tr>
                    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.team")%></td>
                    <td><%=LanguageHelper.getInstance().getHeroName(lang, heroid)%><%=LanguageHelper.getInstance().getInterfaceName(lang, "hd.teamup.hero.bonus")%></td>
                    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "team.bonus")%></td>
                    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.number.strikers") %></td>
                </tr>

                <%
                List<HeroTeam> suggestion = hero.getTeamupSuggestion(sortby);
                    for (HeroTeam team : suggestion) {
                        
                        out.println("<tr><td style='width:140px'>");
                        for (Hero h : team.getHeros()) {
                            %>
                            <a href='./heroDetail.jsp?heroid=<%=h.getHeroId() %>&lang=<%=lang %>&sortby=<%=sortby %>' data-ref='hd_tus'>
                            <div class="allhero36" style="float:left; background-position: -<%= ((h.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((h.getHeroId()-1)/10))*36 %>px;"></div></a>
<%
                        }

                        out.print("</td>");
                        
                        HashMap<String, Unit> au = team.getHeroDetail(6, hero);
                        
                        if("atk".equals(sortby)) {
                            float value=0f;
                            if(au.containsKey(hero.getAttackType()+"atk")) {
                                value+=au.get(hero.getAttackType()+"atk").getValue();
                                
                            }
                            if(au.containsKey("aatk")) {
                                value+=au.get("aatk").getValue();
                                
                            }
                            
                            if(value>0f) {
                                out.print("<td>"+value+"%</td>");
                            }
                            else {
                                out.print("<td> </td>");
                            }
                            
                        }
                        else {
                            if(au.containsKey(sortby)) {
                                out.print("<td>"+au.get(sortby).getData()+"</td>");
                            }
                            else {
                                out.print("<td> </td>");
                            }
                        }
                        %>
                        
                        <td><%=team.getAbilityString(lang) %></td>
<td><a href="./queryStriker.jsp?lang=<%=lang %>&heroteam=<%=team.getHeroidList("_") %>"><%= team.getPairs() %> (<%= df2.format(team.getAverageProb()*100f) %>%)</a></td>
                        </tr>
                <%
                    }
                %>

            </table>

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
        List<HeroTeam> teambonus = TeamBonusHelper.getInstance().queryHeroBelongTo(heroid);
    %>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title"><%=LanguageHelper.getInstance().getInterfaceName(lang, "team.bonus.header")%></h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <tr>
                    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "tq.result.team")%></td>
                    <td><%=LanguageHelper.getInstance().getInterfaceName(lang, "team.bonus")%></td>
                </tr>

                <%
                    for (HeroTeam team : teambonus) {
                        %>
                        <tr><td style='width:140px'>
                        
                        <%
                        for (Hero h : team.getHeros()) {
                            %>
                            <a href='./heroDetail.jsp?heroid=<%=h.getHeroId() %>&lang=<%=lang %>&sortby=<%=sortby %>' data-ref='hd_tb'>
                            <div class="allhero36" style="float:left; background-position: -<%= ((h.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((h.getHeroId()-1)/10))*36 %>px;"></div>
                            <%
                        }
                        %>
                       </td>
                       <td><div style="font-size:16px;height:36px;color:#3366FF"><%=LocalizationHelper.getInstance().getText(lang,"TEAM_SET_BUFF_"+team.getId()) %></div><div><%=team.getAbilityString(lang)  %></div></td>
                       </tr>
                <%
                    }
                %>

            </table>

        </div>
    </div>
    
    
	   <div class="panel panel-success">

        <div class="panel-body">

            <table class="table table-striped">
	               <tr>
                    <td><div><%=LanguageHelper.getInstance().getInterfaceName(lang, "striker")%><br /><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.average_probability_of_striker")%>:
                            <%=df2.format(hero.getAverageHelperPercentage() * 100f)%>%
                        </div>
                        <div>
                            <%
                            List<Striker> strikers = new ArrayList<Striker>(hero.getHelpers().values());
                            Collections.sort(strikers, new StrikerSorter());
                            
                                for (Striker s : strikers) {
                                    Hero h = s.getHero();
                            %>
                                    <div style='float:left; margin:5px'><div style='float:left;width:40px'>
                                    <a href='./heroDetail.jsp?heroid=<%=h.getHeroId() %>&lang=<%=lang %>&sortby=<%=sortby %>' data-ref='hd_st'>
                                    <div class="allhero36" style="float:left;background-position: -<%= ((h.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((h.getHeroId()-1)/10))*36 %>px;"></div>
                                            </a></div><div style='float:left'><span style='font-size:10px;color:gray'>
                                            <%= LanguageHelper.getInstance().getInterfaceName(lang, "qs.autotime." + s.getAutoType()) %>, <%=df2.format(s.getProb() * 100f) %>%<br/>
                                            <%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.cooltime") %>:<%=s.getCoolTime() %>s</span></div></div>
                            <%
                                }
                            %>
                            <div style='clear: both'></div>
                        </div></td>
                </tr>
                <tr>
                    <td><div><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.be_striker_of_whom")%><br /><%=LanguageHelper.getInstance().getInterfaceName(lang, "hero.be_striker_of_whom.probability")%>:
                            <%=df2.format(hero.getAverageRatioOfBeingHelper() * 100f)%>%
                        </div>
                        <div>
                            <%
                                List<Hero> whos = hero.getHelpWhos();
                            
                                for (Hero h : whos) {
                                    Striker s = h.getHelper(hero.getHeroId());
                            %>
                                    <div style='float:left; margin:5px'><div style='float:left;width:40px'>
                                    <a href='./heroDetail.jsp?heroid=<%=h.getHeroId() %>&lang=<%=lang %>&sortby=<%=sortby %>' data-ref='hd_hw'>
                                    <div class="allhero36" style="float:left; background-position: -<%= ((h.getHeroId()-1)%10)*36 %>px -<%= ((int)Math.floor((h.getHeroId()-1)/10))*36 %>px;"></div>
                                            </a>
                                            </div><div style='float:left'><span style='font-size:10px;color:gray'>
                                            <%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.autotime." + s.getAutoType()) %>, <%=df2.format(s.getProb() * 100f) %>%<br/>
                                            <%=LanguageHelper.getInstance().getInterfaceName(lang, "qs.cooltime") %>: <%=s.getCoolTime() %>s</span></div></div>
                                            
                            <%
                                }
                            %>
                            <div style='clear: both'></div>
                        </div></td>
                </tr>
	
	           </table>
        </div>

    </div>
	
	
	<jsp:include page="./footer_include.jsp" />
</body>
</html>