//consultarstorage()


  const cargarDat =async () => {
    var  documento= localStorage.getItem('documento');

    var persona = localStorage.getItem("persona");
    if (persona == null) {

  //const response = await fetch("http://localhost:5000/consutarDoc?doc="+documento);
  const response = await fetch("https://anunciaig.com/consutarDoc?doc="+documento);
  const datos = await response.json();
  // Fuerzo artificialmente a que dure más para que se pueda observar el Spinner
  await accionAsincrona();
       
       var nombre = datos.nombre;
       localStorage.setItem("persona",JSON.stringify(datos));
       // alert(JSON.stringify(datos));
        if (datos) {
          localStorage.setItem("admin",datos.admin),
          localStorage.setItem("nombre",datos.nombre),
          localStorage.setItem("apellido",datos.apellido),
          localStorage.setItem("id",datos.id)
          console.log(JSON.stringify(datos.mensajes));
          localStorage.setItem("permisosMenu",JSON.stringify(datos.permisosMenu))
          localStorage.setItem("coordinadorActual",JSON.stringify(datos.coordinadorActual))
          localStorage.setItem("mensajes",JSON.stringify(datos.mensajes))
          if(datos.mensajes.length >0){
          $('#mensajesModal').modal('show');
          }
        } else {
          alert("no se encontro informacion de este usuario")
        }

        //location.reload();
      // línea ocultando el spinner
 // document.getElementById("spinner").style.display="none";
  //document.getElementById('wrapper').style.display= "block";
}



/*Obtener datos almacenados*/
var  admin= localStorage.getItem('admin');
if (admin== "true") {
consultarPermisosAdmin(localStorage.getItem('permisosMenu'));
}else{
consultarPermisosAdmin(localStorage.getItem('permisosMenu'));
}
/*Obtener datos almacenados*/
var  coordinadorActual = localStorage.getItem('coordinadorActual');
if (admin != "true" && coordinadorActual == "false") {
document.getElementById("menuCoordinador").style.display = "none";
}else{
document.getElementById("menuCoordinador").style.display = "block";
document.getElementById("menuHisCordinador").style.display = "block";
}
/*Mostrar datos almacenados*/      

var nombre=localStorage.getItem('nombre');
console.log( "nombre de esta persona "+nombre);
document.getElementById("nombre").value =nombre;
console.log( "id de esta persona "+localStorage.getItem('id'));
/* datos adminstrador*/
document.getElementById("id").value = localStorage.getItem('id');
document.getElementById("idPersonaTCD").value = localStorage.getItem('id');
document.getElementById("idProgramacion").value = localStorage.getItem('id');
document.getElementById("id").style.display = "none";
document.getElementById("idHistorico").value = localStorage.getItem('id');
document.getElementById("idCoordinador").value = localStorage.getItem('id');
document.getElementById("idCursoUser").value = localStorage.getItem('id');
document.getElementById("idPCurso").value = localStorage.getItem('id');
document.getElementById("idPersonaConsolidacion").value = localStorage.getItem('id');
document.getElementById("idPersonaSugerencia").value = localStorage.getItem('id');
document.getElementById("idPersonaMensajes").value = localStorage.getItem('id');
document.getElementById("idPMensajes").value = localStorage.getItem('id');

/* datos usuario
document.getElementById("idUser").value = localStorage.getItem('id');
document.getElementById("idPersonaTCDUser").value = localStorage.getItem('id');
document.getElementById("idPersonaConsolidacion").value = localStorage.getItem('id');
document.getElementById("idPersonaMinisterioUser").value = localStorage.getItem('id');
document.getElementById("idUser").style.display = "none";*/
/*Mostrar pagina*/  

var x = document.getElementById("contenido");
if (x.style.display === "none") {
    x.style.display = "block";
} else {
    x.style.display = "none";
}

var s = document.getElementById("spinner");
if (s.style.display === "none") {
    s.style.display = "block";
} else {
    s.style.display = "none";
}

}

  cargarDat();





const cargarDatos = async () => {
  document.getElementById('wrapper').style.display= "none";
  document.getElementById("spinner").style.display="block";
  const response = await fetch("http://localhost:5000/consutarEmail?email=fricar18@hotmail.com");
  /*const response = await fetch("http://anunciados-aws-env.eba-sdf4vzvf.us-east-1.elasticbeanstalk.com/consutarEmail?email=fricar18@hotmail.com");*/
  const datos = await response.json();
  
  // Fuerzo artificialmente a que dure más para que se pueda observar el Spinner
  await accionAsincrona();
       
       var nombre = datos.nombre;
       localStorage.setItem("email",JSON.stringify(datos));
        alert(JSON.stringify(datos));


      // línea ocultando el spinner
  document.getElementById("spinner").style.display="none";
  document.getElementById('wrapper').style.display= "block";

}

const accionAsincrona = async () => {
  return new Promise((resolve, reject) => {
  setTimeout(() => {
      resolve();
  }, 3000);
});   
}

function consultarPermisosAdmin(permisosMenu){
let perm=[];
perm=JSON.parse(permisosMenu);
 var existe=false;
    for (i=0; i < perm.length; i++) {
        console.log("estado "+perm[i].estado);
        console.log("nombreBotonMenu "+perm[i].nombreBotonMenu);
        if(perm[i].estado == 'false'){

         let menuObtenido = perm[i].menu;
             for (j=0; j < menuObtenido.subMenu.length; j++) {
                         document.getElementById(menuObtenido.subMenu[j].etiqueta).style.display = "none";
                         }
                     }
            }
        //perm[i].estado == 'true'?document.getElementById(perm[i].nombreBotonMenu).style.display = "block":document.getElementById(perm[i].nombreBotonMenu).style.display = "none";

    }


function PermisosUser(permisosMenu){
let perm=[];
perm=JSON.parse(permisosMenu);
 var existe=false;
    for (i=0; i < perm.length; i++) {
        console.log("estado "+perm[i].estado);
        console.log("nombreBotonMenu "+perm[i].nombreBotonMenu);
         for (j=0; j < perm[i].subMenu.length; j++) {
         console.log(perm[i].subMenu[j].etiqueta)
         document.getElementById(perm[i].subMenu[j].etiqueta).style.display = "none";}
   }
}


function consultarstorage(){

  let email = localStorage.getItem('email');
  let validar = localStorage.getItem('nombre');
 
/*Obtener datos almacenados*/   
var  admin= localStorage.getItem('admin');
if (admin== "true") {
document.getElementById("user").style.display = "none";
}else{
document.getElementById("admin").style.display = "none";
}


/*Mostrar datos almacenados*/      

var nombre=localStorage.getItem('nombre');
console.log( "nombre de esta persona "+nombre);
document.getElementById("nombre").value =nombre;
console.log( "id de esta persona "+localStorage.getItem('id'));
document.getElementById("id").value = localStorage.getItem('id');
document.getElementById("id").style.display = "none";


/*document.getElementById("email").innerHTML = localStorage.getItem('email');*/

}
