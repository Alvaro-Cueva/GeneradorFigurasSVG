# Mi Proyecto Web

Este es un proyecto web que utiliza **Tomcat** como servidor de aplicaciones, con **JSP** y **Servlets** para el manejo dinámico de la lógica del backend. La aplicación permite generar figuras dinámicas (como rectángulos, círculos, etc.) en formato **SVG** a través de una interfaz de usuario.

Además, este proyecto abarca diferentes disciplinas de desarrollo, incluyendo **frontend** y **devops**, lo que implica tanto la creación de interfaces de usuario interactivas como la gestión del ciclo de vida del software y la infraestructura en la nube.

## Características

- Generación de figuras dinámicas (rectángulos, círculos, polígonos) en formato **SVG**.
- Uso de **Servlets** para manejar la lógica del backend.
- Utilización de **JSP** para la presentación de las vistas dinámicas.
- Comunicación entre frontend (HTML/JavaScript) y backend (Servlet) para la creación de figuras.
- Respuesta en formato **SVG** para representar gráficamente las figuras generadas.
- **Frontend** interactivo con formularios dinámicos y actualización en tiempo real utilizando **JavaScript**.
- **DevOps**: Integración continua, despliegue automático y gestión del entorno en la nube para la aplicación.

## Requisitos

- **Tomcat 9** o superior (o cualquier otro servidor compatible con Servlets y JSP).
- **Java 8** o superior.
- **JDK** para la compilación y ejecución del proyecto.
- Herramientas y servicios para la integración y despliegue continuo, si es necesario.

## Cómo usarlo

1. **Clona este repositorio**:

   ```bash
   git clone https://github.com/usuario/mi-proyecto-web.git
2. ***Abre el proyecto en tu IDE favorito (como Eclipse, IntelliJ IDEA, o NetBeans).*
3. **Configura Tomcat**:
   # Si estás utilizando un IDE como Eclipse:
  - Asegúrate de tener configurado Tomcat en el IDE.
  - Agrega el proyecto a Tomcat y despliega la aplicación.

# Si estás ejecutando Tomcat manualmente:
  - Genera el archivo WAR del proyecto.
  - Despliega el archivo WAR en el directorio `webapps` de Tomcat.
4.**Accede a la aplicación desde tu navegador**:

    ```bash
   http://localhost:8080/mi-proyecto-web

