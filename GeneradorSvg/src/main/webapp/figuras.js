function mostrarFormulario() {
    const figura = document.getElementById("figura").value;
    const div = document.getElementById("formulario-dinamico");
    div.innerHTML = "";

    let campos = "";

    switch (figura) {
        case "rectangulo":
            campos = `
                X: <input type="number" id="x"><br>
                Y: <input type="number" id="y"><br>
                Ancho: <input type="number" id="ancho"><br>
                Alto: <input type="number" id="alto"><br>
            `;
            break;
        case "circulo":
            campos = `
                Centro X: <input type="number" id="x"><br>
                Centro Y: <input type="number" id="y"><br>
                Radio: <input type="number" id="radio"><br>
            `;
            break;
        case "elipse":
            campos = `
                Centro X: <input type="number" id="x"><br>
                Centro Y: <input type="number" id="y"><br>
                Radio X: <input type="number" id="rx"><br>
                Radio Y: <input type="number" id="ry"><br>
            `;
            break;
        case "linea":
            campos = `
                X1: <input type="number" id="x1"><br>
                Y1: <input type="number" id="y1"><br>
                X2: <input type="number" id="x2"><br>
                Y2: <input type="number" id="y2"><br>
            `;
            break;
        case "lineaPoligonal":
        case "poligono":
            campos = `
                Puntos (x,y separados por espacios, ej: 10,10 20,20 30,10):<br>
                <input type="text" id="puntos" size="40"><br>
            `;
            break;
    }

    const estilos = `
        <br><strong>Estilo:</strong><br>
        Color R: <input type="number" id="r" min="0" max="255"><br>
        Color G: <input type="number" id="g" min="0" max="255"><br>
        Color B: <input type="number" id="b" min="0" max="255"><br>
        Grosor: <input type="number" id="grosor" min="1"><br><br>
    `;

    div.innerHTML = campos + estilos + `<button onclick="enviarFormulario()">Dibujar</button>`;
}

function enviarFormulario() {
    const tipo = document.getElementById("figura").value;
    const datos = {
        tipo,
        r: parseInt(document.getElementById("r").value, 10),
        g: parseInt(document.getElementById("g").value, 10),
        b: parseInt(document.getElementById("b").value, 10),
        grosor: document.getElementById("grosor").value
    };

    // Verificar que los colores estén en el rango adecuado (0-255)
    if (datos.r < 0 || datos.r > 255 || datos.g < 0 || datos.g > 255 || datos.b < 0 || datos.b > 255) {
        alert("Los valores de color deben estar entre 0 y 255.");
        return;
    }

    // Parámetros específicos por figura
    switch (tipo) {
        case "rectangulo":
            datos.x = document.getElementById("x").value;
            datos.y = document.getElementById("y").value;
            datos.ancho = document.getElementById("ancho").value;
            datos.alto = document.getElementById("alto").value;
            break;
        case "circulo":
            datos.x = document.getElementById("x").value;
            datos.y = document.getElementById("y").value;
            datos.radio = document.getElementById("radio").value;
            break;
        case "elipse":
            datos.x = document.getElementById("x").value;
            datos.y = document.getElementById("y").value;
            datos.rx = document.getElementById("rx").value;
            datos.ry = document.getElementById("ry").value;
            break;
        case "linea":
            datos.x1 = document.getElementById("x1").value;
            datos.y1 = document.getElementById("y1").value;
            datos.x2 = document.getElementById("x2").value;
            datos.y2 = document.getElementById("y2").value;
            break;
        case "lineaPoligonal":
        case "poligono":
            datos.puntos = document.getElementById("puntos").value;
            break;
    }

    const params = new URLSearchParams();
    for (const key in datos) {
        params.append(key, datos[key]);
    }

    // Enviar los parámetros al servlet
    fetch("FigurasServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: params.toString()
    })
    .then(response => response.text())
    .then(svg => {
        document.getElementById("resultado").innerHTML = svg;
    })
    .catch(error => {
        console.error("Error al generar SVG: ", error);
        alert("Hubo un problema al procesar la solicitud.");
    });
}
