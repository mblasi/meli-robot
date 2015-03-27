<g:if test="${session.user}">
  <%= "${session.user}" %> <a href="<g:createLink action='logout'/>"><button class="ch-btn">Logout</button></a>
</g:if>
<g:else>
   <a href="<g:createLink action='authenticate'/>"><button class="ch-btn">Login to MercadoLibre</button></a>
</g:else>