const cargarDatos = async () => {
    /*const response = await fetch("http://api.covid19api.com/summary");*/
    const response = await fetch("http://3.86.179.20:8080/consutarEmail?email=fricar18@hotmail.com");
    const datos = await response.json();
    
    // Fuerzo artificialmente a que dure más para que se pueda observar el Spinner
    await accionAsincrona();
    
   /* var cov19 = datos.Global;
    alert(cov19.NewConfirmed);
    cadena = `
        <tr>
        <td><input type="number" value="${cov19.NewConfirmed}"></td>
        <td><input type="number" value="${cov19.TotalConfirmed}"></td>
        <td><input type="number" value="${cov19.NewDeaths}"></td>
        <td><input type="number" value="${cov19.TotalDeaths}"></td>
        <td><input type="number" value="${cov19.NewRecovered}"></td>
        <td><input type="number" value="${cov19.TotalRecovered}"></td>
        </tr>`
        */
       
         var nombre = datos.nombre;
         localStorage.setItem("email",JSON.stringify(datos));
            alert(JSON.stringify(datos));
            /*fetch('http://52.90.194.79:8080/redirectDashboard' + email)*/

            fetch('http://localhost:8080/redirectDashboard', {
            credentials: 'include'
            });
         

      
}

const accionAsincrona = async () => {
    return new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve();
    }, 3000);
  });   
}

cargarDatos();