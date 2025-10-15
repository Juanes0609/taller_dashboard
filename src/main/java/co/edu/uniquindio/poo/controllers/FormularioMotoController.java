package co.edu.uniquindio.poo.controllers;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.repositories.MotoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para el formulario de creación de productos
 */
public class FormularioMotoController {

    @FXML private TextField txtPlate;
    @FXML private TextField txtBrand;
    @FXML private TextField txtYearModel;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private MotoRepository motoRepository; 
    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        motoRepository = MotoRepository.getInstance(); 
    }

    /**
     * Establece el controlador del dashboard para poder regresar
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    /**
     * Maneja el evento de guardar producto
     */
    @FXML
    private void onSaveProduct() {
        if (!validateFields()) {
            return;
        }

        try {
            String plate = txtPlate.getText().trim();
            String brand = txtBrand.getText().trim();
            String yearModel = txtYearModel.getText().trim();
            
            

            if (motoRepository.searchByPlate(plate) != null) {
                showAlert("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            Moto newMoto = new Moto(brand, yearModel, plate);
            motoRepository.addMoto(newMoto); 

            showAlert("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);
            
            // Volver al dashboard
            backToDashboard();

        } catch (NumberFormatException e) {
            showAlert("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de cancelar
     */
    @FXML
    private void onCancel() {
        backToDashboard();
    }

    /**
     * Vuelve a mostrar el dashboard
     */
    private void backToDashboard() {
        if(dashboardController != null) {
            dashboardController.backToDashboard();
        }
    }

    /**
     * Valida que los campos del formulario estén completos
     */
    private boolean validateFields() {

        if (txtPlate.getText().trim().isEmpty() || txtBrand.getText().trim().isEmpty() || 
            txtYearModel.getText().trim().isEmpty()) {
            showAlert("Error de validación", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        return true;
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