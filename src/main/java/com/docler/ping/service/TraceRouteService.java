package com.docler.ping.service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docler.ping.config.AppProperties;
import com.docler.ping.helper.AppException;
import com.docler.ping.helper.CommandRunner;
import com.docler.ping.model.Status;

/**
 * Service to perform TraceRoute operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Service
public class TraceRouteService {

	@Autowired
	private AppProperties prop;

	@Autowired
	private ReportingService reportingService;

	private final ScheduledExecutorService executorService;

	@Autowired
	public TraceRouteService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	public void schedule() throws AppException {

		/*
		 * Schedule runnable task by encapsulating CommandRunner instance to execute
		 * TraceRoute command for every host name and reporting failures.
		 * 
		 */
		for (final String hostname : prop.getHostnames()) {
			
			if(null == hostname || hostname.isEmpty()) {
				throw new AppException("[hostnames] property is not provided. Check configuration file.");
			}

			final String traceRouteCommand = prop.getTracertCommand().trim() + " " + hostname;

			Runnable traceRouteTask = () -> {
				Status status = new CommandRunner().run(traceRouteCommand);
				reportingService.report(hostname, null, status.getText(), null);
			};

			executorService.scheduleAtFixedRate(traceRouteTask, prop.getTracertInitialDelay(),
					prop.getTracertCycleDelay(), TimeUnit.MILLISECONDS);
		}

	}

}
