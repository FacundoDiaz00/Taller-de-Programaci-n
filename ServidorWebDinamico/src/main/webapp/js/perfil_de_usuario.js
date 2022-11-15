
function cambiarSeleccionado($botonSeleccionado, $botonOtro1, $botonOtro2, $botonOtro3,$botonOtro4, $botonOtro5, $botonOtro6){
    console.log("cambiarSeleccionado");
    $botonSeleccionado.addClass("active");
    $botonSeleccionado.addClass("show");
    
    $botonOtro1.removeClass("active");
    $botonOtro1.removeClass("show");
    $botonOtro1.removeClass("cardPerfil");
    
    $botonOtro2?.removeClass("show");
    $botonOtro2?.removeClass("active");
    $botonOtro2?.removeClass("cardPerfil");
    
    $botonOtro3?.removeClass("show");
    $botonOtro3?.removeClass("active");
    $botonOtro3?.removeClass("cardPerfil");
    
    $botonOtro4?.removeClass("show");
    $botonOtro4?.removeClass("active");
    $botonOtro4?.removeClass("cardPerfil");
    
    $botonOtro5?.removeClass("show");
    $botonOtro5?.removeClass("active");
    $botonOtro5?.removeClass("cardPerfil");
    
    $botonOtro6?.removeClass("show");
    $botonOtro6?.removeClass("active");
    $botonOtro6?.removeClass("cardPerfil");
}

$("#boton-actividades")[0]?.addEventListener("click", function() {
	console.log("soy boton actividades: ", $("#boton-actividades"))
    cambiarSeleccionado($("#boton-actividades-pane"), $("#boton-general-pane"), $("#boton-salidas-pane"),$("#boton-salidasprov-pane"),$("#boton-paquetes-pane"),$("#boton-seguidos-pane"),$("#boton-seguidores-pane"));
})

$("#boton-general")[0]?.addEventListener("click", function() {
    $("#boton-general-pane").addClass("cardPerfil");
    cambiarSeleccionado($("#boton-general-pane"), $("#boton-actividades-pane"), $("#boton-salidas-pane"),$("#boton-salidasprov-pane"),$("#boton-paquetes-pane"),$("#boton-seguidos-pane"),$("#boton-seguidores-pane"));
})

$("#boton-salidas")[0]?.addEventListener("click", function() {
    cambiarSeleccionado($("#boton-salidas-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"),$("#boton-salidasprov-pane"),$("#boton-paquetes-pane"),$("#boton-seguidos-pane"),$("#boton-seguidores-pane"));
})

$("#boton-salidasprov")[0]?.addEventListener("click", function() {
    cambiarSeleccionado($("#boton-salidasprov-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"),$("#boton-salidas-pane"), $("#boton-paquetes-pane"),$("#boton-seguidos-pane"),$("#boton-seguidores-pane"));
})

$("#boton-paquetes")[0]?.addEventListener("click", function() {
    cambiarSeleccionado($("#boton-paquetes-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"),$("#boton-salidas-pane"),$("#boton-salidasprov-pane"),$("#boton-seguidos-pane"),$("#boton-seguidores-pane"));
})

$("#boton-general-2")[0]?.addEventListener("click", function() {
    console.log("boton-general-2: ", $("#boton-general-2"));
    $("#boton-general-pane-2").addClass("cardPerfil");
    cambiarSeleccionado($("#boton-general-pane-2"), $("#boton-actividades-pane-2"), null);
})

$("#boton-actividades-2")[0]?.addEventListener("click", function() {
    console.log("boton-actividades-2: ", $("#boton-actividades-2"));
    cambiarSeleccionado($("#boton-actividades-pane-2"), $("#boton-general-pane-2"), null);
})

console.log($("#boton-seguidos"));

$("#boton-seguidos")[0]?.addEventListener("click", function() {
    cambiarSeleccionado($("#boton-seguidos-pane"),$("#boton-paquetes-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"),$("#boton-salidas-pane"),$("#boton-salidasprov-pane"),$("#boton-seguidores-pane"));
})

$("#boton-seguidores")[0]?.addEventListener("click", function() {
    console.log("entre a seguidores");
    cambiarSeleccionado($("#boton-seguidores-pane"),$("#boton-paquetes-pane"), $("#boton-actividades-pane"), $("#boton-general-pane"),$("#boton-salidas-pane"),$("#boton-salidasprov-pane"),$("#boton-seguidos-pane"));
})