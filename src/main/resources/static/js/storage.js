obtener_localStorage();


function obtener_localStorage() {
  

  var  nombre= localStorage.getItem("nombre");
  var  apellido= localStorage.getItem("apellido");
  
  
  document.getElementById('nombre').innerHTML = localStorage.getItem("nombre")+" "+localStorage.getItem("apellido");

  console.log("nombre obtenido de local Storage "+nombre  +" "+ apellido);


}
