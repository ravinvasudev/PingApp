package com.docler.ping.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

/**
 * A class representing application properties.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
public class AppProperties {

	@Value("#{'${hostnames}'.split(',')}")
	private List<String> hostnames;

	@Value("${icmp.command?:ping}")
	private String icmpCommand;

	@Value("${icmp.initial.delay?:0}")
	private Integer icmpInitialDelay;

	@Value("${icmp.cycle.delay?:5000}")
	private Integer icmpCycleDelay;

	@Value("${trace.route.command?:tracert}")
	private String tracertCommand;

	@Value("${trace.route.initial.delay?:0}")
	private Integer tracertInitialDelay;

	@Value("${trace.route.cycle.delay?:5000}")
	private Integer tracertCycleDelay;

	@Value("#{'${http.urls}'.split(',')}")
	private List<String> httpURLs;

	@Value("${http.connection.timeout?:0}")
	private Integer httpConnectionTimeout;

	@Value("${http.read.timeout?:0}")
	private Integer httpReadTimeout;

	@Value("${http.initial.delay?:0}")
	private Integer httpInitialDelay;

	@Value("${http.cycle.delay?:5000}")
	private Integer httpCycleDelay;

	@Value("${reporting.url?:http://localhost:8080/report}")
	private String reportURL;

	public AppProperties() {

	}

	public List<String> getHostnames() {
		return hostnames;
	}

	public String getIcmpCommand() {
		return icmpCommand.trim();
	}

	public Integer getIcmpInitialDelay() {
		return icmpInitialDelay;
	}

	public Integer getIcmpCycleDelay() {
		return icmpCycleDelay;
	}

	public String getTracertCommand() {
		return tracertCommand.trim();
	}

	public Integer getTracertInitialDelay() {
		return tracertInitialDelay;
	}

	public Integer getTracertCycleDelay() {
		return tracertCycleDelay;
	}

	public List<String> getHttpURLs() {
		return httpURLs;
	}

	public Integer getHttpConnectionTimeout() {
		return httpConnectionTimeout;
	}

	public Integer getHttpReadTimeout() {
		return httpReadTimeout;
	}

	public Integer getHttpInitialDelay() {
		return httpInitialDelay;
	}

	public Integer getHttpCycleDelay() {
		return httpCycleDelay;
	}

	public String getReportURL() {
		return reportURL.trim();
	}

	@Override
	public String toString() {
		return String.format(
				"AppProperties [hostnames=%s, icmpCommand=%s, icmpInitialDelay=%s, icmpCycleDelay=%s, tracertCommand=%s, tracertInitialDelay=%s, tracertCycleDelay=%s, httpURLs=%s, httpConnectionTimeout=%s, httpReadTimeout=%s, httpInitialDelay=%s, httpCycleDelay=%s, reportURL=%s]",
				hostnames, icmpCommand, icmpInitialDelay, icmpCycleDelay, tracertCommand, tracertInitialDelay,
				tracertCycleDelay, httpURLs, httpConnectionTimeout, httpReadTimeout, httpInitialDelay, httpCycleDelay,
				reportURL);
	}

}
