package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.User;
import java.util.List;

public class AdminView {
    private TableView<Product> productTable;
    private Button addProductButton;
    private Button updateProductButton;
    private Button deleteProductButton;
    private TextField idField;
    private TextField nameField;
    private TextField priceField;
    private TextField stockField;
    private Stage stage;
    private User user;
    private Label welcomeLabel;

    public AdminView(Stage stage) {
        this.stage = stage;

        // Initialize UI components
        productTable = new TableView<>();
        addProductButton = new Button("Add Product");
        updateProductButton = new Button("Update Product");
        deleteProductButton = new Button("Delete Product");
        idField = new TextField();
        nameField = new TextField();
        priceField = new TextField();
        stockField = new TextField();

        // Labels for the form
        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label priceLabel = new Label("Price:");
        Label stockLabel = new Label("Stock:");

        // Layout for form and buttons
        VBox formLayout = new VBox(5, idLabel, idField, nameLabel, nameField, priceLabel,
                priceField, stockLabel, stockField);
        VBox mainLayout = new VBox(10, productTable, formLayout, addProductButton,
                updateProductButton, deleteProductButton);

        Scene scene = new Scene(mainLayout, 600, 400);

        // Configure the stage
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    // Returns product details entered in the form
    public Product getProductDetailsFromForm() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            return new Product(id, name, price, stock);
        } catch (NumberFormatException e) {
            showMessage("Please enter valid product details.");
            return null;
        }
    }

    // Get the ID of the selected product in the table
    public int getSelectedProductId() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        return selectedProduct != null ? selectedProduct.getId() : -1;
    }

    // Getters for buttons
    public Button getAddProductButton() {
        return addProductButton;
    }

    public Button getUpdateProductButton() {
        return updateProductButton;
    }

    public Button getDeleteProductButton() {
        return deleteProductButton;
    }

    // Display products in the table
    public void displayProducts(List<Product> products) {
        productTable.getItems().setAll(products);
    }

    // Show an informational message
    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    // Show the admin view
    public void show() {
        stage.show();
    }

    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
    }
}
