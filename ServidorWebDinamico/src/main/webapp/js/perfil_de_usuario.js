
function cambiarSeleccionado($botonSeleccionado, $botonOtro1, $botonOtro2) {
    console.log("cambiarSeleccionado");
    $botonSeleccionado.addClass("active");
    $botonSeleccionado.addClass("show");
    $botonOtro1.removeClass("active");
    $botonOtro1.removeClass("show");
    $botonOtro1.removeClass("cardPerfil");
    $botonOtro2?.removeClass("show");
    $botonOtro2?.removeClass("active");
    $botonOtro2?.removeClass("cardPerfil");
}

$("#boton-actividades")[0]?.addEventListener("click", function() {
	console.log("soy boton actividades: ", $("#boton-actividades"))
    cambiarSeleccionado($("#boton-actividades-pane"), $("#boton-general-pane"), $("#boton-salidas-pane"));
})

$("#boton-general")[0]?.addEventListener("click", function() {
    $("#boton-general-pane").addClass("cardPerfil");
    cambiarSeleccionado($("#boton-general-pane"), $("#boton-actividades-pane"), $("#boton-salidas-pane"));
})

$("#boton-salidas")[0]?.addEventListener("click", function() {
    cambiarSeleccionado($("#boton-salidas-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"));
})


$("#boton-general-2")[0]?.addEventListener("click", function() {sss
    console.log("boton-general-2: ", $("#boton-general-2"));
    $("#boton-general-pane-2").addClass("cardPerfil");
    cambiarSeleccionado($("#boton-general-pane-2"), $("#boton-actividades-pane-2"), null);
})

$("#boton-actividades-2")[0]?.addEventListener("click", function() {
    console.log("boton-actividades-2: ", $("#boton-actividades-2"));
    cambiarSeleccionado($("#boton-actividades-pane-2"), $("#boton-general-pane-2"), null);
})