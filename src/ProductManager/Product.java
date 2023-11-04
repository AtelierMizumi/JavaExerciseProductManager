package ProductManager;

public class Product {
    private String ProductID;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product() {
    }

    public Product(String ProductID, String name, double price, int quantity, String category) {
        this.ProductID = ProductID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public String toString() {
        return  ProductID + ", " + name + ", " + price + ", " + quantity + ", " + category;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
