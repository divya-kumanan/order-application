package com.vodafoneziggo.orderapplication.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class OrderConfig {
    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }
}