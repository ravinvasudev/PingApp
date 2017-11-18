package com.docler.ping.service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docler.ping.config.AppProperties;
import com.docler.ping.helper.AppException;
import com.docler.ping.helper.TCPIP;
import com.docler.ping.model.Status;

/**
 * Service to perform TCP IP operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Service
public class TCPService {

	@Autowired
	private AppProperties prop;

	@Autowired
	private ReportingService reportingService;

	private final ScheduledExecutorService executorService;

	@Autowired
	public TCPService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	public void schedule() throws AppException {

		/*
		 * Schedule runnable task by encapsulating TCPIP instance to execute Http
		 * Request for every URL and reporting failures.
		 * 
		 */
		final int connectionTimeout = prop.getHttpConnectionTimeout();
		final int readTimeout = prop.getHttpReadTimeout();
		for (final String httpURL : prop.getHttpURLs()) {

			if(null == httpURL || httpURL.isEmpty()) {
				throw new AppException("[http.urls] property is not provided. Check configuration file.");
			}
			
			Runnable tcpTask = () -> {
				Status status = new TCPIP().run(httpURL, connectionTimeout, readTimeout);
				reportingService.report(httpURL, null, null, status.getText());
			};

			executorService.scheduleAtFixedRate(tcpTask, prop.getHttpInitialDelay(), prop.getHttpCycleDelay(),
					TimeUnit.MILLISECONDS);
		}

	}

}
