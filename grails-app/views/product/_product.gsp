<g:set var="auth" value="${session.user != null}" />
<g:set var="dataService" bean="dataService" />

<g:set var="img" value="${it.image}" />
<g:set var="baseDir" value="${grailsApplication.config.folders.images}" />
<p>
	<g:img dir="${baseDir}" file="${img}" witdh="60" height="60" />
	<br>
	<%= "${it.name}" %>
	</em><strong>$<%= "${it.price}" %></strong> <br> <em><%="${it.stock_qty}" %>
		left! <br> <small><%= "${it.description}" %></small> <br> <g:if
			test="${dataService.isPublished(it)}">
			<strike><a
				href="http://articulo.mercadolibre.com.ar/${dataService.meliId(it)}"><%="publised!"%></a></strike>
		</g:if> <g:else>
			<g:if test="${auth == true}">
				<a
					href="<g:createLink action='publish' params='[id: "${it._id}" ]'/>">publish!</a>
			</g:if>
			<g:else>
				<span style="color: transparent; text-shadow: 0 0 5px #000;">publish!</i>
			</g:else>
		</g:else>
</p>