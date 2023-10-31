
<%-- <%@page import="logica.datatypes.DTUsuario"%> --%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
</head>
<body>
    
<div class="container">
    <div id="centerRow">
    <h1 style="justify-content: center; display: flex;margin-bottom: 40%;">Turismo.uy</h1>
        
          <form class="card" id="form-iniciar-sesion" name="iniciarSesionForm" method="post" action="IniciarSesion">
          		
                <div class="card-body">
                <div class="input-group mb-3 contenedorinput">
					<select id="idForm" name="idForm" class="form-select" aria-label="Default select example">
			                <option selected value="1">Email</option>
			                <option value="2">Nickname</option>
			            </select>
			        </div>  
			        <div class="input-group mb-3 contenedorinput">
			   
			            <% String email = "";%>
			            
                        <input id="input-correo-electronico" 
                         	   type="email" 
                         	   name="email" 
                         	   class="form-control" 
                         	   placeholder="Ingrese un correo" 
                         	   aria-label="Correo electronico" 
                         	   aria-describedby="basic-addon1"
                         	   value="<%=email%>">
                        	
                      	<% String nickname = "";%>
                      	<input id="input-nickname" 
                          	   type="hidden" 
                          	   name="nickname" 
                          	   class="form-control" 
                          	   placeholder="Ingrese un nickname" 
                          	   aria-label="nickname" 
                          	   aria-describedby="basic-addon1"
                          	   value="<%=nickname%>">
			        </div>

                    <div class="contenedorinput mb-3">
                    <% String password = ""; %>
                        <input id="input-contrasenia" 
                        	   type="password" 
                        	   required name="password" 
                        	   class="form-control" 
                        	   placeholder="Ingrese una contraseña" 
                        	   aria-label="Contraseña" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= password %>"
                        	   >
                    </div>
                   
		        <div style="display: flex; justify-content: center;">
		            <input type="submit" id="aceptar-boton" class="btn btn-success btn-lg btn-block" value="Ingresar">
		        </div>
                </div>
            </form>
    </div>
</div>
<div style="bottom: 0;position: absolute; width: 100%">
<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
</div>


<script>
        $(function(){
        $('#idForm').bind('change', function () {
        var valor = $(this).val(); // get selected value
        console.log("cambia el idForm: ", valor);
       	nickname = null;
    	email = null;
        if(valor == 1){
        	console.log("entre a 1");
        	$("#input-nickname")[0].type = "hidden";
        	$("#input-correo-electronico")[0].type = "email";
        }else{
        	$("#input-correo-electronico")[0].type = "hidden";
        	$("#input-nickname")[0].type = "text";
        }
         });
       });
</script>

    <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error al iniciar sesión" , mensajeError , 200);
    </script>
    <%} %>


</body>
</html>