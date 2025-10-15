

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para el formulario de creación de productos
 */
public class FormularioMotoController {

    @FXML private TextField txtCode;
    @FXML private TextField txtName;
    @FXML private TextField txtDescription;
    @FXML private TextField txtPrice;
    @FXML private TextField txtStock;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private ProductoRepository productoRepository; 
    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        productoRepository = ProductoRepository.getInstance(); 
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
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            if (productoRepository.searchByCode(code) != null) {
                showAlert("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            Producto nuevoProducto = new Producto(code, name, description, price, stock);
            productoRepository.addProduct(nuevoProducto); 

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

        if (txtCode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || 
            txtDescription.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() || 
            txtStock.getText().trim().isEmpty()) {
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