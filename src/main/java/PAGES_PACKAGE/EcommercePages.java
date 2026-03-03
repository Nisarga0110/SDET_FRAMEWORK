package PAGES_PACKAGE;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.FrameworkCore;

public class EcommercePages {

    private WebDriver driver;
    private WebDriverWait wait;

    public EcommercePages() {
        this.driver = FrameworkCore.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ================= LOGIN PAGE =================

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public EcommercePages enterUsername(String user) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.clear();
        username.sendKeys(user);
        return this;
    }

    public EcommercePages enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
        return this;
    }

    public EcommercePages clickLogin() {
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("inventory"));
        return this;
    }

    // ================= PRODUCTS PAGE =================

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public EcommercePages addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackToCart));
        addBackpackToCart.click();
        return this;
    }

    public EcommercePages openCart() {
        cartIcon.click();
        wait.until(ExpectedConditions.urlContains("cart"));
        return this;
    }

    // ================= CHECKOUT PAGE =================

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(className = "complete-header")
    private WebElement orderConfirmation;

    public EcommercePages proceedToCheckout() {
        checkoutBtn.click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        return this;
    }

    public EcommercePages enterCheckoutDetails(String first, String last, String zip) {
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.clear();
        firstName.sendKeys(first);

        lastName.clear();
        lastName.sendKeys(last);

        postalCode.clear();
        postalCode.sendKeys(zip);

        return this;
    }

    public EcommercePages clickContinue() {
        continueBtn.click();
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        return this;
    }

    public EcommercePages finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        finishBtn.click();
        return this;
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
        return orderConfirmation.getText();
    }
}