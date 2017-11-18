package com.docler.ping.service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docler.ping.config.AppProperties;
import com.docler.ping.helper.AppException;
import com.docler.ping.helper.CommandRunner;
import com.docler.ping.model.Status;
import com.docler.ping.model.Status.Code;

/**
 * Service to perform ICMP operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Service
public class ICMPService {

	@Autowired
	private AppProperties prop;

	@Autowired
	private ReportingService reportingService;

	private final ScheduledExecutorService executorService;

	@Autowired
	public ICMPService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	public void schedule() throws AppException {

		/*
		 * Schedule runnable task by encapsulating CommandRunner instance to execute
		 * ICMP command for every host name and reporting failures.
		 * 
		 */
		for (final String hostname : prop.getHostnames()) {

			if(null == hostname || hostname.isEmpty()) {
				throw new AppException("[hostnames] property is not provided. Check configuration file.");
			}
			
			final String icmpCommand = prop.getIcmpCommand() + " " + hostname;

			Runnable icmpTask = () -> {
				final Status status = new CommandRunner().run(icmpCommand);
				if(null != status && status.getCode().equals(Code.FAILURE)) {
					reportingService.report(hostname, status.getText(), null, null);
				}
			};

			executorService.scheduleAtFixedRate(icmpTask, prop.getIcmpInitialDelay(), prop.getIcmpCycleDelay(),
					TimeUnit.MILLISECONDS);
		}

	}

}
