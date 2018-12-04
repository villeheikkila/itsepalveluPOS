package itsepalvelupos.domain;

import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Store {
    Integer id;
    String name;
    int cash;
    private ArrayList<Integer> shoppingCart;


    public Store(Integer id, String name, int cash) {
        this.cash = cash;
        this.shoppingCart = new ArrayList<>();
    }

    public void addToCart(Integer id) throws SQLException {
        shoppingCart.add(id);
    }

    public void getShoppingCart() {
        for (Integer temp : shoppingCart) {
            System.out.println("Cart: " + temp);
        }
    }
}
