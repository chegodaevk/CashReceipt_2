package by.chegodaev;

public class CashReceipt {
    public static void main(String[] args) throws Exception {
        Shopping shopping = new Shopping();

        DataReceipt dataReceipt = new DataReceipt();
        dataReceipt.getReceiptData(shopping.getArrayShopping());
    }
}
