<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="A mobile game, Marvel Future Fight, related website. Provide information and team combination suggestion."/>
<meta name="keywords" content="Marvel,future fight,mobile game,hero"/>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
<style>
  .allhero {
  background: url("img/allheroes48.4.4.0.png") no-repeat;
  width:48px;
  height:48px;
  margin: 2px;
  display:block;
  }
   .allhero36 {
  background: url("img/allheroes36.4.4.0.png") no-repeat;
  width:36px;
  height:36px;
  margin: 2px;
    display:block;
  } 

  .allhead {
  background: url("img/allhead64.4.4.0.png") no-repeat;
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