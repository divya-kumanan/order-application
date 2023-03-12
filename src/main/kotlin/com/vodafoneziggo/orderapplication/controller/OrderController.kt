package com.vodafoneziggo.orderapplication.controller


import com.vodafoneziggo.orderapplication.model.OrderDetails
import com.vodafoneziggo.orderapplication.model.OrderRequest
import com.vodafoneziggo.orderapplication.model.OrderResponse
import com.vodafoneziggo.orderapplication.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest): ResponseEntity<OrderResponse> {
        val orderId = orderService.createOrder(orderRequest)
        val orderResponse = OrderResponse(listOf(OrderDetails(orderId = orderId)))
        return ResponseEntity.ok(orderResponse)
    }

    @GetMapping
    fun getAllOrders(): ResponseEntity<OrderResponse> {
        val orders = orderService.getAllOrders()
        val orderResponse = OrderResponse.from(orders = orders)
        return ResponseEntity.ok(orderResponse)
    }
}