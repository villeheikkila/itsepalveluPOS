package itsepalvelupos.domain;

import java.util.Objects;

public class Account {
    private int id;
    private String username;
    private String password;
    private boolean admin;

    public Account(int id, String username, String password, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) { this.admin = admin; }

    public boolean passwordEquals(String pass) {
        if (this.password.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
