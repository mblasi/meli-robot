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

      <g:set var="auth" value="${session.user != null}"/>
      <g:set var="dataService" bean="dataService"/>

      <% products.each { product -> %>
		<g:set var="img" value="${product?.image}" />
		<g:set var="baseDir" value="${grailsApplication.config.folders.images}" />
		<p>
		  <g:img dir="${baseDir}" file="${img}" witdh="60" height="60"/>
          <br>
		  <%= "${product?.name}" %> </em><strong>$<%= "${product?.price}" %></strong>
          <br>
		  <em><%="${product?.stock_qty}" %> left!
		  <br>
		  <small><%= "${product?.description}" %></small>
	      <br>
		  <g:if test="${dataService.isPublished(product)}">
            <strike><%="publised!"%></strike>
          </g:if>
		  <g:else>
		    <g:if test="${auth == true}">
              <a href="<g:createLink action='publish' params='[id: "${product._id}" ]'/>">publish!</a>
			</g:if>
            <g:else>
              <span style="color: transparent; text-shadow: 0 0 5px #000;">publish!</i>
            </g:else>
		  </g:else>
        </p>
      <%}%>
   </body>
</html>
