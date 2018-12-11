# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Pakkaus _itsepalvelupos.ui_ sisältää JavaFX:llä ja JFoenixilla toteutetun käyttöliittymän _itsepalvelupos.domain_ sovelluslogiikan ja _itsepalvelupos.dao_ tietojen tallentamisen tietokantaan.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat Account, Product ja Store.
Account kuvaa kauppaa käyttäviä käyttäjiä, Product kaupassa olevia tuotteita ja Store itse kauppaa.

ProductService vastaa kaikesta tuotteita käsittelevästä logiikasta ja vastaavasti AccountService kaikesta käyttäjiä koskevasta logiikasta.

StoreService tarjoaa kaikille käyttöliittymän toiminnoille omat metodit.
  
Alustava luokka/pakkauskaavio (tämä tulee varmasti muuttumaan):

<img src="kuvat/luokka-pakkauskaavio.png" width="500">

Sekvessi kaavio kirjautumisesta, UI osuutta ei ole vielä toteutettu.

<img src="kuvat/sekvessikaavio.png" width="500">


## Tietojen pysyväistallennus

Pakkauksen _itsepalvelupos.database_ luokat _AccountDao_, _ProductDao_ ja _StoreDao_ huolehtivat tietojen tallettamisesta SQLite tietokantaan.

## Tietokanta

Kaikki pysyvät tiedot tallennetaan oletuksena _pos.db_ nimiseen SQLite tietokantaan.