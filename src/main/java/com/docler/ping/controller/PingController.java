package com.docler.ping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.docler.ping.helper.AppException;
import com.docler.ping.service.ICMPService;
import com.docler.ping.service.TCPService;
import com.docler.ping.service.TraceRouteService;

/**
 * Main controller class to initiate service operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Controller
public class PingController {
	
	private final static Logger LOG = LoggerFactory.getLogger(PingController.class);

	@Autowired
	private ICMPService icmpService;

	@Autowired
	private TraceRouteService traceRouteService;

	@Autowired
	private TCPService tcpService;

	public PingController() {

	}

	public void start() {
		try {
			icmpService.schedule();
			traceRouteService.schedule();
			tcpService.schedule();
		} catch (AppException e) {
			LOG.error(e.getMessage());
			System.exit(0);
		}

	}

}
