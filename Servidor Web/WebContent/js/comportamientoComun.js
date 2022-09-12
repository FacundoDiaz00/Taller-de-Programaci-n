const alert = (message, type, position) => {
    let oldAlert = $("#alert")[0];
    console.log(oldAlert)
    if(oldAlert !== undefined)
        oldAlert.remove()

    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
        `<div class="alert alert-${type} alert-dismissible" id="alert" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('')

    position.append(wrapper)
}