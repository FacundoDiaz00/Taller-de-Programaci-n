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

	<%! public String getTextWithoutNull(String value){
			return value == null ? "" : value;
		}
	%>
	
<div class="bs-example">
                            <!-- Button HTML (to Trigger Modal) -->
                            <button type="button" class="btn btn-lg btn-primary">Editar perfil</button>

                            
                            <% DTUsuario usuario = (DTUsuario)session.getAttribute("usuarioLogeado"); %>
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
							                        <span class="input-label">Imagen:</span>
							                        <input id="input-imagen"
							                        		name="modificar_img"
							                        	   type="file" 
							                        	   accept="image/*" 
							                        	   name="img" 
							                        	   class="form-control" 
							                        	   placeholder="Imagen" 
							                        	   aria-label="Imagen" 
							                        	   aria-describedby="basic-addon1" 
							                        	   >
	                                                
	                                            </div>
	
	                                            <div class="popUp">
	                                                <div>
	                                                    <div class="contenedorinput mb-3">
	                                                    
	                                                        <span class="input-label" >Nickname:</span>
	                                                        <input id="input-nickname" 
	                                                        name="nickname" 
	                                                        type="text" 
	                                                        required
	                                                        class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Nickname" 
	                                                        aria-describedby="basic-addon1" 
	                                                        disabled
	                                                        value="<%= usuario.getNickname() %>"
	                                                        >
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Nombre:</span>
	                                                        <% String modificar_nombre = usuario.getNombre(); %>
	                                                        <input id="modificar_nombre"
	                                                        name="modificar_nombre" 
	                                                        type="text" 
	                                                        required class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Nombre" 
	                                                        aria-describedby="basic-addon1"
	                                                        value="<%= modificar_nombre %>"
	                                                        >
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Apellido: </span>
	                                                        
	                                                        <input id="input-apellido" 
	                                                        name="modificar_apellido"
	                                                        type="text" 
	                                                        required class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Apellido" 
	                                                        aria-describedby="basic-addon1"
	                                                        value="<%= usuario.getApellido() %>"
	                                                        
	                                                        >
	                                                    </div>
	                                                    
	                                                    <% if (usuario instanceof DTTurista){ %>
	                                                    <div class="contenedorinput mb-3" id="nacionalidad-field">
	                                              		  <span class="input-label">Nacionalidad: </span>
	                                                		<input id="input-nacionalidad" 
	                                                		name="modificar_nacionalidad"
	                                                		type="text" 
	                                                		class="form-control" 
	                                                		placeholder="" 
	                                                		aria-label="Imagen" 
	                                                		aria-describedby="basic-addon1"
	                                                		<% DTTurista tur = (DTTurista) usuario;  %>
	                                                		value="<%= tur.getNacionalidad() %>"
	                                                		>
	                                         		   </div>
	                                         		     <% } %>
	
	                                                </div>
	
	                                                <div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Fecha de nacimiento:</span>
	                                                        <input id="input-fecha-nacimiento"
	                                                        name="modificar_fechaNac"
	                                                        type="date" 
	                                                        required 
	                                                        class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Fecha" 
	                                                        aria-describedby="basic-addon1"
	                                                        value=<%= usuario.getFechaNac().toString() %>
	                                                        >
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label">Contraseña: </span>
	                                                        <input id="input-contraseña" 
	                                                        type="password"
	                                                        name="modificar_contrasenia"
	                                                        required 
	                                                        class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Contraseña" 
	                                                        aria-describedby="basic-addon1"
	                                                        value="soy_una_contraseña"
	                                                        >
	                                                    </div>
	
	                                                    <div class="contenedorinput mb-3">
	                                                        <span class="input-label"> Correo electronico:</span>
	                                                        <input id="input-correo" 
	                                                        type="text" 
	                                                        required class="form-control" 
	                                                        placeholder="" 
	                                                        aria-label="Correo" 
	                                                        aria-describedby="basic-addon1" 
	                                                        value="<%= usuario.getCorreo() %>"
	                                                        disabled
	                                                        
	                                                        >
	                                                    </div>
														
	                                                
	                                                </div>
	
	                                            </div>
	                                            
	                                            
	                                            <% if (usuario instanceof DTProveedor){ 
	                                            		DTProveedor prov = (DTProveedor) usuario;
	                                            %>
	                                            <div class="contenedorinput mb-3" id="descripcion-general-field">
                                            <span class="input-label">Descripcion general:</span>
                                            <input id="input-descripcionGeneral"
                                            name="modificar_descripcion"
                                            type="text" 
                                            required class="form-control" 
                                            placeholder="Ingrese una descripcion general" 
                                            aria-label="Descripcion general" 
                                            aria-describedby="basic-addon1"
                                            value="<%= prov.getDescrpicionGeneral() %>"
                                        	>
                                        </div>

                                        <div class="contenedorinput mb-3" id="link-sito-web-field">
                                            <span class="input-label">Link sito web:</span>
                                            <input id="input-link"
                                            type="text"
                                             name="modificar_link"
                                            class="form-control"
                                            placeholder=""
                                            aria-label="Link"
                                            aria-describedby="basic-addon1"
                                            value="<%= prov.getLink() %>"
                                            >
                                        </div>
                                        <% } %>
                                        
                                           <div class="modal-footer" style="display: flex;justify-content: space-between;">
	                                            <button type="button" class="btn btn-secondary close" data-dismiss="modal">Cancelar</button>
	                                            <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
                                           </div>
											</form>
	                                        

	                                        
	                                    </div>
	                                    	
	                                </div>
	                            </div>
	                        </div>


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