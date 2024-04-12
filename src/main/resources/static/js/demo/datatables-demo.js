// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarPersonas();

  $('#dataTable').DataTable();
});

async function cargarPersonas(){

  const cargarPersonas = await fetch('listar', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const personas = await cargarPersonas.json();
  
    console.log(personas);
    
   
    let listadohtml= '';
    
  for(let persona of personas){
  
   let botonEliminar ='<a href="#" onClick="eliminarPersonas('+persona.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    
  
  let productohtml = '<tr><td>'+persona.documento+'</td><td>'+persona.tipodocumento
  					+'</td><td>'+persona.nombre +'</td><td>'+persona.apellido +'</td><td>'+persona.telefono 
  					+'</td><td>'+persona.fechanacimiento 
  					+'</td><td>'+botonEliminar+'</td></tr>'
  listadohtml += productohtml;
  }

  document.querySelector('#dataTable tbody').outerHTML=listadohtml

}

async function eliminarPersonas(id){

  const eliminarPersona = await fetch('eliminar/'+id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    
  });
  const productos = await eliminarPersona.json();

}
