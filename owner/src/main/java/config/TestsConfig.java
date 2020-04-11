package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestsConfig extends Config {
    @Key("url")
    String url();

    @Key("browser")
    String browser();
}
