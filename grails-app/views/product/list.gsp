<html>
   <body>
      <% products.each { product -> %>
         <p><%="${product?._id} - ${product?.name}" %></p>
      <%}%>
   </body>
</html>
