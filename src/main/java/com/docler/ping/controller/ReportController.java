package com.docler.ping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docler.ping.model.ReportRequest;

/**
 * Utility controller for accepting report data for HTTP Post operation. It only
 * accept the JSON request and does no useful function.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@RestController("/report")
public class ReportController {

	private final static Logger LOG = LoggerFactory.getLogger(ReportController.class);

	public ReportController() {
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void report(@RequestBody ReportRequest request) {
		LOG.info(request.toString());
	}
}
