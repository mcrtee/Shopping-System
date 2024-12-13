package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Product;
import model.User;

import java.util.List;

public class ShoppingView {
    private User user;
    private Label welcomeLabel;
    private ListView<Product> productList;  // ListView for products
    private Button viewProductsButton;
    private Button logoutButton;
    private Button searchButton;
    private TextField searchTextField;  // Fixed TextField issue
    private Stage stage;

    public ShoppingView(Stage stage) {
        this.stage = stage;

        // Initialize UI components
        welcomeLabel = new Label();
        productList = new ListView<>();
        viewProductsButton = new Button("View Products");
        logoutButton = new Button("Logout");
        searchButton = new Button("Search");
        searchTextField = new TextField();  // Initialize TextField
        searchTextField.setPromptText("Enter search query...");

        // Layout for the top section (welcome, searchTextField, searchButton, logoutButton)
        HBox topLayout = new HBox(10, welcomeLabel, searchTextField, searchButton, logoutButton);
        topLayout.setPadding(new Insets(10));

        // Layout for the bottom section (cart button)
        HBox bottomLayout = new HBox(10, viewProductsButton);
        bottomLayout.setPadding(new Insets(10));

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(productList);  // Use ListView for products
        mainLayout.setBottom(bottomLayout);

        // Create scene and set on stage
        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setTitle("Shopping System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set the user details and update the welcome message.
     */
    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
    }

    /**
     * Show the shopping view.
     */
    public void show() {
        stage.show();
    }

    public Button getViewCartButton() {
        return viewProductsButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    public ListView<Product> getProductList() {
        return productList;
    }

    /**
     * Display the list of products in the ListView.
     */
    public void displayProducts(List<Product> products) {
        System.out.println("Displaying products: " + products.size()); // Debug
        productList.getItems().clear();
        productList.getItems().addAll(products);

        productList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (empty || product == null) {
                    setText(null);
                } else {
                    setText(product.getId() + ": " + product.getName() + " - $" + product.getPrice() + " (Stock: " + product.getStock() + ")");
                }
            }
        });
    }


    /**
     * Show a message to the user.
     */
    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
