<nav>
    <div class="nav-wrapper">
      <a href="#" class="brand-logo">
          <c:if test="${not empty sessionScope.admin}">
              Bienvenido ${sessionScope.admin.nombreUser} <!-- nombreUser est� en la base de datos -->
          
          </c:if>
          <c:if test="${not empty sessionScope.persona}">
              Bienvenido ${sessionScope.persona.nombreUser}
              
          </c:if>
      
       </a>
              
      <ul id="nav-mobile" class="right hide-on-med-and-down">
          <c:if test="${not empty sessionScope.admin}">
            <li><a href="misdatos.jsp">Mis Datos</a></li>
            <li><a href="categoria.jsp">M�dulo Categor�a</a></li>
            <li><a href="admin_producto.jsp">M�dulo Productos</a></li>
            <li><a href="#">M�dulo Ventas</a></li>
            <li><a href="salir.jsp">Cerrar Sesi�n</a></li>
          </c:if>
          <c:if test="${not empty sessionScope.persona}">
              <li><a href="misdatos2.jsp">Mis Datos</a></li>
              <li><a href="#">Carrito de compras</a></li>
              <li><a href="producto.jsp">Productos</a></li>
              <li><a href="salir.jsp">Cerrar Sesi�n</a></li>
          </c:if>
          
        
      </ul>
    </div>
  </nav>
