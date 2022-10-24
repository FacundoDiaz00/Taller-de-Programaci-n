<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- String idCategoriaMarcada (opcional)
- String idDepartamentoMarcada (opcional)

 --%>

<%@page import="logica.datatypes.DTProveedor"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="logica.datatypes.DTUsuario" %>
<%@ page import="logica.datatypes.DTProveedor" %>

 		<div id="menu-lateral">
 		
	 		<% 
				String idCategoriaMarcada = (String) request.getAttribute("idCategoria");
				String idDepartamentoMarcada = (String) request.getAttribute("idDepartamento");
			%>
        
            <!--Acciones generales-->
            <div class="card list-group" id="opciones">
                <h5 class="card-title">Acciones</h5>
                <a href="ConsultaDeUsuario" class="list-group-item">Ver usuarios</a>
                
                <% 
                	DTUsuario usuario = (DTUsuario)session.getAttribute("usuarioLogeado");   
                	if(session.getAttribute("usuarioLogeado") != null && session.getAttribute("usuarioLogeado") instanceof DTProveedor){%>
		                <a href="AltaDeActividad" class="list-group-item">Crear Actividad turística</a>
                	<%}%>
                
            </div>

            <!--Categoroias-->
            <div class="card list-group" id="filtro-categoria">
                <h5 class="card-title">Categorías</h5>
                <%	
                	List<String> categoiras = (List<String>) request.getAttribute("categorias"); 
                    for(String cat : categoiras){
                    	if( idCategoriaMarcada != null && idCategoriaMarcada.equals(cat) ){   	
                %>
                			<a href="index?idCategoria=<%= cat %>" class="list-group-item active"><%= cat %></a>	
                
                <% 		} else { %>
                			<a href="index?idCategoria=<%= cat %>" class="list-group-item"><%= cat %></a>	
                
                <% 		}
                    } %>
            </div>

            <!--Departametos-->
            <div class="card list-group" id="filtro-departamentos">
                <h5 class="card-title">Departamentos</h5>
               
					<% 
					List<String> departamentos = (List<String>) request.getAttribute("departamentos");
					
					for(String dep: departamentos){
						if( idDepartamentoMarcada != null && idDepartamentoMarcada.equals(dep) ){ 
						%>
							<a href="index?idDepartamento=<%= dep %>" class="list-group-item active"><%= dep %></a>	
					  <%} else {%>
					  		<a href="index?idDepartamento=<%= dep %>" class="list-group-item"><%= dep %></a>	
				<%		}
					}	%>              
                
            </div>
        </div>



