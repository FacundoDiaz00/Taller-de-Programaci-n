<%@page import="utils.Utiles"%>
<%@page import="publicar.usuarioturisticasservice.DtProveedor"%>
<%@page import="publicar.usuarioturisticasservice.DtTurista"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>





	<%! public String getTextWithoutNull(String value){
			return value == null ? "" : value;
		}
	%>
	
<div class="bs-example">
                            <!-- Button HTML (to Trigger Modal) -->
                            <button type="button" class="botonModificar btn btn-lg btn-primary">Editar perfil</button>

                            
                            <% DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogeado"); %>
                            <!-- Modal HTML -->
                            <div id="myModal" class="modal fade" tabindex="-1">
	                                <div class="modal-dialog">
	                                <% if (usuario instanceof DtTurista){ %>
	                                    <div class="modal-content" style =" width: 598px; height: 790px;">
	                                    
	                     			<% } else {%>
	                     				<div class="modal-content" style =" width: 598px; height: 955px;">
	                     			<% } %>
	                                        <div class="modal-header">
	                                            <h5 class="modal-title">Modificar Usuario</h5>
	                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
	                                        </div>
	                                        
	                                        
		                                     <form class="modal-body" id="form-modificar-usuario" style="width: 560px" name="modificarUsuarioForm" enctype="multipart/form-data" method="post" action="ConsultaDeUsuario">

																			
							                    <div class="contenedorinput mb-3" style="display: grid; grid-template-columns: 1fr auto; grid-column-gap: 20px; align-items: flex-end;">
							                    	<div> 
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
							                        	   
							                        <div style="display: flex; align-content: center; margin-bottom: 10px">
							                        	
	    												<span class="input-label"  style="margin-right: 5px" >Eliminar foto:</span>
		                                                <input id="input-borrar-foto" 
		                                                name="borrar_imagen" 
		                                                type="checkbox"
		                                                >
	                                                </div>
	                                               
	                                            
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
	                                                    
	                                                    <% if (usuario instanceof DtTurista){ %>
	                                                    <div class="contenedorinput mb-3" id="nacionalidad-field">
	                                              		  <span class="input-label">Nacionalidad: </span>
	                                                		<input id="input-nacionalidad" 
	                                                		name="modificar_nacionalidad"
	                                                		type="text" 
	                                                		class="form-control" 
	                                                		placeholder="" 
	                                                		aria-label="Imagen" 
	                                                		aria-describedby="basic-addon1"
	                                                		<% DtTurista tur = (DtTurista) usuario;  %>
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
	                                                        value=<%=Utiles.stringToLocalDate(usuario.getFechaNacStr()).toString()  %>
	                                                        >
	                                                    </div>
	                                                  
	                                                    
	                                                    <div class="contenedorinput mb-3">									                    
									                        <span class="input-label">Contraseña:<span class="obligatorio"> *</span></span>
									                        <input id="input-contrasenia" 
									                        	   type="password" 
									                        	   name="input-contrasenia" 
									                        	   class="form-control" 
									                        	   placeholder="Ingrese una contraseña" 
									                        	   aria-label="Contraseña" 
									                        	   aria-describedby="basic-addon1">
									                    </div>
									
									                    <div class="contenedorinput mb-3">
									                        <span class="input-label">Confirmación contraseña:<span class="obligatorio"> *</span></span>
									                        <input id="input-confirmacion-contrasenia" 
									                        	   type="password" 									                        	 
									                        	   class="form-control" 
									                        	   placeholder="Vuelva a ingresar la contraseña" 
									                        	   aria-label="Nickname" 
									                        	   aria-describedby="basic-addon1">
									                    </div>
														
	                                                
	                                                </div>
	
	                                            </div>
	                                            
	                                            
	                                            <% if (usuario instanceof DtProveedor){ 
	                                            	DtProveedor prov = (DtProveedor) usuario;
	                                            %>
	                                            <div class="contenedorinput mb-3" id="descripcion-general-field">
                                            <span class="input-label">Descripcion general:</span>
                                            <textarea 
                                            	rows = "4" cols = "60" 
                                            	name = "modificar_descripcion"
                                            	id="input-descripcionGeneral"
		                                           
		                                            required class="form-control" 
		                                            placeholder="Ingrese una descripcion general" 
		                                            aria-label="Descripcion general" 
		                                            aria-describedby="basic-addon1"
		                                           
                                            	><%= prov.getDescrpicionGeneral() %></textarea><br>
                                           
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
                                            value="<%= prov.getLink() == null ? "" : prov.getLink() %>"
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
	                        
	                        
	<script src="js/jquery-3.3.1.slim.min.js"></script>

    <script src="js/modificar_usuario.js"></script>
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
            $(".botonModificar").click(function(){
            	console.log('holllo')
                $("#myModal").modal('show');
            });
            $(".close").click(function(){
                $("#myModal").modal('hide');
            });
        });
    </script>
   