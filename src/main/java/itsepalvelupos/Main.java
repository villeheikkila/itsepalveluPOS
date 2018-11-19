package itsepalvelupos;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;


public class Main {

    public static void main(String[] args) throws Exception {

        Database database = new Database("pos.db");
        database.databaseInit();
    }
}