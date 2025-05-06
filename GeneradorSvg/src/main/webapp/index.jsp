<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generador de Figuras SVG</title>
    <script src="figuras.js"></script>
</head>
<body>
    <h1>Generador de Figuras</h1>

    <label for="figura">Elige una figura:</label>
    <select id="figura" onchange="mostrarFormulario()">
    	<option value="">Selecciona...</option>
    	<option value="rectangulo">Rectángulo</option>
    	<option value="circulo">Círculo</option>
    	<option value="elipse">Elipse</option>
    	<option value="linea">Línea</option>
    	<option value="lineaPoligonal">Línea poligonal</option>
    	<option value="poligono">Polígono</option>
	</select>


    <div id="formulario-dinamico"></div>

    <h2>Resultado SVG:</h2>
    <div id="resultado"></div>
</body>
</html>
