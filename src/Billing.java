public class Billing {

    public static void main(String[] args) {
        Check ck = new Check();
        ck.readDatabase();
        ck.handlerOrder();
    }
}
