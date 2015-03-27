<!doctype html>

<%@ page contentType="text/html;charset=UTF-8" %>

<!--[if IE 7]>    <html class="no-js lt-ie10 lt-ie9 lt-ie8 ie7" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie10 lt-ie9 ie8" lang="en"> <![endif]-->
<!--[if IE 9]>    <html class="no-js lt-ie10 ie9" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-js" lang="en"> <!--<![endif]-->

<head>
    <!-- Avoid script blocking -->
    <script></script>
    <meta charset="utf-8"/>
    <title>Products list</title>
    <g:javascript library="jquery" plugin="jquery"/>
    <!-- Chico UI Core stylesheet -->
	<!--    <asset:stylesheet src="chico.min.css"/> --> <!-- TODO: this fails trying to load icons -->
	<link rel="stylesheet" href="http://static.mlstatic.com/org-img/ch/ui/1.0.0/chico.min.css">
</head>
   <body>
    <asset:javascript src="chico.min.js"/>
      <h1>Axis Products</h1>

      <!-- TODO: check a better location for the template -->
      <g:render template="login"/>

      <g:render template="product" collection="${products}"/>

   </body>
</html>
