<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="logica.datatypes.DTActividadTuristicaDetalle"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Turismo UY</title>

<!--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilosComun.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alta_de_actividad_turistica.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome-free-6.2.0-web/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">




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
            <form class="card" id="form-alta-salida" name="altaSalidaForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/AltaDeSalida">
                <div class="card-body">
                    <h5 class="card-title">Alta de Salida Turística</h5>
                    
                    <% DTActividadTuristicaDetalle datosActividad = (DTActividadTuristicaDetalle) request.getAttribute("datosActividad"); %>

                    <div class="contenedorinput mb-3">
                    	<% String departamento = datosActividad.getDepartamento(); %>
                        <span class="input-label" id="labelDepartamento">Departamento:<span class="obligatorio"> *</span></span>
                        <input
                        	id="departamento"
                       	   	name="departamento" 
                        	required 
                        	class="form-control" 
                        	aria-label="Departamento"
                        	disabled
                        	value="<%= departamento %>">
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String actividad = datosActividad.getNombre(); %>
                    	
                        <span class="input-label">Actividad<span class="obligatorio"> *</span></span>
                        <input  id="actividad"
                        	   	name="actividad" 
                        		type="text" 
                        		required 
                        		class="form-control" 
                        		placeholder="" 
                        		aria-label="Actividad" 
                        		aria-describedby="basic-addon1"
                        		disabled
                        	   	value="<%= actividad %>">
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
                    	<% String fechaSalida = getTextWithoutNull((String)request.getAttribute("fechaSalida")); %>
                        <span class="input-label">Fecha:<span class="obligatorio"> *</span></span>
                        <input id="fechaSalida" 
                        	   type="date" 
                        	   required 
                        	   name="fechaSalida" 
                        	   class="form-control" 
                        	   placeholder="Ingrese fecha" 
                        	   aria-label="Fecha de salida" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= fechaSalida %>">
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String horaSalida = getTextWithoutNull((String)request.getAttribute("horaSalida")); %>
                        <span class="input-label">Hora:<span class="obligatorio"> *</span></span>
                        <input id="horaSalida" 
                        	   type="time" 
                        	   required 
                        	   name="horaSalida" 
                        	   class="form-control" 
                        	   placeholder="Ingrese hora" 
                        	   aria-label="Hora de salida" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= horaSalida %>">
                    </div>
                    
                    <div class="contenedorinput mb-3">
                    	<% String lugar = getTextWithoutNull((String)request.getAttribute("lugar")); %>
                        <span class="input-label">Lugar:<span class="obligatorio"> *</span></span>
                        <input 	type="text"
                           		id="lugar"
                       	   		name="lugar" 
                       	   	 	value="<%= lugar %>"
                        		required 
                        		class="form-control" 
                        		placeholder="Ingrese lugar" 
                        		aria-label="Lugar" 
                        		aria-describedby="basic-addon1">
                    </div>                    

                    <div class="contenedorinput mb-3">
                    	<% String cantMaxTur = getTextWithoutNull((String)request.getAttribute("cantMaxTur")); %>
                        <span class="input-label">Cantidad Máxima de Turistas:<span class="obligatorio"> *</span></span>
                        <div class="input-group">
                            <input 	type="number" 
                            		min="0" 
                            		id="cantMaxTur"
                        	   		name="cantMaxTur" 
                        	   	 	value="<%= cantMaxTur %>"
                            		required 
                            		class="form-control" 
                            		placeholder="Ingrese cantidad" 
                            		aria-label="Cantidad máxima de turistas" 
                            		aria-describedby="basic-addon1">
                            <span class="input-group-text" id="basic-addon1">Turistas</span>
                        </div>
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
    
</body>
</html>