package restapitester.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Client {

    @JsonProperty("salt")
    private String salt;

    @JsonProperty("created")
    private String created;

    @JsonProperty("id")
    private int id;

    @JsonProperty("secret")
    private String secret;
    @JsonProperty("login")
    private String login;
    @JsonProperty("enabled")
    private boolean enabled;

    public Client(@JsonProperty("login") String login, @JsonProperty("salt") String salt, @JsonProperty("secret") String secret) {
        this.salt = salt;
        this.secret = secret;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return salt.equals(client.salt) && secret.equals(client.secret) && login.equals(client.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salt, secret, login);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return
                "Client{" +
                        "salt = '" + salt + '\'' +
                        ",created = '" + created + '\'' +
                        ",id = '" + id + '\'' +
                        ",secret = '" + secret + '\'' +
                        ",login = '" + login + '\'' +
                        ",enabled = '" + enabled + '\'' +
                        "}";
    }
}