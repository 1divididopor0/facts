package facts;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class App  {

    public static void main (String[] args ) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\MindSparkQA\\Documents\\facts\\geckodriver.exe");

        FirefoxDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();

      /********************** Login Page ************************************************/

        driver.get("https://fngfactsqa1.foxinc.com/Account/Login?ReturnUrl=%2F");
        driver.getTitle();
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

        WebElement element1 = driver.findElement(By.id("tbxUserName"));
        fnHighlightMe(driver,element1);
        element1.sendKeys("coreywei");

        WebElement element2 = driver.findElement(By.id("tbxPassword"));
        fnHighlightMe(driver,element2);
        element2.sendKeys("FS2testing1!");

        WebElement element3 = driver.findElement(By.xpath("//*[@id='btnSubmit']"));
        fnHighlightMe(driver,element3);
        element3.click();

        /****************************** Main Page ********************************************/

        /** WebElement element4= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Department')]"));
        fnHighlightMe(driver,element4);


        Select select1 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.departmentId']")));

        select1.selectByVisibleText("MLB Remote");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element5= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Location')]"));
        fnHighlightMe(driver,element5);

        Select select2 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.location']")));

        select2.selectByVisibleText("Detroit, MI");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element6= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Venue')]"));
        fnHighlightMe(driver,element6);

        Select select3 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.venueId']")));

        select3.selectByVisibleText("Comerica Park"); **/

        /**************************************** Reports Page **************************************************/



        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element5= driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]"));
        fnHighlightMe(driver,element5);
        element5.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='main-menu']/li[5]/ul/li[1]")));
        WebElement element6= driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]/ul/li[1]"));
        fnHighlightMe(driver,element6);

        String mainWindowHanhle=driver.getWindowHandle();

        element6.click();




       /************************************** Print Page *****************************************************/
        Set<String> childParentHandles=driver.getWindowHandles();
        childParentHandles.remove(mainWindowHanhle);
        driver.switchTo().window((String) childParentHandles.toArray()[0]);

       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element7= driver.findElement(By.xpath("//div[@label='Payroll Date From']/div[@class='input-group date date-picker']/input[@class='form-control medium']"));
        fnHighlightMe(driver,element7);
        element7.clear();
        element7.sendKeys("06/10/2017");

        WebElement element8= driver.findElement(By.xpath("//div[@label='Payroll Date To']/div[@class='input-group date date-picker']/input[@class='form-control medium']"));
        fnHighlightMe(driver,element8);
        element8.clear();
        element8.sendKeys("06/20/2017");

        WebElement element9= driver.findElement(By.xpath("//*[@id='command-bar-section']/command-button"));
        fnHighlightMe(driver,element9);
        element9.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        /************************************** Final Print Page *****************************************/
        Set<String> grandChild_Child_ParentHandles=driver.getWindowHandles();
        grandChild_Child_ParentHandles.remove(mainWindowHanhle);
        grandChild_Child_ParentHandles.remove(childParentHandles);
        driver.switchTo().window((String) grandChild_Child_ParentHandles.toArray()[0]);

        captureScreenShot(driver);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Select select5 = new Select(driver.findElement(By.xpath("//select[@id='reportViewer_ReportToolbar_ExportGr_FormatList_DropDownList']")));
        select5.selectByVisibleText("Acrobat (PDF) file");

        WebElement element10= driver.findElement(By.xpath("//input[@class='Enabled' and @title='Print']"));
        fnHighlightMe(driver,element10);
        element10.click();

        //driver.close();
        //driver.quit();






    }


    private static void fnHighlightMe(WebDriver driver, WebElement element) throws InterruptedException{
        //Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int iCnt = 0; iCnt < 3; iCnt++) {
            //Execute javascript
            js.executeScript("arguments[0].style.border='8px groove red'", element);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", element);
        }

    }

    private static void captureScreenShot(WebDriver ldriver){

        // Take screenshot and store as a file format
        File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the  screenshot to desired location using copyFile method

            FileUtils.copyFile(src, new File("C:/selenium/"+System.currentTimeMillis()+".png"));
        }

        catch (IOException e)

        {

            System.out.println(e.getMessage());

        }

    }

}



