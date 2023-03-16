package com.media.orderapplication.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class UserApiConfig(@Value("\${user.api.url}") val url: String)