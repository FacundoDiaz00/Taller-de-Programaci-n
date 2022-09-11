boton_seleccionado = $("#boton-paquetes");
console.log("Cargando perfil_de_usuario.js", $("#boton-paquetes"));
$("#boton-paquetes")[0].addEventListener("click", function() {
    $("#boton-paquetes-pane").addClass("active");
    $("#boton-paquetes-pane").addClass("show");
    $("#boton-general-pane").removeClass("active");
    $("#boton-general-pane").removeClass("show");
    $("#boton-salidas-pane").removeClass("show");
    $("#boton-salidas-pane").removeClass("active");

    console.log("Paquetes");
})