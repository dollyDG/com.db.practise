package com.db.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DBConnectionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-   method stub
		WebDriver driver;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dolly", "root", "newrootpassword");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from students where id=1");
		while(rs.next())
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Dolly\\eclipse-workspace\\SeleniumSetUp\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://login.salesforce.com/");
			driver.manage().window().maximize();
			String myfirstname=rs.getString("first_name"); // can use index in getString as getString(1) or col name
			//System.out.println(myfirstname);
			driver.findElement(By.id("username")).sendKeys(myfirstname);
			driver.findElement(By.id("password")).sendKeys(rs.getString("last_name"));
			driver.findElement(By.id("Login")).click();
		}
	}

}
