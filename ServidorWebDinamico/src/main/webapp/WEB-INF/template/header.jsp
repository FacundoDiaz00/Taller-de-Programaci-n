<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.gamebook.model.Usuario"%>
<div id="header">
	<div class="logo">
		<a href="/gamebook">gamebook</a>
	</div>
	<div class="subtitulo">Porque los gamers necesitaban su red
		social</div>

	<%
	Usuario usr = (Usuario) session.getAttribute("usuario_logueado");
	if (usr != null) {
	%>
	<div class="usuario">
		<a href="/gamebook/usuarios">Usuarios</a> | <a href="/gamebook/jugar">Jugar</a>
		| Logueado como <a href="/gamebook/perfil"><%=usr.getNombre()%></a>
		(<%=usr.getEmail()%>)
	</div>
	<%
	}
	%>
</div>

<div id="body-container">