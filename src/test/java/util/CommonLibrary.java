package util;

import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paulhammant.ngwebdriver.ByAngularBinding;
import com.paulhammant.ngwebdriver.NgWebDriver;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


@Slf4j
public class CommonLibrary extends PageObject {
   

   
    // SystemProperty prop = new SystemProperty();
    String parentHandleWindow;
   
    String appTypeRadioBtnXpath = "//input[@type='radio']";
   
    @FindBy(xpath = "//input[contains(@value, 'Continue')]")
    protected static WebElementFacade saveContinueBtn;
   
    @FindBy(xpath = "//input[@value='Save']")
    protected static WebElementFacade saveBtn;
   
    @FindBy(xpath = "//input[@id='continue_button']")
    protected static WebElementFacade saveContinueBtnOnUlpoadPage;
   
    @FindBy(xpath = "//input[@value='Continue']")
    protected static WebElementFacade continueBtn;
   
    @FindBy(xpath = "//a[@id='link_back']")
    protected static WebElementFacade backToLink;
   
    @FindBy(xpath = "//p[4]/strong[1]")
    protected static WebElementFacade applicationID;
   
    // @FindBy(xpath = "//a[@id='link_bfbbeffdjgdgbdbd']")
    @FindBy(linkText = "Log Out")
    protected static WebElementFacade appLogoutBtn;
   
    @FindBy(xpath = "//p[3]/strong[1]")
    protected static WebElementFacade statusField;
   
    @FindBy(xpath = "//div[1]/p[1]/strong[1]")
    protected static WebElementFacade statusFieldForWithdrawApp;
   
    @FindBy(xpath = "//p[2]/strong[1]")
    protected static WebElementFacade statusFieldForWithdawAppId;
   
    @FindBy(xpath = "//input[@value='Save & Continue']")
    protected static WebElementFacade saveAndContinueBtn;
   
    @FindBy(xpath = "//input[contains(@value,'Submit')]")
    protected static WebElementFacade SubmitBtn;
   
    @FindBy(xpath = "//input[contains(@value,'Start My Application')]")
    protected static WebElementFacade startMyApplicationButton;
   
    @FindBy(xpath = "//a[@id='close-ineligible-dialog']")
    protected static WebElementFacade continueEditingPopUpBtn;
   
    @FindBy(xpath = "//*[@id='ineligible-form:return_link']")
    protected static WebElementFacade reviewAndSubmitPopUpBtn;
   
    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div[3]/button[2]")
    protected static WebElementFacade hrsaPopUpNoThankBtn;
   
    // Random ran = new Random();
   
    /**
     * Handle alert with a loop.
     *
     * @param Nothing
     * @return Nothing.
     * @exception NoAlertPresentException
     * @see
     */
    public void handleAlert() {
        waitABit(5000);
        // String ActualAlertText = "",
        // ExpectedAlertText = ApplicationConstants.extranetAlertMessageText.replaceAll("[^\\w\\d\\s]", " ");
        final int count = 12;
        int i = 0;
        while (i++ < count) {
            try {
                // ActualAlertText = getAlert().getText().replaceAll("[^\\w\\d\\s]", " ");
                getAlert().accept();
                log.info("LOG : acceptAlertWithRetry > alert is accepted");
                waitABit(500);
                // assertTrue(ActualAlertText.contentEquals(ExpectedAlertText));
                // log.info("LOG : Validating the alert text as : " + ExpectedAlertText);
                // Serenity.recordReportData().withTitle("Extranet Page ALERT TEXT Validation")
                // .andContents(ExpectedAlertText);
                return;
            } catch (NoAlertPresentException e) {
                waitABit(500);
                continue;
            }
        }
        log.warn("WARN : acceptAlertWithRetry > Completed handling Alert loop without seeing alert displayed");
       
    }
   
    /**
     * Scroll a web element into view with JavaScript.
     *
     * @param element
     *            to scroll into view
     * @return Nothing
     * @exception
     * @see
     */
    public void scrollElementIntoView(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
    }
   
//    /**
//     * Find a web element based on xpath prefix, name string, and suffix.
//     *
//     * @param prefix
//     *            first part of the xpath
//     * @param name
//     *            the string in the middle of the xpath
//     * @param suffix
//     *            the last part of the xpath
//     * @return web element
//     * @exception
//     * @see
//     */
//    public WebElementFacade findByXpath(String prefix, String name, String suffix) {
//        return element(getDriver().findElement(By.xpath(prefix + name + suffix)));
//    }
//   
//    /**
//     * Find a web element based on xpath prefix, name string, and suffix.
//     *
//     * @param prefix
//     *            first part of the xpath
//     * @param name
//     *            the string in the middle of the xpath
//     * @param suffix
//     *            the last part of the xpath
//     * @param rootElement
//     *            find element under this root element
//     * @return web element
//     * @exception
//     * @see
//     */
//    public WebElementFacade findByXpath(String prefix, String name, String suffix, WebElement rootElement) {
//        return element(rootElement.findElement(By.xpath("." + prefix + name + suffix)));
//    }
   
