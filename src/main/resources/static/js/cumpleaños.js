lanzarConfeti();

    function lanzarConfeti() {
        confetti({
            particleCount: 150,
            spread: 70,
            origin: { y: 0.6 }
        });
    }

    // Ejecución automática al cargar
    window.onload = () => {
        /* Usamos Thymeleaf para evaluar una condición global:
           Si la lista contiene al menos una persona con cumpleActual == true
        */
        const hayCumpleaneros = /*[[${#lists.anyMatch(cumpleanosDiario, p -> p.cumpleActual)}]]*/ false;

        if (hayCumpleaneros) {
            lanzarConfeti();
            // Opcional: repetir el efecto cada 3 segundos para más emoción
            setTimeout(lanzarConfeti, 2000);
        }
    };

    // Listener para el botón manual
    document.getElementById('confettiBtn')?.addEventListener('click', lanzarConfeti);
