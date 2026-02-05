let vista_preliminar = (event)=>{
    let leer_img = new FileReader();
    let id_img = document.getElementById('img-foto');
    leer_img.onload = ()=>{
        id_img.src = leer_img.result;
    }
    leer_img.readAsDataURL(event.target.files[0])
}