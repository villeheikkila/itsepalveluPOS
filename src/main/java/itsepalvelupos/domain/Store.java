package itsepalvelupos.domain;


import java.sql.SQLException;
import java.util.ArrayList;

public class Store {
    Integer id;
    String name;
    int cash;

    public Store(Integer id, String name, int cash) {
        this.cash = cash;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int ammount) {
        this.cash += ammount;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
