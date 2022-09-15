/**
 * Genera un popup de error temporizadorParaDesplegar ms despues. COn el tiulo y mensaje espesificado
 * @param titulo
 * @param mensaje
 * @param temporizadorParaDesplegar
 */

const MENSAJE_TIPO_EXITO = 'success'
const MENSAJE_TIPO_ERROR = 'error'

const generarMensaje = (tipoMensaje, titulo, mensaje, temporizadorParaDesplegar) =>{
    setTimeout(() => {
        Swal.fire({
            icon: tipoMensaje,
            title: titulo,
            text: mensaje,
            confirmButtonText: 'Entendido'
        })
    }, temporizadorParaDesplegar)
}