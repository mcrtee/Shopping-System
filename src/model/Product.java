package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantityInStock;

    // Constructor with all fields
    public Product(int id, String name, String description, double price, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // Constructor with required fields (id, name, price)
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = ""; // Default empty description
        this.quantityInStock = 0; // Default quantity in stock is 0
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description
                + ", price=" + price + ", quantityInStock=" + quantityInStock + "]";
    }
}


