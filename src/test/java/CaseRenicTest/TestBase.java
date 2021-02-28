package CaseRenicTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Базовый клас с методами
 */
public class TestBase {
  ChromeDriver driver;

  @BeforeMethod
  public void driver(){
    settingsDriver();
    settingsChrome();
  }

  /**
   * Настройки драйвера
   */
  private void settingsDriver() {
    WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  /**
   * Настройки браузера
   */
  private void settingsChrome() {
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> prefs = new HashMap<>();
    prefs.put("download.default_directory", System.getProperty("java.io.tmpdir"));
    prefs.put("download.prompt_for_download", false);
    prefs.put("download.directory_upgrade", true);
    prefs.put("plugins.always_open_pdf_externally", true);
    options.setExperimentalOption("prefs",prefs);
  }

  /**
   * Заполнение формы с оформлением кредитной карты
   * 
   */
  protected void fillFromCards(FormCards formCards) {
    driver.findElement(By.xpath("//input[@data-message-title='Фамилия']")).sendKeys(formCards.getSurname());
    driver.findElement(By.xpath("//input[@data-message-title='Имя']")).sendKeys(formCards.getName());
    driver.findElement(By.xpath("//label[text()=' Нет отчества']")).click();
    driver.findElement(By.xpath("//input[@data-message-title='Мобильный телефон']")).sendKeys(formCards.getTelephone());
    driver.findElement(By.xpath("//input[@name='AdditionalEmail']")).sendKeys(formCards.getMail());
    driver.findElement(By.xpath("//div[@id='r1-styler']")).click();
    driver.findElement(By.xpath("//div[text()='Где Вы желаете получить карту?']")).click();
    driver.findElement(By.xpath("//div[@class='jq-selectbox__dropdown']//li[text()='Пензенская область']")).click();
  }

  /**
   * Выбор кредитной карты
   */
  protected void selectCreditCard() {
    driver.findElement(By.xpath("//a[@href='https://rencredit.ru/app/card/365']")).click();
  }

  /**
   * Переход на вкладку с кредитными картами
   */
  protected void gotoCreditCard() {
    driver.findElement(By.xpath("//div[@class='service__title']/a")).click();
  }

  /**
   * Перехо на сайт Ренесанс
   */
  protected void gotoWebsiteRenisans() {
    driver.get("https://rencredit.ru/");
  }

  /**
   * Закрытие окна браузера, после окончания теста
   */
  @AfterMethod
  public void closePage(){
    driver.quit();
  }

  /**
   * Скачивание выбранного документа с условиями и тарифами по кредитным картам
   */
  protected void downloadConditions() {
    driver.findElement(By.xpath(
                    "//a[text()='Общие условия предоставления кредитов и выпуска банковских карт от 05.10.2020']"))
            .click();
  }

  /**
   * Поиск ссылки на тарифы и условия по кредитным картам
   */
  protected void searchConditions() {
    driver.findElement(By.xpath("//div[@class='footer__nav-list']//a[text()='Тарифы и условия']")).click();
  }

  /**
   * Заполнение формы доходности по вкладам
   */
  protected void calculateByDeposit() {
    driver.findElement(By.xpath("//div[@class='calculator__check-row-field check-deposit']")).click();
    driver.findElement(By.xpath("//input[@type='text' and @name='amount']")).click();
    driver.findElement(By.xpath(
            "//label[@class='currency-input-field currency-input-field_rub js-currency-input-field']/input"))
            .sendKeys("1000000");
    WebElement startPeriod = driver.findElement(By.xpath("//div[@data-property='period']//span"));
    WebElement endPeriod = driver.findElement(By.xpath("//div[@class='range-scale range-scale_item_6']//div[5]"));
    Actions actions = new Actions(driver);
    actions.clickAndHold(startPeriod).moveToElement(endPeriod).release().build().perform();
  }

  /**
   * Выбор вклада
   */
  protected void selectDeposit() {
    driver.findElement(By.xpath("//div[@class='service__title']/a[@href='/contributions/']")).click();
  }
}
