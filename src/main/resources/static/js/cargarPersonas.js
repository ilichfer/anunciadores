//getUsers();
autoComplete();
//const personas;



async function getUsers() {
    let url = 'http://localhost:5000/listarProductosJson';
    try {
    const response = await fetch(url);
     const datos = await response.json();
     personas = JSON.stringify(datos);
        var linea = $("#cargarItemProductos").html();
        //linea =  '<tr><td>gafas de prueba</td><td>Gucci-glass-001</td><td>2</td><td>200.000</td><td>400.000</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-eye"></i></a></td> </tr>'
        //console.log(linea);
        document.querySelector("#cargarItemProductos tbody").outerHTML = linea;
    } catch (error) {
        console.log(error);
    }
}


function autoComplete() {

let url = 'http://localhost:5000/listarPersonasJson';
   fetch(url)
     .then(response => response.json())
     .then(function(json){
        json.forEach(element => {
            console.log(element);
            items.innerHTML += `<option value="${element.nombre}">${element.id}</option>`
        });
     });


}




