package com.docler.ping.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * A class to post report data
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Component
public class HttpPost {

	private final static Logger LOG = LoggerFactory.getLogger(HttpPost.class);

	public Boolean submit(String strUrl, String data) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(HttpMethod.POST.name());
			conn.setRequestProperty("Content-Type", "application/json");
			final OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			os.flush();

			final int responseCode = conn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				return Boolean.TRUE;
			}
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		} catch (ProtocolException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			conn.disconnect();
		}
		return Boolean.FALSE;
	}

}
