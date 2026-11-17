module ni.edu.ni.servicio_web {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens ni.edu.ni.servicio_web to javafx.fxml;
    // Permite a Jackson acceder al paquete model para crear los Usuarios
    opens ni.edu.ni.servicio_web.model to com.fasterxml.jackson.databind;

    exports ni.edu.ni.servicio_web;
}