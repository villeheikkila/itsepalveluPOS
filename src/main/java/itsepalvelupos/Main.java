package itsepalvelupos;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.domain.Product;


public class Main {

    public static void main(String[] args) throws Exception {

        Database database = new Database("pos.db");
        database.databaseInit();
        ProductDao product = new ProductDao(database);
        Product battery = new Product(12345, "Battery", 3, 100);
        product.add(battery);
        product.delete(1);


    }
}