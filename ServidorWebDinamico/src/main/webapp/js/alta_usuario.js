$("#checkTipoProveedor")[0].addEventListener('click', (e) => onProveedorClick(e))
$("#checkTipoTurista")[0].addEventListener('click', (e) => onTuristaClick(e))
$("#form-alta-usuario")[0].addEventListener('submit', (e) => onSubmit(e))

const onTuristaClick = (e) =>{
    console.log("Click en radiobutton turista")
    $("#nacionalidad-field")[0].classList.remove("eliminarElemento")
    $("#descripcion-general-field")[0].classList.add("eliminarElemento")
    $("#link-sito-web-field")[0].classList.add("eliminarElemento")
    $("#input-nacionalidad")[0].type = "text"
    $("#input-descripcionGeneral")[0].type = "hidden"
    $("#input-link")[0].type = "hidden"

}

const onProveedorClick = (e) =>{
    console.log("Click en radiobutton turista")
    $("#nacionalidad-field")[0].classList.add("eliminarElemento")
    $("#descripcion-general-field")[0].classList.remove("eliminarElemento")
    $("#link-sito-web-field")[0].classList.remove("eliminarElemento")

    $("#input-nacionalidad")[0].type = "hidden"
    $("#input-descripcionGeneral")[0].type = "text"
    $("#input-link")[0].type = "text"
}

const onSubmit = (e) =>{
	const passwordOriginal = $("#input-contrasenia")[0].value;
	const passwordverificar = $("#input-confirmacion-contrasenia")[0].value;
	if(passwordOriginal !== passwordverificar){
		e.preventDefault();
		generarMensaje('error', "Error" , "Las contraseñas no son identicas. Haga que coincidan y luego vuelva a enviar" , 0);	
	}
}



