limpiarlocalStorage();
function limpiarlocalStorage() {

  localStorage.clear();

}


function subir_localStorage() {

  var email = document.getElementById("documento").value;
  localStorage.setItem("documento",email);

}
