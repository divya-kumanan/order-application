package com.vodafoneziggo.orderapplication.controller

import com.vodafoneziggo.orderapplication.OrderNotFoundException
import com.vodafoneziggo.orderapplication.model.OrderDetails
import com.vodafoneziggo.orderapplication.model.OrderErrorResponse
import com.vodafoneziggo.orderapplication.model.OrderRequest
import com.vodafoneziggo.orderapplication.model.OrderResponse
import com.vodafoneziggo.orderapplication.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orderapplication")
@Tag(name = "Order API", description = "Order API Documentation")
class OrderController(private val orderService: OrderService) {


    @Operation(summary = "Create Order")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Order Creation Successful",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "400", description = "Invalid Order Request",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "404", description = "User has not been associated with the account",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "500", description = "Internal Server Error",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            )
        ]
    )
    @PostMapping("order")
    fun createOrder(@RequestBody orderRequest: OrderRequest): OrderResponse {
        val orderId = orderService.createOrder(orderRequest)
        return OrderResponse(
            description = "Order has been created successfully",
            listOf(OrderDetails(orderId = orderId, productId = orderRequest.productId))
        )
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successfully retrieved all the orders",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "404", description = "Order does not exists",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "500", description = "Internal Server Error",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            )
        ]
    )
    @GetMapping("orders")
    fun getAllOrders(@RequestParam(required = false) email: String?): OrderResponse {
        val orders = if (email.isNullOrEmpty()) {
            orderService.getAllOrders()
        } else {
            val order = orderService.getOrdersByEmail(email)
            if(order.isNullOrEmpty())
                throw OrderNotFoundException("Order does not exists")
            else
                order
        }
        return OrderResponse.from(orders = orders)
    }

    @Operation(summary = "Get specific order based on OrderID")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successfully retrieved all the orders",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "404", description = "Order does not exists",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            ),
            ApiResponse(
                responseCode = "500", description = "Internal Server Error",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = OrderErrorResponse::class))
                    )]
            )
        ]
    )
    @GetMapping("orders/{orderId}")
    fun getOrder(@PathVariable orderId: Long): OrderResponse {
        val order = orderService.getOrderByOrderId(orderId).orElse(null)
            ?: throw OrderNotFoundException("Order does not exists")
        return OrderResponse.from(order)
    }
}