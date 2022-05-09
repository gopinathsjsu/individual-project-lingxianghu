public class Luxury extends Item {
    public Luxury(int quantity, double price, String name, int quantityCap) {
        super.setQuantity(quantity);
        super.setPrice(price);
        super.setName(name);
        super.setCategory(Category.Luxury);
        super.setQuantityCap(quantityCap);
    }
}
