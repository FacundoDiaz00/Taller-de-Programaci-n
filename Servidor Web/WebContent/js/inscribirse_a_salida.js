$("#checkGeneral")[0].addEventListener('click', (e) => onGeneralClick(e))
$("#checkPaquete")[0].addEventListener('click', (e) => onPaqueteClick(e))


const onGeneralClick = (e)=>{
    console.log($("#comboPaquetes")[0].classList)
    $("#comboPaquetes")[0].classList.add("eliminarElemento")
/*
    ("#comboPaquetes")[0].classList.toggle("eliminarElemento")
    */
}
const onPaqueteClick = (e)=>{
    console.log($("#comboPaquetes")[0].classList)
        $("#comboPaquetes")[0].classList.remove("eliminarElemento")
    /*
    ("#comboPaquetes")[0].classList.toggle("eliminarElemento")
*/
}

