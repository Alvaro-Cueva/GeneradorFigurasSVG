package coop.gsd.daw.unservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gsd.daw.prog.figuras.*;


/**
 * Servlet implementation class Prueba01
 */
@WebServlet("/FigurasServlet")
public class Prueba01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prueba01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        Contenedor contenedor = new Contenedor(500, 500);

        try {
            // Verificar si los colores están dentro del rango permitido (0-255)
            byte r = validarColor(request.getParameter("r"));
            byte g = validarColor(request.getParameter("g"));
            byte b = validarColor(request.getParameter("b"));
            int grosor = Integer.parseInt(request.getParameter("grosor"));

            // Crear un objeto Stroke con el color y grosor
            Stroke stroke = new Stroke(new Color(r, g, b), grosor);

            // Dependiendo del tipo de figura, agregar la figura correspondiente al contenedor
            switch (tipo) {
                case "rectangulo":
                    Rectangulo rect = new Rectangulo(
                            new Punto(Integer.parseInt(request.getParameter("x")),
                                      Integer.parseInt(request.getParameter("y"))),
                            Integer.parseInt(request.getParameter("ancho")),
                            Integer.parseInt(request.getParameter("alto"))
                    );
                    rect.setStroke(stroke);
                    contenedor.addRectangulo(rect);
                    break;

                case "circulo":
                    Circulo circ = new Circulo(
                            new Punto(Integer.parseInt(request.getParameter("x")),
                                      Integer.parseInt(request.getParameter("y"))),
                            Integer.parseInt(request.getParameter("radio"))
                    );
                    circ.setStroke(stroke);
                    contenedor.addCirculo(circ);
                    break;

                case "elipse":
                    Elipse elip = new Elipse(
                            new Punto(Integer.parseInt(request.getParameter("x")),
                                      Integer.parseInt(request.getParameter("y"))),
                            Integer.parseInt(request.getParameter("rx")),
                            Integer.parseInt(request.getParameter("ry"))
                    );
                    elip.setStroke(stroke);
                    contenedor.addElipse(elip);
                    break;

                case "linea":
                    Linea lin = new Linea(
                            new Punto(Integer.parseInt(request.getParameter("x1")),
                                      Integer.parseInt(request.getParameter("y1"))),
                            new Punto(Integer.parseInt(request.getParameter("x2")),
                                      Integer.parseInt(request.getParameter("y2")))
                    );
                    lin.setStroke(stroke);
                    contenedor.addLinea(lin);
                    break;

                case "lineaPoligonal":
                    Punto[] puntosLP = parsearPuntos(request.getParameter("puntos"));
                    LineaPoligonal lp = new LineaPoligonal(puntosLP);
                    lp.setStroke(stroke);
                    contenedor.addLineaPoligonal(lp);
                    break;

                case "poligono":
                    Punto[] puntosP = parsearPuntos(request.getParameter("puntos"));
                    Poligono p = new Poligono(puntosP);
                    p.setStroke(stroke);
                    contenedor.addPoligono(p);
                    break;

                default:
                    throw new Exception("Tipo de figura no reconocido.");
            }

        } catch (Exception e) {
            // Manejo de excepciones, devolvemos un mensaje de error
            response.setContentType("text/plain");
            response.getWriter().write("Error en el servidor: " + e.getMessage());
            return;
        }

        // Enviar el SVG generado al cliente
        response.setContentType("image/svg+xml");
        response.getWriter().write(contenedor.toSvg());
    }

    /**
     * Método para validar que los valores de los colores están en el rango de 0 a 255
     * @param color El valor del color (r, g, b)
     * @return El valor validado
     * @throws Exception Si el valor no es válido
     */
    private byte validarColor(String color) throws Exception {
        int valor = Integer.parseInt(color);
        if (valor < 0 || valor > 255) {
            throw new Exception("El valor del color debe estar entre 0 y 255.");
        }
        return (byte) valor;
    }

    /**
     * Método para parsear los puntos de una cadena en formato "x,y x,y ..."
     * @param entrada La cadena con los puntos
     * @return Un arreglo de objetos Punto
     */
    private Punto[] parsearPuntos(String entrada) {
        String[] coords = entrada.trim().split("\\s+");
        Punto[] puntos = new Punto[coords.length];
        for (int i = 0; i < coords.length; i++) {
            String[] xy = coords[i].split(",");
            puntos[i] = new Punto(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
        }
        return puntos;
    }
}