package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ville
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void kassaToimii() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
     
    @Test
    public void kateisOstoToimiiKunRahaaTarpeeksEdullinen() {
        assertEquals(10, kassa.syoEdullisesti(250));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoToimiiKunRahaaTarpeeksMaukas() {
        assertEquals(10, kassa.syoMaukkaasti(410));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoToimiiKunRahaaEiOleTarpeeksEdullinen() {
        assertEquals(230, kassa.syoEdullisesti(230));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisOstoToimiiKunRahaaEiOleTarpeeksMaukas() {
        assertEquals(390, kassa.syoMaukkaasti(390));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiOstoToimiiKunRahaaEiOleTarpeeksEdullinen() {
        kortti.otaRahaa(400);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiOstoToimiiKunRahaaEiOleTarpeeksMaukas() {
        kortti.otaRahaa(400);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void korttiOstoToimiiKunRahaaOnTarpeeksiMaukas() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());    
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiOstoToimiiKunRahaaOnTarpeeksiEdullinen() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());    
        assertEquals(260, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());        
    }
    
    @Test
    public void syoEdullisestiToimii() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortinLatausToimii() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void kortinLatausToimiiKunNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(500, kortti.saldo());
    }
}
