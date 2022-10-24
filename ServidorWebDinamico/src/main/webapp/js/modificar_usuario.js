
$("#form-modificar-usuario")[0].addEventListener('submit', (e) => onSubmit(e))

const onSubmit = (e) =>{
	const passwordOriginal = $("#input-contrasenia")[0].value;
	const passwordverificar = $("#input-confirmacion-contrasenia")[0].value;
	if(passwordOriginal !== passwordverificar){
		e.preventDefault();
		generarMensaje('error', "Error" , "Las contraseñas no son identicas. Haga que coincidan y luego vuelva a enviar" , 0);	
	}
}


$("#input-borrar-foto")[0].addEventListener('click', (e) => onCLickEliminarFoto(e))


const onCLickEliminarFoto = (e) => {
	if($("#input-borrar-foto")[0].checked){		
		$("#input-imagen")[0].disabled = true
	} else {
		$("#input-imagen")[0].disabled = false
	}
} 

