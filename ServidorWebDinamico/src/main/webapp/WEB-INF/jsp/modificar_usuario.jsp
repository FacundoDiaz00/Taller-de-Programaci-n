<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="logica.datatypes.DTProveedor"%>
<%@page import="logica.datatypes.DTTurista"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Turismo UY</title>

<!--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilosComun.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alta_de_actividad_turistica.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.2.0-web/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil_de_usuario.css">
</head>



<body>
<div class="bs-example">
                            <!-- Button HTML (to Trigger Modal) -->
                            <button type="button" class="btn btn-lg btn-primary">Editar perfil</button>

                            
                            <%
                            DTUsuario usuario = (DTUsuario)session.getAttribute("usuarioLogeado");
                            if (usuario instanceof DTProveedor){
                            %>	
                            <!-- Modal HTML -->
                            <div id="myModal" class="modal fade" tabindex="-1">
	                                <div class="modal-dialog">
	                                    <div class="modal-content" style ="width: 800px;height: 600px;">
	                                        <div class="modal-header">
	                                            <h5 class="modal-title">Modificar Usuario</h5>
	                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
	                                        </div>
	                                        
	                                        
		                                     <form class="modal-body" id="form-modificar-usuario" style="width: 790px" name="modificarUsuarioForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/ConsultaDeUsuario">
	
	                                            <div class="contenedorinput mb-3">
	                                                <span class="input-label">Foto de Perfil:</span>
	                                                <img style="width: 160px; height: 120px; margin:20px" src="../img/la_chiqui.png">
	                                                <input id="input-imagen" type="file" accept="image/*"  class="form-control" placeholder="Imagen" aria-label="Imagen" aria-describedby="basic-addon1">
	                                            </div>
	
	                                            <div class="popUp">
	                                                <div>
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label" >Nickname:</span>
	                                                        <input id="input-nickname" type="text" required class="form-control" placeholder="lachiqui" aria-label="Nickname" aria-describedby="basic-addon1" readonly>
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Nombre:</span>
	                                                        <input id="input-nombre" type="text" required class="form-control" placeholder="Rosa María" aria-label="Nombre" aria-describedby="basic-addon1">
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Apellido: </span>
	                                                        <input id="input-apellido" type="text" required class="form-control" placeholder="Martínez" aria-label="Apellido" aria-describedby="basic-addon1">
	                                                    </div>
	                                                    
	                                                    <div class="contenedorinput mb-3" id="nacionalidad-field">
	                                              		  <span class="input-label">Nacionalidad: </span>
	                                                		<input id="input-nacionalidad" type="text" class="form-control" placeholder="Argentina" aria-label="Imagen" aria-describedby="basic-addon1">
	                                         		   </div>
	
	                                                </div>
	
	                                                <div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Fecha de nacimiento:</span>
	                                                        <input id="input-fecha-nacimiento" type="date" required class="form-control" placeholder="23/2/1927" aria-label="Fecha nacimiento" aria-describedby="basic-addon1">
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Contraseña: </span>
	                                                        <input id="input-contraseña" type="password" required class="form-control" placeholder="Contraseña" aria-label="Nickname" aria-describedby="basic-addon1">
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label"> Correo electronico:</span>
	                                                        <input id="input-correo" type="password" required class="form-control" placeholder=" mirtha.legrand.ok@hotmail.com.ar" aria-label="Nickname" aria-describedby="basic-addon1" readonly>
	                                                    </div>
														
	                                                
	                                                </div>
	
	                                            </div>
	
	                                            
	</form>
	                                        
	                                        <div class="modal-footer" style="display: flex;justify-content: space-between;">
	                                            <button type="button" class="btn btn-secondary close" data-dismiss="modal">Cancelar</button>
	                                            <button type="button" class="btn btn-primary">Guardar</button>
	                                        </div>
	                                    </div>
	                                    	
	                                </div>
	                            </div>
	                        </div>
	                        
	                        
	                        
	                        
                            <% } 
                            else { 
                            %>
                            	
                            <%}
                            %>
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
					


    <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error al crear la salida turística" , mensajeError , 200);
    </script>
    <%} %>
    
    <%if(request.getAttribute("exito") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('Exito', "Operacion completada" , "Se ha realizado un alta de salida satisfactoriamente" , 500);
    </script>
    <%} %>
        <script>
        $(document).ready(function(){
            $(".btn").click(function(){
                $("#myModal").modal('show');
            });
            $(".close").click(function(){
                $("#myModal").modal('hide');
            });
        });
    </script>
</body>
</html>