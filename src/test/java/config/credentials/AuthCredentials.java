package config.credentials;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/auth.properties")
public interface AuthCredentials extends Config {

    @Key("login")
    @DefaultValue("login")
    String login();

    @Key("password")
    @DefaultValue("password")
    String password();
}
