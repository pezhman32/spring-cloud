package com.brogrammer.spring.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample class to read configurations from remote server
 *
 * @author Pezhman Jahanmard
 */
@RestController
@Configuration
@RequestMapping("/config")
public class ConfigReaderController {

	/**
	 * Reads data from https://github.com/spring-cloud-samples/config-repo/blob/master/application.yml
	 * Or you can use
	 */
	@Value("${info.description}")
	String infoDescription;

	/**
	 * Reads data from https://github.com/spring-cloud-samples/config-repo/blob/master/application.yml
	 * Or you can use
	 */
	@Value("${info.url}")
	String infoUrl;

	@RequestMapping("/info/descriptionAndUrl")
	public String home() {
		return "Description : " + infoDescription + "\n<br />\nUrl : " + infoUrl;
	}
}
