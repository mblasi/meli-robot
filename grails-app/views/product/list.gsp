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

      <g:set var="auth" value="${session.user != null}"/>

      <% products.each { product -> %>
		<g:set var="img" value="${product?.image}" />
		<g:set var="baseDir" value="${grailsApplication.config.folders.images}" />
		<p>
		  <g:img dir="${baseDir}" file="${img}" witdh="60" height="60"/>
		  <br>
		  <%= "${product?.name}" %>
          <em>(<%="${product?._id}" %>)</em> - <em><%="${product?.stock_qty}" %> left! </em><strong>$<%= "${product?.price}" %></strong>
		  <br>
		  <small><%= "${product?.description}" %></small>
	      <br>
		  <g:if test="${product.published}">
            <%="Already publised!"%>
          </g:if>
		  <g:else>
            <button ${!auth ? 'disabled':''}>publish!</button>
		  </g:else>
		  

        </p>
      <%}%>
   </body>
</html>
