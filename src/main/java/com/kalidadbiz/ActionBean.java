package com.kalidadbiz;

import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Edward P. Legaspi
 **/
@ManagedBean
public class ActionBean {
	private Log log = LogFactory.getLog(RestClient.class);

	public void submit() {
		log.debug("submit");

		RestClient restClient = new RestClient("http://localhost:8080/jax-rs-security",
				"api/rest/user-service/users/1");

		System.out.println(restClient.execute());
		log.debug("response=" + restClient.execute());
	}
}
