package by.chegodaev;

import java.util.HashMap;
import java.util.Map;

public class ProductAndCardData {

    public Map<Integer, String[]> productMap = new HashMap<>();
    public Map<Integer,Integer> discountMap = new HashMap<>();

    public ProductAndCardData() {
        productMap.put(1, new String[] {"Milk", "24", "promo"});
        productMap.put(2, new String[] {"Bread", "33.8"});
        productMap.put(3, new String[] {"Butter", "19", "promo"});
        productMap.put(4, new String[] {"Salt", "59.75", "promo"});
        productMap.put(5, new String[] {"Wine", "79.1"});
        productMap.put(6, new String[] {"Water", "29.9", "promo"});
        productMap.put(7, new String[] {"Banana", "16"});
        productMap.put(8, new String[] {"Beer", "72"});
        productMap.put(9, new String[] {"Chips", "49.5", "promo"});
        productMap.put(10, new String[] {"Cheese", "71"});
        productMap.put(11, new String[] {"Pasta", "27"});
        productMap.put(12, new String[] {"Hamburger", "48.3"});
        productMap.put(13, new String[] {"Rice", "34", "promo"});
        productMap.put(14, new String[] {"Salad", "13"});
        discountMap.put(1234, 10);
        discountMap.put(2345, 15);
        discountMap.put(3456, 20);
        discountMap.put(4567, 25);
        discountMap.put(6789, 30);
        discountMap.put(7890, 35);
    }

}
