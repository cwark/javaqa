package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config.properties", "system:properties", "system:env"})
public interface TestsConfig extends Config {
    @Key("url")
    String url();

    @Key("browser")
    String browser();

    @Key("lgn")
    String login();

    @Key("pwd")
    String password();

    @Key("influxdb.use")
    String influxUse();

    @Key("influxdb.url")
    String influxUrl();

    @Key("influxdb.usr")
    String influxUser();

    @Key("influxdb.pwd")
    String influxPassword();

    @Key("influxdb.database")
    String influxDB();

}
