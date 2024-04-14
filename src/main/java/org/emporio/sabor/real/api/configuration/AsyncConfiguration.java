package org.emporio.sabor.real.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "taskExecutor")
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpuCores = Runtime.getRuntime().availableProcessors();
        String cpuLimitStr = System.getenv("CPU_LIMIT");
        int cpuLimit = cpuLimitStr != null ? Integer.parseInt(cpuLimitStr) : cpuCores;

        executor.setCorePoolSize(cpuLimit);
        executor.setMaxPoolSize(cpuLimit * 2);
        executor.setThreadNamePrefix("taskExecutor_thread");
        executor.initialize();
        return executor;
    }
}
