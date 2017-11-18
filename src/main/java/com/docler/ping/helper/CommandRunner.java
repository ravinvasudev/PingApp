package com.docler.ping.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.docler.ping.model.Status;
import com.docler.ping.model.Status.Code;

/**
 * A class to execute ICMP & Trace Route command. It uses core java features to
 * do the necessary operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
public class CommandRunner {

	private final static Logger LOG = LoggerFactory.getLogger(CommandRunner.class);

	public Status run(String command) {

		final String refId = System.identityHashCode(command) + "" + System.currentTimeMillis();

		LOG.info(String.format("RefId[%s] - Executing Command <-> %s", refId, command));

		Status status = new Status();
		StringBuilder response = null;
		String trace = null;
		int statusCode = Integer.MIN_VALUE;

		try {
			Process process = Runtime.getRuntime().exec(command);
			statusCode = process.waitFor();
			LOG.info(String.format("RefId[%s] - Command Status[%s]", refId, statusCode));

			String line = null;
			final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			if (null != reader) {
				response = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					response.append(line);
					response.append("\n");
				}
				reader.close();
				trace = response.length() > 0 ? response.toString() : null;
			}
		} catch (IOException e) {
			/* LOG.error(e.getMessage()); */
			trace = e.getMessage();
		} catch (InterruptedException e) {
			/* LOG.error(e.getMessage()); */
			trace = e.getMessage();
		}

		if (statusCode == 0) {
			status.setCode(Code.SUCCESS);
			LOG.info(String.format("RefId[%s] - %s", refId, trace));
		} else {
			status.setCode(Code.FAILURE);
			LOG.warn(String.format("RefId[%s] - %s", refId, trace));
		}
		status.setText(trace);
		return status;
	}

}
