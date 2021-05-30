const selectPermiso = document.querySelector(".select-permiso")
const groupPatente = document.querySelector(".group-patente")

selectPermiso.addEventListener("change", e => {
    if(e.target.value == false){
        groupPatente.classList.remove("hidden")
    } else {
        groupPatente.classList.add("hidden")
    }
})