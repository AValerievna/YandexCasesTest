package yandex.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class CategoryPage extends BasePage {
    private static final By catPageIdent = By.className("n-layout_name_catalog");
    private By btnHome = By.className("logo_part_market");

    public CategoryPage() {
        super(catPageIdent);
    }

    public boolean rightCategoryThemeTitle(String keyText) {
        Map<String, String> accord = new HashMap<>();
        accord.put("Электроника", "Электроника");
        accord.put("Компьютеры", "Компьютерная техника");
        accord.put("Бытовая техника", "Бытовая техника");
        accord.put("Детские товары", "Детские товары");
        accord.put("Зоотовары", "Товары для животных");
        accord.put("Дом, дача, ремонт", "Товары для дома");
        accord.put("Одежда и обувь", "Одежда и обувь");
        accord.put("Красота и здоровье", "Товары для красоты и здоровья");
        accord.put("Авто", "Товары для авто- и мототехники");

        return wd.findElement(By.xpath("//h1")).getText().contains(accord.get(keyText));
    }


    public void goToHomePage() {
        wd.findElement(btnHome).click();
    }
}
