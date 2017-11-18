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

import com.docler.ping.helper.TCPIP;
import com.docler.ping.model.Status;
import com.docler.ping.model.Status.Code;

@RunWith(Parameterized.class)
public class TCPTest {

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { 
			{ "http://google.com", "http://invalidhost1.com", 5000, 5000 },
			{ "http://facebook.com", "http://invalidhost2.com", 5000, 5000 } };
		return Arrays.asList(data);
	}

	@Parameter(0)
	public String validHost;

	@Parameter(1)
	public String inValidHost;

	@Parameter(2)
	public int connectionTimeout;

	@Parameter(3)
	public int readTimeout;

	private static TCPIP tcp;

	@BeforeClass
	public static void init() {
		tcp = new TCPIP();
	}

	@Test
	public void tcpWithValidAddressShouldReturnSuccessStatusCode() {
		final Status status = tcp.run(validHost, connectionTimeout, readTimeout);
		Assert.assertEquals(Code.SUCCESS, status.getCode());
	}

	@Test
	public void tcpWithInvalidAddressShouldReturnFailureStatusCode() {
		final Status status = tcp.run(inValidHost, connectionTimeout, readTimeout);
		Assert.assertEquals(Code.FAILURE, status.getCode());
	}

	@Test
	public void tcpWithConnectionTimeoutShouldReturnFailureStatusCode() {
		final Status status = tcp.run(validHost, 1, readTimeout);
		Assert.assertEquals(Code.FAILURE, status.getCode());
	}

	@Test
	public void tcpWithReadTimeoutShouldReturnFailureStatusCode() {
		final Status status = tcp.run(validHost, connectionTimeout, 1);
		Assert.assertEquals(Code.FAILURE, status.getCode());
	}

}
