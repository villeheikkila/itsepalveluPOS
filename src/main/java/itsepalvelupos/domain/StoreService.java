package itsepalvelupos.domain;

import itsepalvelupos.database.StoreDao;

import java.sql.SQLException;

public class StoreService {

    StoreDao storeDao;
    Store store;

    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public boolean createStore(String name, int cash) throws SQLException {
        if (name.length() > 3) {
            store = new Store(name, cash);
            storeDao.add(store);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCash(Integer ammount) throws SQLException {
        int current = store.getCash();
        if ((current + ammount) >= 0) {
            store.setCash(current + ammount);
            storeDao.update(store);
            return true;
        } else { return false; }
    }
}
