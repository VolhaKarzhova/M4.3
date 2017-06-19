package pageObject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailListPage extends AbstractPage {

    public static final By LETTER_BLOCK_LOCATOR = By.xpath("//div[@data-mnemo='letters']");
    private static final By DELETE_DRAFT_LETTER_BUTTON_LOCATOR = By.xpath("//div[@data-cache-key='500001_undefined_false']//div[@data-name='remove']/span");
    private static final By DELETE_LETTER_FROM_TRASH_BUTTON_LOCATOR = By.xpath("//div[@data-cache-key='500002_undefined_false']//div[@data-name='remove']/span");
    private static final By NO_SPAM_BUTTON_LOCATOR = By.xpath("//div[@data-cache-key='950_undefined_false']//div[@data-name='noSpam']/span");
    private static final By MAIL_BLANK_SUBJECT_LOCATOR = By.xpath("(//a[@class='js-href b-datalist__item__link'][not(@data-subject)])[1]");
    private static final String MAIL_BY_SUBJECT_LOCATOR = "//*[@data-subject='%s']";
    private static final String LETTER_CHECKBOX_LOCATOR = "//*[@data-subject='%s']//div[@class='b-checkbox__box']";
    private static final String SPAM_LETTER_BY_SUBJECT_LOCATOR = "//div[@data-cache-key='950_undefined_false']//a[@data-subject='%s']";
    private static final String CHECKBOX_NOSPAM_LOCATOR = "//div[@data-cache-key='950_undefined_false']//a[@data-subject='%s']//div[@class='b-checkbox__box']";

    public MailListPage(WebDriver driver) {
        super(driver);
    }

    public ContentLetterPage openLetterBySubject(String subject) {
        waitForElementEnabled(By.xpath(String.format(MAIL_BY_SUBJECT_LOCATOR, subject)));
        driver.findElement(By.xpath(String.format(MAIL_BY_SUBJECT_LOCATOR, subject))).click();
        waitForElementVisible(ContentLetterPage.ADDRESSEE_MAIL_LOCATOR);
        return new ContentLetterPage(driver);
    }

    public ContentLetterPage openLetterWithoutSubject() {
        waitForElementEnabled(MAIL_BLANK_SUBJECT_LOCATOR);
        driver.findElement(MAIL_BLANK_SUBJECT_LOCATOR).click();
        waitForElementVisible(ContentLetterPage.ADDRESSEE_MAIL_LOCATOR);
        return new ContentLetterPage(driver);
    }

    public boolean checkLetterBySubjectIsDisplayed(String subject) {
        if (driver.findElement(By.xpath(String.format(MAIL_BY_SUBJECT_LOCATOR, subject))).isDisplayed()) {
            return true;
        } else return false;
    }

    public boolean checkLetterPresentInSpamFolder(String subject) {
        if (driver.findElement(By.xpath(String.format(SPAM_LETTER_BY_SUBJECT_LOCATOR, subject))).isDisplayed()) {
            return true;
        } else return false;
    }

    public MailListPage clickLetterCheckbox(String subject) {
        waitForElementVisible(By.xpath(String.format(LETTER_CHECKBOX_LOCATOR, subject)));
        driver.findElement(By.xpath(String.format(LETTER_CHECKBOX_LOCATOR, subject))).click();
        return new MailListPage(driver);
    }

    public MailListPage clickLetterCheckboxInSpamFolder(String subject) {
        waitForElementVisible(By.xpath(String.format(CHECKBOX_NOSPAM_LOCATOR, subject)));
        driver.findElement(By.xpath(String.format(CHECKBOX_NOSPAM_LOCATOR, subject))).click();
        return new MailListPage(driver);
    }

    public MailListPage deleteDraftLetter(String subject) {
        driver.findElement(DELETE_DRAFT_LETTER_BUTTON_LOCATOR).click();
        waitForElementDisappear(By.xpath(String.format(MAIL_BY_SUBJECT_LOCATOR, subject)));
        return new MailListPage(driver);
    }

    public MailListPage deleteLetterFromTrash(String subject) {
        driver.findElement(DELETE_LETTER_FROM_TRASH_BUTTON_LOCATOR).click();
        waitForElementDisappear(By.xpath(String.format(MAIL_BY_SUBJECT_LOCATOR, subject)));
        return new MailListPage(driver);
    }

    public MailListPage markLetterAsNoSpam(String subject) {
        driver.findElement(NO_SPAM_BUTTON_LOCATOR).click();
        waitForElementDisappear(By.xpath(String.format(SPAM_LETTER_BY_SUBJECT_LOCATOR, subject)));
        return new MailListPage(driver);
    }
}