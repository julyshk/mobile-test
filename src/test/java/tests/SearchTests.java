package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;


public class SearchTests extends TestBase {

    @Test
    @Tag("android")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    void successfulGoToPageTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Click to Javanese language", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).findBy(text("Javanese language")).click();
        });
        step("Verify error", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(exactText("An error occurred"));
        });
        step("Click button Go Back", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_button")).click();
        });
        step("Backtracking check", () -> {
            $(id("org.wikipedia.alpha:id/single_fragment_toolbar_wordmark")).shouldBe(visible);
        });
    }

    @Test()
    @Tag("ios")
    void checkInputTestiOS() {
        step("Click button Text", () -> {
            $(accessibilityId("Text Button")).click();
        });
        step("Check Text Input", () -> {
            $(accessibilityId("Text Input")).shouldBe(visible);
        });
    }
}