<!-- Here we should have the option to login to mercadolibre or see the loged in status -->
<g:if test="${session.user}">
  <%= "${session.user}" %> <a href="<g:createLink action='logout'/>">logout!</a>
</g:if>
<g:else>
   <a href="<g:createLink action='authenticate'/>">login to mercadolibre</a>
</g:else>