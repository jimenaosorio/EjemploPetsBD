<%@include file="templates/header.jsp" %>
<%@include file="templates/menu.jsp" %>

<c:set var="categorias" scope="page" value="<%=servicio.getCategorias()%>" />
<c:set var="productos" scope="page" value="<%=servicio.getProductos()%>"/>

<div class="row">
    <div class="col s6 offset-s3">
        <div class="card-panel">
            <h4>Agregar Producto</h4>
            <form action="control.do" method="post" enctype="multipart/form-data">
                <div class="input-field col s12">
                    <input name="nombre" id="nombre" type="text" class="validate">
                    <label for="nombre">Nombre</label>
                </div>
                <div class="input-field col s12">
                    <input name="precio" id="precio" type="text" class="validate">
                    <label for="precio">Precio</label>
                </div>
                <div class="input-field col s12">
                    <input name="unidad" id="unidad" type="text" class="validate">
                    <label for="unidad">Unidades</label>
                </div>
                <div class="input-field col s12">
                    <textarea name="descripcion" class="materialize-textarea col s12"></textarea>
                    <label for="descripcion">Descripcion</label>
                </div>
                <label for="categoria">Categoria</label>
                <select name="idcategoria" class="col s12" id="categoria">
                    <c:forEach items="${pageScope.categorias}" var="c">
                        <option value="${c.idCategoria}">${c.nombreCategoria}</option>
                    </c:forEach>
                </select>
                
                <br/><br/><br/>
                <div class="file-field input-field col s12">
                    <div class="btn">
                        <span>Foto</span>
                        <input type="file" name="foto"/>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate col s6" type="text"/>
                    </div>
                </div>
                <br/><br/><br/>
                <div class="col s12">
                    <button name="boton" value="nuevoproducto" class="btn right">Ingresar</button>
                    <br/><br/><br/>
                </div>
                
            </form>
            <div class="col s12">
                ${requestScope.msg}
            </div>
            <br/><br/><br/>
        </div>
    </div>
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