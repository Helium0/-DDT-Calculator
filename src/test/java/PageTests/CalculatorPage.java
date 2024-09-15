package PageTests;

import Utility.ExcelUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CalculatorPage {

    private WebDriver driver;
    private String myExcelPath = ExcelUtility.calculatorFile();
    private String excelSheet = "Calculator";

    @FindBy(id = "CIT-chart-submit")
    private WebElement clickChart;
    @FindBy(id = "mat-input-0")
    private WebElement websiteDepositAmountField;
    @FindBy(id = "mat-input-1")
    private WebElement websiteLengthField;
    @FindBy(id = "mat-input-2")
    private WebElement websiteInterestRateField;
    @FindBy(tagName = "mat-select")
    private WebElement websiteCompoundingField;
    @FindBy(xpath = "//div[@id='mat-select-0-panel']//mat-option")
    private List<WebElement> matSelectList;
    @FindBy(xpath = "//span[@id='displayTotalValue']")
    private WebElement websiteActualTotalValue;


    public CalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void clickButton() {
        clickChart.click();
    }

    public WebDriverWait waitForSomething() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void readCellsFromExcelFile() throws IOException {

        int rows = ExcelUtility.getRowsNumber(myExcelPath, excelSheet);

        for (int i = 1; i <= rows; i++) {
            String depositAmount = ExcelUtility.getCellData(myExcelPath, excelSheet, i, 0);
            String lengthMonths = ExcelUtility.getCellData(myExcelPath, excelSheet, i, 1);
            String interestRate = ExcelUtility.getCellData(myExcelPath, excelSheet, i, 2);
            String compounding = ExcelUtility.getCellData(myExcelPath, excelSheet, i, 3);
            String expectedTotal = ExcelUtility.getCellData(myExcelPath, excelSheet, i, 4);

            websiteDepositAmountField.clear();
            websiteDepositAmountField.sendKeys(depositAmount);
            websiteLengthField.clear();
            websiteLengthField.sendKeys(lengthMonths);
            websiteInterestRateField.clear();
            websiteInterestRateField.sendKeys(interestRate);
            websiteCompoundingField.click();
            waitForSomething().until(ExpectedConditions.visibilityOf(websiteCompoundingField));
            matSelectList.stream().filter(element -> element.getText().equals(compounding))
                    .forEach(element -> element.click());

            clickButton();



            if (websiteActualTotalValue.getText().equals(expectedTotal)) {
                System.out.println("Passed");
                ExcelUtility.setCellData(myExcelPath, excelSheet, i, 6, "Passed");
                ExcelUtility.fillGreen(myExcelPath, excelSheet, i, 6);

            } else {
                System.out.println("Failed");
                ExcelUtility.setCellData(myExcelPath, excelSheet, i, 6, "Failed");
                ExcelUtility.fillRed(myExcelPath, excelSheet, i, 6);
            }
        }
    }


}
