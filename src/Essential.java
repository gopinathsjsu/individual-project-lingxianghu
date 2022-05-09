public class Essential extends Item {
    public Essential(int quantity, double price, String name, int quantityCap) {
        super.setQuantity(quantity);
        super.setPrice(price);
        super.setName(name);
        super.setCategory(Category.Essential);
        super.setQuantityCap(quantityCap);
    }
}
