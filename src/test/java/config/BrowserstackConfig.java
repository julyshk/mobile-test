package config;
import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})

public interface BrowserstackConfig extends Config {
    @Key("browserstack.user")
    String getUser();

    @Key("browserstack.key")
    String getKey();

    @Key("app")
    String getApp();

    @Key("device")
    String getDevice();

    @Key("baseurl")
    String getBaseUrl();

    @Key("version")
    String getVersion();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();

    @Key("platform_version")
    String getPlatformVersion();

    @Key("device_name")
    String getDeviceName();

    @Key("app_activity")
    String getAppActivity();

    @Key("app_package")
    String getAppPackage();

    @Key("device_url")
    String getDeviceUrl();
}