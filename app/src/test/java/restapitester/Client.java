package restapitester;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;


/**
 * Client
 * <p>
 * Entity with personalized information about client
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "login",
        "salt",
        "secret"
})
@Generated("jsonschema2pojo")
public class Client {

    /**
     * Client login for auth
     * (Required)
     */
    @JsonProperty("login")
    @JsonPropertyDescription("Client login for auth")
    private String login;
    /**
     * Client salt
     * (Required)
     */
    @JsonProperty("salt")
    @JsonPropertyDescription("Client salt")
    private String salt;
    /**
     * Client secret
     * (Required)
     */
    @JsonProperty("secret")
    @JsonPropertyDescription("Client secret")
    private String secret;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Client() {
    }

    /**
     * @param salt
     * @param secret
     * @param login
     */
    public Client(String login, String salt, String secret) {
        super();
        this.login = login;
        this.salt = salt;
        this.secret = secret;
    }

    /**
     * Client login for auth
     * (Required)
     */
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    /**
     * Client login for auth
     * (Required)
     */
    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public Client withLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Client salt
     * (Required)
     */
    @JsonProperty("salt")
    public String getSalt() {
        return salt;
    }

    /**
     * Client salt
     * (Required)
     */
    @JsonProperty("salt")
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Client withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    /**
     * Client secret
     * (Required)
     */
    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    /**
     * Client secret
     * (Required)
     */
    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Client withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Client withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}