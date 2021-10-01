package restapitester;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


//@Builder(toBuilder = true)
@Entity
public class Client {
    @JsonProperty("id")
    private long id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("salt")
    private String salt;
    @JsonProperty("created")
    private Timestamp created;
    @JsonProperty("enabled")
    private boolean enabled;
    public Client(@JsonProperty("login") String login, @JsonProperty("salt") String salt, @JsonProperty("secret") String secret) {
        this.salt = salt;
        this.secret = secret;
        this.login = login;
    }

//    public Client(@JsonProperty("id") long id,@JsonProperty("login") String login, @JsonProperty("salt") String salt, @JsonProperty("secret") String secret) {
//        this.salt = salt;
//        this.secret = secret;
//        this.login = login;
//        this.id = id;
//    }

    public Client() {}

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

    @Basic
    @Column(name = "SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Generated(GenerationTime.INSERT)
    @Column(name = "CREATED")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SECRET")
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Basic
    @Column(name = "LOGIN", unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "ENABLED")
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