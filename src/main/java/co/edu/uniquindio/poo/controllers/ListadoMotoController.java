import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListadoMotoController {

    @FXML private TableView<Producto> productsTable; 
    @FXML private TableColumn<Producto, String> colCode;
    @FXML private TableColumn<Producto, String> colName;
    @FXML private TableColumn<Producto, String> colDescription;
    @FXML private TableColumn<Producto, Double> colPrice;
    @FXML private TableColumn<Producto, Integer> colStock;

    private ProductoRepository productoRepository; 
    private ObservableList<Producto> productsList;
    private DashboardController dashboardController;

    @FXML
    private void initialize() {
        productoRepository = ProductoRepository.getInstance(); 
        
        colCode.setCellValueFactory(new PropertyValueFactory<>("code")); 
        colName.setCellValueFactory(new PropertyValueFactory<>("name")); 
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description")); 
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price")); 
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock")); 

        colPrice.setCellFactory(column -> new TableCell<Producto, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", price));
                }
            }
        });
        
        loadProducts();
    }

     public void loadProducts() {
        productsList = FXCollections.observableArrayList(productoRepository.getProducts()); 
        productsTable.setItems(productsList);
        productsTable.refresh();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void onDeleteButton() {
        Producto selectedProducts = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProducts == null) {
            showAlert("Advertencia", "Por favor seleccione un producto para eliminar", Alert.AlertType.WARNING);
            return;
        }
        
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar eliminación");
        confirmation.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmation.setContentText("Producto " + selectedProducts.getName());

        confirmation.showAndWait().ifPresent(response -> { 
            if(response == ButtonType.OK) {
                productoRepository.deleteProduct(selectedProducts); 
                loadProducts();
                showAlert("Éxito", "Producto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }
    
    @FXML
    private void onBackToDashboard() {
        if(dashboardController != null) {
            dashboardController.backToDashboard();
        } 
    }

    private void showAlert(String title, String message, Alert.AlertType type) { 
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}