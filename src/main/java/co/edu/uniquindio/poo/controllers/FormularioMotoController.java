package co.edu.uniquindio.poo.controllers;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.model.MotoFactory;
import co.edu.uniquindio.poo.model.MotoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para el formulario de creación de motos
 */
public class FormularioMotoController {

    @FXML private TextField txtPlate;
    @FXML private TextField txtBrand;
    @FXML private TextField txtYearModel;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private MotoService motoService;
    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        motoService = new MotoService();

        // Configuración visual inicial para mejorar la UX
        txtPlate.setPromptText("Ej: ABC123");
        txtBrand.setPromptText("Ej: Yamaha");
        txtYearModel.setPromptText("Ej: 2024");

        // Deshabilita el botón Guardar si los campos están vacíos
        btnSave.disableProperty().bind(
            txtPlate.textProperty().isEmpty()
                .or(txtBrand.textProperty().isEmpty())
                .or(txtYearModel.textProperty().isEmpty())
        );
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void onSaveProduct() {
        if (!validateFields()) return;

        String plate = txtPlate.getText().trim();
        String brand = txtBrand.getText().trim();
        String yearModel = txtYearModel.getText().trim();

        if (motoService.searchByPlate(plate) != null) {
            showAlert("Error", "Ya existe una moto con esa placa", Alert.AlertType.ERROR);
            return;
        }

        Moto newMoto = MotoFactory.createMoto(plate, brand, yearModel);
                               
        motoService.addMoto(newMoto);

        showAlert("Éxito", "Moto creada correctamente", Alert.AlertType.INFORMATION);
        backToDashboard();
    }

    @FXML
    private void onCancel() {
        backToDashboard();
    }

    private void backToDashboard() {
        if (dashboardController != null) {
            dashboardController.backToDashboard();
        }
    }

    private boolean validateFields() {
        if (txtPlate.getText().trim().isEmpty() ||
            txtBrand.getText().trim().isEmpty() ||
            txtYearModel.getText().trim().isEmpty()) {
            showAlert("Error de validación", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}
