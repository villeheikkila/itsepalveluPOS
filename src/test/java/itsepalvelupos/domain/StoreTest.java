package itsepalvelupos.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    Store store;

    @Before
    public void setUp() {
        store = new Store("store", 100);
    }

    @Test
    public void addCash() {
        store.addCash(10);
        assertEquals(store.getCash(), 110);
    }

    @Test
    public void addCashNegative() {
        store.addCash(-10);
        assertEquals(store.getCash(), 90);
    }
}