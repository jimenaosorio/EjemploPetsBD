<%@include file="templates/header.jsp" %>
<%@include file="templates/menu.jsp" %>

<c:set var="categorias" scope="page" value="<%=servicio.getCategorias()%>" />
<c:set var="productos" scope="page" value="<%=servicio.getProductos()%>"/>

<div class="row">
    <div class="col s6 offset-s3">
        <div class="card-panel">
            <h4>Productos</h4>
            
            <table class="bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Unidades</th>
                    <th>Categoria</th>
                    <th>Foto</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pageScope.productos}" var="p">
                        <tr>
                            <td>${p.idProducto}</td>
                            <td>${p.nombreProducto}</td>
                            <td>${p.precioProducto}</td>
                            <td>${p.unidadesProducto}</td>
                            <td>${p.categoria.nombreCategoria}</td>
                            <td>
                                <xx:tagImage array="${p.fotoProducto}" tam="50"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<%@include file="templates/footer.jsp" %>