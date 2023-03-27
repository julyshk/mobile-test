package config;
import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})

public interface EmulatorConfig extends Config {

    @Key("appiumServer")
    String appiumServer();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();
}