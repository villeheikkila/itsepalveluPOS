# itsepalveluPOS

itsepalveluPOS on alusta esimerkiksi kerhotiloissa tapahtuvaa myyntiä varten. Sovellus tulee tarjoamaan graafisen ja kosketusnäyttö ystävällisen käyttöliittymän ostosten tekoon. Tämän lisäksi se tarjoaa työkalut varaston ja myynnin seurantaan.

## Dokumentaatio

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _itsepalveluPOS-1.0-SNAPSHOT_
