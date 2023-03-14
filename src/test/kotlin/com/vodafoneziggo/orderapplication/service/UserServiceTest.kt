package com.vodafoneziggo.orderapplication.service

import com.vodafoneziggo.orderapplication.exception.UserApiException
import com.vodafoneziggo.orderapplication.model.User
import com.vodafoneziggo.orderapplication.model.UserResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `when user api return successful response`(){
        val user = User(id=1, firstName = "George", lastName = "Bluth", email =  "abc@def.com")
        //given
        `when`(restTemplate.getForEntity("https://reqres.in/api/users", UserResponse::class.java))
            .thenReturn(ResponseEntity(UserResponse(data = listOf(user)), HttpStatus.OK))

        //when
        val userResponse = userService.retrieveUserResponse()

        //then
        assertEquals(user, userResponse!!.data!![0])
    }

    @Test
    fun `when user api returns exception`() {
        //given
        `when`(restTemplate.getForEntity("https://reqres.in/api/users", UserResponse::class.java))
            .thenThrow(RestClientException("RestClientException"))

        // when, then
        val exception = assertThrows(UserApiException::class.java) {
            userService.retrieveUserResponse()
        }

        assertEquals("Error calling User API", exception.message)
    }
}