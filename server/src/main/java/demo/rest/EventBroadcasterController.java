package demo.rest;

import com.brogrammer.spring.cloud.events.CloudRemoteEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Broadcast some simple events to other clients
 *
 * @author Pezhman Jahanmard
 */
@RestController
@Configuration
@RequestMapping("/event")
public class EventBroadcasterController  implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher eventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST)
	public String home(@RequestParam final String text) {
		Map<String, String> values = new HashMap<String, String>() {{
			put("message", text);
		}};
//		eventPublisher.publishEvent(new CloudRemoteEvent(this, "server.demo.rest.EventBroadcasterController", null, values)); ;
		eventPublisher.publishEvent(new CloudRemoteEvent(this, "configserver:8888", "client1:8080", values)); ;

		return "message sent.";
	}
}
