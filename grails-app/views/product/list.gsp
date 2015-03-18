<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
   <title>Products list</title>
   <g:javascript library="jquery" plugin="jquery"/>
</head>
   <body>
      <h3 >my products list</h3>

      <!-- TODO: check a better location for the template -->
      <g:render template="login"/>

      <g:render template="product" collection="${products}"/>

   </body>
</html>
