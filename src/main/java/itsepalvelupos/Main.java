package itsepalvelupos;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.domain.Product;
import itsepalvelupos.domain.Store;



public class Main {

    public static void main(String[] args) throws Exception {

        Database database = new Database("pos.db");
        Store store = new Store(1000);
        database.databaseInit();

        store.addProduct(database, "Pepsi", 5, 150);
        store.listProducts(database);

    }
}