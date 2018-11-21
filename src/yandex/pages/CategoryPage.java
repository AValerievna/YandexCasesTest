package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class CategoryPage extends Page{
    private static final String SUBSTR1 = "//h1[text()=\"";
    private static final String SUBSTR_2 = "\"]";

    public CategoryPage(WebDriver wb){
        super(wb);
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

        return elementExists(By.xpath(SUBSTR1 + accord.get(keyText)+ SUBSTR_2));
    }
}
