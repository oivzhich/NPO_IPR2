package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.example.pages.HomePage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  public void testHomePageHasAHeader() {
    driver.get(baseUrl);
    Assert.assertNotEquals(homepage.header.getText(), "");
  }

  @Test
  public void userCanLoginByUsername() {
    open("/login");
    $(By.name("user.name")).setValue("johny");
    $("#submit").click();
    $(".loading_progress").should(disappear); // Само подождёт, пока элемент исчезнет
    $("#username").shouldHave(text("Hello, Johny!")); // Само подождёт, пока у элемента появится нужный текст
  }
}
