package CaseRenicTest;

import org.testng.annotations.Test;

public class SelectionDepositsTest extends TestBase {

  @Test
  public void depositTest(){
    gotoWebsiteRenisans();
    selectDeposit();
    calculateByDeposit();
    searchConditions();
    downloadConditions();
  }
}
