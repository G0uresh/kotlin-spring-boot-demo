package com.gouresh.employeerestapidemo.config

import com.hazelcast.config.Config
import com.hazelcast.config.MapConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EmployeeCacheConfig {

    @Bean
    fun createConfig(): Config {
        return Config()
            .setInstanceName("hazel-instance")
            .addMapConfig(MapConfig()
                .setName("employee-cache")
                .setTimeToLiveSeconds(3000))
    }
}