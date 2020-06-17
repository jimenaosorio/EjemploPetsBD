<%@include file="templates/header.jsp" %>

<div class="row">
    <div class="col s4 offset-s4 z-depth-3">
        <h4>Ingreso</h4>
        <form action="control.do">
            <div class="input-field col s12">
                <input name="rut" id="rut" type="text" class="validate">
                <label for="rut">RUT</label>
            </div>
            <div class="input-field col s12">
                <input name="clave" id="clave" type="password" class="validate">
                <label for="clave">Clave</label>
            </div>
            <div class="col">
                <button class="btn right" name="boton" value="login">Entrar</button>
                <br/><br/><br/>
            </div>
            <div class="col">
                <a href="registro.jsp">Si no tienes cuenta regístrate aquí</a>
            </div>
        </form>
        <br/><br/>
        <p class="red-text">${requestScope.msg}</p>
    </div>
</div>


<%@include file="templates/footer.jsp" %>