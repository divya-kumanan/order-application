package com.vodafoneziggo.orderapplication.controller

import com.vodafoneziggo.orderapplication.model.OrderDetails
import com.vodafoneziggo.orderapplication.model.OrderRequest
import com.vodafoneziggo.orderapplication.model.OrderResponse
import com.vodafoneziggo.orderapplication.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest): OrderResponse {
        val orderId = orderService.createOrder(orderRequest)
        return OrderResponse(description = "Order has been created successfully", listOf(OrderDetails(orderId = orderId, productId = orderRequest.productId)))
    }

    @GetMapping
    fun getAllOrders(): OrderResponse {
            val orders = orderService.getAllOrders()
        return OrderResponse.from(orders = orders)
    }
}