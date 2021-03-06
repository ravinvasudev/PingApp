package com.docler.ping.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.docler.ping.helper.CommandRunner;
import com.docler.ping.model.Status;
import com.docler.ping.model.Status.Code;

@RunWith(Parameterized.class)
public class ICMPTest {

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { 
			{ "jasmin.com", "invalidhost1.com" },
			{ "oranum.com", "invalidhost2.com" } };
		return Arrays.asList(data);
	}

	@Parameter(0)
	public String validHost;

	@Parameter(1)
	public String inValidHost;

	private static CommandRunner runner;
	
	@BeforeClass
	public static void init() {
		runner = new CommandRunner();
	}

	@Test
	public void icmpWithValidHostShouldReturnSuccessStatusCode() {
		final String command = "ping -n 5 " + validHost;
		final Status status = runner.run(command);
		Assert.assertEquals("Result", Code.SUCCESS, status.getCode());
	}

	@Test
	public void icmpWithInvalidHostShouldReturnFailureStatusCode() {
		final String command = "ping -n 5 " + inValidHost;
		final Status status = runner.run(command);
		Assert.assertEquals("Result", Code.FAILURE, status.getCode());
	}

}
