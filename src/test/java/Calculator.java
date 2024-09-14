import Utility.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Calculator extends ExcelUtility {


    @Test
    public void getCalculator() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");

        String myExcelPath = ExcelUtility.calculatorFile();

        int rows = ExcelUtility.getRowsNumber(myExcelPath, "Calculator");


        for (int i = 1; i <= rows; i++) {
            String depositAmount = ExcelUtility.getCellData(myExcelPath, "Calculator", i, 0);
            String lengthMonths = ExcelUtility.getCellData(myExcelPath, "Calculator", i, 1);
            String interestRate = ExcelUtility.getCellData(myExcelPath, "Calculator", i, 2);
            String compounding = ExcelUtility.getCellData(myExcelPath, "Calculator", i, 3);
            String expectedTotal = ExcelUtility.getCellData(myExcelPath, "Calculator", i, 4);

            WebElement ele = driver.findElement(By.id("mat-input-0"));
            ele.clear();
            ele.sendKeys(depositAmount);
            WebElement el2 = driver.findElement(By.id("mat-input-1"));
            el2.clear();
            el2.sendKeys(lengthMonths);

            WebElement el3 = driver.findElement(By.id("mat-input-2"));
            el3.clear();
            el3.sendKeys(interestRate);

            WebElement el4 = driver.findElement(By.tagName("mat-select"));
            el4.click();
            Thread.sleep(3000);
            List<WebElement> lista = driver.findElements(By.xpath("//div[@id='mat-select-0-panel']//mat-option"));

            lista.stream().filter(element -> element.getText().equals(compounding))
                    .forEach(element -> element.click());


            driver.findElement(By.id("CIT-chart-submit")).click();
            String actualTotal = driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();


            if (actualTotal.equals(expectedTotal)) {
                System.out.println("Test passed");
                ExcelUtility.setCellData(myExcelPath, "Calculator", i, 6, "Passed");
                ExcelUtility.fillGreen(myExcelPath, "Calculator", i, 6);

            } else {
                System.out.println("Test failed");
                ExcelUtility.setCellData(myExcelPath, "Calculator", i, 6, "Failed");
                ExcelUtility.fillRed(myExcelPath, "Calculator", i, 6);
            }



        }
        driver.quit();
    }
}
