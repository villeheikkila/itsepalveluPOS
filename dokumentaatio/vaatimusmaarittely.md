# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista ostaa tuotteita ennalta määritellystä listasta. Jokaisella käyttäjälä on oma tili, joka sisältää tiedot saldosta.

## Käyttäjät

Sovelluksessa on alussa vain _pääkäyttäjä_, jolla on oikeus lisätä uusia tuotteita ja poistaa muita käyttäjiä. Sovellukseen voi lisätä mielivaltaisen määrän _normaaleja käyttäjiä_. Normaaleille käyttäjille on mahdollista antaa pääkäyttäjän oikeudet.

## Käyttöliittymäluonnos

Sovelluksen käynnistäessä tulee esiin kirjautumislomake:

<img src="kuvat/kirjautuminen.png" width="500">

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista kirjautua sisään tai painamalla "Luo uusi käyttäjä" luoda uusi käyttäjä annetuilla tiedoilla.

Varsinainen sovellus aukeaa kun on kirjauduttu sisään:

<img src="kuvat/sovellus.png" width="500">

Tuotteita klikkaamalla ne lisääntyvät "ostoskoriin" ja ostettavien tuotteiden ikoni näkyy valittuna. Tuote ostetaan lopulta painamalla "Osta" painiketta.
Ikkunan yläkulmassa näkyy käyttäjätunnus ja saldo. Lopuksi alhaalta kirjaudutaan ulos.


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 6 merkkiä
  - salasanan täytyy olla vähiintää 6 merkkiä pitkä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus ja salasana kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- käyttäjä näkee listan kaupan tuotteista

- käyttäjä voi klikata tuotetta, jolloin ne lisätään ostettavien tuotteiden listalle

- käyttäjä voi viimeistellä oston painalla Checkout näppäintä, jolloin tuotteiden hinta vähennetään käyttäjän saldosta

- käyttäjä voi lisätä saldoa

- käyttäjä voi kirjautua ulos järjestelmästä

## Pääkäyttäjän oikeudet

- voi lisätä tuotteita tuotteiden lisäyslomakkeella

- voi poistaa muita käyttäjiä

- voi muuttaa muiden käyttäjien salasanoj

- voi hyväksyä saldo lisäykset

## Jatkokehitysideoita

Perusversion jälkeen projektiin lisätään muita hyödyllisiä ominaisuuksia.

- ostoshistoria, joka pitää kirjaa ostetuista tuotteista

- inventaario, johon voi määrittää kaupassa olevien tuotteiden määrät ja seurata muutoksia

- laajemmat käyttäjätiedot, kuten sähköposti ja avatar

- loppuneen tuotteen tilauspyyntö

- erilliset sivut eri tuotekategorioille