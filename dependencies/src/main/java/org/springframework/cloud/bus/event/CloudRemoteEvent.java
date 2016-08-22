package org.springframework.cloud.bus.event;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Map;

/**
 * Simple event to be sent by server and listened by another client
 *
 * The Bus can carry any event of type RemoteApplicationEvent, but the default transport is JSON and the deserializer needs
 * to know which types are going to be used ahead of time. To register a new type it needs to be in a subpackage of
 * org.springframework.cloud.bus.event. You can use @JsonTypeName on your custom class or rely on the default strategy
 * which is to use the simple name of the class. Note that both the producer and the consumer will need access to the class definition.
 * http://cloud.spring.io/spring-cloud-static/spring-cloud-bus/1.1.0.RELEASE/
 *
 * @author Pezhman Jahanmard
 */
@JsonTypeName("CloudRemoteEvent")
public class CloudRemoteEvent extends RemoteApplicationEvent {
	private Map<String, String> values;

	/**
	 * For de-serializers
	 */
	@SuppressWarnings("unused")
	public CloudRemoteEvent() {}

	public CloudRemoteEvent(Object source, String originService, String destinationService, Map<String, String> values) {
		super(source, originService, destinationService);
		this.values = values;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}
}
