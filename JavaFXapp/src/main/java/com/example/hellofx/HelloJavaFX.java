package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        // 1. Text Field
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        // 2. Dropdown (ComboBox)
        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Lusaka", "Copperbelt", "Central", "Eastern",
                "Luapula", "Northern", "North-Western", "Southern", "Western", "Muchinga"
        );
        provinceBox.setPromptText("Select Province");

        // 3. Buttons
        Button addButton = new Button("Add Customer");
        Button deleteButton = new Button("Delete Customer");

        // 4. Table and Columns
        TableView<Customer> table = new TableView<>();
        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        table.getColumns().addAll(nameCol, provinceCol);
        table.setItems(customerList);

        // ================= LOGIC =================

        // Add Button Logic (With Validation)
        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String province = provinceBox.getValue();

            // Validation: Check if name is empty or province is not selected
            if (name.isEmpty() || province == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a name and select a province.");
                alert.showAndWait();
                return;
            }

            // If validation passes, add the customer
            customerList.add(new Customer(name, province));

            // Clear the form for the next entry
            nameField.clear();
            provinceBox.getSelectionModel().clearSelection();
            nameField.requestFocus();
        });

        // Delete Button Logic (With Confirmation)
        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                // Nothing selected
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a customer from the table to delete.");
                alert.showAndWait();
            } else {
                // Confirm before deleting
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Delete");
                confirm.setHeaderText(null);
                confirm.setContentText("Are you sure you want to delete " + selected.getName() + "?");

                if (confirm.showAndWait().get() == ButtonType.OK) {
                    customerList.remove(selected);
                }
            }
        });

        // ==========================================

        // 5. Layout Arrangement
        HBox formLayout = new HBox(10);
        formLayout.getChildren().addAll(nameField, provinceBox, addButton, deleteButton);
        formLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(formLayout, table);
        mainLayout.setPadding(new Insets(10));

        // 6. Scene and Stage
        Scene scene = new Scene(mainLayout, 700, 400);
        stage.setTitle("Customer Manager - 202508384");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}