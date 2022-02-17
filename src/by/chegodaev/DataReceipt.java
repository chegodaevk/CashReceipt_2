package by.chegodaev;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class DataReceipt {

    ProductAndCardData productAndCardData = new ProductAndCardData();
    private double sum = 0.0;
    private int id_card = 0;
    private int id_product = 0;
    private int quantity = 0;
    FileWriter fw;

    public DataReceipt()  throws Exception {
        this.fw = new FileWriter("CASH RECEIPT.txt");;
    }

    public void getReceiptData(ArrayList array) throws Exception{
        getHeaderReceipt();
        for(int i=0; i<array.size(); i++){
            String str = (String) array.get(i);
            String str_id = str.substring(0,str.indexOf("-"));
            String str_id_card = str.substring(str.indexOf("-")+1);

            if (!str_id.equals("card")){
                id_product = stringToInteger(str_id);
                quantity = stringToInteger(str_id_card);
                try {
                    double price = Double.parseDouble(productAndCardData.productMap.get(id_product)[1]);
                    if (quantity <= 5) {
                        fw.write(String.format(" %-2s %-12s %8s %8s \n", quantity, productAndCardData.productMap.get(id_product)[0],
                                "$" + price, "$" + price * quantity));
                        sum = sum + (price * quantity);
                    } else {
                        double priceWithPromo = Math.round((price - price / 10) * 1e2) / 1e2;
                        fw.write(String.format(" %-2s %-12s %8s \n %-2s %-12s %8s %8s \n", quantity, productAndCardData.productMap.get(id_product)[0],
                                "$" + price, "", "action -10%", "$" + priceWithPromo, "$" + Math.round(priceWithPromo * quantity * 1e2) / 1e2));

                        sum = sum + (Math.round((price * quantity - price * quantity / 10) * 1e2) / 1e2);
                    }
                } catch (Exception e){
                    Logger.getLogger(DataReceipt.class.getName()).info("Product with id " + id_product + " does not exist");
                }
            } else{
                id_card = stringToInteger(str_id_card);
            }
        }
        getFooterReceipt();
    }

    private Integer stringToInteger(String str){
        int id = 0;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e){
            System.err.println("Неправильный формат строки");
        }
        return id;
    }

    private void getHeaderReceipt () throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        fw.write(String.format("%23s \n", "CASH RECEIPT"));
        fw.write(String.format("%25s \n", "supermarket 24/7"));
        fw.write(String.format("%30s \n", "152, Independence, StarWars"));
        fw.write(String.format("%29s \n", "phone: +375(44) 753 44 99"));
        fw.write(String.format("%-17s %-14s \n", "CASHIER №" + (int)(Math.random()*10000),"DATE: " + dateFormat.format(new Date())));
        fw.write(String.format("%17s %-14s \n", "", "TIME: " + timeFormat.format(new Date())));
        fw.write(String.format("%-3s %-12s %8s %8s \n","QTI","DESCRIPTION", "PRICE", "TOTAL"));
        fw.write("----------------------------------\n");
    }

    private void getFooterReceipt() throws Exception{
        fw.write("==================================\n");
        fw.write(String.format("%-20s %13s \n","Total amount:","$" + Math.round(sum * 1e2)/1e2));
        if(id_card != 0) {
            try {
                fw.write(String.format("%-20s %13s \n", "Discount:", productAndCardData.discountMap.get(id_card) + "%"));
                double total_cost = sum - (double) sum * (productAndCardData.discountMap.get(id_card)) / 100;
                fw.write(String.format("%-20s %13s", "Total cost:", "$" + Math.round(total_cost * 1e2) / 1e2));
            } catch (Exception e) {
                Logger.getLogger(DataReceipt.class.getName()).info("Discount card with number " + id_card + " does not exist");
            }
        }else {
            fw.write(String.format("%-20s %13s", "Total cost:", "$" + Math.round(sum * 1e2) / 1e2));
        }
        fw.close();
    }
}
