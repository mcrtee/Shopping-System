package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ShoppingView {
    private User user;
    private Label welcomeLabel;
    private TableView<Object> productTable; // Placeholder for products
    private Button viewCartButton;
    private Button logoutButton;
    private Stage stage;

    public ShoppingView(Stage stage) {
        this.stage = stage;
        // Initialize UI components
        welcomeLabel = new Label();
        productTable = new TableView<>();
        viewCartButton = new Button("View Cart");
        logoutButton = new Button("Logout");

        // Set up the product table (columns as placeholders)
        TableColumn<Object, String> nameColumn = new TableColumn<>("Product Name");
        TableColumn<Object, Double> priceColumn = new TableColumn<>("Price");
        productTable.getColumns().addAll(nameColumn, priceColumn);

        // Layout for the top section (welcome and logout button)
        HBox topLayout = new HBox(10, welcomeLabel, logoutButton);
        topLayout.setPadding(new Insets(10));

        // Layout for the bottom section (cart button)
        HBox bottomLayout = new HBox(10, viewCartButton);
        bottomLayout.setPadding(new Insets(10));

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(productTable);
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
        return viewCartButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public TableView<Object> getProductTable() {
        return productTable;
    }
}
