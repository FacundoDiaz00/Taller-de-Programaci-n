<%@page import="utils.Utile"%>

<%@page import="publicar.usuarioturisticasservice.DtUsuarioSeparadosPorTipoCollection"%>
<%@ page import="publicar.usuarioturisticasservice.DtUsuario" %>
<%@ page import="publicar.usuarioturisticasservice.DtTurista" %>
<%@ page import="publicar.usuarioturisticasservice.DtProveedor" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>





	<%!public String getTextWithoutNull(String value){
			return value == null ? "" : value;
		}%>
            <div class="card" id="contenedor-actividades-turisticas" style="margin-top: 20px">
                
                	<%
                                	DtUsuarioSeparadosPorTipoCollection users = (DtUsuarioSeparadosPorTipoCollection) request.getAttribute("seguidores");
                                	                	  ArrayList<DtUsuario> usuarios= new ArrayList<DtUsuario>();
                                	                	  List<DtTurista> turistas = users.getTuristas();
                                	                	  List<DtProveedor> proveedores = users.getProveedores();
                                	                	  
                                	                	  for(DtTurista turista: turistas){
                                	                		  usuarios.add((DtUsuario)turista);
                                	                	  }
                                	                	  
                                	                	  for(DtProveedor proveedor: proveedores){
                                	                		  usuarios.add((DtUsuario) proveedor);
                                	                	  }
                                	                	  
                                	                	  System.out.println("cant seguidores: " + usuarios.size());
                                	                	  
                                					for(DtUsuario usr: usuarios){
                                						System.out.println("seguidor: " + usr.getNickname());
                                	%>
							<div class="card mb-3" style="max-width: 800px; margin-left: 15px">
				                <div class="row g-0">
				                    <div class="col-md-4 img-contain">
				                        <img src="<%=Utile.obtenerUrlParaImagen(usr.getImg())%>" class="img-fluid rounded-start">
				                    </div>
				                    <div class="col-md-8">
				                        <div class="card-body">
				                            <div class="card-body">
				                                <h5 class="card-title"><%=usr.getNombre()%> <%=usr.getApellido()%></h5>
				                                <p class="card-text"><b>Nombre:</b> <%=usr.getNombre()%></p>
				                                <p class="card-text"><b>Apellido:</b> <%= usr.getApellido()%></p>
				                                <p class="card-text"><b>Nickname:</b> <%= usr.getNickname()%></p>
				                                <p class="card-text"><b>Tipo usuario:</b> <%= usr instanceof DtProveedor ? "Proveedor" : "Turista"%></p>
				                                <div class="botonera">
				                                    <a href="ConsultaDeUsuario?id=<%=usr.getNickname()%>&listar=false" class="btn btn-primary">Ver más</a>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>
						
					<% } %>
                

            </div>
           
	<script src="js/jquery-3.3.1.slim.min.js"></script>

   