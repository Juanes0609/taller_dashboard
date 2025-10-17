package co.edu.uniquindio.poo.controllers;

import java.io.IOException;

import co.edu.uniquindio.poo.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * Controlador para el Dashboard principal con estructura de menú lateral
 */
public class DashboardController {

    @FXML private BorderPane mainContainer;
    @FXML private StackPane contentArea; // Área central donde se cargan las vistas
    @FXML private Button btnGoToForm;
    @FXML private Button btnGoToList;

    @FXML
    public void initialize() { }

    /**
     * Maneja el evento de click para ir al Formulario de Creación
     */
    @FXML
    private void onGoToForm() {
        loadView("/co/edu/uniquindio/poo/FormularioMoto.fxml", "formulario");
    }

    /**
     * Maneja el evento de click para ir al Listado de Motos
     */
    @FXML
    private void onGoToList() {
        loadView("/co/edu/uniquindio/poo/ListadoMoto.fxml", "listado");
    }

    /**
     * Vuelve a la vista de inicio del dashboard (texto de bienvenida)
     */
    @FXML
    private void onGoToDashboard() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Volviendo al inicio..."));
    }

    /**
     * Usado por las vistas hijas para volver al dashboard
     */
    void backToDashboard() {
        onGoToDashboard();
    }

    /**
     * Carga una vista FXML en el área de contenido (StackPane)
     */
    private void loadView(String fxmlPath, String typeView) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            Parent view = loader.load();

            if (typeView.equals("formulario")) {
                FormularioMotoController controller = loader.getController();
                controller.setDashboardController(this);
            } else if (typeView.equals("listado")) {
                ListadoMotoController controller = loader.getController();
                controller.setDashboardController(this);
                controller.loadProducts();
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la vista: " + typeView, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}
