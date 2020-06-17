<%@include file="templates/header.jsp" %>

<div class="row">
    <div class="col s4 offset-s4 z-depth-3">
        <h4>Registro de Usuarios</h4>
        <form method="post" action="control.do">
            <div class="input-field col s12">
                <input name="rut" id="rut" type="text" class="validate">
                <label for="rut">RUT</label>
            </div>
            <div class="input-field col s12">
                <input name="nombre" id="nombre" type="text" class="validate">
                <label for="nombre">Nombre</label>
            </div>
            <div class="input-field col s12">
                <input name="apellido" id="apellido" type="text" class="validate">
                <label for="apellido">Apellido</label>
            </div>
            <div class="input-field col s12">
                <input name="email" id="email" type="text" class="validate">
                <label for="email">E-Mail</label>
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
                <button class="btn right" name="boton" value="registrar">Registrarse</button>
                <br/><br/><br/>
            </div>
        </form>
        <br/><br/><br/>
        <p class="red-text">${requestScope.msg}</p>
    </div>
</div>

<%@include file="templates/footer.jsp" %>