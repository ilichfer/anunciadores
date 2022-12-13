
function subir_localStorage() {

var email = document.getElementById("txtEmail").value
localStorage.setItem("email",email);

}



function obtener_localStorage() {
  

  var  nombre= localStorage.getItem("nombre");
  
  console.log("nombre obtenido de local Storage "+nombre );

function consultarUsuario(){

var  email= localStorage.getItem("email");
  
  console.log("email obtenido de local Storage "+email );

fetch('http://localhost:8080/consutarEmail' + email)
  .then(response => response.json())
  .then(data => console.log(data));

}
}


