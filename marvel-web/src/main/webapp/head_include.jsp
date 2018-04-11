<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="A mobile game, Marvel Future Fight, related website. Provide information and team combination suggestion."/>
<meta name="keywords" content="Marvel,future fight,mobile game,hero"/>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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