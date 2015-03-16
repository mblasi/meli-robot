<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
   <title>Products list</title>
   <g:javascript library="jquery" plugin="jquery"/>
</head>
   <body>
      <h3>my products list</h3>

      <!-- TODO: check a better location for the template -->
      <g:render template="login"/>

      <% products.each { product -> %>
		<g:set var="img" value="${product?.image}" />
		<g:set var="baseDir" value="${grailsApplication.config.folders.images}" />
		<p>
		  <img src='${createLinkTo(dir: "${baseDir}", file: "${img}")}'>
		  <%= "${product?.name}" %>
          <em>(<%="${product?._id}" %>)</em> - <em><%="${product?.stock_qty}" %> left</em>
		  <br>
		  <small><%= "${product?.description}" %></small>

		  <g:if test="${product.published}">
            <br><%="Already publised!"%>
          </g:if>
		  <g:if test="${!product.published}">
            <button>publish!</button>
		  </g:if>
		  

        </p>
      <%}%>
   </body>
</html>
