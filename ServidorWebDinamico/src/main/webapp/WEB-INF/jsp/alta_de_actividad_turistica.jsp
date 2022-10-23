<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Turismo UY</title>

<!--    <link rel="stylesheet" href="css/bootstrap.min.css">-->
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/alta_de_actividad_turistica.css">





</head>
<body>
    
	<jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
	
	<%! public String getTextWithoutNull(String value){
			return value == null ? "" : value;
		}
	%>
	
	<%! public <E> List<E> getListWithoutNull(List<E> value){
			return value == null ? new ArrayList<E>() : value;
		}
	%>


    <section id="contenedor">
    
    	<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
        
        <div id="contenedorForm">
            <form class="card" id="form-alta-actividad" name="altaActividadForm" enctype="multipart/form-data" method="post" action="AltaDeActividad">
                <div class="card-body">
                    <h5 class="card-title">Crear Actividad Turística</h5>

                    <div class="contenedorinput mb-3">
                    	<% String departamento = getTextWithoutNull((String)request.getAttribute("departamento")); %>
                        <span class="input-label" id="labelDepartamento">Departamento:<span class="obligatorio"> *</span></span>
                        <select 
                        	id="departamento"
                       	   	name="departamento" 
                        	required 
                        	class="form-select" 
                        	aria-label="Departamentos">
                        
                        <%for (String dep: (List<String>) request.getAttribute("departamentos")) { 
                        	if (dep.equals(departamento)) { %>
                        		<option selected value="<%=dep%>"><%=dep%></option>
                        	<%} else {%>
                            	<option value="<%=dep%>"><%=dep%></option>
                        	<%}%>
						<%}%>
						
                        </select>
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String nombre = getTextWithoutNull((String)request.getAttribute("nombre")); %>
                        <span class="input-label">Nombre:<span class="obligatorio"> *</span></span>
                        <input  id="nombre"
                        	   	name="nombre" 
                        		type="text" 
                        		required 
                        		class="form-control" 
                        		placeholder="Ingrese un nombre" 
                        		aria-label="Nombre" 
                        		aria-describedby="basic-addon1"
                        	   	value="<%= nombre %>">
                    </div>

					<div class="contenedorinput mb-3">
						<% String descripcion = getTextWithoutNull((String)request.getAttribute("descripcion")); %>
                        <span class="input-label">Descripción:<span class="obligatorio"> *</span></span>
                        <textarea  	id="descripcion"
                        	   		name="descripcion" 
                        			required
                        			class="form-control" 
                        			placeholder="Ingrese una descripcion" 
                        			aria-label="Descripcion" 
                        			aria-describedby="basic-addon1"
                       	><%=descripcion%></textarea>
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String duracion = getTextWithoutNull((String)request.getAttribute("duracion")); %>
                        <span class="input-label">Duración:<span class="obligatorio"> *</span></span>
                        <div class="input-group">
                            <input 	type="number" 
                            		min="0" 
                            		id="duracion"
                        	   		name="duracion" 
                        	   	 	value="<%= duracion %>"
                            		required 
                            		class="form-control" 
                            		placeholder="Ingrese una duracion " 
                            		aria-label="Duracion" 
                            		aria-describedby="basic-addon1">
                            <span class="input-group-text" id="basic-addon1">Horas</span>
                        </div>
                    </div>

                    <div class="contenedorinput mb-3 hideArrows" >
                    	<% String costo = getTextWithoutNull((String)request.getAttribute("costo")); %>
                        <span class="input-label">Costo:<span class="obligatorio"> *</span></span>
                        <div class="input-group">
                            <input 	type="number"
                            		step="0.01"
                            		min="0" 
                            		id="costo"
                        	   		name="costo" 
                        	   	 	value="<%= costo %>"
                            		required 
                            		class="form-control" 
                            		placeholder="Ingrese un costo " 
                            		aria-label="Costo" 
                            		aria-describedby="basic-addon1">
                            <span class="input-group-text" id="basic-addon2">$</span>
                        </div>
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String ciudad = getTextWithoutNull((String)request.getAttribute("ciudad")); %>
                        <span class="input-label">Cuidad:<span class="obligatorio"> *</span></span>
                        <input 	type="text"
                           		id="ciudad"
                       	   		name="ciudad" 
                       	   	 	value="<%= ciudad %>"
                        		required 
                        		class="form-control" 
                        		placeholder="Ingrese una cuidad" 
                        		aria-label="Cuidad" 
                        		aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Imagen:</span>
                        <input id="input-imagen" 
                        	   type="file" 
                        	   accept="image/*" 
                        	   name="img" 
                        	   class="form-control" 
                        	   placeholder="Imagen" 
                        	   aria-label="Imagen" 
                        	   aria-describedby="basic-addon1">
                    </div>
                    
                    <div class="contenedorinput mb-3">
                        <span class="input-label">Categorías:</span>
                        <div id="contenedorCategorias">
                                <% 	int i = 0;
                                	List<String> categoriasSeleccionadas;
                                	if(request.getAttribute("categoriasSeleccionadas") != null){
                                		categoriasSeleccionadas = (List<String>) request.getAttribute("categoriasSeleccionadas");
                                	}else{
                                		categoriasSeleccionadas = new ArrayList();
                                	}
                                
                                	
                                	for (String cat: getListWithoutNull((List<String>) request.getAttribute("categorias"))) { %>
		                            <div class="form-check">
	                                	<input 	class="form-check-input opcion-categoria" 
			                                	id="categoria_<%=i %>"
		                        	   			name="categorias" 
	                                			type="checkbox"
	                                			<%= categoriasSeleccionadas.contains(cat) ? "checked" : "" %>                         			
	                                			value="<%= cat %>" 
	                                			id="flexCheckDefault">
		                                	<label class="form-check-label" for="flexCheckDefault">
		                                    	<%= cat %>
		                                	</label>
		                            </div>
                                <%
                                	i++;
                                } %>
                         </div>
                    </div>


                    <div id="botonera">
                        <a href="index.html" class="btn btn-danger">Cancelar</a>
                        <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
                    </div>

                </div>
            </form>
        </div>
        <div></div>


    </section>

   	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	<script src="js/alta_de_actividad_turistica.js"></script>
    
    <%if(request.getAttribute("motivoDeError") != null){ %>
	    <script>
	    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
	    	generarMensaje('error', "Error al crear la actividad turística" , mensajeError , 200);
	    </script>
    <%} %>
    
    <%if(request.getAttribute("exito") != null){ %>
	    <script>
	    	generarMensaje('success', "Operacion completada" , "Se ha realizado un alta de actividad satisfactoriamente" , 500);
	    </script>
    <%} %>
    
</body>
</html>