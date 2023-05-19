package tests.browserstack;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.browserstack.TestBaseBrowserstack;
import tests.local.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MishkinTest extends TestBaseBrowserstack {

    @Test
    @Disabled("Пример из лекции")
    @DisplayName("Successful search")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("m_android")
    @DisplayName("Выбор статьи на главном экране")
    void openArticle() {
        String text = "Amidst a political crisis in Ecuador, President Guillermo Lasso (pictured) dissolves the National Assembly and triggers an early general election.";
        step("Нажимаем на статью", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/horizontal_scroll_list_item_image")).click();
        });
        step("Проверка названия статьи", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/view_news_fullscreen_story_text")).shouldHave(Condition.text(text)));
    }

//    @Test
//    @Tag("ios")
//    @DisplayName("Выбор статьи на главном экране")
//    void openArticle1() {
//        String text = "Amidst a political crisis in Ecuador, President Guillermo Lasso (pictured) dissolves the National Assembly and triggers an early general election.";
//        step("Нажимаем на статью", () -> {
//            $(AppiumBy.id("org.wikipedia.alpha:id/horizontal_scroll_list_item_image")).click();
//        });
//        step("Проверка названия статьи", () ->
//                $(AppiumBy.id("org.wikipedia.alpha:id/view_news_fullscreen_story_text")).shouldHave(Condition.text(text)));
//    }
}