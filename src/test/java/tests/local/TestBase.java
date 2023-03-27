package tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import drivers.RealMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    public static void setDriver(){
        String deviceHost = System.getProperty("deviceHost");
        switch (deviceHost) {
            case "android":
            case "ios":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "real":
                Configuration.browser = RealMobileDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String deviceHost = System.getProperty("deviceHost");

        Attach.pageSource();
        closeWebDriver();
        if (deviceHost.equals("android")) {
            Attach.addVideo(sessionId().toString());
        } else if (deviceHost.equals("ios")) {
            Attach.addVideo(sessionId().toString());
        }
    }
}