package tests.local;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTestsLocal extends TestBase {

    @Test
    @Tag("emulator")
    @Tag("real")
    void successfulSearchTest() {
        step("Skip onboarding screen", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("emulator")
    @Tag("real")
    void successfulOnboardingTest() {
        step("Verify 1st onboarding", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(exactText("The Free Encyclopedia …in over 300 languages"));
        });

        step("Verify 2st onboarding", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(exactText("New ways to explore"));
        });

        step("Verify 3st onboarding", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(exactText("Reading lists with sync"));
        });

        step("Verify 4st onboarding", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(exactText("Send anonymous data"));
            $(id("org.wikipedia.alpha:id/rejectButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/acceptButton")).shouldBe(visible);
        });
    }
}