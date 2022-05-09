public class Misc extends Item {
    public Misc(int quantity, double price, String name, int quantityCap) {
        super.setQuantity(quantity);
        super.setPrice(price);
        super.setName(name);
        super.setCategory(Category.Misc);
        super.setQuantityCap(quantityCap);
    }
}
