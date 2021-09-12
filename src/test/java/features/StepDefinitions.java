package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOfStartBalance(double startBalance) {
        danny.getAccount("EUR").setBalance(startBalance);
    }

    @When("Danny now tops up by {int}")
    public void dannyNowTopsUpByTopUpAmount(double topUpAmount) {

        //danny.getAccount("EUR").addFunds(topUpAmount);
        if (topUpMethod.isRejects()) {
            System.out.println("Payment of " + topUpAmount + " Rejected by bank" );
        }
        else {
            danny.getAccount("EUR").addFunds(topUpAmount);
        }
    }

    @Then("The balance in his euro account should be {double}")
    public void theBalanceInHisEuroAccountShouldBeNewBalance(double newBalance) {
        double expectedResult = newBalance;
        double actualResult = danny.getAccount("EUR").getBalance();

        Assert.assertEquals(expectedResult, actualResult, 0);
    }

    @Given("Danny has {double} euro in his Revolut")
    public void dannyHasEuroInHisRevolut(double arg0) {
        danny.getAccount("EUR").setBalance(arg0);
    }

    @And("Danny selects his {paymentService} to top up with")
    public void dannySelectsHisCreditCardToTopUpWith(PaymentService topUpSource) {
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny adds {double} euro")
    public void dannyAddsEuro(double arg0) {
        if (topUpMethod.isRejects()) {
            System.out.println("Payment of " + arg0 + " Rejected by bank" );
        }
        else {
            danny.getAccount("EUR").addFunds(arg0);
        }

    }

    @Then("The topup fails and Dannys balance remains {double}")
    public void theTopupFailsAndDannysBalanceRemains(double arg0) {
        double expectedResult = arg0;
        double actualResult = danny.getAccount("EUR").getBalance();

        Assert.assertEquals(expectedResult, actualResult, 0);
    }


    @Given("that Danny has {double} euro in his account")
    public void thatDannyHasEuroInHisAccount(double arg0) {
        danny.getAccount("EUR").setBalance(arg0);
    }

    @When("Danny makes a purchase of {double} euro")
    public void dannyMakesAPurchaseOfEuro(double arg0) {
        double newAccBal = danny.getAccount("EUR").getBalance() - arg0;
        danny.getAccount("EUR").setBalance(newAccBal);
    }

    @Then("The balance should be {double}")
    public void theBalanceShouldBe(double arg0) {
        double actualResult = danny.getAccount("EUR").getBalance();
        double expectedResult = arg0;

        Assert.assertEquals(expectedResult, actualResult, 0);
    }
}
