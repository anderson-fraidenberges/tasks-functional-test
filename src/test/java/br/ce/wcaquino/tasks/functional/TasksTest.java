package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Assert;

public class TasksTest {
	
	private WebDriver acessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "D:\\devops\\chromedriver\\chromedriver.exe");		
		DesiredCapabilities cap = DesiredCapabilities.chrome(); 

		 WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.142:4444/wd/hub"),cap);
		 driver.navigate().to("http://192.168.0.142:8001/tasks");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso()throws MalformedURLException {
		 WebDriver driver = acessarAplicacao();
		 
		 try {
		 //clicar em Add Todo
		 
		 driver.findElement(By.id("addTodo")).click();
		 
		 
		 //escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		 
		 
		 //escrever a data
		 driver.findElement(By.id("dueDate")).sendKeys("10/12/2023");
		 
		 
		 //clicar em salvar
		 driver.findElement(By.id("saveButton")).click();
		 
		 //validar mensagem de sucesso
		 String message = driver.findElement(By.id("message")).getText();
		 
		 Assert.assertEquals("Success!",message);
		 } finally {
		 //Fechar o browser
		 driver.quit();
		 }
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException{
		 WebDriver driver = acessarAplicacao();
		 
		 try {
		 //clicar em Add Todo
		 
		 driver.findElement(By.id("addTodo")).click();
		 
		 
		 //escrever a data
		 driver.findElement(By.id("dueDate")).sendKeys("29/09/2023");
		 
		 
		 //clicar em salvar
		 driver.findElement(By.id("saveButton")).click();
		 
		 //validar mensagem de sucesso
		 String message = driver.findElement(By.id("message")).getText();
		 Assert.assertEquals("Fill the task description", message);
		 } finally {
		 //Fechar o browser
		 driver.quit();
		 }
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException{
		 WebDriver driver = acessarAplicacao();
		 
		 try {
		 //clicar em Add Todo
		 
		 driver.findElement(By.id("addTodo")).click();
		 
		 
		 //escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		 
		 
		 //clicar em salvar
		 driver.findElement(By.id("saveButton")).click();
		 
		 //validar mensagem de sucesso
		 String message = driver.findElement(By.id("message")).getText();
		 Assert.assertEquals("Fill the due date",message);
		 } finally {
		 //Fechar o browser
		 driver.quit();
		 }
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException{
		 WebDriver driver = acessarAplicacao();
		 
		 try {
		 //clicar em Add Todo
		 
		 driver.findElement(By.id("addTodo")).click();
		 
		 
		 //escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		 
		 
		 //escrever a data
		 driver.findElement(By.id("dueDate")).sendKeys("10/10/1910");
		 
		 
		 //clicar em salvar
		 driver.findElement(By.id("saveButton")).click();
		 
		 //validar mensagem de sucesso
		 String message = driver.findElement(By.id("message")).getText();
		 Assert.assertEquals("Due date must not be in past",message);
		 } finally {
		 //Fechar o browser
		 driver.quit();
		 }
	}

}
