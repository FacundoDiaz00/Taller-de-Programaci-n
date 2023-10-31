$("#form-alta-actividad")[0].addEventListener('submit', (e) => {
	e.preventDefault();
	
	let algunoCheck = false;
	const opciones = $(".opcion-categoria")
	for (let i = 0; i<opciones.length; i++ ){
		if(opciones[i].checked){
			algunoCheck = true
			break
		}
	}
	if(algunoCheck){
		$("#form-alta-actividad")[0].submit()		
	} else {
		generarMensaje('error',"Error", "Debe elegir alguna categoría."  , 200);
	}
	
})

