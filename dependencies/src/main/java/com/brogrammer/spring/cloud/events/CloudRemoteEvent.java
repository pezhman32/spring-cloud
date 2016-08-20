package com.brogrammer.spring.cloud.events;

import org.springframework.cloud.bus.event.EnvironmentChangeRemoteApplicationEvent;

import java.util.Map;

/**
 * Simple event to be sent by server and listened by another client
 *
 * @author Pezhman Jahanmard
 */
public class CloudRemoteEvent extends EnvironmentChangeRemoteApplicationEvent {
//	private final Map<String, String> values;

	public CloudRemoteEvent(Object source, String originService, String destinationService, Map<String, String> values) {
		super(source, originService, destinationService, values);
//		this.values = values;
	}
}
