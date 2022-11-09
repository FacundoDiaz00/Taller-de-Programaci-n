<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- DtUsuario "usuarioLogeado" (opcional)

 --%>

<%@page import="utils.Utiles"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>




    <nav class="navbar navbar-dark navbar-expand-lg bg-dark">

        <div class="container-fluid">

            <a class="navbar-brand" href="index">Turismo UY</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">



                <ul class="navbar-nav d-flex">
                    <form class="d-flex buscar" role="search" id="buscador">
                        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
                        <button class="btn" ><i class="fas fa-search"></i></button>
                    </form>
                    
                    <% 
                	DtUsuario usuario = (DtUsuario)session.getAttribute("usuarioLogeado");   
                    if(session.getAttribute("usuarioLogeado") == null){ %>
	                    <li class="alta_de_usuario">
	                        <a class="nav-link active" aria-current="page" href="AltaDeUsuario">Registrarse</a>
	                    </li>
	                    <li class="iniciar_sesion">
	                        <a class="nav-link active" href="IniciarSesion">Iniciar Sesión</a>
	                    </li>
                    
                    <% }else{ %>
                    
	                    <div class="dropdown">
	
	                        <a class="nav-link dropdown-toggle nickname_usuario  dropdown-toggle" data-bs-toggle="dropdown" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                            <%=usuario.getNombre() %>

				                <img class="imagen_perfil " src="<%=Utiles.obtenerUrlParaImagen(usuario.getImg())%>" alt="">
	                            
	                        </a>
	                        <ul class="dropdown-menu" id="dropDown">
	                            <li><a class="dropdown-item" href="ConsultaDeUsuario?id=<%=usuario.getNickname()%>&listar=false">Ver Perfil</a></li>
	                            <li><a class="dropdown-item" href="index?sesionCerrada=<%=true%>">Cerrar Sesión</a></li>
	                        </ul>
	                    </div>
                    
                    <% } %>
                    
                </ul>
                
                 
                
            </div>
        </div>

    </nav>



