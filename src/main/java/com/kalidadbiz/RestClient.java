package com.kalidadbiz;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

/**
 * @author Edward P. Legaspi
 **/
public class RestClient {
	private Log log = LogFactory.getLog(RestClient.class);

	private String host;
	private String api;
	private Properties properties = new Properties();

	public RestClient() {

	}

	public RestClient(String host, String api) {
		this.host = host;
		this.api = api;
	}

	public void addParam(String key, String value) {
		properties.put(key, value);
	}

	public String execute() {
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("edward", "edward"));

			String params = "";
			if (properties != null) {
				for (String key : properties.stringPropertyNames()) {
					String value = properties.getProperty(key);
					if (params != null) {
						params += "&";
					}
					params += key + "=" + value;
				}
			}

			String apiUrl = host + "/" + api;
			if (params != null && params.length() > 0) {
				apiUrl = apiUrl + "?" + params;
			}
			WebResource webResource = client.resource(apiUrl);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			return response.getEntity(String.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			return "";
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}
}
