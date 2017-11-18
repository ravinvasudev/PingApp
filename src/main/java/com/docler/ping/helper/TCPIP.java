package com.docler.ping.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.docler.ping.model.Status;
import com.docler.ping.model.Status.Code;

/**
 * A class to execute TCP command via HTTP Request handler. It uses core java
 * features to do the necessary operations.
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
public class TCPIP {

	private final static Logger LOG = LoggerFactory.getLogger(TCPIP.class);

	private final static List<Integer> acceptableHttpStatusCodes;

	static {
		acceptableHttpStatusCodes = new ArrayList<>();
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_OK);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_CREATED);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_ACCEPTED);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_NO_CONTENT);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_RESET);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_PARTIAL);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_MULT_CHOICE);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_MOVED_PERM);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_MOVED_TEMP);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_SEE_OTHER);
		acceptableHttpStatusCodes.add(HttpURLConnection.HTTP_NOT_MODIFIED);
	}

	public Status run(String url, int connectionTimeout, int readTimeout) {

		String refId = System.identityHashCode(url) + "" + System.currentTimeMillis();

		LOG.info(String.format("RefId[%s] - Executing TCP/IP URL <-> %s", refId, url));

		Status status = new Status();
		StringBuilder response = null;
		StringBuilder trace = new StringBuilder();
		int responseCode = Integer.MIN_VALUE;

		try {
			URL httpUrl = new URL(url);
			long startTime = System.currentTimeMillis();

			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setConnectTimeout(connectionTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setRequestMethod("GET");
			conn.connect();

			long endTime = System.currentTimeMillis();
			long responseTime = endTime - startTime;
			LOG.info(String.format("RefId[%s] - ResponseTime[%s]", refId, responseTime));

			responseCode = conn.getResponseCode();
			LOG.info(String.format("RefId[%s] - ResponseCode[%s]", refId, responseCode));

			trace.append("Http Status - ");
			trace.append(responseCode);
			trace.append(", Response Time - ");
			trace.append(responseTime);
			trace.append(" - ");

			BufferedReader reader = null;
			if (acceptableHttpStatusCodes.contains(responseCode)) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				if (null != conn.getErrorStream()) {
					reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
			}

			if (null != reader) {
				response = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					response.append(line);
					response.append("\n");
				}
				reader.close();
				trace.append(response.length() > 0 ? response.toString() : null);
			}

		} catch (SocketTimeoutException e) {
			/* LOG.error(e.getMessage()); */
			trace.append(e.getMessage());
		} catch (UnknownHostException e) {
			/* LOG.error(e.getMessage()); */
			trace.append("Unknown Host - ");
			trace.append(e.getMessage());
		} catch (MalformedURLException e) {
			/* LOG.error(e.getMessage()); */
			trace.append("Malformed URL - ");
			trace.append(e.getMessage());
		} catch (IOException e) {
			/* LOG.error(e.getMessage()); */
			trace.append(e.getMessage());
		}

		if (acceptableHttpStatusCodes.contains(responseCode)) {
			status.setCode(Code.SUCCESS);
			LOG.info(String.format("RefId[%s] - %s", refId, trace));
		} else {
			status.setCode(Code.FAILURE);
			LOG.warn(String.format("RefId[%s] - %s", refId, trace));
		}
		status.setText(trace.toString());
		return status;
	}

}
