//consultarstorage()


  const cargarDat =async () => {
    var  email= localStorage.getItem('email');


    var persona = localStorage.getItem("persona");
    if (persona == null) {
      
    
  
  //const response = await fetch("http://anunciados-aws-env.eba-sdf4vzvf.us-east-1.elasticbeanstalk.com/consutarEmail?email=fricar18@hotmail.com");
  const response = await fetch("http://localhost:5000/consutarEmail?email="+email);
  //const response = await fetch("http://anunciados-aws-env.eba-sdf4vzvf.us-east-1.elasticbeanstalk.com/consutarEmail?email="+email);
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
        } else {
          alert("no se encontro informacion en localStorage")
        }

        //location.reload();
      // línea ocultando el spinner
 // document.getElementById("spinner").style.display="none";
  //document.getElementById('wrapper').style.display= "block";
}



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
