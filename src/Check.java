import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Check {
    private HashMap<String, Integer> itemQuantity = new HashMap<String, Integer>();
    private HashMap<Category, Integer> categoryCap = new HashMap<Category, Integer>();
    private HashMap<String, Double> itemPrice = new HashMap<String, Double>();
    private HashMap<String, String> itemToCategory = new HashMap<String, String>();
    private HashSet<String> creditCardSet = new HashSet<String>();
    private String COMMA = ",";

    public void readDatabase() {
        readDatesetSheet();
        ConfigFile();
        readCardsSheet();
    }

    private void ConfigFile() {
        System.out.println("Enter for read config file.");
        try {
            File f = new File("config.csv");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                try (Scanner row = new Scanner(sc.nextLine())) {
                    row.useDelimiter(COMMA);
                    ArrayList<String> ary = new ArrayList<>();
                    while (row.hasNext()) {
                        ary.add(row.next());
                    }
                    Item curItem;
                    if (ary.get(0).equals("Essential")) {
                        categoryCap.put(Category.Essential, Integer.valueOf(ary.get(1)));
                    } else if (ary.get(0).equals("Luxury")) {
                        categoryCap.put(Category.Luxury, Integer.valueOf(ary.get(1)));
                    } else {
                        categoryCap.put(Category.Misc, Integer.valueOf(ary.get(1)));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No config file.");
        }
    }

    private void readDatesetSheet() {
        System.out.println("Input the file name of dataset, Enter for Dateset - Sheet1.csv:\n");
        Scanner sc = new Scanner(System.in);
        boolean haveFile = false;
        while (!haveFile) {
            String fileName = sc.nextLine();
            if (fileName.isEmpty()) {
                fileName = "Dataset - Sheet1.csv";
            }
            try {
                File f = new File(fileName);
                Scanner getInput = new Scanner(f);
                haveFile = true;
                getInput.nextLine();
                while (getInput.hasNextLine()) {
                    try (Scanner row = new Scanner(getInput.nextLine())) {
                        row.useDelimiter(COMMA);
                        ArrayList<String> ary = new ArrayList<>();
                        while (row.hasNext()) {
                            ary.add(row.next());
                        }
                        itemToCategory.put(ary.get(1), ary.get(0));
                        itemQuantity.put(ary.get(1), Integer.valueOf(ary.get(2)));
                        itemPrice.put(ary.get(1), Double.valueOf(ary.get(3)));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error, input your file name:");
            }
        }
    }

    private void readCardsSheet() {
        System.out.println("Input the file name of credit cards, Enter for Cards - Sheet1.csv:\n");
        Scanner sc = new Scanner(System.in);
        boolean haveFile = false;
        while (!haveFile) {
            String cardData = sc.nextLine();
            if (cardData.isEmpty()) {
                cardData = "Cards - Sheet1.csv";
            }
            try {
                File f = new File(cardData);
                Scanner getInput = new Scanner(f);
                haveFile = true;
                getInput.nextLine();
                while (getInput.hasNextLine()) {
                    creditCardSet.add(getInput.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error. input file name:");
            }
        }
    }

    public void handlerOrder() {
        System.out.println("Input the file name of orders:, Enter for Order - Sheet1.csv:\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Item> ary = new ArrayList<>();
        boolean haveFile = false;

        while (!haveFile) {
            String fileName = sc.nextLine();
            if (fileName.isEmpty()) {
                fileName = "Order - Sheet1.csv";
            }
            try {
                File f = new File(fileName);
                Scanner getInput = new Scanner(f);
                haveFile = true;
                ArrayList<String> errRes = new ArrayList<>();
                double totalAmount = 0.0;
                getInput.nextLine();   //skip the header
                while (getInput.hasNextLine()) {
                    try (Scanner row = new Scanner(getInput.nextLine())) {
                        row.useDelimiter(COMMA);
                        ArrayList<String> ary2 = new ArrayList<>();
                        while (row.hasNext()) {
                            ary2.add(row.next());
                        }

                        if (itemToCategory.get(ary2.get(0)).equals("Essential")) {
                            ary.add(new Essential(Integer.valueOf(ary2.get(1)),
                                    itemPrice.get(ary2.get(0)), ary2.get(0), itemQuantity.get(ary2.get(0))));
                        } else if (itemToCategory.get(ary2.get(0)).equals("Luxury")) {
                            ary.add(new Luxury(Integer.valueOf(ary2.get(1)),
                                    itemPrice.get(ary2.get(0)), ary2.get(0), itemQuantity.get(ary2.get(0))));
                        } else if (itemToCategory.get(ary2.get(0)).equals("Misc")) {
                            ary.add(new Misc(Integer.valueOf(ary2.get(1)),
                                    itemPrice.get(ary2.get(0)), ary2.get(0), itemQuantity.get(ary2.get(0))));
                        }
                        if (!creditCardSet.contains(ary2.get(2))) {
                            creditCardSet.add(ary2.get(2));
                            try {
                                System.out.println("Adding to new card to Cards - Sheet1.csv");
                                FileWriter w = new FileWriter("Cards - Sheet1.csv", true);
                                w.append("\n");
                                w.append(ary2.get(2));
                                w.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                errRes = checkErr(ary);
                if (!errRes.isEmpty()) {
                    getError errorHandler = new getError(errRes);
                    errorHandler.process();
                    return;
                }
                totalAmount = getTotal(ary);
                outputResult(totalAmount);
            } catch (FileNotFoundException e) {
                System.out.println("Error, input file name:");
            }
        }
    }

    private double getTotal(ArrayList<Item> items) {
        double res = 0.0;
        for (Item item : items) {
            res += item.getPrice() * item.getQuantity();
        }
        return res;
    }

    private ArrayList<String> checkErr(ArrayList<Item> items) {
        ArrayList<String> res = new ArrayList<>();
        HashMap<Category, Integer> curCateCapMap = new HashMap<>();
        curCateCapMap.put(Category.Essential, 0);
        curCateCapMap.put(Category.Luxury, 0);
        curCateCapMap.put(Category.Misc, 0);
        for (Item item : items) {
            curCateCapMap.put(item.getCategory(), item.getQuantity() + curCateCapMap.get(item.getCategory()));
            if (item.getQuantity() > item.getQuantityCap() || curCateCapMap.get(item.getCategory())
                    > categoryCap.get(item.getCategory())) {
                res.add(item.getName() + "~" + item.getQuantity());
            }
        }
        return res;
    }

    private void outputResult(double amount) {
        try {
            FileWriter w = new FileWriter("Output.csv");
            w.write("Total Amount Paid\n");
            w.write("" + amount);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
