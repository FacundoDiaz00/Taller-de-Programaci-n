<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- DTUsuario "usuarioLogeado" (opcional)

 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>



	<% DtUsuario usuario = (DtUsuario)session.getAttribute("usuarioLogeado"); %>
    <nav class="navbar navbar-dark navbar-expand-lg bg-dark">

        <div class="container-fluid">

            <a class="navbar-brand" href="index.html">Turismo UY</a>
             <b class="navbar-text" >Bienvenido/a <%=usuario.getNombre() %></b>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                <ul class="navbar-nav d-flex">
                    
                   <%--  <%   
                    if(session.getAttribute("usuarioLogeado") == null){ %>
                    <% }else{ %> --%>
                    
                    <li class="alta_de_usuario">
	                        <a class="nav-link active" aria-current="page" href="AltaDeUsuario">Ver Actividades</a>
	                    </li>
                     <li class="alta_de_usuario">
                        <a class="nav-link active" aria-current="page" href="AltaDeUsuario">Ver Salidas</a>
                    </li>
	                    <li class="iniciar_sesion">
	                        <a class="nav-link active" href="index?sesionCerrada=<%=true%>">Salir</a>
	                    </li>
  
                    <%-- <% } %> --%>
                    
                </ul>
            </div>
        </div>

    </nav>



