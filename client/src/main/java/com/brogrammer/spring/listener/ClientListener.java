package com.brogrammer.spring.listener;

import org.springframework.cloud.bus.event.CloudRemoteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * To do some basic tests on some listeners
 */
@Component
public class ClientListener {

	@EventListener(classes = CloudRemoteEvent.class)
	public void onApplicationEvent(CloudRemoteEvent remoteApplicationEvent) {
		System.out.println("ClientListener received some events..." + remoteApplicationEvent);
	}
}
