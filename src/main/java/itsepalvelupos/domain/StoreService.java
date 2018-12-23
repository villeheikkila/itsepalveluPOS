package itsepalvelupos.domain;

import itsepalvelupos.database.StoreDao;

import java.sql.SQLException;

public class StoreService {

    StoreDao storeDao;
    Store store;

    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    /**
     * Metodi lisää kaupan Store tietokantatauluun.
     *
     * @param   name   Kaupan nimi (pituus vähintään 3 merkkiä).
     * @param   cash  Kaupan kassan saldo (pituus vähintään 3 merkkiä).
     *
     * @return palauttaa true, jos kaupalle annetut tiedot ovat kelvollisia tai false, jos syöte ei ole kelvollinen tai tietokannassa on jo kauppa.
     *
     * @throws SQLException mikäli tapahtuu virhe.
     *
     */

    public boolean createStore(String name, int cash) throws SQLException {
        if ((name.length() > 3) && (storeDao.getStore() == null)) {
            store = new Store(name, cash);
            storeDao.add(store);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi lisää annettavan summan kaupan kassaa, arvo voi olla myös negatiivinen.
     *
     * @param  ammount   Lisättävä summa (kokonaisluku).
     *
     * @throws SQLException mikäli tapahtuu virhe.
     *
     * @return palauttaa true, jos rahan lisääminen tai vähentäminen onnistuu, muuten false.
     *
     */

    public boolean changeCash(Integer ammount) throws SQLException {
        store = storeDao.getStore();
        if ((store != null) && (store.getCash() + ammount) >= 0) {
            store.setCash(store.getCash() + ammount);
            storeDao.update(store);
            return true;
        } else {
            return false;
        }
    }
}
