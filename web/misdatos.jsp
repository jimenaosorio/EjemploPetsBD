<%@include file="templates/header.jsp" %>
<%@include file="templates/menu.jsp" %>

<div class="row">
    <div class="col s6 offset-s3">
        <div class="card-panel">
            <h4>Mis Datos</h4>
            <form action="control.do" method="post">
                <div class="input-field col s12">
                    <input disabled="disabled" name="rut" id="rut" type="text" class="validate" value="${sessionScope.admin.rutUser}"/>
                    <label for="rut">RUT</label>
                </div>
            <div class="input-field col s12">
                <input disabled="disabled" name="nombre" id="nombre" type="text" class="validate" value="${sessionScope.admin.nombreUser}"/>
                <label for="nombre">Nombre</label>
            </div>
            <div class="input-field col s12">
                <input disabled="disabled" name="apellido" id="apellido" type="text" class="validate" value="${sessionScope.admin.apellidoUser}"/>
                <label for="apellido">Apellido</label>
            </div>
            <div class="input-field col s12">
                <input disabled="disabled" name="email" id="email" type="text" class="validate" value="${sessionScope.admin.emailUser}"/>
                <label for="email">E-Mail</label>
            </div>
            <div class="input-field col s12">
                <input name="email2" id="email2" type="text" class="validate" />
                <label for="email2">Nuevo E-Mail</label>
            </div>  
            <div class="input-field col s12">
                <input name="clave" id="clave" type="password" class="validate">
                <label for="clave">Clave</label>
            </div>
            <div class="input-field col s12">
                <input name="clave2" id="clave2" type="password" class="validate">
                <label for="clave2">Confirme la clave</label>
            </div>
            <div class="col">
                <button class="btn right" name="boton" value="editardatos">Actualizar</button>
                <br/><br/><br/>
            </div>
            </form>
                <br/><br/><br/>
        <p class="red-text">${requestScope.msg}</p>
        </div>
    </div>
</div>

<%@include file="templates/footer.jsp" %>