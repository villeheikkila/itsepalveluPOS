package itsepalvelupos.domain;

public class Store {
    Integer id;
    String name;
    int cash;

    public Store(Integer id, String name, int cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }

    public Store(String name, int cash) {
        this.name = name;
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
