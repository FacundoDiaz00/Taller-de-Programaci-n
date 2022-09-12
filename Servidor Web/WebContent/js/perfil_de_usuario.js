function cambiarSeleccionado($botonSeleccionado, $botonOtro1, $botonOtro2) {
    console.log("cambiarSeleccionado");
    $botonSeleccionado.addClass("active");
    $botonSeleccionado.addClass("show");
    $botonOtro1.removeClass("active");
    $botonOtro1.removeClass("show");
    $botonOtro2.removeClass("show");
    $botonOtro2.removeClass("active");
}

$("#boton-paquetes")[0].addEventListener("click", function() {
    console.log($("#boton-paquetes-pane"));
    cambiarSeleccionado($("#boton-paquetes-pane"), $("#boton-general-pane"), $("#boton-salidas-pane"));
})

$("#boton-general")[0].addEventListener("click", function() {
    cambiarSeleccionado($("#boton-general-pane"), $("#boton-paquetes-pane"), $("#boton-salidas-pane"));
})

$("#boton-salidas")[0].addEventListener("click", function() {
    cambiarSeleccionado($("#boton-salidas-pane"), $("#boton-paquetes-pane"), $("#boton-general-pane"));
})