<g:set var="auth" value="${session.user != null}" />
<g:set var="dataService" bean="dataService" />

<div class="ch-box ch-box-lite">
	<h3><%= "${it.name}" %></h3>
	<br>
    <img src="${it.image}" witdh="60" height="60"></img>
	<br>
	<label>Price: </label><strong>$<%= "${it.price}" %></strong>
	<br>
	<div class="ch-box-icon ch-box-info">
	<i class="ch-icon-info-sign"></i><h2><%="${it.stock_qty}" %> left!</h2>
	</div>
	<p><%= "${raw(it.description)}" %></p>
	<br>
	<g:if test="${dataService.isPublished(it)}">
		<a href="http://articulo.mercadolibre.com.ar/${dataService.meliId(it)}"><button class="ch-btn ch-btn-small">published!</button></a>
    </g:if>
    <g:else>
		<g:if test="${auth == true}">
			<a class="ch-btn ch-btn-small" href="<g:createLink action='publish' params='[productId: "${it._id}", productName: "${it.name}", productDescription: "${it.description}", productPrice: "${it.price}", productStock: "${it.stock_qty}", productImage: "${it.image}" ]'/>">publish!</a>
		</g:if>
		<g:else>
			<button disabled class="ch-btn ch-btn-small">publish!</button>
		</g:else>
	</g:else>
</div>