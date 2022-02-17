package by.chegodaev;

import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Shopping {

    public ArrayList getArrayShopping() throws Exception {

        ArrayList arrayList = new ArrayList<>();

        try {
            FileReader fr = new FileReader("receipt.txt");
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                arrayList.add(scan.nextLine());
            }
        } catch (IOException e) {
            Logger.getLogger(DataReceipt.class.getName()).info("File receipt.txt does not exist");
        }
        return arrayList;
    }
}