package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
        addButton = new Button("Add Product");
        updateButton = new Button("Update Product");
        deleteButton = new Button("Delete Product");
        welcomeLabel = new Label("Welcome, Admin!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Create form for product details
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Product ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Product Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Price:"), 0, 2);
        form.add(priceField, 1, 2);
        form.add(new Label("Stock:"), 0, 3);
        form.add(stockField, 1, 3);

        // Create button bar
        HBox buttonBar = new HBox(10, addButton, updateButton, deleteButton);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(10));

        // Layout for the entire view
        VBox layout = new VBox(15, welcomeLabel, form, buttonBar, new Label("Product List:"), productListView);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        // Add some default styling for buttons
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        updateButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-weight: bold;");

        Scene scene = new Scene(layout, 450, 600);
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
