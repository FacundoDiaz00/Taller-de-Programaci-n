
$("#form-alta-actividad")[0].addEventListener('submit', (e) =>onSubmit(e))

const onSubmit = (e) => {
    generarMensaje(MENSAJE_TIPO_ERROR, "Mensaje de prueba", "Esto seria un mensaje de error", 0)
    e.preventDefault(); //Esto cancela el submit
}