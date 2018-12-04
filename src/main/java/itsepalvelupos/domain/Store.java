package itsepalvelupos.domain;


import java.sql.SQLException;
import java.util.ArrayList;

public class Store {
    Integer id;
    String name;
    int cash;
    private ArrayList<Integer> shoppingCart;

    public Store(Integer id, String name, int cash) {
        this.cash = cash;
        this.shoppingCart = new ArrayList<>();
    }

    public void addToCart(Integer id) {
        shoppingCart.add(id);
    }

    public ArrayList<Integer> getShoppingCart() {
        return this.shoppingCart;
    }
}
