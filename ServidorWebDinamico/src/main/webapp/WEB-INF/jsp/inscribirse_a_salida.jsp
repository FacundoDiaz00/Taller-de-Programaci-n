<%@page import="utils.Utiles"%>
<%@ page import="java.util.List" %>
<%@page import="logica.datatypes.DTSalidaTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/inscribirse_a_salida.css">

	
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

<section id="contenedor">
		
	<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
    <div id="contenedorForm">
         
            <div class="card-body">
            
                <h5 class="card-title" id ="tituloPagina">Inscrbirse a Salida.</h5>
                
          
            
            <form class="card" id="form-inscripcion-a-salida" name="inscripcionASalidaForm" method="post" action="InscribiseASalida"> 
					<div style="margin: 10px">
					
					<div class="contenedorinput mb-3">
	                    <span class="input-label">Cantidad De Turistas:<span class="obligatorio"> *</span></span>
	                     <%String cantTuristas = "";%>
	                    <input 
	                    	id="input-cantTur" 
	                    	type="number" 
	                    	name="cantTuristas"
	                    	required class="form-control" 
	                    	placeholder="Cantidad de turistas a Inscribir" 
	                    	aria-label="Cantidad de Turistas" 
	                    	aria-describedby="basic-addon1"
	                    	value="<%=cantTuristas%>"
	                    	min="1" 
	                    	max="999"
	                    	>
	                </div>
	
				    
				      
	                <div class="contenedorinput mb-3">
	                    <span class="input-label">Forma de Pago:<span class="obligatorio"> *</span></span>
	                    
	                    <%	List<String> paquetes = (List<String>) request.getAttribute("paquetes"); %>
	                    
		                 <div class="form-check">
		                        <input class="form-check-input" value="0" type="radio" name="formaPago" id="checkGeneral" checked>
		                        <label class="form-check-label" for="checkGeneral">
		                            General
		                        </label>
		                </div>
		                
	                    <div class="form-check" >
	                    	<label class="form-check-label" for="checkPaquete">
	                            Por Paquete
	                        </label>
	                        <%
	                        if(paquetes.toArray().length > 0){
	                        %>
	                        <input class="form-check-input"  value="1" type="radio" name="formaPago" id="checkPaquete">
	                       <%
	                        }else{
	                        %>
	                        <input class="form-check-input"type="radio" disabled="disabled">
	                        <b style="color: red; margin-left: 20px">No cuenta con ningún paquete disponible para la inscripción</b>
	                        <%
	                        }
	                        %>
	                        
	                    </div> 
	                    
		           </div>
	           
		                
		                <div class="contenedorinput mb-3" id="comboPaquetes">
		                    <div class="form-group">
                    	<%	
		                    for(String paq : paquetes){ 
			                %>
		                        <label>Paquete:</label>
		                         <select class="combobox input-large form-control" name="paquete" >

		                            	<option value="<%=paq%>" selected="selected"><%=paq%></option>
		                            <% }%>
		                        </select>
		                    </div>
		                </div>
		                
		                
		                <div id="botonera">
		                    <a href="index.html" class="btn btn-danger ">Cancelar</a>
		                    <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
		                </div>
           
				</div>
                

        </form>
        
      </div>
    </div>
    <div id="contenedorDer">
        <div class="card" style="width: 18rem;">  
            <% DTSalidaTuristica salida = (DTSalidaTuristica) request.getAttribute("salida");%>	
            <img src="<%=Utiles.obtenerUrlParaImagen(salida.getImg())%>" alt="..." style="margin: 10px;width: 267px;">
            <div class="card-body">
                <h4 class="card-title">Degusta Setiembre</h4>
                <div class="div-doble" id="FechaYhoraSalida">
                    <h5 class="label">Fecha y hora de partida: </h5>
                    <p><%=salida.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ' a las ' HH:mm"))%></p>
                </div>
                <div class="div-doble" id="Costo">
                    <h5 class="label">Capacidad de turistas: </h5>
                    <p><%=salida.getCantMaxTuristas()%></p>
                </div>
                <div class="div-doble" id="Cuidad">
                    <h5 class="label">Lugar: </h5>
                    <p><%=salida.getLugarSalida()%></p>
                </div>
            </div>
        </div>
    </div>
</section>

 	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>


<script src="../js/jquery-3.3.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/sweetalert2.all.min.js"></script>
<script src="../js/comportamientoComun.js"></script>
    <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error inscribirse a salida turística" , mensajeError , 200);
    </script>
    <%} %>
    
    
    <% if( (Boolean)request.getAttribute("exito") == Boolean.TRUE){ %>
    <script>
    
	    setTimeout(() => {
	        Swal.fire({
	            icon: "success",
	            title: "Éxito",
	            text: "La inscripción ha sido realizada con éxito",
	            confirmButtonText: 'Entendido'  
	        }).then((res) => {
	        	window.location.href = "index";
	        })
	    }, 200)
    
	</script>
	<%} %>

    <script>
    
    var element = document.getElementById("comboPaquetes");
    element.style.display = "none";

    $("#checkGeneral")[0].addEventListener("click", ()=>{
    	var element = document.getElementById("comboPaquetes");
        element.style.display = "none";
    })
    
    $("#checkPaquete")[0].addEventListener("click", ()=>{
    	var element = document.getElementById("comboPaquetes");
        element.style.display = "block";
    })
    </script>
</body>
</html>