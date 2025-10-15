package co.edu.uniquindio.poo.controllers;

import java.io.IOException;
import java.lang.StackWalker.StackFrame;

import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controllers.*; 

/**
 * Controlador para el Dashboard principal con estructura de menú lateral
 */
public class DashboardController {

    @FXML private Border mainContainer; 
    @FXML private StackFrame contentArea; // Área central donde se cargan las vistas
    @FXML private ButtonBorder btnGoToForm;
    @FXML private ButtonUI btnGoToList;

    @FXML
    public void initialize() {}

    /**
     * Maneja el evento de click para ir al Formulario de Creación
     */
    @FXML
    private void onGoToForm () { 
        loadView("/co/edu/uniquindio/fx10/vista/FormularioMoto.fxml", "formulario");
    }

     /**
     * Maneja el evento de click para ir al Listado de Productos
     */
    @FXML
    private void onGoToList() {
        loadView("/co/edu/uniquindio/vista/ListadoMoto.fxml", "listado");
    }

    /**
     * Vuelve a la vista de inicio del dashboard (texto de bienvenida)
     */
    @FXML 
    private void onGoToDashboard() { 
        // Implementación simple para mostrar el texto de bienvenida
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
    public void loadView(String fxmlPath, String typeView) {
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

           // Cargar la nueva vista en el StackPane central
           contentArea.getChildren().clear();
           contentArea.getChildren().add(view);
            
        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la vista: " + typeView, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Muestra una alerta al usuario
     */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}