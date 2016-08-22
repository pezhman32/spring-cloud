package com.brogrammer.spring.cloud.client.task;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

/**
 * Register listeners for specific events that occur during the task lifecycle. This is done by creating a class that
 * implements the TaskExecutionListener interface. The class that implements the TaskExecutionListener interface will
 * be notified for the following events:
 * http://docs.spring.io/spring-cloud-task/1.0.2.RELEASE/reference/htmlsingle/#features-task-execution-listener
 *
 * @author Pezhman Jahanmard
 */
@Component
public class ClientTaskExecutionListener implements TaskExecutionListener {
	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		System.out.println("%%%%%%%%%% onTaskStartup > " + taskExecution);
	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		System.out.println("%%%%%%%%%% onTaskEnd > " + taskExecution);
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		System.out.println("%%%%%%%%%% onTaskFailed > " + taskExecution);
	}
}
