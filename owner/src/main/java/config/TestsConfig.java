package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestsConfig extends Config {
    @Key("url")
    String url();

    @Key("browser")
    String browser();

    @Key("influxdb.url")
    String influxUrl();

    @Key("influxdb.usr")
    String influxUser();

    @Key("influxdb.pwd")
    String influxPassword();

    @Key("influxdb.database")
    String influxDB();

}
