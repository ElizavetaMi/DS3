import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // headless-режим

        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void shouldSubmitFormSuccessfully() {
        // Вводим имя и фамилию
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Петров Иван");

        // Вводим телефон
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");

        // Ставим галочку
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();

        // Нажимаем кнопку "Продолжить"
        driver.findElement(By.cssSelector("button.button")).click();

        // Проверка текста об успешной заявке
        String successText = driver.findElement(By.cssSelector("[data-test-id=order-success]"))
                .getText().trim();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", successText);
    }
}
