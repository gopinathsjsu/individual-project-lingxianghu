public class Item {
    private Category category;
    private String name;
    private int quantity;
    private int quantityCap;
    private double price;

    public void setCategory(Category category) { this.category = category; }
    public Category getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return price; }

    public void setQuantityCap(int quantityCap) { this.quantityCap = quantityCap; }
    public int getQuantityCap() { return quantityCap; }

}
