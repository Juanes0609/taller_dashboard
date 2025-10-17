package co.edu.uniquindio.poo.controllers;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.model.MotoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListadoMotoController {

    @FXML private TableView<Moto> motosTable;
    @FXML private TableColumn<Moto, String> colPlate;
    @FXML private TableColumn<Moto, String> colBrand;
    @FXML private TableColumn<Moto, String> colYearModel;

    private MotoService motoService = new MotoService();
    private ObservableList<Moto> motosList;
    private DashboardController dashboardController;

    @FXML
    private void initialize() {
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colYearModel.setCellValueFactory(new PropertyValueFactory<>("yearModel"));
        loadProducts();
    }

    public void loadProducts() {
        motosList = FXCollections.observableArrayList(motoService.getAllMotos());
        motosTable.setItems(motosList);
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void onDeleteButton() {
        Moto selected = motosTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Advertencia", "Seleccione una moto para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar eliminación");
        confirmation.setHeaderText("¿Está seguro de eliminar esta moto?");
        confirmation.setContentText(selected.getBrand());

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                motoService.deleteMoto(selected);
                loadProducts();
                showAlert("Éxito", "Moto eliminada correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }

    @FXML
    private void onBackToDashboard() {
        if (dashboardController != null) {
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
