# Testausdokumentti

Ohjelman sovelluslogiikka ja DAO-toiminallisuus on testattu JUnit yksikkötesteillä ja kokeilemalla erilaisia ääritapauksia käsin. 

## Yksikkö- ja integraatiotestaus

Sovelluksen luokilla Account, Store ja Product ei ole lainkaan testejä, koska ne sisältävät vain konstruktoreita, settereitä ja gettereitä.
Luokkia AccountService, ProductService ja StoreService testataan kattavasti yksikkö- ja integraatiotesteillä. Testit testaavat kaikkia metodeja 
enemmän tai vähemmän. Testit pyrkivät kattamaan suurimman osan tapauksista, johon sovellusta käytettäessä voitaisiin törmätä.

### DAO-luokat

itsepaveluPOS käyttää SQLiteä kaiken pysyvän datan tallentamiseen. Sovelluksesta löytyvät DAO-luokat AccountDao, ProductDao, StoreDao ja Database.
Kaikkia näiden luokkien metodeja testataan JUnit testeillä. Testit luovat väliaikaisen tietokannan, jonka avulla toiminallisuutta kokeillaan.

### Testauskattavuus

Sovelluksen testikattavuus on erittäin korkea. itsepalvepos.domain:illa 79% ja itsepalvelupos.database:lla 96%. posServicen luokan testit jäivät tekemättä.
Käyttäliittymän testausta ei ole myöskään lainkaan automatisoitu.

### Asennus

Sovellus on testattu toimivan sekä Windows että Linux ympäristöissä.

### Toiminnallisuudet

Kaikki määrittelydokumentaation MVP-toiminallisuudet on testattu toimivaksi.

## Virheilmoitukset

Sovelluksessa virheilmoituksia ei ole toteutettu järkeävsti eikä yhtenäisesti. Suurin osa käyttäjän kohtaamista virheistä ilmoitetaan kuitenkin
käyttäjälle käyttöliittymässä. Osa virheistä tulostetaan päätteeseen.

