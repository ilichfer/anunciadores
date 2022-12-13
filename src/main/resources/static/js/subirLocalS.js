limpiarlocalStorage();
function limpiarlocalStorage() {

  localStorage.clear();

}


function subir_localStorage() {

  var email = document.getElementById("txtEmail").value;
  localStorage.setItem("email",email);

}
