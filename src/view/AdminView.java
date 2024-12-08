package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.User;

import java.util.List;

public class AdminView {
    private final ListView<String> productListView;
    private final TextField idField;
    private final TextField nameField;
    private final TextField priceField;
    private final TextField stockField;
    private final Button addButton;
    private final Button updateButton;
    private final Button deleteButton;
    private final Label welcomeLabel;
    private final Stage stage;

    public AdminView(Stage stage) {
        this.stage = stage;

        // Initialize components
        productListView = new ListView<>();
        idField = new TextField();
        nameField = new TextField();
        priceField = new TextField();
        stockField = new TextField();
        addButton = new Button("Add");
        updateButton = new Button("Update");
        deleteButton = new Button("Delete");
        welcomeLabel = new Label("Welcome, Admin!");

        // Layout
        VBox layout = new VBox(10,
                welcomeLabel,
                new Label("Product ID:"), idField,
                new Label("Product Name:"), nameField,
                new Label("Price:"), priceField,
                new Label("Stock:"), stockField,
                addButton, updateButton, deleteButton, productListView);

        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
    }

    public void show() {
        stage.show();
    }

    public Button getAddProductButton() {
        return addButton;
    }

    public Button getUpdateProductButton() {
        return updateButton;
    }

    public Button getDeleteProductButton() {
        return deleteButton;
    }

    public Product getProductDetailsFromForm() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            return new Product(id, name, price, stock);
        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter valid data.");
            return null;
        }
    }

    public void displayProducts(List<Product> products) {
        productListView.getItems().clear();
        for (Product product : products) {
            productListView.getItems().add(
                    product.getId() + ": " + product.getName() + " - $" + product.getPrice() + " (Stock: " + product.getStock() + ")"
            );
        }
    }

    public int getSelectedProductId() {
        String selectedItem = productListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                return Integer.parseInt(selectedItem.split(":")[0]);
            } catch (NumberFormatException e) {
                showMessage("Error extracting product ID.");
            }
        }
        return -1;
    }

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    public void setUser(User user) {
        if (user != null) {
            welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
        }
    }
}
