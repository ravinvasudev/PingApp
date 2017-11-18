package com.docler.ping.model;

/**
 * Report model
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
public class ReportRequest {

	private String host;
	private String icmpTrace;
	private String tcpTrace;
	private String traceRouteTrace;

	public ReportRequest() {

	}

	public ReportRequest(String host, String icmpTrace, String traceRouteTrace, String tcpTrace) {
		this.host = host;
		this.icmpTrace = icmpTrace;
		this.traceRouteTrace = traceRouteTrace;
		this.tcpTrace = tcpTrace;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIcmpTrace() {
		return icmpTrace;
	}

	public void setIcmpTrace(String icmpTrace) {
		this.icmpTrace = icmpTrace;
	}

	public String getTcpTrace() {
		return tcpTrace;
	}

	public void setTcpTrace(String tcpTrace) {
		this.tcpTrace = tcpTrace;
	}

	public String getTraceRouteTrace() {
		return traceRouteTrace;
	}

	public void setTraceRouteTrace(String traceRouteTrace) {
		this.traceRouteTrace = traceRouteTrace;
	}

	@Override
	public String toString() {
		return String.format("Posted ReportRequest [host=%s, icmpTrace=%s, tcpTrace=%s, traceRouteTrace=%s]",
				host, icmpTrace, tcpTrace, traceRouteTrace);
	}

}
