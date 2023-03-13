package com.vodafoneziggo.orderapplication.service

import com.vodafoneziggo.orderapplication.UserApiException
import com.vodafoneziggo.orderapplication.model.UserResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
class UserService(private val restTemplate: RestTemplate) {
    fun retrieveUserResponse(): UserResponse? {
        try {
            val userResponse = restTemplate.getForEntity("https://reqres.in/api/users", UserResponse::class.java)
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