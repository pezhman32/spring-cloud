package com.brogrammer.spring.cloud.client.task;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.deployer.resource.maven.MavenProperties;
import org.springframework.cloud.deployer.resource.maven.MavenResource;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.deployer.spi.task.TaskStatus;
import org.springframework.cloud.task.batch.configuration.TaskBatchExecutionListenerBeanPostProcessor;
import org.springframework.cloud.task.batch.partition.DeployerPartitionHandler;
import org.springframework.cloud.task.batch.partition.DeployerStepExecutionHandler;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pezhman Jahanmard
 */
@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class TaskClientApplication {

	private static ConfigurableApplicationContext applicationContext;

	@Autowired
	private ApplicationContext appContext;

	@Bean
	public PartitionHandler partitionHandler(/*TaskLauncher taskLauncher,*/
	                                         JobExplorer jobExplorer) throws Exception {

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% partitionHandler");

		TaskLauncher taskLauncher = new TaskLauncher() {
			@Override
			public String launch(AppDeploymentRequest appDeploymentRequest) {
				return null;
			}

			@Override
			public void cancel(String s) {

			}

			@Override
			public TaskStatus status(String s) {
				return null;
			}
		};


		MavenProperties mavenProperties = new MavenProperties();
		mavenProperties.setRemoteRepositories(new HashMap<>(Collections.singletonMap("springRepo",
				new MavenProperties.RemoteRepository(/*repository*/))));

		MavenResource resource =
				MavenResource.parse(String.format("%s:%s:%s",
						"io.spring.cloud",
						"partitioned-batch-job",
						"1.0.0.RELEASE"), mavenProperties);

		DeployerPartitionHandler partitionHandler =
				new DeployerPartitionHandler(taskLauncher, jobExplorer, resource, "workerStep");

		Map<String, String> environmentProperties = new HashMap<>();
		environmentProperties.put("spring.profiles.active", "worker");

		partitionHandler.setEnvironmentProperties(environmentProperties);
		partitionHandler.setMaxWorkers(2);

		return partitionHandler;
	}

	@Bean
	public DeployerStepExecutionHandler stepExecutionHandler(JobExplorer jobExplorer) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% stepExecutionHandler");
		DeployerStepExecutionHandler handler =
			new DeployerStepExecutionHandler(appContext, jobExplorer, new SimpleJobRepository(new MapJobInstanceDao(), new MapJobExecutionDao(), new MapStepExecutionDao(), new MapExecutionContextDao()));

		return handler;
	}

	@Bean
	public TaskBatchExecutionListenerBeanPostProcessor batchTaskExecutionListenerBeanPostProcessor() {
		TaskBatchExecutionListenerBeanPostProcessor postProcessor =
				new TaskBatchExecutionListenerBeanPostProcessor();

		postProcessor.setJobNames(Arrays.asList(new String[] {"job1", "job2"}));

		return postProcessor;
	}

	@Bean(name = "commandLineRunner1")
	public CommandLineRunner commandLineRunner() {
		return new HelloWorldCommandLineRunner();
	}

	/*@Bean
	public Job instantiateJob() {
		SimpleJob simpleJob = new SimpleJob("MySimpleJob1");
		simpleJob.addStep(new JobStep().set);
	}*/

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(TaskClientApplication.class, args);
	}

	public static class HelloWorldCommandLineRunner implements CommandLineRunner {
		@Override
		public void run(String... strings) throws Exception {
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Hello World!");
		}
	}
}
