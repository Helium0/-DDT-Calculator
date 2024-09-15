package BaseTest;

import PageTests.BaseTest;
import PageTests.CalculatorPage;
import org.testng.annotations.Test;
import java.io.IOException;


public class Calculator extends BaseTest {


    @Test
    public void getCalculator() throws IOException {
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.readCellsFromExcelFile();


    }
}

