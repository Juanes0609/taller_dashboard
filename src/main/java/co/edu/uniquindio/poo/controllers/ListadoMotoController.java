package co.edu.uniquindio.poo.controllers;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.repositories.MotoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListadoMotoController {

    @FXML private TableView<Moto> productsTable; 
    @FXML private TableColumn<Moto, String> colPlate;
    @FXML private TableColumn<Moto, String> colBrand;
    @FXML private TableColumn<Moto, String> colYearModel;

    private MotoRepository motoRepository; 
    private ObservableList<Moto> motosList;
    private DashboardController dashboardController;

    @FXML
    private void initialize() {
        motoRepository = MotoRepository.getInstance(); 
        
        colPlate.setCellValueFactory(new PropertyValueFactory<>("code")); 
        colBrand.setCellValueFactory(new PropertyValueFactory<>("name")); 
        colYearModel.setCellValueFactory(new PropertyValueFactory<>("description")); 
        loadProducts();
    }

     public void loadProducts() {
        motosList = FXCollections.observableArrayList(motoRepository.getMotos()); 
        productsTable.setItems(motosList);
        productsTable.refresh();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void onDeleteButton() {
        Moto selectedMotos = productsTable.getSelectionModel().getSelectedItem();

        if (selectedMotos == null) {
            showAlert("Advertencia", "Por favor seleccione un producto para eliminar", Alert.AlertType.WARNING);
            return;
        }
        
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar eliminación");
        confirmation.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmation.setContentText("Moto " + selectedMotos.getBrand());

        confirmation.showAndWait().ifPresent(response -> { 
            if(response == ButtonType.OK) {
                motoRepository.deleteMoto(selectedMotos); 
                loadProducts();
                showAlert("Éxito", "Moto eliminado correctamente", Alert.AlertType.INFORMATION);
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