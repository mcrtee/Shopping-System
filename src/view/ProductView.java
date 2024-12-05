package view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import model.Product;

import java.util.List;

public class ProductView {
    private TextField searchField;
    private Button searchButton;
    private ListView<String> productListView;
    private Label messageLabel;

    public ProductView(Stage stage) {
        searchField = new TextField();
        searchButton = new Button("Search");
        productListView = new ListView<>();
        messageLabel = new Label();

        VBox layout = new VBox(10, searchField, searchButton, productListView, messageLabel);
        stage.setScene(new javafx.scene.Scene(layout, 400, 300));
        stage.show();
    }

    public String getSearchQuery() {
        return searchField.getText();
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void displayProducts(List<Product> products) {
        productListView.getItems().clear();
        for (Product product : products) {
            productListView.getItems().add(
                    product.getName() + " - $" + product.getPrice() + " (Stock: " + product.getStock() + ")"
            );
        }
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }
}
