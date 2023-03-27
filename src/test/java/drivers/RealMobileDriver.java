 package drivers;

 import com.codeborne.selenide.WebDriverProvider;
 import config.BrowserstackConfig;
 import config.EmulatorConfig;
 import config.RealConfig;
 import io.appium.java_client.android.AndroidDriver;
 import io.appium.java_client.android.options.UiAutomator2Options;
 import org.aeonbits.owner.ConfigFactory;
 import org.openqa.selenium.Capabilities;
 import org.openqa.selenium.WebDriver;

 import javax.annotation.Nonnull;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.MalformedURLException;
 import java.net.URL;

 import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
 import static io.appium.java_client.remote.MobilePlatform.ANDROID;
 import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

 public class RealMobileDriver implements WebDriverProvider {

     public static URL getAppiumServerUrl() {
         RealConfig realConfig = ConfigFactory.create(RealConfig.class);
         try {
             return new URL(realConfig.appiumServer());
         } catch (MalformedURLException e) {
             throw new RuntimeException(e);
         }
     }

     @Nonnull
     @Override
     public WebDriver createDriver(@Nonnull Capabilities capabilities) {
         RealConfig realConfig = ConfigFactory.create(RealConfig.class);
         UiAutomator2Options options = new UiAutomator2Options();
         options.merge(capabilities);

         options.setAutomationName(ANDROID_UIAUTOMATOR2)
                 .setPlatformName(ANDROID)
                 .setDeviceName(realConfig.deviceName())
                 .setPlatformVersion(realConfig.platformVersion())
                 .setApp(getAppPath())
                 .setAppPackage(realConfig.appPackage())
                 .setAppActivity(realConfig.appActivity());

         return new AndroidDriver(getAppiumServerUrl(), options);
     }

     private String getAppPath() {
         String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/" +
                 "releases/download/latest/app-alpha-universal-release.apk";
         String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

         File app = new File(appPath);
         if (!app.exists()) {
             try (InputStream in = new URL(appUrl).openStream()) {
                 copyInputStreamToFile(in, app);
             } catch (IOException e) {
                 throw new AssertionError("Failed to download application", e);
             }
         }
         return app.getAbsolutePath();
     }

 }
