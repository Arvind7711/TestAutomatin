package ProjPakage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import java.awt.TextField;
import java.util.List;

import javax.lang.model.element.Element;

import org.openqa.selenium.BuildInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;

public class Login {
    

    static void LoginWebpage(String[] userName,String[] password,WebDriver Driver) throws InterruptedException
    {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.ChromeDriver","/chromedriver");
        Driver.get("https://netcportal.maximusinfoware.in/");
        Driver.manage().window().fullscreen();
        WebElement Login =Driver.findElement(By.xpath("//input[@id='txtUserName']"));
        WebElement Passw =Driver.findElement(By.xpath("//input[@id='txtPassword']"));
        WebElement LoginBtn =Driver.findElement(By.xpath("//input[@id='btnLogin']"));
    
        
        for (int i = 0,j=0;i<userName.length && j<password.length;i++,j++)
        {
            String textfield=userName[i];
            Login.sendKeys(textfield);
            Thread.sleep(1000);
            Login.clear();      
            String PassFiled =password[j];      
            Passw.sendKeys(PassFiled);
            Thread.sleep(1000);
            Passw.clear();              
            if ("Maximus".equals(textfield) && "Maximus@123".equals(PassFiled))
            {  
                Login.sendKeys(textfield);
                Passw.sendKeys(PassFiled);
                LoginBtn.click();
                
            }else {
                System.out.println("Username '" + textfield + "' Is Invalid .");
                System.out.println("Password '" + PassFiled + "' Is Invalid .");
            }   
         }
     }
    
    static void DropDownTest(WebDriver Driver,String[] DDlist) throws InterruptedException
    {
        try {
        
        //DropDown Test case sheet 
       WebElement DropDown =Driver.findElement(By.linkText("Reports"));
        DropDown.click();
        //WebElement DropDown =Driver.findElement(By.xpath("//input[@id=''hrefsubpg19]"));
        
        List<WebElement> ulElement = Driver.findElements(By.xpath("//*[@id=\"subPages19\"]"));
        
        for (WebElement Elements:ulElement)
           {
            System.out.println("Text of the <ul> element: " + Elements.getText());
            Elements.findElement(By.linkText("Transaction Report")).click();
           }
        }

        finally {
            //Driver.quit();
         }
        
//To find the the elements in the Report page>> Transaction report page
        
        WebElement TxnType =Driver.findElement(By.xpath("//*[@id=\"cpbdCarde_panelLimitConfig\"]/div[1]/div[2]/span/span[1]/span"));
        TxnType.click();
       
        try 
        {
        	
        	 List<WebElement> Dropdownlist =Driver.findElements(By.xpath("//ul[@class=\"select2-results__options\"]/li"));  	 
        	 System.out.println("Dropdown list size: " + Dropdownlist.size());    	 
             for (WebElement option : Dropdownlist) {  
                if(option.getText().equals(DDlist[0]))
                {
                	System.out.println("selectedoption"+option.getText());
                	Thread.sleep(1000);
                	option.click();
                }
                else 
                {
                	System.out.println("Test");
                }

           }
     
        }
            
        finally
        {
            System.out.println("Failed");
        }
        
        
        WebElement Txnstatus=Driver.findElement(By.xpath("//span[@class=\"select2-selection__rendered\" and @title=\"--Transaction Status--\"]"));
        Txnstatus.click();
        
        List<WebElement> DDTxnstatus=Driver.findElements(By.xpath("//ul[@class=\"select2-results__options\"]//li"));
        System.out.println(DDTxnstatus.size());
        
        for(WebElement Dtxt:DDTxnstatus)
        {
        	
        	if(Dtxt.getText().equals("Accepted"))
        	{
        		System.out.println("selectedoption"+   Dtxt.getText());
        		Dtxt.click();
        	}
        }   
      }

    
    public static void main(String[] args) throws InterruptedException {
        String[] UserName= {"Maximus254444444444444444444444444444444444444444444","Maximus123","Maximus"};
        String[] Password= {"admin@1234","admin@1235","Maximus@123"};
        String[] DDlist= {"Switch Transaction Date","Exit Transaction Date"};
        WebDriver Driver=new ChromeDriver();
        
        LoginWebpage(UserName,Password,Driver);
        DropDownTest(Driver,DDlist);
     }

}