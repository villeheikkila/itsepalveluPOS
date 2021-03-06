package itsepalvelupos.domain;

import itsepalvelupos.database.AccountDao;
import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao;
    private Account currentUser;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Metodilla voidaan kirjautua sisään annetun käyttäjän tiedoilla.
     *
     * @param   userName   Käyttäjän nimi (pituus vähintään 3 merkkiä)
     * @param   password  Käyttäjän salasana (pituus vähintään 3 merkkiä)
     *
     *
     * @return palauttaa true, jos kirjautuminen onnistuu tai false, jos käyttäjää ei ole tai salasana on väärin.
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public boolean login(String userName, String password) throws SQLException {
        Account account = accountDao.findName(userName);

        if (account == null) {
            return false;
        }

        if (account.getPassword().equals(password)) {
            currentUser = account;
            return true;
        }

        return false;
    }

    public Account find(String userName) throws SQLException {
        Account account = accountDao.findName(userName);
        return account;
    }

    /**
     * Metodi lisää uuden käyttäjän Accounts tietokantatauluun
     *
     * @param   userName   Käyttäjän nimi (pituus vähintään 3 merkkiä)
     * @param   password  Käyttäjän salasana (pituus vähintään 3 merkkiä)
     * @param   admin   Ilmaisee onko käyttäjällä pääkäyttäjän oikeudet (totuusarvo)
     * @param   balance   Käyttäjän saldo (positiivinen kokonaisluku)
     *
     *
     * @return palauttaa true, jos tuotteelle annetut tiedot ovat kelvollisia tai false, jos syöte ei ole kelvollinen tai käyttäjä on jo olemassa
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public boolean createUser(String userName, String password, boolean admin, int balance) throws SQLException  {
        if ((accountDao.findName(userName) != null) || (password.length() < 3) || (password.length() < 3)) {
            return false;
        }

        Account account = new Account(userName, password, admin, balance);
        accountDao.add(account);

        return true;
    }

    /**
     * Metodi tekee annetusta käyttäjästä pääkäyttäjän.
     *
     * @param   id   Käyttäjän id (positiivinen kokonaisluku)
     *
     * @return palauttaa true, jos nykyisellä käyttäjällä on oikeus korottaa oikeuksia, muuten false.
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public boolean makeAdmin(Integer id) throws SQLException {
        if (currentUser.isAdmin() == true) {
            Account account = accountDao.findOne(id);
            account.setAdmin(true);
            accountDao.update(account);
            return true;
        } else {
            return false;
        }
    }

    public void makeCurrentUserAdmin() throws SQLException {
        currentUser.setAdmin(true);
        accountDao.update(currentUser);
    }

    /**
     * Metodi lisää annettavan summan käyttäjän saldoon..
     *
     * @param  ammount   Lisättävä summa (kokonaisluku)
     *
     * @throws SQLException mikäli tapahtuu virhe
     *
     */

    public void changeBalance(Integer ammount) throws SQLException {
        int current = currentUser.getBalance();
        currentUser.setBalance(current + ammount);
        accountDao.update(currentUser);
    }
    /**
     * Metodi kirjaa nykyisen käyttäjän ulos
     *
     */

    public void logOut() {
        currentUser = null;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän.
     *
     * @return palauttaa nykyisen käyttöjän
     *
     */

    public Account getCurrentUser() {
        return currentUser;
    }
}
