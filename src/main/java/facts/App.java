package facts;

import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        System.setProperty("webdriver.ie.driver","C:\\Users\\MindSparkQA\\IEDriverServer_Win32_3.4.0\\IEDriverServer.exe");

        FirefoxDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();
        //WebDriver driver = new InternetExplorerDriver();

      /********************** Login Page ************************************************/

        driver.get("https://fngfactsqa1.foxinc.com/Account/Login?ReturnUrl=%2F");
        driver.getTitle();
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tbxUserName")));
        WebElement element1 = driver.findElement(By.id("tbxUserName"));
        WebElement element2 = driver.findElement(By.id("tbxPassword"));
        WebElement element3 = driver.findElement(By.xpath("//*[@id='btnSubmit']"));

        fnHighlightMe(driver,element1);
        element1.sendKeys("coreywei");
        fnHighlightMe(driver,element2);
        element2.sendKeys("FS2testing1!");
        fnHighlightMe(driver,element3);
        element3.click();

        /****************************** Main Page ********************************************/

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement element4= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Department')]"));
        Select select1 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.departmentId']")));
        WebElement element5= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Location')]"));
        Select select2 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.location']")));
        WebElement element6= driver.findElement(By.xpath("//*[@class='control-label' and contains(text(),'Venue')]"));
        Select select3 = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.venueId']")));

        fnHighlightMe(driver,element4);
        select1.selectByVisibleText("MLB Remote");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fnHighlightMe(driver,element5);
        select2.selectByVisibleText("Detroit, MI");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fnHighlightMe(driver,element6);
        select3.selectByVisibleText("Comerica Park");

        /**************************************** AtlPerEventPaymentsReport Page **************************************************/

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final int prevWndCount = driver.getWindowHandles().size();
        WebElement element7= driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]"));
        WebElement element8= driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]/ul/li[1]"));

        fnHighlightMe(driver,element7);
        element7.click();
        fnHighlightMe(driver,element8);
        element8.click();
        downloadReportPdf(driver,prevWndCount);

        /**************************************** AtlRecurringPaymentsReport Page **************************************************/

        WebElement element13 = driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]/ul/li[2]"));
        fnHighlightMe(driver,element7);
        element7.click();
        fnHighlightMe(driver,element13);
        element13.click();
        downloadReportPdf(driver,prevWndCount);

        /**************************************** AtlRecurringPaymentsAccrualReport Page **************************************************/

        WebElement element14 = driver.findElement(By.xpath("//ul[@id='main-menu']/li[5]/ul/li[3]"));
        fnHighlightMe(driver,element7);
        element7.click();
        fnHighlightMe(driver,element14);
        element14.click();
        downloadReportPdf(driver,prevWndCount);

        //driver.quit();
    }

    private static void fnHighlightMe(WebDriver driver, WebElement element) throws InterruptedException{
        //Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int iCnt = 0; iCnt < 2; iCnt++) {
            //Execute javascript
            js.executeScript("arguments[0].style.border='8px groove red'", element);
            Thread.sleep(500);
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

    private static void downloadReportPdf(WebDriver driver, final int prevWndCount) throws InterruptedException
    {
        /************************************** Print Page *****************************************/

        WebDriverWait wait2 = new WebDriverWait(driver,10);
        wait2.until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver d){
                return d.getWindowHandles().size() > prevWndCount;
            }
        });
        String mainWindowHandle=driver.getWindowHandle();
        Set<String> childParentHandles=driver.getWindowHandles();
        childParentHandles.remove(mainWindowHandle);
        String childWindowHandle = (String)childParentHandles.toArray()[0];
        driver.switchTo().window(childWindowHandle);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        WebElement element9= driver.findElement(By.xpath("//div[@label='Payroll Date From']/div[@class='input-group date date-picker']/input[@class='form-control medium']"));
//        fnHighlightMe(driver,element9);
//        element9.clear();
//        element9.sendKeys("06/10/2017");
//
//        WebElement element10= driver.findElement(By.xpath("//div[@label='Payroll Date To']/div[@class='input-group date date-picker']/input[@class='form-control medium']"));
//        fnHighlightMe(driver,element10);
//        element10.clear();
//        element10.sendKeys("06/20/2017");

        final int prevWndCount2 = driver.getWindowHandles().size();
        WebElement element11= driver.findElement(By.xpath("//*[@id='command-bar-section']/command-button"));
        fnHighlightMe(driver,element11);
        element11.click();
        wait2.until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver d){
                return d.getWindowHandles().size() > prevWndCount2;
            }
        });

        /************************************** Final Print Page *****************************************/

        Set<String> grandChild_Child_ParentHandles=driver.getWindowHandles();
        grandChild_Child_ParentHandles.remove(mainWindowHandle);
        grandChild_Child_ParentHandles.remove(childWindowHandle);
        driver.switchTo().window((String) grandChild_Child_ParentHandles.toArray()[0]);

        captureScreenShot(driver);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='reportViewer_ReportToolbar_ExportGr_FormatList_DropDownList']")));
        Select select5 = new Select(driver.findElement(By.xpath("//select[@id='reportViewer_ReportToolbar_ExportGr_FormatList_DropDownList']")));
        WebElement element12= driver.findElement(By.xpath("//input[@class='Enabled' and @title='Print']"));
        select5.selectByVisibleText("Acrobat (PDF) file");
        fnHighlightMe(driver,element12);
        element12.click();

        /*************************************** PDF Export Box ******************************************/

        String jacobDllVersionToUse = "jacob-1.18-x64.dll";
        File file = new File("lib",jacobDllVersionToUse);
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
        AutoItX x = new AutoItX();

        // For IE
        // I tried the following code but it wasn't working. -RS
        //x.sleep(20000);
        //String frame = x.winGetHandle("[Class:IEFrame]");
        //x.controlSend(frame,"",x.controlGetHandle(frame,"","[Class:DirectUIHWND;INSTANCE:1]"),"!N");
        //x.controlSend(frame,"",x.controlGetHandle(frame,"","[Class:DirectUIHWND;INSTANCE:1]"),"{TAB}");
        //x.sleep(500);
        //x.controlSend(frame,"",x.controlGetHandle(frame,"","[Class:DirectUIHWND;INSTANCE:1]"),"{TAB}");
        //x.sleep(500);
        //x.controlSend(frame,"",x.controlGetHandle(frame,"","[Class:DirectUIHWND;INSTANCE:1]"),"{ENTER}");

        // For other browsers
        x.winActivate("Opening");
        x.controlSend("Opening","","","{ENTER}");

        driver.close();
        driver.switchTo().window(childWindowHandle);
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }
}