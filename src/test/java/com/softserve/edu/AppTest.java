package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void testApp() {
		System.out.println("+++This is Test");
		System.out.println("+++surefire.reports.directory="
				+ System.getProperty("surefire.reports.directory"));
		System.out.println("+++selenium.version="
				+ System.getProperty("selenium.version"));
		//
		long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("\nUsed Memory = " 
				+ usedMemory / (1024.0 * 1024.0) + "Mb");
		System.out.println("\nTotal Amount of Memory = " 
				+ Runtime.getRuntime().totalMemory() / (1024.0 * 1024.0) + "Mb");
		System.out.println("\nMaximum Amount of Memory = " 
				+ Runtime.getRuntime().maxMemory() / (1024.0 * 1024.0) + "Mb");
		Assert.assertTrue(true);
	}

	@Test
	public void testApp1() throws Exception {
		System.out.println("System.getProperty(\"IS_JENKINS\") = " + System.getProperty("IS_JENKINS"));
		System.out.println("System.getenv().get(\"IS_JENKINS\") = " + System.getenv().get("IS_JENKINS"));
		System.out.println("pom.xml ci = " + System.getProperty("ci"));
		System.out.println("System.getenv().database.password = " + System.getenv().get("DATABASE_PASSWORD"));
	}
}
