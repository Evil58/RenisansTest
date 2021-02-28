package CaseRenicTest;

import org.testng.annotations.Test;

public class CreditСardTest extends TestBase {

  @Test
  public void cardTest(){
    gotoWebsiteRenisans();
    gotoCreditCard();
    selectCreditCard();
    fillFromCards(new FormCards("Лёшин", "Максим", "9534450758", "Gaijin73@mail.ru"));
  }
}
