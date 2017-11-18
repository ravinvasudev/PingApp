package com.docler.ping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docler.ping.config.AppProperties;
import com.docler.ping.helper.HttpPost;
import com.docler.ping.model.ReportRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service to perform reporting operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Service
public class ReportingService {

	private final static Logger LOG = LoggerFactory.getLogger(ReportingService.class);

	@Autowired
	private HttpPost httpPost;

	@Autowired
	private AppProperties prop;

	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	public ReportingService() {
	}

	public Boolean report(String host, String icmpTrace, String traceRouteTrace, String tcpTrace) {

		if ((null == icmpTrace || icmpTrace.isEmpty()) && (null == traceRouteTrace || traceRouteTrace.isEmpty())
				&& (null == tcpTrace || tcpTrace.isEmpty())) {
			return Boolean.FALSE;
		}

		final ReportRequest reportRequest = new ReportRequest(host, icmpTrace, traceRouteTrace, tcpTrace);
		String jsonData = null;
		try {
			jsonData = mapper.writeValueAsString(reportRequest);
		} catch (JsonProcessingException e) {
			LOG.error("Failed to convert ReportRequest to JSON format.");
		}

		if (null != jsonData) {
			LOG.warn(jsonData);
			return httpPost.submit(prop.getReportURL(), jsonData);
		}
		return Boolean.FALSE;

	}
}
