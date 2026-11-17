package ni.edu.ni.servicio_web.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.ni.servicio_web.model.Usuario;
import ni.edu.ni.servicio_web.service.UsuarioService;

import java.util.List;

public class UsuariosController {


    @FXML private TableView<Usuario> tvUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colUsername;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, String> colTelefono;
    @FXML private TableColumn<Usuario, String> colCiudad;
    @FXML private TableColumn<Usuario, String> colEmpresa;

    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        // Los nombres entre comillas DEBEN coincidir exactamente con los atributos de tu clase Usuario
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email")); // En la clase se llama email, no correo
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
    }

    @FXML
    protected void onConsultarClick(ActionEvent event) {
        try {

            List<Usuario> lista = usuarioService.obtenerUsuarios();

            ObservableList<Usuario> observableLista = FXCollections.observableArrayList(lista);


            tvUsuarios.setItems(observableLista);

        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
            e.printStackTrace();
        }
    }
}