    public void pressEnter() {
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
           
            e.printStackTrace();
        }
       
    }
   
    public void busyIndicatorSpinner() {
        try {
            String busyIndicatorXpath = "//div[@id='page-busy-indicator']";
            int t = 0;
            waitABit(1000);
            while (t < 30) {
                if (element(busyIndicatorXpath).isCurrentlyVisible()) {
                    log.info("detected spinner, waiting till it's absent to countinue");
                    waitForAbsenceOf(busyIndicatorXpath);
                   
                } else {
                    break;
                }
                t++;
            }
           
        } catch (Exception e) {
           
        }
       
    }
   
    public void startApplication() {
        WebElementFacade startAppButton = find(By.id("commandButton_start-application"));
        startAppButton.click();
    }
   
    public void clickOnSaveContinueBtn() {
        // force focusing the save and continue button
        element(saveContinueBtn).waitUntilEnabled();
        // String id = element(saveContinueBtn).getAttribute("id");
        // WebElementFacade saveContinueButton = find(By.id(id));
        // waitABit(1000);
        try {
            log.info("LOG INFO: Clicking Save and Continue button");
            withAction().moveToElement(saveContinueBtn).click().perform();
        } catch (Exception e) {
            log.error("LOG INFO: Save and Continue button not clicked");
        }
       
    }
   
    public void clickOnSaveAndContinueBtn() {
        element(saveAndContinueBtn).waitUntilVisible();
        element(saveAndContinueBtn).waitUntilClickable();
        log.info("Clicking Save and Continue button");
        try {
            clickOn(saveAndContinueBtn);
        } catch (Exception e) {
            log.error("LOG INFO: Save and Continue button not clicked");
        }
    }
   
    public void clickOnSaveBtn() {
        element(saveBtn).isVisible();
        clickOn(saveBtn);
       
    }
   
    public void clickOnStartMyApplicationButton() {
        waitFor(ExpectedConditions.elementToBeClickable(startMyApplicationButton));
        try {
            element(startMyApplicationButton).click();
            log.info("LOG INFO: Start My Application button clicked");
        } catch (Exception e) {
            log.error("LOG INFO: Start My Application button not clicked");
        }
    }
   
    public void clickOnSubmitBtn() {
        element(SubmitBtn).waitUntilVisible();
        // String id = element(SubmitBtn).getAttribute("id");
        // WebElementFacade submitButton = find(By.id(id));
        // scrollIntoView(submitButton, null);
        // waitABit(1000);
        // clickOn(SubmitBtn);
        try {
            log.info("LOG INFO: Clicking Save and Continue button");
            withAction().moveToElement(SubmitBtn).click().perform();
        } catch (Exception e) {
            log.error("LOG INFO: Save and Continue button not clicked");
        }
    }
   
    public void clickOnSaveContinueBtnOnDocPage() {
        element(saveContinueBtnOnUlpoadPage).isVisible();
        clickOn(saveContinueBtnOnUlpoadPage);
       
    }
   
    public void clickOnContinueBtn() {
        element(continueBtn).isVisible();
        try {
            clickOn(continueBtn);
        } catch (Exception e) {
            clickOn(continueBtn);
        }
       
    }
   
    // public void goToSitePocHomePage() {
    // try {
    // getDriver().navigate().to(prop.getProperties().getProperty("webdriver.sitepoc.url").toString());
    // log.info("open bmiss landing page");
    // } catch (Exception e) {
    // }
    // }
   
    /**
     * this method will return list of radio buttons on the current page
     *
     * @return
     */
    public List<WebElementFacade> radioBtn() {
        String xpath = "//input[@type='radio']";
        List<WebElementFacade> radioBtnList = findAll(xpath);
        return radioBtnList;
       
    }
   
    /**
     * this method will Selects radio button by index
     *
     * @return
     */
   
    public void clickOnAppTypeRadioBtn(int locator) {
        List<WebElementFacade> appRadioBtn = findAll(appTypeRadioBtnXpath);
        element(appRadioBtn.get(locator)).waitUntilVisible();
        appRadioBtn.get(locator).click();
        waitABit(5000);
       
    }
   
    /**
     * this method will return list of checkbox on the current page
     *
     * @return
     */
    public List<WebElementFacade> checkBoxList() {
        List<WebElementFacade> checkboxList = findAll("//input[@type='checkbox']");
        return checkboxList;
       
    }
   
    /**
     * this method will return list of editbox on the current page
     *
     * @return
     */
    public List<WebElementFacade> editBoxList() {
        List<WebElementFacade> editboxList = findAll("//input[@type='text']");
        return editboxList;
       
    }
   
    /**
     * this method will return list of select drop down on the current page
     *
     * @return
     */
    public List<WebElementFacade> selectDropDownList() {
        List<WebElementFacade> selectList = findAll("//select");
        return selectList;
    }
   
    /**
     * this method called only for account submission process going to back page
     *
     */
    public void gotoPreviousAppPage() {
       
        try {
            element(backToLink).isVisible();
            clickOn(backToLink);
           
        } catch (Exception e) {
            log.error("back to previous page link not found or not clickable");
        }
    }
   
    public String getTextFromTheElement(WebElement element) {
       
        String getText = element(element).waitUntilVisible().getText().trim();
       
        return getText;
    }
   
    public String getApplicantionId() {
        waitFor(applicationID);
        log.info("LOG INFO: Getting User ApplicationID: " + applicationID);
        return getTextFromTheElement(applicationID);
       
    }
   
    /**
     * this method will return the application status after review and submitted for s2s lrp
     *
     */
    public String getApplicationStatusAfterReviewAndSubmitted() {
        element(statusField).isPresent();
        List<WebElementFacade> appStatusFieldList = findAll("//div/p[3]/strong[1]");
        String applicationStatus = appStatusFieldList.get(0).getText();
        return applicationStatus;
       
    }
   
    public String getApplicationStatusForWithdraw() {
        element(statusFieldForWithdrawApp).isPresent();
       
        String applicationStatus = getTextFromTheElement(statusFieldForWithdrawApp);
        return applicationStatus;
       
    }
   
    public String getApplicationIdForWithdraw() {
        return getTextFromTheElement(statusFieldForWithdawAppId);
    }
   
    public void appLogout() {
       
        waitFor(appLogoutBtn);
        element(appLogoutBtn).isPresent();
        clickOn(appLogoutBtn);
        waitABit(2000);
       
    }
   
    public void clickSaveAndContinueStep() {
        clickOnSaveAndContinueBtn();
        waitForAngularRequestsToFinish();
        busyIndicatorSpinner();
        waitABit(4000);
    }
   
    public void verifyAPageStatusOnHomePage(String expectedPageName, String expectedPageStatus) {
        String xpathExpression = ".//*[@id='sectionStatusTable-in-progress']/tbody/tr";
        // ".//*[@id='sectionStatusTable-in-progress']/tbody/tr[" + rowNum + "]/td[" + colmNum
        // + "]";
       
        List<WebElementFacade> tableList = findAll(xpathExpression);
        for (int i = 0; i < tableList.size(); i++) {
            String acutalPageName = tableList.get(i).findElement(By.xpath("td[1]")).getText();
            if (acutalPageName.equalsIgnoreCase(expectedPageName)) {
                String acutalPageStatus = tableList.get(i).findElement(By.xpath("td[2]")).getText();
                log.info("validating page name = " + expectedPageName + " and status as = " + expectedPageStatus);
                assertThat(acutalPageStatus, CoreMatchers.containsString(expectedPageStatus));
                log.info("page status succcessfully");
                break;
            }
        }
       
    }
   
    public String getApplicationStatusOrId(String positionId) {
        String status = getDriver().findElement(By.xpath("//p[" + positionId + "]/strong[1]")).getText();
        return status;
    }
   
    public void assertThatStringValidation(String expectedString, String actualString) {
        assertThat(expectedString, CoreMatchers.containsString(actualString));
       
    }
   
    public void assertthatValidation(WebElement passElememt, String passVerificationTExt) {
        log.info("Get element text");
        String succesffulKickOffReview = element(passElememt).waitUntilVisible().getText();
        assertThat(succesffulKickOffReview, CoreMatchers.containsString((passVerificationTExt)));
        log.info("Verification text is =: " + succesffulKickOffReview + "----" + passVerificationTExt);
    }
   
    public void clickOnConitineEditingPopupBtnIsExist() {
        if (continueEditingPopUpBtn.isCurrentlyVisible()) {
            clickOn(continueEditingPopUpBtn);
            log.info("clicked on continue editing btn on the popup window");
           
            waitABit(4000);
        }
    }
   
    public void clickOnReviewAndSubmitPopupBtnIfVisible() {
        if (reviewAndSubmitPopUpBtn.isCurrentlyVisible()) {
            clickOn(reviewAndSubmitPopUpBtn);
            log.info("clicked on review and submit btn on the popup window");
           
            waitABit(4000);
        }
    }
   
    public void switchToNewWindow() {
        String parent = getDriver().getWindowHandle();
        Set<String> s1 = getDriver().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                getDriver().switchTo().window(child_window);
                System.out.println(getDriver().switchTo().window(child_window).getTitle());
            }
        }
    }
   
    private void setParentWindow(String parentWindow) {
        parentHandleWindow = parentWindow;
    }
   
    private String getParentWindow() {
        return parentHandleWindow;
    }
   
    @Override
    public boolean containsText(final String textValue) {
        return getRenderedView().containsText(textValue);
    }
   
    /**
     * Scroll to a particular element.
     *
     * @param ele
     *            element to be focused
     * @param yOffset
     *            scroll with offset
     */
    public void scrollIntoView(WebElement ele, String yOffset) {
        // default to -250 if null
        String offset = (yOffset == null || yOffset.equals("")) ? "-250" : yOffset;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", ele);
        js.executeScript("scroll(0, " + offset + ");");
    }
   
    public void scroll(String yOffset) {
        // default to -250 if null
        String offset = (yOffset == null || yOffset.equals("")) ? "-250" : yOffset;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("scroll(0, " + offset + ");");
    }
   
    public void scrollTillPageEnd() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
   
    public void waitForMilliseconds(long timeInMilliseconds) {
        waitABit(timeInMilliseconds);
    }
   
    /**
     * Dismisses survey modal.
     */
    public void dismissSurvey() {
        waitABit(1000);
        try {
            if (getDriver().findElement(By.xpath("//button[contains(text(),'No Thanks')]")).isDisplayed() == true) {
                log.info("Click on popup window");
                getDriver().findElement(By.xpath("//button[contains(text(),'No Thanks')]")).click();
            }
        } catch (Exception e) {
            log.info("No pop up");
        }
    }
   
    public void handleHrsaSurveyPopup() {
        log.info("Checking for survey popup");
        if (hrsaPopUpNoThankBtn.isCurrentlyVisible()) {
            try {
                hrsaPopUpNoThankBtn.click();
            } catch (Exception e) {
                log.info("Hrsa survey button not present");
            }
        }
    }
   
    
   
    public void selectingValuesFromList(String value) {
        try {
            log.info("selecting only one value");
            final WebElement eles;
            if (value.contains("'")) {
                eles = getDriver().findElement(By.xpath("//li[contains(.,\"" + value + "\")]"));
                eles.click();
            } else {
                eles = getDriver().findElement(By.xpath("//li[contains(.,'" + value + "')]"));
                eles.click();
            }
        } catch (final Exception e) {
            log.error("Not able to select values in the drop down");
            log.error(e.getMessage());
        }
    }
   
    /**
     * This method gets the current url of the page
     *
     * @author BEmerson
     * @return the current url
     */
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
   
    /**
     * This method will reload the current page
     *
     * @author DGabunia
     */
    public void reloadPage() {
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().refresh();
    }
   
    /**
     * This method generates username email
     *
     * @author
     */
    public String generateEmailUsername(String prefixEmail, String domainsuffix) {
        // RandomStringUtils.randomAlphanumeric(4);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmdd_hh_mm_ss");
        String strDate = dateFormat.format(date);
        return prefixEmail + strDate + domainsuffix;
    }
   
    /*
     * This method will generate random 9 digit number and verify if the pattern matches the ssn pattern
     * Verify the generated ssn is not in DB - If false generate another number
     */
   
//    public String uniqueValidSSN() throws SQLException, IOException {
//        DatabaseAccessories dbAccessories = new DatabaseAccessories();
//        boolean ssnValidationFlag;
//        String regex = "^(?!666|000|9\\d{2})\\d{3}(?!00)\\d{2}(?!0{4})\\d{4}$";
//        String ranSSNum;
//        do {
//            ranSSNum = RandomStringUtils.randomNumeric(9);
//            String last4SSN = ranSSNum.substring(ranSSNum.length() - 4);
//            boolean ss = Pattern.compile(regex).matcher(ranSSNum).matches();
//            if (ss == true) {
//                if (dbAccessories.findAllLast4SSN(last4SSN) == false) {
//                    ssnValidationFlag = false;
//                } else {
//                    ssnValidationFlag = true;
//                }
//            } else {
//                ssnValidationFlag = true;
//            }
//        } while (ssnValidationFlag);
//        return ranSSNum;
//    }
   
}
