package com.media.orderapplication.service

import com.media.orderapplication.config.UserApiConfig
import com.media.orderapplication.exception.UserApiException
import com.media.orderapplication.model.UserResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
class UserService(private val restTemplate: RestTemplate, private val userApiConfig: UserApiConfig) {
    fun retrieveUserResponse(): UserResponse? {
        try {
            val userResponse = restTemplate.getForEntity(userApiConfig.url, UserResponse::class.java)
            return userResponse.body
        } catch (ex: HttpStatusCodeException) {
            val responseBodyAsString = ex.responseBodyAsString
            throw UserApiException("User API returned unsuccessful response : + $responseBodyAsString")
        } catch (ex: RestClientException) {
            throw UserApiException("Error calling User API")
        } catch(e: Exception){
            throw UserApiException("Error calling User API")
        }
    }
}