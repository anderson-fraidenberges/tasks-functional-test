package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

public class TasksTest {
	
	private WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "D:\\devops\\chromedriver\\chromedriver.exe");
		 WebDriver driver = new  ChromeDriver();
		 driver.navigate().to("http://localhost:8001/tasks");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		 WebDriver driver = acessarAplicacao();
		 
		 try {
		 //clicar em Add Todo
		 
		 driver.findElement(By.id("addTodo")).click();
		 
		 
		 //escrever descrição
		 driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		 
		 
		 //escrever a data
		 driver.findElement(By.id("dueDate")).sendKeys("29/09/2023");
		 
		 
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
