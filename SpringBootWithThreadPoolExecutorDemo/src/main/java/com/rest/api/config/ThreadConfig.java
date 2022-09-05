package com.rest.api.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {
	
	@Bean(name="taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("poolThread-");
        executor.initialize();
        return executor;
    }

	@Bean
	public ArrayBlockingQueue<Runnable> arrayBlockingQueue() {
		ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(20, true);
		return arrayBlockingQueue;
	}

	@Bean(name = "threadPoolExecutorC")
	public ThreadPoolExecutor executorC() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(10, true));
		return executor;
	}

	@Bean(name = "singleExecutor")
	public TaskExecutor singleExecutor() {
		final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(1);
		taskExecutor.setMaxPoolSize(1);
		taskExecutor.setKeepAliveSeconds(0);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setAwaitTerminationSeconds(300);
		return taskExecutor;
	}

	@Bean(name = "threadPoolExecutorA")
	public TaskExecutor executorA() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("default_task_executor_thread");
		executor.initialize();
		return executor;
	}

	@Bean(name = "threadPoolExecutorB")
	public TaskExecutor executorB() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("executor-B");
		executor.initialize();
		return executor;
	}

}