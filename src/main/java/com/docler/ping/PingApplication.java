package com.docler.ping;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.docler.ping.controller.PingController;

/**
 * Entry point of application managed by Spring container.
 * <p>
 * SpringBoot used to create bean definitions and lifecycle management. All core
 * functionality like Threads and Commands execution was written in core Java.
 * </p>
 * 
 * @author Ravin Vasudev
 * @version 1.0
 *
 */
@SpringBootApplication
public class PingApplication {

	private final static Logger LOG = LoggerFactory.getLogger(PingApplication.class);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		LOG.info("<<<<<<<<<<< PingApplication Started >>>>>>>>>>>");

		final SpringApplication app = new SpringApplication(PingApplication.class);
		app.setRegisterShutdownHook(true);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(Boolean.FALSE);

		final ApplicationContext context = app.run(args);
		final PingController controller = context.getBean(PingController.class);
		controller.start();

	}

}
