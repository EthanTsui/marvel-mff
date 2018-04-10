<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
<meta name="description" content="A mobile game, Marvel Future Fight, related website. Provide information and team combination suggestion."/>
<meta name="keywords" content="Marvel,future fight,mobile game,hero"/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
  .allhero {
  background: url("img/allheroes48.3.9.0.png") no-repeat;
  width:48px;
  height:48px;
  margin: 2px;
  display:block;
  }
   .allhero36 {
  background: url("img/allheroes36.3.9.0.png") no-repeat;
  width:36px;
  height:36px;
  margin: 2px;
    display:block;
  } 

  .allhead {
  background: url("img/allhead64.3.9.0.png") no-repeat;
  width:64px;
  height:64px;
  margin: 2px;
  display:block;
  }

</style>
<script>
$(document).ready(function() {
    $("[data-ref]").each(function() {
        $(this).attr("href", $(this).attr("href")+"&ref="+$(this).attr("data-ref"));
    });
});

</script>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
     (adsbygoogle = window.adsbygoogle || []).push({
          google_ad_client: "ca-pub-7688138317444669",
          enable_page_level_ads: true
     });
</script>