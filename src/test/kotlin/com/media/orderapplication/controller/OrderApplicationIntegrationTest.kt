/*
package com.media.orderapplication.controller

import com.media.orderapplication.model.OrderRequest
import com.media.orderapplication.model.OrderResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntegrationTest(@Autowired val restTemplate: TestRestTemplate) {
    @Test
    fun `test createOrder API`() {
        val orderRequest = OrderRequest("prd1234", "emma.wong@reqres.in")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
        val requestEntity = HttpEntity(orderRequest, headers)

        val response = restTemplate.postForEntity("orderapplication/order", requestEntity, OrderResponse::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body.orderDetails[0].orderId)

        val orderId = response.body.orderDetails[0].orderId
        val orderResponse = restTemplate.getForObject("orderapplication/order/$orderId", OrderResponse::class.java)
        assertEquals(1L, orderResponse.orderDetails[0].orderId)
        assertEquals("emma.wong@reqres.in", orderResponse.orderDetails[0].email)
    }
}

*/
