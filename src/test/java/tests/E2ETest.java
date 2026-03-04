package tests;

import org.testng.annotations.Test;
import PAGES_PACKAGE.*;
import core.FrameworkCore;

public class E2ETest extends FrameworkCore {

    @Test//(retryAnalyzer = utils.RetryAnalyzer.class)
    public void completeE2EFlow() {

        EcommercePages page = new EcommercePages();

        page.enterUsername("standard_user")
            .enterPassword("secret_sauce")
            .clickLogin()
            .addProductToCart()
            .openCart()
            .proceedToCheckout()
            .enterCheckoutDetails("NISARGA", "K", "583101")
            .clickContinue()
            .finishOrder();

        System.out.println(page.getConfirmationMessage());
    }
}